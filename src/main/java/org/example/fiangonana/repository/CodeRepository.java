package org.example.fiangonana.repository;

import org.example.fiangonana.model.Code;
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
}