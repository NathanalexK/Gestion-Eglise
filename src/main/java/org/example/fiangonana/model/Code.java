package org.example.fiangonana.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.exception.ExceptionList;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "codes")
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codes_id_gen")
    @SequenceGenerator(name = "codes_id_gen", sequenceName = "codes_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", length = 10)
    private String code;

    @Column(name = "libelle", length = 50)
    private String libelle;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ColumnDefault("now()")
    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie")
    private CategorieCompte categorieCompte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_groupe")
    private GroupeCompteRecap groupeCompteRecap;

    public void controller() throws ExceptionList {
        ExceptionList e = new ExceptionList();
        if(code.length() < 3) {
            e.addMessage("Code doit contenir au moins 3 caractères");
        }
        if(libelle.length() < 4) {
            e.addMessage("Libelle doit contenir au moins 4 caractères");
        }
        if(categorieCompte == null) {
            e.addMessage("Categorie de compte ne doit pas être vide");
        }

        e.throwWhenNotEmpty();
    }

    public String getCategorieLibelle() {
        return this.getCategorieCompte() != null ? this.getCategorieCompte().getLibelle() : "";
    }

    public String  getTypeCompteLibelle() {
        return this.getCategorieCompte() != null && this.getCategorieCompte().getTypeCompte() != null ? this.getCategorieCompte().getTypeCompteLibelle() : "";
    }

    public String getGroupeCompteLibelle() {
        return this.getGroupeCompteRecap() != null ? this.getGroupeCompteRecap().getLibelle() : "-";
    }
}