package org.example.fiangonana.util;

import java.util.List;

public class Mois {
    private Integer intVal;
    private String strVal;


    public Mois(Integer intVal, String strVal) {
        this.intVal = intVal;
        this.strVal = strVal;
    }

    public static List<Mois> getAllMois() {
        return List.of(
                new Mois(1, "Janvier"),
                new Mois(2, "Fevrier"),
                new Mois(3, "Mars"),
                new Mois(4, "Avril"),
                new Mois(5, "Mai"),
                new Mois(6, "Juin"),
                new Mois(7, "Juillet"),
                new Mois(8, "Août"),
                new Mois(9, "Septembre"),
                new Mois(10, "Octobre"),
                new Mois(11, "Novembre"),
                new Mois(12, "Decembre")
        );
    }


    public Integer getIntVal() {
        return intVal;
    }

    public void setIntVal(Integer intVal) {
        this.intVal = intVal;
    }

    public String getStrVal() {
        return strVal;
    }

    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }


}
