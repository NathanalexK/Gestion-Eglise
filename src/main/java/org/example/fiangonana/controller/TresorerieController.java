package org.example.fiangonana.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.fiangonana.component.SessionManager;
import org.example.fiangonana.dto.tresorerie.*;
import org.example.fiangonana.exception.ExceptionList;
import org.example.fiangonana.model.MvtCaisse;
import org.example.fiangonana.model.Utilisateur;
import org.example.fiangonana.service.*;
import org.example.fiangonana.util.DateUtils;
import org.example.fiangonana.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tresorerie")
public class TresorerieController extends BaseController {
    private final CodeService codeService;
    private final MvtCaisseService mvtCaisseService;
    private final SessionManager sessionManager;
    private final AuthService authService;
    private final CategorieCompteService categorieCompteService;
    private final BudgetService budgetService;


    public TresorerieController(CodeService codeService, MvtCaisseService mvtCaisseService, SessionManager sessionManager, AuthService authService, CategorieCompteService categorieCompteService, BudgetService budgetService) {
        super();
        this.codeService = codeService;
        this.mvtCaisseService = mvtCaisseService;
        this.sessionManager = sessionManager;
        this.authService = authService;
        this.categorieCompteService = categorieCompteService;
        this.budgetService = budgetService;
    }

    @GetMapping("/saisie-ligne")
    public ModelAndView affichageSaisieParLigne(@RequestParam(value = "id", required = false) MvtCaisse mvtCaisse)
    {
        ModelAndView modelAndView = this.getPage("tresorerie/saisie-ligne.jsp");
        modelAndView.addObject("mvtCaisse", mvtCaisse);
        System.out.println("MvtCaisse: " + mvtCaisse);
        modelAndView.addObject("categories", categorieCompteService.getAllCategorieComptes());
        modelAndView.addObject("budget[]", budgetService.getAllBudgetsCompletsDisponibles());
//        modelAndView.addObject("codesEntree", codeService.getCodesEntrees());
//        modelAndView.addObject("codesSortie", codeService.getCodesSorties());
        return modelAndView;
    }

    @PostMapping("/saisie-ligne/confirmer")
    public ModelAndView afficherConfirmation(@ModelAttribute MvtCaisse mvtCaisse) throws ExceptionList {
        mvtCaisseService.controller(mvtCaisse);
        ModelAndView modelAndView = this.getPage("tresorerie/confirmation-saisie.jsp");
        modelAndView.addObject("mvtCaisse[]", List.of(mvtCaisse));
        return modelAndView;
    }

    @GetMapping("/saisie-ensemble")
    public ModelAndView affichageFormuSaisieEnsemble() {
        ModelAndView modelAndView = this.getPage("tresorerie/saisie-ensemble.jsp");
        modelAndView.addObject("codesEntree", codeService.getCodesEntrees());
        modelAndView.addObject("codesSortie", codeService.getCodesSorties());
        modelAndView.addObject("categories", categorieCompteService.getAllCategorieComptes());
        modelAndView.addObject("budget[]", budgetService.getAllBudgetsCompletsDisponibles());
        return modelAndView;
    }

    @PostMapping("/saisie-ensemble/confirmer")
    public ModelAndView afficherConfirmationEnsemble(@ModelAttribute ConfirmationSaisieMvtCaisseEnsemble mvtCaisseEnsemble) throws ExceptionList {
//        try {
        mvtCaisseEnsemble.filtrer();
        mvtCaisseEnsemble.controller();
        ModelAndView modelAndView = this.getPage("tresorerie/confirmation-saisie.jsp");
        mvtCaisseEnsemble.ajouterDate();
//            mvtCaisseEnsemble.ajo
        modelAndView.addObject("mvtCaisse[]", mvtCaisseEnsemble.getMvtCaisses());
        return modelAndView;
//        } catch (ExceptionList e) {
//            return new ModelAndView("redirect:/tresorerie/saisie-ensemble");
//        }
    }


    @PostMapping("/valider")
    public String valider(@ModelAttribute ValiderMvtCaisse validerMvtCaisse) throws Exception {
        Utilisateur u = authService.utilisateurObligatoire();
        mvtCaisseService.enregistrerMvtCaisses(validerMvtCaisse.getMvtCaisses(), u);
        sessionManager.addSuccessAlert("Operation ajouté avec succès!");
        return redirect("/tresorerie/liste/date");
    }

    @GetMapping("/liste/date")
    public ModelAndView afficherListeEntre2Dates(
            @RequestParam(name = "dmin", required = false) LocalDate dmin,
            @RequestParam(name = "dmax", required = false) LocalDate dmax
    ) {
        if(dmin == null && dmax == null) {
            LocalDate[] dates = DateUtils.getIntervalleMois(LocalDate.now());
            dmin = dates[0];
            dmax = dates[1];
        }
        ModelAndView modelAndView = this.getPage("tresorerie/liste-2dates.jsp");
        modelAndView.addObject("mvtCaisse[]", mvtCaisseService.getMvtCaissesEntre2Dates(dmin, dmax));
        MvtCaisseAffichage affichage = new MvtCaisseAffichage();
        affichage.setMvtCaisses(mvtCaisseService.getMvtCaissesEntre2Dates(dmin, dmax));
        affichage.setDateMin(dmin);
        affichage.setDateMax(dmax);
        modelAndView.addObject("affichage", affichage);
        modelAndView.addObject("dmin", dmin);
        modelAndView.addObject("dmax", dmax);
        return modelAndView;
    }

    @GetMapping("/recap")
    public ModelAndView afficherRecapEntre2Dates(
            @RequestParam(name = "dmin", required = false) LocalDate dmin,
            @RequestParam(name = "dmax", required = false) LocalDate dmax
    ) {

        ModelAndView modelAndView = this.getPage("tresorerie/recap.jsp");
        modelAndView.addObject("affichage", mvtCaisseService.getRecapAffichage(dmin, dmax));
        return modelAndView;
    }

    @GetMapping("/recherche")
    public ModelAndView afficherPageRecherche(@ModelAttribute MvtCaisseRechercheAffichage rechercheAffichage) {
        ModelAndView modelAndView = this.getPage("tresorerie/recherche.jsp");
        modelAndView.addObject("affichage", mvtCaisseService.recherche(rechercheAffichage));
        return modelAndView;
    }

    @GetMapping("/supprimer")
    public String supprimerMvtCaisse(@RequestParam("id") MvtCaisse mvtCaisse, HttpServletRequest request) {
        mvtCaisseService.supprimerMvtCaisse(mvtCaisse);
        sessionManager.addSuccessAlert("Operation supprimé avec succès!");
        return redirect(WebUtils.getUrlPrecedente(request));
    }

//    @GetMapping("")

    @GetMapping("/recap/details")
    public ModelAndView voirDetailsRecap(
            @RequestParam("idGroupe") Integer idGroupe,
            @RequestParam("dateMin") LocalDate dateMin,
            @RequestParam("dateMax") LocalDate dateMax,
            @RequestParam(name = "lib", required = false, defaultValue = "") String lib
    ) {
        DetailsRecapTresorerie recap = new DetailsRecapTresorerie();
        recap.setIdGroupe(idGroupe);
        recap.setDateMin(dateMin);
        recap.setDateMax(dateMax);
        recap.setLibelle(lib);
        ModelAndView modelAndView = this.getPage("tresorerie/details-recap.jsp");
        modelAndView.addObject("affichage", mvtCaisseService.getDetailsRecapTresorie(recap));
        return modelAndView;
    }
}
