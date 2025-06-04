package org.example.fiangonana.dto.tresorerie;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MvtCaisseRechercheGroupe {
    private LocalDate dateMin;
    private LocalDate dateMax;
    private String colonneCle;
    private String colonneValeur;
    private String fonction = "sum";
    private String valeurCle;
    private boolean applique = false;


//    public String getLibelleCle() {
//        String[] parts = colonneCle.split("");
//        return parts.length > 1 ? parts[1] : parts[0] ;
//    }


//    public String getValeurCle() {
//        if(colonneCle.toLowerCase().endsWith("date") && valeurCle != null) {
//            valeurCle.split("/");
//        }
//    }
}
