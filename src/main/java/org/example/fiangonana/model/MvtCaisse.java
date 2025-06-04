package org.example.fiangonana.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.util.NombreUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "mvt_caisse")
public class MvtCaisse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mvt_fond_id_gen")
    @SequenceGenerator(name = "mvt_fond_id_gen", sequenceName = "mvt_fond_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "libelle")
    private String libelle;

//    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "code")
    private String code;

    @Column(name = "entree")
    private Double entree;

    @Column(name = "sortie")
    private Double sortie;

    @Column(name = "observation", length = Integer.MAX_VALUE)
    private String observation;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compte")
    private Code compte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_budget")
    private Budget budget;


//    public boolean estValide() {
//        boolean checkMontant = (this.getEntree() > 0 || this.getSortie() > 0) && this.getEntree() >= 0 && this.getSortie() >= 0;
//        boolean checkCode = this.getCode().length() < 3;
//        boolean checkLibelle = this.getLibelle().length() > 4;
//
//        return checkMontant && checkCode && checkLibelle;
//    }

    public String getLibelleFormu() {
        if(this.getLibelle() == null || this.getLibelle().trim().isBlank()) {
            return this.getCompte().getLibelle();
        }
        return this.getLibelle();
    }



    public void controller() throws Exception {
        if(this.getEntree() < 0 || this.getSortie() < 0) {
            throw new Exception("Le montant ne doit pas être négatif");
        }

        if(NombreUtils.comparerDouble(this.getEntree(), 0) && NombreUtils.comparerDouble(this.getSortie(), 0)){
            throw new Exception("Montant invalide: entrée et sortie ne doit pas être 0.00 en même temps");
        }

//        if()

//        if(this.getCode().length() < 3) {
//            throw new Exception("Le code ou numero compte doit contenir au moins 3 chiffres");
//        }
//
//        if(this.getCode().length() > 10) {
//            throw new Exception("Le code ou numero compte doit contenir au plus dix chiffres");
//        }
//
//        if(this.getLibelle().length() < 4) {
//            throw new Exception("Libellé doit contenir au moins 4 caractères");
//        }
    }

    public String getClasseCouleur() {
        if(this.getBudget() != null) {
            return "operation-budget";
        }
        return "";
    }

//    public void checkDate() {
//        if(this.get)
//    }


}