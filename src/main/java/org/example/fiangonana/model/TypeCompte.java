package org.example.fiangonana.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "type_compte")
public class TypeCompte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_compte_id_gen")
    @SequenceGenerator(name = "type_compte_id_gen", sequenceName = "type_compte_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "libelle", length = 50)
    private String libelle;

    @OneToMany(mappedBy = "typeCompte", fetch = FetchType.LAZY)
    private List<CategorieCompte> categorieComptes = new ArrayList<>();

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<GroupeCompteRecap> groupeCompteRecaps = new ArrayList<>();

}