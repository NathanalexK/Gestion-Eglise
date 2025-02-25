package org.example.fiangonana.util;

import jakarta.servlet.http.HttpServletRequest;

public class WebUtils {

    public static String getUrlPrecedente(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        return referer != null ? referer : "/";
    }
}
