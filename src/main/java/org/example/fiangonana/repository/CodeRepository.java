package org.example.fiangonana.repository;

import org.example.fiangonana.model.CategorieCompte;
import org.example.fiangonana.model.Code;
import org.example.fiangonana.model.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Integer>, JpaSpecificationExecutor<Code> {
    @Query("SELECT c FROM Code c WHERE c.code LIKE '2%'")
    List<Code> getCodesEntree();

    @Query("SELECT c FROM Code c WHERE c.code LIKE '3%'")
    List<Code> getCodesSortie();

    @Query("select c from Code c where c.code = ?1")
    Code findByNumeroCompte(String code);

    @Query("SELECT c FROM Code c ORDER BY c.code ASC")
    List<Code> findAllCodes();

    @Query("""
        SELECT c
        FROM Code c
        WHERE
            (:code IS NULL OR c.code like :code%) AND
            (:libelle IS NULL OR c.libelle ilike %:libelle%) AND
            (:categorie IS NULL OR c.categorieCompte = :categorie) AND
            (:typeCompte IS NULL OR c.categorieCompte.typeCompte = :typeCompte)
        ORDER BY c.code ASC
    """)
    List<Code> rechercher(
            String code,
            String libelle,
            CategorieCompte categorie,
            TypeCompte typeCompte
    );
}