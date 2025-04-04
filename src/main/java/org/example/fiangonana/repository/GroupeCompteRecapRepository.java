package org.example.fiangonana.repository;

import org.example.fiangonana.model.GroupeCompteRecap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeCompteRecapRepository extends JpaRepository<GroupeCompteRecap, Integer> {
}
