package org.example.fiangonana.controller;

import org.example.fiangonana.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class BaseController {

    @Autowired
    private ConfigurationService configurationService;

//    public BaseController(ConfigurationService configurationService) {
//        this.configurationService = configurationService;
//    }

    public ModelAndView getPage(String page) {
        ModelAndView modelAndView = new ModelAndView("layout");
        modelAndView.addObject("content", page);
        modelAndView.addObject("conf", configurationService.getConfigutation());
        return modelAndView;
    }

    public String redirect(String url) {
        return "redirect:" + url;
    }
}
