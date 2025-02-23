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
}
