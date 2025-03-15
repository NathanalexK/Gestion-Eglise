package org.example.fiangonana.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.exception.ExceptionList;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "groupe_compte_recaps")
public class GroupeCompteRecap {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupe_compte_recaps_id_gen")
    @SequenceGenerator(name = "groupe_compte_recaps_id_gen", sequenceName = "groupe_compte_recaps_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "libelle", length = 100)
    private String libelle;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type")
    private TypeCompte type;

    @Column(name = "identification", length = 50)
    private String identification;

    @OneToMany(mappedBy = "groupeCompteRecap", fetch = FetchType.LAZY)
    private List<Code> comptes = new ArrayList<>();


    public void controller() throws ExceptionList {
        ExceptionList e = new ExceptionList();
        if(this.getLibelle().length() < 4) {
            e.addMessage("Le libellé doit contenir au moins 4 caractères");
        }
        if(this.getType() == null) {
            e.addMessage("Le type de compte ne doit pas etre vide");
        }
        if(this.getIdentification() == null) {
            this.setIdentification("");
        }
        e.thowWhenNotEmpty();
    }

    public String getTypeCompteLibelle() {
        return this.getType() != null ? this.getType().getLibelle() : "";
    }

}