package org.example.fiangonana.controller;

import org.springframework.web.servlet.ModelAndView;

public class BaseController {

    public ModelAndView getPage(String page) {
        ModelAndView modelAndView = new ModelAndView("layout");
        modelAndView.addObject("content", page);
        return modelAndView;
    }

    public String redirect(String url) {
        return "redirect:" + url;
    }
}
