package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MvtCaisseAffichageDTO {
    private List<MvtCaisseLigneDTO> mvtCaisses = new ArrayList<>();
    private Double entree = null;
    private Double sortie = null;


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
