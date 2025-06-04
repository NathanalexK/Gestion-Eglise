package org.example.fiangonana.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@ToString
@Table(name = "configuration")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;
    @Column(name = "date_min_defaut")
    private LocalDate dateMinDefaut;
    @Column(name = "date_max_defaut")
    private LocalDate dateMaxDefaut;
    @Column(name = "couleur_budget")
    private String couleurBudget;


    public Integer getId() {
        return 1;
    }

    public String getCouleurBudget() {
        if(couleurBudget != null && (couleurBudget.equalsIgnoreCase("#000000") || couleurBudget.equalsIgnoreCase("#ffffff"))) {
            return "null";
        }
        return couleurBudget;
    }
}
