package org.example.fiangonana.controller;

import org.example.fiangonana.component.SessionManager;
import org.example.fiangonana.model.Configuration;
import org.example.fiangonana.service.ConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/configuration")
public class ConfigurationController extends BaseController {


    private final SessionManager sessionManager;
    private final ConfigurationService configurationService;

    public ConfigurationController(SessionManager sessionManager, ConfigurationService configurationService) {
        super();
        this.sessionManager = sessionManager;
        this.configurationService = configurationService;
    }


    @GetMapping("/form")
    public ModelAndView afficherModificationConfigurationPage() {
        Configuration conf = configurationService.getConfigutation();
        ModelAndView modelAndView = this.getPage("configuration/form.jsp");
        modelAndView.addObject("conf", conf);
        return modelAndView;
    }

    @PostMapping("/save")
    public String modifierConfiguration(@ModelAttribute Configuration configuration) {
        configurationService.setConfiguration(configuration);
        sessionManager.addSuccessAlert("Configuration Modifié avec succès!");
        return redirect("/configuration/form");
    }


}
