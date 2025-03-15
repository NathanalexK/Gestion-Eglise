package org.example.fiangonana.model.immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_budget_cpl")
public class VBudgetCpl {
    @Id
    @Column(name = "id")
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

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "montant_use")
    private Double montantUse;

    @Column(name = "reste")
    private Double reste;

    public boolean estArrete() {
        return this.getDateArret() != null;
    }

}