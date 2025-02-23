package org.example.fiangonana.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fiangonana.model.Utilisateur;
import org.example.fiangonana.service.AuthService;
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

    public AuthController(AuthService authService, HttpSession httpSession) {
        this.authService = authService;
        this.httpSession = httpSession;
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
            return "redirect:/";
        }
        return "redirect:/login?error=Identifiant ou mot de passe incorrect";
    }

}


