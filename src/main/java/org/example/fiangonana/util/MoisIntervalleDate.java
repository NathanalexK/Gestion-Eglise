package org.example.fiangonana.util;

import java.time.LocalDate;

public class MoisIntervalleDate {
    private int mois;
    private int annee;
    private String nom;
    private LocalDate debutMois;
    private LocalDate finMois;

    public MoisIntervalleDate(int mois, int annee, String nom, LocalDate debutMois, LocalDate finMois) {
        this.mois = mois;
        this.nom = nom;
        this.annee = annee;
        this.debutMois = debutMois;
        this.finMois = finMois;
    }

    // Getters et toString() (facultatif, utile pour affichage)
    public int getMois() {
        return mois;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDebutMois() {
        return debutMois;
    }

    public LocalDate getFinMois() {
        return finMois;
    }

    @Override
    public String toString() {
        return nom + " " + debutMois.getYear() + ": " + debutMois + " à " + finMois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
}
