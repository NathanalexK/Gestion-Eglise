package org.example.fiangonana.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categorie_comptes")
public class CategorieCompte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorie_comptes_id_gen")
    @SequenceGenerator(name = "categorie_comptes_id_gen", sequenceName = "categorie_comptes_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "libelle", length = 100)
    private String libelle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type")
    private TypeCompte typeCompte;

    @OneToMany(mappedBy = "categorieCompte", fetch = FetchType.LAZY)
    private List<Code> codes;
}