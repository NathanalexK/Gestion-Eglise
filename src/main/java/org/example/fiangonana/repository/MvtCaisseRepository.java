package org.example.fiangonana.repository;

import org.example.fiangonana.dto.tresorerie.BilanTresorerie;
import org.example.fiangonana.dto.tresorerie.MvtCaisseLigne;
import org.example.fiangonana.dto.tresorerie.MvtCaisseRecapLigne;
import org.example.fiangonana.model.Budget;
import org.example.fiangonana.model.MvtCaisse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MvtCaisseRepository extends JpaRepository<MvtCaisse, Integer>, JpaSpecificationExecutor<MvtCaisse> {


    @Query(value = """
                WITH solde_initial AS (
                    SELECT COALESCE(SUM(entree - sortie), 0) AS solde
                    FROM mvt_caisse
                    WHERE COALESCE(:dmin, date) > date
                ),
                transactions_filtrees AS (
                 SELECT
                     m.id,
                     m.code,
                     m.libelle,
                     m.date,
                     m.entree,
                     m.sortie,
                     b.id as id_budget,
                     b.libelle as libelle_budget
                 FROM mvt_caisse m
                 LEFT JOIN budgets b ON m.id_budget = b.id
                 WHERE COALESCE(:dmin, date) <= date AND COALESCE(:dmax, date) >= date
                )
                SELECT
                    t.id,
                    t.code,
                    t.date,
                    t.libelle,
                    t.entree,
                    t.sortie,
                    t.id_budget,
                    t.libelle_budget,
                    solde_initial.solde + SUM(t.entree - t.sortie) OVER (ORDER BY t.date, t.id) AS soldes
                FROM transactions_filtrees t, solde_initial
                UNION (
                    SELECT
                        null,
                        '2111',
                        null,
                        'solde precedent',
                        case when solde < 0 then 0 else solde end,
                        case when solde < 0 then solde else 0 end,
                        NULL,
                        NULL,
                        solde
                    FROM solde_initial
                )
                ORDER BY date NULLS FIRST, id 
            
                ;
            """, nativeQuery = true

    )
    public List<MvtCaisseLigne> getMvtCaisseEntre2Dates(@Param("dmin") LocalDate dmin, @Param("dmax") LocalDate dmax);


//    @Query(value = """
//
//            WITH caisse_recap AS (
//                SELECT
//                    code    as numero,
//                    SUM(entree - mvt_caisse.sortie) as total
//                FROM mvt_caisse
//                WHERE COALESCE(:dmin, date) <= date AND COALESCE(:dmax, date) >= date
//                group by code
//                ORDER BY numero ASC
//            )
//            SELECT
//                numero,
//                c.libelle,
//                recap.total
//            FROM caisse_recap recap
//            JOIN codes c
//            ON c.code like recap.numero
//            """, nativeQuery = true)
    @Query(value = """
        WITH rapport AS (
            SELECT
                g.id,
                STRING_AGG(DISTINCT mvt.libelle, ', ') AS lib_details,
                SUM(entree - sortie) AS total
            FROM mvt_caisse mvt
            JOIN codes c ON mvt.id_compte = c.id
            JOIN groupe_compte_recaps g ON c.id_groupe = g.id
            WHERE COALESCE(:dmin, mvt.date) <= mvt.date AND COALESCE(:dmax, mvt.date) >= mvt.date
            GROUP BY g.id
        )
        SELECT
            g.id as numero,
            g.identification as libelle,
            g.libelle as lib_origine,
            r.total as total,
            r.lib_details as lib_details
        FROM rapport r
        JOIN groupe_compte_recaps g ON r.id = g.id
        """, nativeQuery = true
    )
    List<MvtCaisseRecapLigne> getRecapCaisse(LocalDate dmin, LocalDate dmax);


    @Query(value = """
                 with rapport as (
                     SELECT
                         code,
                         sum(entree - sortie) as total
                     FROM mvt_caisse mvt
                     WHERE COALESCE(:dmin, mvt.date) <= mvt.date AND COALESCE(:dmax, mvt.date) >= mvt.date
                     group by code
                 )
                 SELECT
                       codes.id as id,
                      codes.libelle as identification,
                         codes.libelle as lib_origine,
                    rapport.total as total,
                    '' as lib_details
                 FROM rapport
                 JOIN codes ON rapport.code = codes.code
            """, nativeQuery = true
    )
    List<MvtCaisseRecapLigne> getRecapCaisseDefaut(LocalDate dmin, LocalDate dmax);





    @Query("""
        SELECT COALESCE(SUM(mvt.entree - mvt.sortie), 0)
        FROM MvtCaisse mvt WHERE COALESCE(:date, mvt.date) > mvt.date
    """)
    Double getSoldePrecedent(LocalDate date);

    @Query("SELECT mvt FROM MvtCaisse mvt WHERE mvt.budget.id = :budget ORDER BY mvt.date DESC")
    List<MvtCaisse> findAllByBudget(Integer budget);


    @Query("SELECT m FROM MvtCaisse m " +
            "WHERE (COALESCE(:dateMin, m.date) <= m.date ) " +
            "AND (COALESCE(:dateMax, m.date) >= m.date ) " +
            "AND (:numeroCompte IS NULL OR m.code LIKE :numeroCompte%) " +
            "AND (:entreeMin IS NULL OR m.entree >= :entreeMin) " +
            "AND (:entreeMax IS NULL OR m.entree <= :entreeMax) " +
            "AND (:sortieMin IS NULL OR m.sortie >= :sortieMin) " +
            "AND (:sortieMax IS NULL OR m.sortie <= :sortieMax) " +
            "AND (:libelle IS NULL OR LOWER(m.libelle) ILIKE %:libelle%)"
    )
    Page<MvtCaisse> recherche(
            @Param("dateMin") LocalDate dateMin,
            @Param("dateMax") LocalDate dateMax,
            @Param("numeroCompte") String numeroCompte,
            @Param("entreeMin") Double entreeMin,
            @Param("entreeMax") Double entreeMax,
            @Param("sortieMin") Double sortieMin,
            @Param("sortieMax") Double sortieMax,
            @Param("libelle") String libelle,
            Pageable pageable
    );




    @Query("SELECT SUM(m.entree - m.sortie) FROM MvtCaisse m WHERE m.date < :date")
    Double getSolde(@Param("date") LocalDate date);


    @Query("SELECT SUM(m.entree) AS entree, SUM(m.sortie) AS sortie, SUM(m.entree - m.sortie) AS total FROM MvtCaisse m WHERE m.date >= COALESCE(:dateMin, m.date) AND m.date <= COALESCE(:dateMax, m.date) ")
    Map<String, Object> getBilanSimpleEntre2Dates(@Param("dateMin") LocalDate dateMin, @Param("dateMax") LocalDate dateMax);





//    @Query(
//        """
//            SELECT m FROM MvtCaisse m
//
//        """
//    )
//    BilanTresorerie getBilan(
//            @Param("dateMin") LocalDate dateMin,
//            @Param("dateMax") LocalDate dateMax
//    );


//    List<MvtCaisse> rechercher(
//            LocalDate dmin,
//            LocalDate dmax,
//            String numCompte,
//            Double entreeMin,
//            Double entreeMax
//    );

}