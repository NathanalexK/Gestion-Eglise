package org.example.fiangonana.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.fiangonana.component.SessionManager;
import org.example.fiangonana.util.HtmlTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final SessionManager sessionManager;

    public GlobalExceptionHandler(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    //    @ExceptionHandler({Exception.class, RuntimeException.class})
    public String handleException(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        HttpSession session = request.getSession();
        session.setAttribute("eswal", e.getMessage());
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }


    @ExceptionHandler(ExceptionList.class)
    public ModelAndView handleExceptionList(ExceptionList e) {
        ModelAndView modelAndView = new ModelAndView("page-erreur");
        sessionManager.addErrorAlert(HtmlTemplate.buildList(e.getMessages()));
        return modelAndView;
    }

    @ExceptionHandler(NoUserLoggedException.class)
    public String handleNoUserLoggedException(NoUserLoggedException e) {
        return "redirect:/login?alert=Veuillex d'abord vous connecter";
    }
}
