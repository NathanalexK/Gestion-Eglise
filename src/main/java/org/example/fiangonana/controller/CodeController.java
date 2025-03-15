package org.example.fiangonana.controller;

import org.example.fiangonana.component.SessionManager;
import org.example.fiangonana.dto.compte.CompteRecherche;
import org.example.fiangonana.exception.ExceptionList;
import org.example.fiangonana.model.Code;
import org.example.fiangonana.service.CategorieCompteService;
import org.example.fiangonana.service.CodeService;
import org.example.fiangonana.service.GroupeCompteService;
import org.example.fiangonana.service.TypeCompteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/compte")
public class CodeController extends BaseController{

    private final CategorieCompteService categorieCompteService;
    private final CodeService codeService;
    private final SessionManager sessionManager;
    private final GroupeCompteService groupeCompteService;
    private final TypeCompteService typeCompteService;

    public CodeController(CategorieCompteService categorieCompteService, CodeService codeService, SessionManager sessionManager, GroupeCompteService groupeCompteService, TypeCompteService typeCompteService) {
        super();
        this.categorieCompteService = categorieCompteService;
        this.codeService = codeService;
        this.sessionManager = sessionManager;
        this.groupeCompteService = groupeCompteService;
        this.typeCompteService = typeCompteService;
    }

    @GetMapping("/saisie")
    public ModelAndView afficherSaisieFormu(@RequestParam(value = "id",required = false) Code code) {
        ModelAndView modelAndView = this.getPage("code/saisie.jsp");
//        modelAndView.addObject("categories", categorieCompteService.getAllCategorieComptes());
        modelAndView.addObject("type[]", typeCompteService.getAllTypeComptes());
        modelAndView.addObject("code", code);
//        modelAndView.addObject("g[]", groupeCompteService.getAllGroupesCompteRecaps());
        return modelAndView;
    }

    @PostMapping("/save")
    public String traiterFormuSaisie(@ModelAttribute Code c) throws ExceptionList {
//        c.controller();
        codeService.enregistrerCode(c);
        sessionManager.addSuccessAlert("Le compte: " + c.getCode() + " " + c.getLibelle() + " a été enregistré");
        return redirect("/compte/liste");
    }

    @GetMapping("/liste")
    public ModelAndView afficherListe(@ModelAttribute CompteRecherche compteRecherche) {
        ModelAndView modelAndView = this.getPage("code/liste.jsp");
        modelAndView.addObject("recherche", codeService.rechercher(compteRecherche));
        modelAndView.addObject("type[]", typeCompteService.getAllTypeComptes());
        modelAndView.addObject("code[]", codeService.getAllCodes());
        return modelAndView;
    }
}
