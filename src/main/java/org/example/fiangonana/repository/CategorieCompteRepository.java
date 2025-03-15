package org.example.fiangonana.repository;

import org.example.fiangonana.model.CategorieCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieCompteRepository extends JpaRepository<CategorieCompte, Integer> {
    @Query("SELECT c FROM CategorieCompte c ORDER BY c.typeCompte.id DESC")
    List<CategorieCompte> findAll();
}
