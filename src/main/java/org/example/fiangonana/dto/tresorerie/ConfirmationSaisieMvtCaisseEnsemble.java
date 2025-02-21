package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.exception.ExceptionList;
import org.example.fiangonana.model.MvtCaisse;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ConfirmationSaisieMvtCaisseEnsemble {
    private LocalDate date;
    private List<MvtCaisse> mvtCaisses;


    public void controller() throws ExceptionList {
        ExceptionList exceptionList = new ExceptionList();
        if(this.getDate() == null) {
            exceptionList.addMessage("Il doit y avoir une date pour enregistrer une opération");
        }

        if(date.isAfter(LocalDate.now())) {
            exceptionList.addMessage("Date ne doit pas être une date futur");
        }

        if(mvtCaisses == null || mvtCaisses.isEmpty()) {
            exceptionList.addMessage("Doit avoir au moins une operation");

        } else {
            mvtCaisses.forEach((mvtCaisse) -> {
                try {
                    mvtCaisse.controller();
                } catch (Exception e) {
                    exceptionList.addMessage(mvtCaisse.getLibelle() + ": " + e.getMessage());
                }
            });
        }

        if(!exceptionList.getMessages().isEmpty()) {
            throw exceptionList;
        }
    }


    public void ajouterDate() {
        this.mvtCaisses.forEach(mvtCaisse -> {
            mvtCaisse.setDate(this.getDate());
        });
    }



    public void filtrer() {
        if(this.mvtCaisses != null && !this.mvtCaisses.isEmpty()) {
            this.mvtCaisses = this.getMvtCaisses()
                    .stream()
                    .filter(mvtCaisse -> mvtCaisse.getCode() != null && !mvtCaisse.getCode().trim().isBlank())
                    .toList();
        }
    }
}
