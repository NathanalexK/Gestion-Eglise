package org.example.fiangonana.controller;

import org.example.fiangonana.component.SessionManager;
import org.example.fiangonana.dto.budget.ArretBudget;
import org.example.fiangonana.dto.budget.BudgetCpl;
import org.example.fiangonana.dto.budget.BudgetRecherche;
import org.example.fiangonana.exception.ExceptionList;
import org.example.fiangonana.model.Budget;
import org.example.fiangonana.model.immutable.VBudgetCpl;
import org.example.fiangonana.service.BudgetService;
import org.example.fiangonana.service.MvtCaisseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/budget")
public class BudgetController extends BaseController {

    private final BudgetService budgetService;
    private final SessionManager sessionManager;
    private final MvtCaisseService mvtCaisseService;

    public BudgetController(BudgetService budgetService, SessionManager sessionManager, MvtCaisseService mvtCaisseService) {
        super();
        this.budgetService = budgetService;
        this.sessionManager = sessionManager;
        this.mvtCaisseService = mvtCaisseService;
    }

    @GetMapping("/saisie")
    public ModelAndView afficherSaisieFormu(@RequestParam(name ="id", required = false) Budget budget) {
        ModelAndView modelAndView = this.getPage("budget/saisie.jsp");
        modelAndView.addObject("budget", budget);
        return modelAndView;
    }

    @GetMapping("/liste")
    public ModelAndView afficherListe(@ModelAttribute BudgetRecherche b) {
        ModelAndView modelAndView = this.getPage("budget/liste.jsp");
        modelAndView.addObject("budget[]", budgetService.rechercgerBudgets(b));
        return modelAndView;
    }

    @PostMapping("/save")
    public String traiterSaisieFormu(@ModelAttribute Budget budget) throws ExceptionList {
        budgetService.enregistrerAvecControl(budget);
        sessionManager.addSuccessAlert("Le budget a été enregistré avec succès");
        return redirect("/budget/liste");
    }

    @GetMapping("/details")
    public ModelAndView afficherDetails(@RequestParam("id") Integer idBudget) throws ExceptionList {
        ModelAndView modelAndView = this.getPage("budget/details.jsp");
        VBudgetCpl budget = budgetService.getBudgetCompletById(idBudget);
        if(budget == null) throw new ExceptionList("Aucun budget trouvé ayant ID: " + idBudget);
        modelAndView.addObject("budget",budget );
        modelAndView.addObject("mc[]", mvtCaisseService.getAllByIdBudget(idBudget));
        return modelAndView;
    }

    @GetMapping("/arret")
    public ModelAndView afficherSaisieArretBudget(@RequestParam("id") Budget budget) {
        ModelAndView mav = this.getPage("budget/saisie-arret.jsp");
        mav.addObject("budget", budget);
        return mav;
    }

    @PostMapping("/arret")
    public String arreterBudget(@ModelAttribute ArretBudget ab) throws ExceptionList {
        budgetService.arreterBudget(ab);
        sessionManager.addSuccessAlert("Budget arreté avec succès!");
        return redirect("/budget/details?id=" + ab.getIdBudget());
    }

//    @GetMapping("/")


}
