package org.example.fiangonana.repository;

import org.example.fiangonana.dto.tresorerie.MvtCaisseLigneDTO;
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
         WHERE COALESCE(:dmin, date) >= date AND COALESCE(:dmax, date) <= date
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
        ORDER BY date NULLS FIRST 
            
        ;
    """, nativeQuery = true

    )
    public List<MvtCaisseLigneDTO> getMvtCaisseEntre2Dates(@Param("dmin") LocalDate dmin, @Param("dmax") LocalDate dmax);

}