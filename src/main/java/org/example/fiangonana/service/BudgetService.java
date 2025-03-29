package org.example.fiangonana.service;

import org.example.fiangonana.dto.budget.ArretBudget;
import org.example.fiangonana.dto.budget.BudgetRecherche;
import org.example.fiangonana.exception.ExceptionList;
import org.example.fiangonana.model.Budget;
import org.example.fiangonana.model.immutable.VBudgetCpl;
import org.example.fiangonana.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public void controller(Budget b) throws ExceptionList {
        ExceptionList e = new ExceptionList();
        if(b.getMontant() <= 0) {
            e.addMessage("Le montant doit être positif");
        }
        if(b.getDateDebut() == null) {
            e.addMessage("Le budget doit-être daté");
        }
        if(b.getDateFin() == null) {
            e.addMessage("Le budget doit avoir une date fin");
        }
        if(b.getDateDebut() != null && b.getDateFin() != null && b.getDateDebut().isAfter(b.getDateFin())) {
            e.addMessage("la date Fin doit être après la date debut");
        }
        e.throwWhenNotEmpty();
    }

    public Budget arreterBudget(ArretBudget ab) throws ExceptionList {
        ExceptionList e = new ExceptionList();

        Budget budget = budgetRepository.findBudgetById(ab.getIdBudget());
        if(budget == null) {
            e.addMessage("Aucun budget trouvé ayant ID: " + ab.getIdBudget());
            throw e;
        }
        if(budget.getDateArret() != null) {
            e.addMessage("Budget déjà arreté");
        }
        if(ab.getDateArret() == null) {
            ab.setDateArret(LocalDate.now());
        }
        if(ab.getDateArret().isBefore(budget.getDateDebut())) {
            e.addMessage("Le budget doit-être arreté après l'enregistrement");
        }
        e.throwWhenNotEmpty();
        budget.setDateArret(ab.getDateArret());
        return budgetRepository.save(budget);
    }

    public Budget enregistrer(Budget b) {
        return budgetRepository.save(b);
    }

    public Budget enregistrerAvecControl(Budget b) throws ExceptionList {
        controller(b);
        return enregistrer(b);
    }

    public List<Budget> getAllBudgetsDisponibles() {
        return budgetRepository.findAllBudgetsDisponibles();
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public List<VBudgetCpl> getAllBudgetsComplets() {
        return budgetRepository.findAllBudgetsComplets();
    }

    public BudgetRecherche rechercgerBudgets(BudgetRecherche recherche) {
        recherche.setBudgets(
                budgetRepository.rechercherBudgets(recherche.getLibelle(), recherche.getIsArret(), recherche.getDateMin(), recherche.getDateMax())
        );
        return recherche;
    }

    public List<VBudgetCpl> getAllBudgetsCompletsDisponibles() {
        return budgetRepository.findAllBudgetCompletDisponibles();
    }

    public Budget arreterBudger(Budget b, LocalDate dateArret) {
        b.setDateArret(dateArret);
        return budgetRepository.save(b);
    }

    public VBudgetCpl getBudgetCompletById(Integer id) {
        return budgetRepository.findBudgetCompletById(id);
    }


}
