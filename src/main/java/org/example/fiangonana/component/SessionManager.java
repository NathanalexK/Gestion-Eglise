package org.example.fiangonana.component;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {
    private final HttpSession session;


    public SessionManager(HttpSession session) {
        this.session = session;
    }

    public void addErrorAlert(String errorMsg) {
        session.setAttribute("eswal", errorMsg);
    }

    public void addSuccessAlert(String successMsg) {
        session.setAttribute("sswal", successMsg);
    }

    public void addAlert(String infoMsg) {
        session.setAttribute("swal", infoMsg);
    }

    public void setAttribute(String key, Object value) {
        session.setAttribute(key, value);
    }

    public Object getAttribute(String key) {
        return session.getAttribute(key);
    }

    public void removeAttribute(String key) {
        session.removeAttribute(key);
    }

}

