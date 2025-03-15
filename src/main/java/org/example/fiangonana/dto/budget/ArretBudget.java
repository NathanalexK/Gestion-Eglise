package org.example.fiangonana.dto.budget;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ArretBudget {
    private Integer idBudget;
    private LocalDate dateArret;
}
