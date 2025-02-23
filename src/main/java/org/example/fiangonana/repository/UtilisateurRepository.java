package org.example.fiangonana.repository;

import org.apache.catalina.User;
import org.example.fiangonana.model.Code;
import org.example.fiangonana.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>, JpaSpecificationExecutor<Utilisateur> {

    @Query("SELECT u FROM Utilisateur u WHERE  u.identifiant Like :identifiant AND u.motDePasse LIKE :motDePasse")
    Optional<Utilisateur> findByIdentifiantAndMotDePasse(String identifiant, String motDePasse);

}