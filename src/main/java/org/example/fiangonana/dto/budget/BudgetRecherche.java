package org.example.fiangonana.dto.budget;

import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.model.immutable.VBudgetCpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class BudgetRecherche {
    private String libelle;
    private Integer isArret;
    private LocalDate dateMin;
    private LocalDate dateMax;

    private List<VBudgetCpl> budgets = new ArrayList<>();

}
