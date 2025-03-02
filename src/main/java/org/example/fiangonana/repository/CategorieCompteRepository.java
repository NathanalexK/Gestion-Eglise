package org.example.fiangonana.repository;

import org.example.fiangonana.model.CategorieCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieCompteRepository extends JpaRepository<CategorieCompte, Integer> {
}
