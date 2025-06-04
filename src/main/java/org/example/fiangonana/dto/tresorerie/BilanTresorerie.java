package org.example.fiangonana.dto.tresorerie;


import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.util.DateUtils;

import java.time.LocalDate;

@Getter
@Setter
public class BilanTresorerie {
    private String type = "mois";
    private Integer mois;
    private Integer annee;
    private Double soldePrecedent;
    private Double totalEntree;
    private Double totalSortie;
    private Double total;
    private Double soldeActuel;
    private LocalDate[] intervalleDates;

    public Double getSoldeActuel() {
        return this.getSoldePrecedent() + this.getTotal();
    }

    public String getLibelle() {
        return DateUtils.convertirMoisEnString(this.getMois()) + " " + this.getAnnee();
    }

    public LocalDate[] getIntervalleDates() {
        if(intervalleDates == null) {
            intervalleDates = DateUtils.getIntervalleMois(this.getMois(), this.getAnnee());
        }
        return this.intervalleDates;
//        return intervalleDates;
    }


    //    private BilanTresorerie bilanMoisActuel;
//    private BilanTresorerie bilanMoisPrecedent;
}
