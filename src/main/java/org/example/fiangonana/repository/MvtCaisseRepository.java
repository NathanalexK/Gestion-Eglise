package org.example.fiangonana.repository;

import org.example.fiangonana.dto.tresorerie.MvtCaisseLigne;
import org.example.fiangonana.dto.tresorerie.MvtCaisseRecapLigne;
import org.example.fiangonana.model.MvtCaisse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MvtCaisseRepository extends JpaRepository<MvtCaisse, Integer>, JpaSpecificationExecutor<MvtCaisse> {


    @Query(value = """
                WITH solde_initial AS (
                    SELECT COALESCE(SUM(entree - sortie), 0) AS solde
                    FROM mvt_caisse
                    WHERE COALESCE(:dmin, date) > date
                ),
                transactions_filtrees AS (
                 SELECT
                     id,
                     code,
                     libelle,
                     date,
                     entree,
                     sortie
                 FROM mvt_caisse
                 WHERE COALESCE(:dmin, date) <= date AND COALESCE(:dmax, date) >= date
                )
                SELECT
                    t.id,
                    t.code,
                    t.date,
                    t.libelle,
                    t.entree,
                    t.sortie,
                    solde_initial.solde + SUM(t.entree - t.sortie) OVER (ORDER BY t.date, t.id) AS soldes
                FROM transactions_filtrees t, solde_initial
                UNION (
                    SELECT
                        null,
                        '111',
                        null,
                        'solde precedent',
                        case when solde < 0 then 0 else solde end,
                        case when solde < 0 then solde else 0 end,
                        solde
                    FROM solde_initial
                )
                ORDER BY date NULLS FIRST, id 
            
                ;
            """, nativeQuery = true

    )
    public List<MvtCaisseLigne> getMvtCaisseEntre2Dates(@Param("dmin") LocalDate dmin, @Param("dmax") LocalDate dmax);


    @Query(value = """
            
            WITH caisse_recap AS (
                SELECT
                    substring(code FROM 1 FOR 3)    as numero,
                    SUM(entree - mvt_caisse.sortie) as total
                FROM mvt_caisse
                WHERE COALESCE(:dmin, date) <= date AND COALESCE(:dmax, date) >= date
                group by substring(code FROM 1 FOR 3)
                ORDER BY numero ASC
            )
            SELECT
                1 as numero,
                c.libelle,
                recap.total
            FROM caisse_recap recap
            JOIN codes c
            ON c.code like recap.numero
            """, nativeQuery = true)
    List<MvtCaisseRecapLigne> getRecapCaisse(LocalDate dmin, LocalDate dmax);


    @Query("""
        SELECT COALESCE(SUM(mvt.entree - mvt.sortie), 0)
        FROM MvtCaisse mvt WHERE COALESCE(:date, mvt.date) > mvt.date
    """)
    Double getSoldePrecedent(LocalDate date);


//    List<MvtCaisse> rechercher(
//            LocalDate dmin,
//            LocalDate dmax,
//            String numCompte,
//            Double entreeMin,
//            Double entreeMax
//    );

}