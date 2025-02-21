package org.example.fiangonana.controller;

import org.example.fiangonana.component.SessionManager;
import org.example.fiangonana.util.HtmlTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    private final SessionManager sessionManager;

    public TestController(SessionManager sessionManager) {
        super();
        this.sessionManager = sessionManager;
    }

    @GetMapping
    public ModelAndView index() {
        return getPage("");
    }

    @GetMapping("/popup")
    public ModelAndView testPopup() {
        ModelAndView modelAndView = new ModelAndView("page-erreur");
        List<String> list = List.of("A", "B", "C");
        sessionManager.addErrorAlert(HtmlTemplate.buildList(list));
        return modelAndView;
    }
}
