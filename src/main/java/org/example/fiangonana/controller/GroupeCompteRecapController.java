package org.example.fiangonana.controller;

import org.example.fiangonana.component.SessionManager;
import org.example.fiangonana.exception.ExceptionList;
import org.example.fiangonana.model.GroupeCompteRecap;
import org.example.fiangonana.repository.TypeCompteRepository;
import org.example.fiangonana.service.GroupeCompteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/groupe-compte")
public class GroupeCompteRecapController extends BaseController{

    private final TypeCompteRepository typeCompteRepository;
    private final GroupeCompteService groupeCompteService;
    private final SessionManager sessionManager;

    public GroupeCompteRecapController(TypeCompteRepository typeCompteRepository, GroupeCompteService groupeCompteService, SessionManager sessionManager) {
        super();
        this.typeCompteRepository = typeCompteRepository;
        this.groupeCompteService = groupeCompteService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/saisie")
    public ModelAndView afficherFormuSaisie(@RequestParam(name = "id", required = false) GroupeCompteRecap g) {
        ModelAndView modelAndView = this.getPage("groupe-compte/saisie.jsp");
        modelAndView.addObject("g", g);
        modelAndView.addObject("type[]", typeCompteRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/save")
    public String traiterFormuSaisie(@ModelAttribute GroupeCompteRecap g) throws ExceptionList {
        g.controller();
        groupeCompteService.enregistrer(g);
        sessionManager.addSuccessAlert("Groupe de compte: " + g.getLibelle() + " enregistré avec succès");
        return redirect("/groupe-compte/liste");
    }

    @GetMapping("/liste")
    public ModelAndView afficherListe() {
        ModelAndView modelAndView = this.getPage("groupe-compte/liste.jsp");
        modelAndView.addObject("g[]", groupeCompteService.getAllGroupesCompteRecaps());
        return modelAndView;
    }

    @GetMapping("/details")
    public ModelAndView PageDetails(@RequestParam(name = "id") GroupeCompteRecap g){
        ModelAndView modelAndView = this.getPage("groupe-compte/details.jsp");
        modelAndView.addObject("g", g);
        return modelAndView;
    }
}
