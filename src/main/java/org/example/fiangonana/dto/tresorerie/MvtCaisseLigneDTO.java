package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class MvtCaisseLigneDTO {
    private Integer id;
    private String code;
    private LocalDate date;
    private String libelle;
    private Double entree;
    private Double sortie;
    private Double soldes;


    public MvtCaisseLigneDTO() {
    }

    public MvtCaisseLigneDTO(Integer id, String code, Date date, String libelle, BigDecimal entree, BigDecimal sortie, BigDecimal soldes) {
        this.id = id;
        this.code = code;
        this.date = date != null? date.toLocalDate(): null;
        this.libelle = libelle;
        this.entree = entree != null ? entree.doubleValue() : 0;
        this.sortie = sortie != null ? sortie.doubleValue() : 0;
        this.soldes = soldes != null ? soldes.doubleValue() : 0;
    }

    @Override
    public String toString() {
        return "MvtCaisseLigneDTO{" +
                "id=" + id +
                ", date=" + date +
                ", libelle='" + libelle + '\'' +
                ", entree=" + entree +
                ", sortie=" + sortie +
                ", soldes=" + soldes +
                '}';
    }
}
