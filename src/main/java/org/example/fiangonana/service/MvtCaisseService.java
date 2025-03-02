package org.example.fiangonana.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.example.fiangonana.dto.tresorerie.*;
import org.example.fiangonana.exception.NoUserLoggedException;
import org.example.fiangonana.model.Historique;
import org.example.fiangonana.model.MvtCaisse;
import org.example.fiangonana.model.Utilisateur;
import org.example.fiangonana.repository.HistoriqueRepository;
import org.example.fiangonana.repository.MvtCaisseRepository;
import org.example.fiangonana.util.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MvtCaisseService {

    private final MvtCaisseRepository mvtCaisseRepository;
    private final JdbcTemplate jdbcTemplate;
    private final AuthService authService;

    @PersistenceContext
    private EntityManager em;
    private final HistoriqueRepository historiqueRepository;

    public MvtCaisseService(MvtCaisseRepository mvtCaisseRepository, JdbcTemplate jdbcTemplate, AuthService authService,
                            HistoriqueRepository historiqueRepository) {
        this.mvtCaisseRepository = mvtCaisseRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.authService = authService;
        this.historiqueRepository = historiqueRepository;
    }


//    public List<MvtCaisse> filtrerEtAjouterDate(List<MvtCaisse> mvtCaisses, LocalDate date) {
//
//    }

    @Transactional
    public List<MvtCaisse> enregistrerMvtCaisses(List<MvtCaisse> mvtCaisses, Utilisateur u) throws Exception {
        boolean isUpdate = false;
        if(!mvtCaisses.isEmpty()) {
            isUpdate = mvtCaisses.get(0).getId() != null;
        }
//        if(mvtCaisses == null )
        mvtCaisseRepository.saveAll(mvtCaisses);

        boolean finalIsUpdate = isUpdate;
//        Thread t = new Thread(() -> {
//            List<Historique> historiques = new ArrayList<>();
//            for(MvtCaisse mvtCaisse: mvtCaisses) {
//                Map<String, Object> data = new HashMap<>();
//                data.put("code", mvtCaisse.getCode());
//                data.put("libelle", mvtCaisse.getLibelle());
//                data.put("entree", mvtCaisse.getEntree());
//                data.put("sortie", mvtCaisse.getSortie());
//                data.put("date", mvtCaisse.getDate());
//                data.put("observation", mvtCaisse.getObservation());
//                Historique h = null;
//                if(!finalIsUpdate) {
//                    h = Historique.makeInsertion(u, MvtCaisse.class, mvtCaisse.getId().longValue(), data);
//                } else  {
//                    h = Historique.makeModification(u, MvtCaisse.class, mvtCaisse.getId().longValue(), data);
//                }
//                historiques.add(h);
//            }
//            historiqueRepository.saveAll(historiques);
//
//        });
//        t.start();
        return mvtCaisses;
    }

    public void supprimerMvtCaisse(MvtCaisse mvtCaisse) {
        mvtCaisseRepository.delete(mvtCaisse);
    }

    public List<String> getLibelles(String motCle) {
        return jdbcTemplate.queryForList("SELECT distinct(libelle) as lib FROM mvt_caisse WHERE libelle ilike '%"+ motCle + "%' LIMIT 5")
                .stream()
                .map((ligne) -> String.valueOf(ligne.get("lib")))
                .toList();
    }

    public List<MvtCaisseLigne> getMvtCaissesEntre2Dates(LocalDate dmin, LocalDate dmax) {
        return mvtCaisseRepository.getMvtCaisseEntre2Dates(dmin, dmax);
    }

    public MvtCaisseRecap getRecapAffichage(LocalDate dmin, LocalDate dmax) {
        if(dmin == null && dmax == null) {
            LocalDate[] dates = DateUtils.getIntervalleMois(LocalDate.now());
            dmin = dates[0];
            dmax = dates[1];
        }
        MvtCaisseRecap recap = new MvtCaisseRecap(mvtCaisseRepository.getRecapCaisse(dmin, dmax));
        recap.setDateDebut(dmin);
        recap.setDateFin(dmax);
        recap.setSoldePrecedent(mvtCaisseRepository.getSoldePrecedent(dmin));
        return recap;
    }

    public MvtCaisseRechercheAffichage recherche(MvtCaisseRechercheAffichage recherche) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MvtCaisse> requete = cb.createQuery(MvtCaisse.class);

        Root<MvtCaisse> table = requete.from(MvtCaisse.class);
        Predicate apresWhere = cb.conjunction();

        if(recherche.getDateMin() != null) {
            apresWhere = cb.and(apresWhere, cb.greaterThanOrEqualTo(table.get("date"), recherche.getDateMin()));
        }
        if(recherche.getDateMax() != null) {
            apresWhere = cb.and(apresWhere, cb.lessThanOrEqualTo(table.get("date"), recherche.getDateMax()));
        }
        if(recherche.getNumeroCompte() != null) {
            apresWhere = cb.and(apresWhere,cb.like(table.get("code"), recherche.getNumeroCompte() + "%"));
        }
        if(recherche.getEntreeMin() != null) {
            apresWhere = cb.and(apresWhere, cb.greaterThanOrEqualTo(table.get("entree"), recherche.getEntreeMin()));
        }
        if(recherche.getEntreeMax() != null) {
            apresWhere = cb.and(apresWhere, cb.lessThanOrEqualTo(table.get("entree"), recherche.getEntreeMax()));
        }
        if(recherche.getSortieMin() != null) {
            apresWhere = cb.and(apresWhere, cb.greaterThanOrEqualTo(table.get("sortie"), recherche.getSortieMin()));
        }
        if(recherche.getSortieMax() != null) {
            apresWhere = cb.and(apresWhere, cb.lessThanOrEqualTo(table.get("sortie"), recherche.getSortieMax()));
        }
        if(recherche.getLibelle() != null) {
            apresWhere = cb.and(apresWhere, cb.like(cb.lower(table.get("libelle")), "%" + recherche.getLibelle().toLowerCase() + "%"));
        }
        requete.where(apresWhere);
        requete.orderBy(cb.asc(table.get("date")));
        recherche.setMvtCaisses(em.createQuery(requete).getResultList());
        return recherche;
    }

    public DetailsRecapTresorerie getDetailsRecapTresorie(DetailsRecapTresorerie recap) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MvtCaisse> requete = cb.createQuery(MvtCaisse.class);
        Root<MvtCaisse> table = requete.from(MvtCaisse.class);

        Predicate apresWhere = cb.conjunction();
        if(recap.getDateMin() != null) {
            apresWhere = cb.and(apresWhere, cb.greaterThanOrEqualTo(table.get("date"), recap.getDateMin()));
        }
        if(recap.getDateMax() != null) {
            apresWhere = cb.and(apresWhere, cb.lessThanOrEqualTo(table.get("date"), recap.getDateMax()));
        }
        apresWhere = cb.and(apresWhere, cb.like(table.get("code"), recap.getCode()));
        requete.where(apresWhere);
        requete.orderBy(cb.asc(table.get("date")));
        recap.setMvtCaisses(em.createQuery(requete).getResultList());
        return recap;
    }

//    public
}
