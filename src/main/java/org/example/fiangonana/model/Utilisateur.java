package org.example.fiangonana.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilisateurs_id_gen")
    @SequenceGenerator(name = "utilisateurs_id_gen", sequenceName = "utilisateurs_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", length = 100)
    private String nom;

    @Column(name = "identifiant", length = 100)
    private String identifiant;

    @Column(name = "mot_de_passe", length = 100)
    private String motDePasse;

    @ColumnDefault("now()")
    @Column(name = "date_creation")
    private Instant dateCreation;

}