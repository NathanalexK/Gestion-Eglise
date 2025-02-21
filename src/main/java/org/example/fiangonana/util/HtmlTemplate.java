package org.example.fiangonana.util;

import java.util.List;

public class HtmlTemplate {

    public static String buildList(Object obj) {
        String html = "<ul>";
        if(obj instanceof List list) {
            for (Object item : list) {
                html += "<li>" + String.valueOf(item) + "</li>";
            }
        } else {
            html += "<li>" + String.valueOf(obj) + "</li>";
        }
        html += "</ul>";
        return html;
    }

}
