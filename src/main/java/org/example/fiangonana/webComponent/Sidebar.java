package org.example.fiangonana.webComponent;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Sidebar {
    private String logo;
    private String titre;
    private String lien = "javascript:void(0);";
    private List<Menu> menus;

    public Sidebar() {
        this.menus = new ArrayList<>();
    }

    public Sidebar(String titre) {
        this();
        this.setTitre(titre);
    }

    public Sidebar(String logo, String titre) {
        this(titre);
        this.setLogo(logo);
    }

    public Sidebar addMenu(Menu menu) {
        this.getMenus().add(menu);
        return this;
    }

    public String getLogo() {
        if(logo == null) return "";
        return logo;
    }

    public String getTitre() {
        if(titre == null) return  "";
        return titre;
    }

    public String getHtml(String currentUrl) {
        String html = "";
        html += "<aside id=\"layout-menu\" class=\"layout-menu menu-vertical menu bg-menu-theme\">";
        html += "<div class=\"menu-inner-shadow\"></div>";
        html += "<ul class=\"menu-inner py-1\">";
        for(Menu menu: this.getMenus()) {
            html += menu.getHtml(currentUrl);
        }
        html += "</ul>";
        html += "</aside>";
        return html;
    }


    public String getHtml(String role, String currentUrl) {
        String html = "";
        html += "<aside id=\"layout-menu\" class=\"layout-menu menu-vertical menu bg-menu-theme\">";
        html += "<div class=\"app-brand demo \">\n" +
            "    <a href=\"" + this.getLien() + "\" class=\"app-brand-link\">\n" +
            "      <span class=\" \">\n" +
            "\n" +
            "<img style=\"border-radius: 50%\" src=\"" + this.getLogo() + "\" width=\"60px\" > </img>"+
            "\n" +
            "</span>\n" +
            "      <span class=\"fw-bold ms-2\">" + titre + "</span>\n" +
            "    </a>\n" +
            "\n" +
//            "    <a href=\"javascript:void(0);\" class=\"layout-menu-toggle menu-link text-large ms-auto\">\n" +
//            "      <i class=\"bx bx-chevron-left bx-sm d-flex align-items-center justify-content-center\"></i>\n" +
//            "    </a>\n" +
            "  </div>";
        html += "<div class=\"menu-inner-shadow\"></div>";
        html += "<ul class=\"menu-inner py-1\">";
        for(Menu menu: this.getMenus()) {
            html += menu.getHtml(role, currentUrl);
        }
        html += "</ul>";
        html += "</aside>";
        return html;
    }

    public Sidebar withLogo(String logo) {
        this.setLogo(logo);
        return this;
    }

    public Sidebar withTitle(String titre) {
        this.setTitre(titre);
        return this;
    }

    public Sidebar withLien(String lien) {
        this.setLien(lien);
        return this;
    }
}
