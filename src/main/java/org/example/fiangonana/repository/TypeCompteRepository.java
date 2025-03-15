package org.example.fiangonana.repository;

import org.example.fiangonana.model.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCompteRepository extends JpaRepository<TypeCompte, Integer> {
}
