package org.example.fiangonana.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fiangonana.model.Utilisateur;
import org.example.fiangonana.service.AuthService;
import org.example.fiangonana.service.ConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class AuthController {

    private final AuthService authService;
    private final HttpSession httpSession;
    private final ConfigurationService configurationService;

    public AuthController(AuthService authService, HttpSession httpSession, ConfigurationService configurationService) {
        this.authService = authService;
        this.httpSession = httpSession;
        this.configurationService = configurationService;
    }

    @GetMapping("/login")
    public ModelAndView afficherPageLogin() {
        return new ModelAndView("/auth/login");
    }

    @PostMapping("/login")
    public String traiterFormuLogin(
            @RequestParam("identifiant") String identifiant,
            @RequestParam("mdp") String motDePasse
    ) {
        Utilisateur u = authService.authentifier(identifiant, motDePasse);
        if(u != null) {
            httpSession.setAttribute("u", u);
            httpSession.setAttribute("conf", configurationService.getConfigutation());
            return "redirect:/";
        }
        return "redirect:/login?error=Identifiant ou mot de passe incorrect";
    }


    @GetMapping("/logout")
    public String seDeconncter() {
        httpSession.removeAttribute("u");
        return "redirect:/login";
    }

}


