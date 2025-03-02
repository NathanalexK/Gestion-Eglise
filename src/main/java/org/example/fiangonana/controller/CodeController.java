package org.example.fiangonana.controller;

import org.example.fiangonana.model.Code;
import org.example.fiangonana.service.CategorieCompteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/compte")
public class CodeController extends BaseController{

    private final CategorieCompteService categorieCompteService;

    public CodeController(CategorieCompteService categorieCompteService) {
        super();
        this.categorieCompteService = categorieCompteService;
    }

    @GetMapping("/saisie")
    public ModelAndView afficherSaisieFormu(@RequestParam(value = "id",required = false) Code code) {
        ModelAndView modelAndView = this.getPage("code/saisie.jsp");
        modelAndView.addObject("categories", categorieCompteService.getAllCategorieComptes());
        modelAndView.addObject("code", code);
        return modelAndView;
    }
}
