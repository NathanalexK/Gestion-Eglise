package org.example.fiangonana.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgets_id_gen")
    @SequenceGenerator(name = "budgets_id_gen", sequenceName = "budgets_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "libelle", length = 100)
    private String libelle;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "date_arret")
    private LocalDate dateArret;

//    @Column(name = "montant_use")
//    private Double montantUse;

    @Column(name =  "description")
    private String description;

    public Double getReste() {
        // TODO: Implementer la fonction
        return this.getMontant();
//        if(this.getMontantUse() == null) return 0.00;
//        return this.getMontant() - this.getMontantUse();
    }

}