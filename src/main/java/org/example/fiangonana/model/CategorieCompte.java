package org.example.fiangonana.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.exception.ExceptionList;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categorie_comptes")
public class CategorieCompte
{
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

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "categorieCompte", fetch = FetchType.LAZY)
    private List<Code> codes;



    public void controller() throws ExceptionList
    {
        ExceptionList e = new ExceptionList();
        if(this.getLibelle().length() < 4)
        {
            e.addMessage("Le libelle doit contenir au moins 4 caractères");
        }
        if(this.getTypeCompte() == null)
        {
            e.addMessage("Type de compte ne doit pas être vide");
        }

        e.throwWhenNotEmpty();
    }


    public String getTypeCompteLibelle() {
        return this.getTypeCompte() != null ? this.getTypeCompte().getLibelle() : "";
    }

    public String getLibelleAvecType() {
        return this.getTypeCompte().getLibelle().toUpperCase() +  ": " + this.getLibelle();
    }
}