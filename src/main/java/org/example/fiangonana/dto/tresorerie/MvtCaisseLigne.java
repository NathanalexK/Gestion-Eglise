package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.util.Constante;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class MvtCaisseLigne {
    private Integer id;
    private String code;
    private LocalDate date;
    private String libelle;
    private Double entree;
    private Double sortie;
    private Double soldes;
    private Integer idBudget;
    private String libelleBudget;
//    private BudgetSimple budgetSimple;


    public MvtCaisseLigne() {
    }

    public MvtCaisseLigne(Integer id, String code, Date date, String libelle, BigDecimal entree, BigDecimal sortie, Integer idBudget, String nomBudget, BigDecimal soldes) {
        this.id = id;
        this.code = code;
        this.date = date != null? date.toLocalDate(): null;
        this.libelle = libelle;
        this.entree = entree != null ? entree.doubleValue() : 0;
        this.sortie = sortie != null ? sortie.doubleValue() : 0;
        this.soldes = soldes != null ? soldes.doubleValue() : 0;
        this.idBudget = idBudget;
        this.libelleBudget = nomBudget;
//        if(idBudget != null && nomBudget != null) {
//            BudgetSimple bs = new Bud
//        }
    }


    public String getClasseCouleur() {
        if(idBudget != null) {
            return Constante.css.OPERATION_BUDGET_CLASS;
        }
        return  "";
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

    public boolean isCompteSolde() {
        return this.getCode().equals(Constante.compte.COMPTE_SOLDE);
    }
}
