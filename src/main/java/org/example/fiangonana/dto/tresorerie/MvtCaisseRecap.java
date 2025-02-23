package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.util.Constante;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MvtCaisseRecap {
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private List<MvtCaisseRecapLigne> recapEntrees = new ArrayList<>();
    private List<MvtCaisseRecapLigne> recapSortie = new ArrayList<>();
    private Double totalEntrees;
    private Double totalSortie;
    private Double soldeRestant;
    private Double soldePrecedent;

    public MvtCaisseRecap() {

    }

    public MvtCaisseRecap(List<MvtCaisseRecapLigne> lignes) {
        double entrees = 0.00;
        double sorties = 0.00;
        for(MvtCaisseRecapLigne ligne: lignes) {
            if(ligne.getType() == Constante.mouvement.ENTREE) {
                entrees += ligne.getTotal();
                recapEntrees.add(ligne);

            } else {
                sorties += ligne.getTotal();
                recapSortie.add(ligne);
            }
        }
        setTotalEntrees(entrees);
        setTotalSortie(sorties);
    }

    public Double getSoldeRestant() {
        return this.getSoldePrecedent() + this.getTotalEntrees() - this.getTotalSortie();
    }
}
