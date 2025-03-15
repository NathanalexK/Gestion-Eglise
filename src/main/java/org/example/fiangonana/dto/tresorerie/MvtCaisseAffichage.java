package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MvtCaisseAffichage {
    private LocalDate dateMin;
    private LocalDate dateMax;
    private List<MvtCaisseLigne> mvtCaisses = new ArrayList<>();
    private Double entree = null;
    private Double sortie = null;
    private Double soldePrecedent = null;

    public MvtCaisseAffichage() {

    }

    public Double getEntree() {
        if(this.entree != null) return this.entree;
        double total = 0.00;

        for(MvtCaisseLigne ligne: this.mvtCaisses) {
            if(ligne.isCompteSolde()) continue;
            total += ligne.getEntree();
        }
        setEntree(total);
        return this.entree;
    }

    public Double getSortie() {
        if(this.sortie != null) return this.sortie;
        double total = 0.00;

        for(MvtCaisseLigne ligne: this.mvtCaisses) {
            if(ligne.isCompteSolde()) continue;
            total += ligne.getSortie();
        }
        setSortie(total);
        return this.sortie;
    }

    public Double getSoldePrecedent() {
        if(this.soldePrecedent != null) return this.soldePrecedent;

        for(MvtCaisseLigne ligne: this.mvtCaisses) {
            if(ligne.isCompteSolde()) {
                setSoldePrecedent(ligne.getSoldes());
                return this.soldePrecedent;
            }
        }
        setSoldePrecedent(0.00);
        return this.soldePrecedent;

    }

    public Double getSolde() {
//        if(this.s)
        return this.getSoldePrecedent() + this.getEntree() - this.getSortie();
    }

    public Double getTotal() {
        return this.getEntree() - this.getSortie();
    }


    //    public MvtCaisseAffichageDTO(){
//    }
//
//    public MvtCaisseAffichageDTO() {
//
//    }
//
//    public Double getEntree() {
//        if(entree != null) return entree;
//
//
//    }
}
