package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RapportCaisseLigne {
    private String libelle;
    private Double montant;
    private List<MvtCaisseLigne> detailsListe;
    private String libelleCpl;

    public String getLibelleCpl() {
        if(libelleCpl != null) {
            return null;
        }
        String libTemp = "";
        libTemp += libelle;

        if(this.getLibelle().contains("...")) {
            List<String> libelles = this.detailsListe
                    .stream()
                    .map(mvt -> mvt.getLibelle())
                    .toList();
            libTemp.replace("...", String.join(", ", libelles));
        }
        this.libelleCpl = libTemp;
        return this.libelleCpl;
    }
}
