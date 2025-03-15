package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.util.Constante;

import java.math.BigDecimal;

@Getter
@Setter
public class MvtCaisseRecapLigne {
    private Integer numero;
    private String libelle;
    private Double total;
    private Integer type;
    private String libOrigine;

    public MvtCaisseRecapLigne() {
    }

    public MvtCaisseRecapLigne(Integer  numero, String libelle, String libOrigine, BigDecimal total, String libDetails) {
        this.numero = numero;
        this.libOrigine = libOrigine;
        if(libelle.contains("...")) {
            libelle = libelle.replace("...", libDetails);
        }

        this.libelle = libelle;
        if(total == null) total = BigDecimal.valueOf(0);
        double totalDouble = total.doubleValue();
        this.type = totalDouble >= 0 ? Constante.mouvement.ENTREE : Constante.mouvement.SORTIE;
        this.total = Math.abs(totalDouble);
    }
}
