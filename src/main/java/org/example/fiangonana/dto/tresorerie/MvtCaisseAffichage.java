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
            total += ligne.getEntree();
        }
        setEntree(total);
        return this.entree;
    }

    public Double getSortie() {
        if(this.sortie != null) return this.sortie;
        double total = 0.00;

        for(MvtCaisseLigne ligne: this.mvtCaisses) {
            total += ligne.getSortie();
        }
        setSortie(total);
        return this.sortie;
    }

    public Double getSolde() {
//        if(this.s)
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
