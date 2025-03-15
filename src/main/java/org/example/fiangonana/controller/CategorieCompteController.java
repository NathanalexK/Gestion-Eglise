package org.example.fiangonana.controller;

import org.example.fiangonana.component.SessionManager;
import org.example.fiangonana.exception.ExceptionList;
import org.example.fiangonana.model.CategorieCompte;
import org.example.fiangonana.repository.TypeCompteRepository;
import org.example.fiangonana.service.CategorieCompteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categorie-compte")
public class CategorieCompteController extends BaseController{

    private final SessionManager sessionManager;
    private final CategorieCompteService categorieCompteService;
    private final TypeCompteRepository typeCompteRepository;

    public CategorieCompteController(SessionManager sessionManager, CategorieCompteService categorieCompteService, TypeCompteRepository typeCompteRepository) {
        this.sessionManager = sessionManager;
        this.categorieCompteService = categorieCompteService;
        this.typeCompteRepository = typeCompteRepository;
    }

    @GetMapping("/saisie")
    public ModelAndView afficherFormuSaisie(@RequestParam(name = "id", required = false) CategorieCompte categorieCompte)
    {
        ModelAndView modelAndView = this.getPage("categorie-compte/saisie.jsp");
        modelAndView.addObject("cc", categorieCompte);
        modelAndView.addObject("type[]", typeCompteRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/save")
    public String traiterFormuSaisie(@ModelAttribute CategorieCompte categorieCompte) throws ExceptionList {
        categorieCompte.controller();
        categorieCompteService.enregistrer(categorieCompte);
        sessionManager.addSuccessAlert("Categorie compte: " + categorieCompte.getLibelle() + " inseré avec succès");
        return redirect("/categorie-compte/liste");
    }


    @GetMapping("/liste")
    public ModelAndView afficherListe() {
        ModelAndView modelAndView = this.getPage("categorie-compte/liste.jsp");
        modelAndView.addObject("cc[]", categorieCompteService.getAllCategorieComptes());
        return modelAndView;
    }

}
