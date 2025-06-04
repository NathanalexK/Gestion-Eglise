package org.example.fiangonana.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.fiangonana.dto.tresorerie.*;
import org.example.fiangonana.exception.ExceptionList;
import org.example.fiangonana.model.MvtCaisse;
import org.example.fiangonana.model.Utilisateur;
import org.example.fiangonana.repository.HistoriqueRepository;
import org.example.fiangonana.repository.MvtCaisseRepository;
import org.example.fiangonana.util.DateUtils;
import org.example.fiangonana.util.NombreUtils;
import org.example.fiangonana.util.PageNavigation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
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

    public void controller(MvtCaisse mvtCaisse) throws ExceptionList {
        ExceptionList e = new ExceptionList();
        if(mvtCaisse.getCompte() == null) {
            e.addMessage("Compte ne doit pas être vide");
        }

        if(mvtCaisse.getLibelle().length() < 3) {
            e.addMessage("Le libelle doit contenir au moins 2 caractères");
        }

        if(mvtCaisse.getEntree() < 0 || mvtCaisse.getSortie() < 0) {
            e.addMessage("Le montant ne doit pas être négatif");
        }

        if(NombreUtils.comparerDouble(mvtCaisse.getEntree(), 0) && NombreUtils.comparerDouble(mvtCaisse.getSortie(), 0)){
            e.addMessage("Montant invalide: entrée et sortie ne doit pas être 0.00 en même temps");
        }

        e.throwWhenNotEmpty();
    }



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

//    @Cacheable(value = "suggestion", key="motCle")
    public List<String> getLibelles(String motCle) {
        log.info("Getting suggestions");
        return jdbcTemplate.queryForList("SELECT distinct(libelle) as lib FROM mvt_caisse WHERE libelle ilike '%"+ motCle + "%'")
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

    public MvtCaisseRecap getRecapDefaut(LocalDate dmin, LocalDate dmax) {
        if(dmin == null && dmax == null) {
            LocalDate[] dates = DateUtils.getIntervalleMois(LocalDate.now());
            dmin = dates[0];
            dmax = dates[1];
        }
        MvtCaisseRecap recap = new MvtCaisseRecap(mvtCaisseRepository.getRecapCaisseDefaut(dmin, dmax));
        recap.setDateDebut(dmin);
        recap.setDateFin(dmax);
        recap.setSoldePrecedent(mvtCaisseRepository.getSoldePrecedent(dmin));
        return recap;
    }

    public List<MvtCaisse> getAllByIdBudget(Integer idBudget) {
        return mvtCaisseRepository.findAllByBudget(idBudget);
    }

    public MvtCaisseRechercheAffichage recherche(MvtCaisseRechercheAffichage recherche) {
        Sort sort = null;
        if(recherche.getOrdreColonne() != null) {
            sort = Sort.by(recherche.getOrdre() != null && recherche.getOrdre().equalsIgnoreCase("desc") ? Sort.Order.desc(recherche.getOrdreColonne()) : Sort.Order.asc(recherche.getOrdreColonne()));
        } else {
            sort = Sort.by(Sort.Order.desc("date"));
        }

        Pageable pageable = PageRequest.of(recherche.getPageNavigation().getNumeroPage(), recherche.getPageNavigation().getTaillePage(), sort);
        Page<MvtCaisse> elements = mvtCaisseRepository.recherche(
                recherche.getDateMin(),
                recherche.getDateMax(),
                recherche.getNumeroCompte(),
                recherche.getEntreeMin(),
                recherche.getEntreeMax(),
                recherche.getSortieMin(),
                recherche.getSortieMax(),
                recherche.getLibelle(),
                pageable
        );
        recherche.setMvtCaisses(elements.getContent());
        recherche.setPageNavigation(new PageNavigation(elements));
        return recherche;

//        if(pageNumber == null) pageNumber = 0;
//        if(pageSize == null) pageSize = 200;

//        return recherche.setP

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<MvtCaisse> requete = cb.createQuery(MvtCaisse.class);
//
//        Root<MvtCaisse> table = requete.from(MvtCaisse.class);
//        Predicate apresWhere = cb.conjunction();
//
//        if(recherche.getDateMin() != null) {
//            apresWhere = cb.and(apresWhere, cb.greaterThanOrEqualTo(table.get("date"), recherche.getDateMin()));
//        }
//        if(recherche.getDateMax() != null) {
//            apresWhere = cb.and(apresWhere, cb.lessThanOrEqualTo(table.get("date"), recherche.getDateMax()));
//        }
//        if(recherche.getNumeroCompte() != null) {
//            apresWhere = cb.and(apresWhere,cb.like(table.get("code"), recherche.getNumeroCompte() + "%"));
//        }
//        if(recherche.getEntreeMin() != null) {
//            apresWhere = cb.and(apresWhere, cb.greaterThanOrEqualTo(table.get("entree"), recherche.getEntreeMin()));
//        }
//        if(recherche.getEntreeMax() != null) {
//            apresWhere = cb.and(apresWhere, cb.lessThanOrEqualTo(table.get("entree"), recherche.getEntreeMax()));
//        }
//        if(recherche.getSortieMin() != null) {
//            apresWhere = cb.and(apresWhere, cb.greaterThanOrEqualTo(table.get("sortie"), recherche.getSortieMin()));
//        }
//        if(recherche.getSortieMax() != null) {
//            apresWhere = cb.and(apresWhere, cb.lessThanOrEqualTo(table.get("sortie"), recherche.getSortieMax()));
//        }
//        if(recherche.getLibelle() != null) {
//            apresWhere = cb.and(apresWhere, cb.like(cb.lower(table.get("libelle")), "%" + recherche.getLibelle().toLowerCase() + "%"));
//        }
//        requete.where(apresWhere);
//        requete.orderBy(cb.asc(table.get("date")));
//        recherche.setMvtCaisses(em.createQuery(requete).getResultList());
//        return recherche;
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
        apresWhere = cb.and(apresWhere, cb.equal(table.join("compte").join("groupeCompteRecap").get("id"), recap.getIdGroupe()));
        requete.where(apresWhere);
        requete.orderBy(cb.asc(table.get("date")));
        recap.setMvtCaisses(em.createQuery(requete).getResultList());
        return recap;
    }


    public BilanTresorerie getBilanMois(int mois, int annee) {
        BilanTresorerie bilan = new BilanTresorerie();
        LocalDate date = LocalDate.of(annee, mois, 1);
        LocalDate[] interDates = DateUtils.getIntervalleMois(date);

        Map<String, Object> map = mvtCaisseRepository.getBilanSimpleEntre2Dates(interDates[0], interDates[1]);
        bilan.setSoldePrecedent(mvtCaisseRepository.getSoldePrecedent(interDates[0]));
        bilan.setTotalEntree(map.get("entree") != null ? (Double) map.get("entree") : 0.00);
        bilan.setTotalSortie(map.get("sortie") != null ?(Double) map.get("sortie") : 0.00);
        bilan.setTotal(map.get("total") != null ? (Double) map.get("total") : 0.00);
        bilan.setMois(mois);
        bilan.setAnnee(annee);

//        LocalDate.
        return bilan;
    }


    public List<Object[]> rechercheGroupee(MvtCaisseRechercheGroupe rg) {
        if(rg.getColonneCle() == null || rg.getColonneValeur() == null) {
            return List.of();
        }

        String commande = String.format("SELECT %s as cle, %s(%s) as valeur FROM mvt_caisse m JOIN codes c ON c.id = m.id_compte WHERE 1=1 AND m.code not like '2111' ", rg.getColonneCle(), rg.getFonction(), rg.getColonneValeur());
        if(rg.getValeurCle() != null && !rg.getValeurCle().isBlank()) {
            commande += " AND LOWER(CAST(" + rg.getColonneCle() + " AS TEXT)) LIKE '%" + rg.getValeurCle().toLowerCase() + "%'";
        }
        if(rg.getDateMin() != null) {
            commande += String.format(" AND date >= '%s'", rg.getDateMin().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if(rg.getDateMax() != null) {
            commande += String.format(" AND date <= '%s'", rg.getDateMax().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        log.info("Commande: {}", commande);
        commande += String.format(" GROUP BY %s", rg.getColonneCle());
//        commande += " HAVING " + rg.getColonneCle() +";
        List<Map<String, Object>> resultats = jdbcTemplate.queryForList(commande);
        return resultats.stream()
                .map(map -> {
                    return new Object[]{map.get("cle") instanceof Date d ? DateUtils.getFormatParDefaut(d.toLocalDate()) : map.get("cle"), map.get("valeur") instanceof Long l ? BigDecimal.valueOf(l) : map.get("valeur")};
                })
                .toList();
    }

//    public
}
