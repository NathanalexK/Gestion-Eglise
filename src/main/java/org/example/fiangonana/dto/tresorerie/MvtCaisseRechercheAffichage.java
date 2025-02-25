package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.model.MvtCaisse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MvtCaisseRechercheAffichage {
    private LocalDate dateMin;
    private LocalDate dateMax;
    private String numeroCompte;
    private Double entreeMin;
    private Double entreeMax;
    private Double sortieMin;
    private Double sortieMax;
    private String libelle;
    private List<MvtCaisse> mvtCaisses = new ArrayList<>();
    private Double totalEntree;
    private Double totalSortie;

    public Double getTotalEntree() {
        if(this.totalEntree != null) return totalEntree;
        double somme = 0.0;
        for(MvtCaisse mvtCaisse: mvtCaisses) {
            somme += mvtCaisse.getEntree();
        }

        this.setTotalEntree(somme);
        return somme;
    }

    public Double getTotalSortie() {
        if(this.totalSortie != null) return totalSortie;
        double somme = 0.0;
        for(MvtCaisse mvtCaisse: mvtCaisses) {
            somme += mvtCaisse.getSortie();
        }

        this.setTotalSortie(somme);
        return somme;
    }

    public Double getTotal() {
        return this.getTotalEntree() - this.getTotalSortie();
    }



}
