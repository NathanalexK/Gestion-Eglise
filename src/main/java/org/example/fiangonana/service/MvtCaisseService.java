package org.example.fiangonana.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.example.fiangonana.dto.tresorerie.*;
import org.example.fiangonana.model.MvtCaisse;
import org.example.fiangonana.repository.MvtCaisseRepository;
import org.example.fiangonana.util.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MvtCaisseService {

    private final MvtCaisseRepository mvtCaisseRepository;
    private final JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager em;

    public MvtCaisseService(MvtCaisseRepository mvtCaisseRepository, JdbcTemplate jdbcTemplate) {
        this.mvtCaisseRepository = mvtCaisseRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


//    public List<MvtCaisse> filtrerEtAjouterDate(List<MvtCaisse> mvtCaisses, LocalDate date) {
//
//    }

    public List<MvtCaisse> enregistrerMvtCaisses(List<MvtCaisse> mvtCaisses) throws Exception {
//        if(mvtCaisses == null )
        return mvtCaisseRepository.saveAll(mvtCaisses);
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
