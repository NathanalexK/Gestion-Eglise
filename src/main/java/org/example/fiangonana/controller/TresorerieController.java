package org.example.fiangonana.controller;

import org.example.fiangonana.component.SessionManager;
import org.example.fiangonana.dto.tresorerie.ConfirmationSaisieMvtCaisseEnsemble;
import org.example.fiangonana.dto.tresorerie.ValiderMvtCaisseDTO;
import org.example.fiangonana.exception.ExceptionList;
import org.example.fiangonana.model.MvtCaisse;
import org.example.fiangonana.repository.CodeRepository;
import org.example.fiangonana.service.CodeService;
import org.example.fiangonana.service.MvtCaisseService;
import org.example.fiangonana.util.DateUtils;
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

    public TresorerieController(CodeService codeService, MvtCaisseService mvtCaisseService, SessionManager sessionManager) {
        super();
        this.codeService = codeService;
        this.mvtCaisseService = mvtCaisseService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/saisie-ligne")
    public ModelAndView affichageSaisieParLigne() {
        ModelAndView modelAndView = this.getPage("tresorerie/saisie-ligne.jsp");
        modelAndView.addObject("codesEntree", codeService.getCodesEntrees());
        modelAndView.addObject("codesSortie", codeService.getCodesSorties());
        return modelAndView;
    }

    @PostMapping("/saisie-ligne/confirmer")
    public ModelAndView afficherConfirmation(@ModelAttribute MvtCaisse mvtCaisse) throws ExceptionList {
        try {
            mvtCaisse.controller();
            ModelAndView modelAndView = this.getPage("tresorerie/confirmation-saisie.jsp");
            modelAndView.addObject("mvtCaisse[]", List.of(mvtCaisse));
            return modelAndView;
        } catch (Exception e) {
            throw new ExceptionList(e.getMessage());
        }
    }

    @GetMapping("/saisie-ensemble")
    public ModelAndView affichageFormuSaisieEnsemble() {
        ModelAndView modelAndView = this.getPage("tresorerie/saisie-ensemble.jsp");
        modelAndView.addObject("codesEntree", codeService.getCodesEntrees());
        modelAndView.addObject("codesSortie", codeService.getCodesSorties());
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
    public String valider(@ModelAttribute ValiderMvtCaisseDTO validerMvtCaisseDTO) throws Exception {
        mvtCaisseService.enregistrerMvtCaisses(validerMvtCaisseDTO.getMvtCaisses());
        sessionManager.addSuccessAlert("Operation ajouté avec succès!");
        return redirect("/test");
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

        modelAndView.addObject("dmin", dmin);
        modelAndView.addObject("dmax", dmax);
        return modelAndView;
    }

//    @GetMapping("/pdf/date")
//    public


}
