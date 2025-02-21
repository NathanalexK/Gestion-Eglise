package org.example.fiangonana.repository;

import org.example.fiangonana.model.Code;
import org.example.fiangonana.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>, JpaSpecificationExecutor<Utilisateur> {


}