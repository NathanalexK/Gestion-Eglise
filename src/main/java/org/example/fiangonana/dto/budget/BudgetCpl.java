package org.example.fiangonana.dto.budget;

import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.model.Budget;
import org.example.fiangonana.util.NombreUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BudgetCpl {
    private Integer id;
    private String libelle;
    private Double montant;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalDate dateArret;
    private String description;
    private Double montantUse;
    private Double reste;


    public BudgetCpl(){}

    public BudgetCpl(Integer id, String libelle, BigDecimal montant, LocalDate dateDebut, LocalDate dateFin, LocalDate dateArret, String description, BigDecimal montantUse, BigDecimal reste) {
        this.id = id;
        this.libelle = libelle;
        this.montant = NombreUtils.getDouble(montant);
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dateArret = dateArret;
        this.description = description;
        this.montantUse = NombreUtils.getDouble(montant);
        this.reste = NombreUtils.getDouble(montant);
    }


}
