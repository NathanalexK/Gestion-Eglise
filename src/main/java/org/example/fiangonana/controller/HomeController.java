package org.example.fiangonana.controller;

import org.example.fiangonana.exception.NoUserLoggedException;
import org.example.fiangonana.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{


    private final AuthService authService;

    public HomeController(AuthService authService) {
        super();
        this.authService = authService;
    }

    @GetMapping
    public ModelAndView showHome() throws NoUserLoggedException {
        authService.utilisateurObligatoire();
        return this.getPage("home.jsp");
    }
}
