package org.example.fiangonana.webComponent;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Menu {
    private String icon;
    private String title;
    private String lien;
    private List<Menu> submenus;
    private List<String> roles;
    private String alias;

    public Menu() {
        submenus = new ArrayList<>();
        roles = new ArrayList<>();
    }

    public Menu(String title) {
        this();
        this.setTitle(title);
    }

    public boolean canAccess(String myRole) {
        if(roles == null ) return true;
        if(roles.isEmpty()) return true;

//        UserRole myRole = utilisateur.getRole();
        for(String role : roles) {
            if(role.equalsIgnoreCase(myRole)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasSubmenu() {
        if(submenus == null) return true;
        return !submenus.isEmpty();
    }

    public Menu withIcon(String icon) {
        this.setIcon(icon);
        return this;
    }

    public Menu withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public Menu withLien(String lien) {
        this.setLien(lien);
        return this;
    }

    public Menu withSubmenus(Menu... menus) {
        List<Menu> submenus = List.of(menus);
//        for(Menu menu: menus) {
//            menu.setName(this.getName());
//        }
        this.setSubmenus(submenus);
        return this;
    }

    public Menu withRoles(String... roles) {
        List<String> rolesList = List.of(roles);
        this.setRoles(rolesList);
        return this;
    }

    public Menu withName(String name) {
//        this.setName(name);
        return this;
    }

    public Menu addSubmenu(Menu menu) {
//        menu.setName(name);
        this.getSubmenus().add(menu);
        return this;
    }

    public String getIcon() {
        if(icon == null) return "menu-icon tf-icons bx bx-layout";
        if(icon.equalsIgnoreCase("")) return "menu-icon tf-icons bx bx-layout";
        return icon;
    }

    public String getLien() {
        if(lien == null) return "javascript:void(0);";
        if(lien.isEmpty()) return "javascript:void(0);";
        return lien;
    }

    public String getTitle() {
        if(title == null) return "";
        return title;
    }

    public boolean hasIcon() {
        if(icon == null) return false;
        return !icon.isEmpty();
    }

    public boolean isActive(String currentUrl) {
        if (currentUrl.endsWith(this.getLien())) {
            return true;
        }
        if(!this.hasSubmenu()) {
            return false;
        }
        for(Menu menu: this.getSubmenus()) {
            if(menu.isActive(currentUrl)) {
                return true;
            }
        }

        return false;
    }

    public String getHtml(String currentUrl) {
        String html = "";
        html += "<li class=\"menu-item ";
        if(this.isActive(currentUrl)) {
            html += "active open";
        }
        html += "\">";
        html += "<a href=\"" + this.getLien() + "\" class=\"menu-link ";
        if(this.hasSubmenu()) {
            html += "menu-toggle";
        }
        html += "\">";
        if(this.hasIcon()) {
            html += "<i class=\"menu-icon tf-icon " + this.getIcon() + "\"></i>";
        }
        html += "<div>" + this.getTitle() + "</div>";
        html += "</a>";

        if(this.hasSubmenu()) {
            html += "<ul class=\"menu-sub\">";
            for(Menu submenu: this.getSubmenus()) {
                html += submenu.getHtml(currentUrl);
            }

            html += "</ul>";
        }
        html += "</li>";

        return html;
    }

    public String getHtml(String role, String currentUrl) {
        if(!this.canAccess(role)) return "";

        String html = "";
        html += "<li class=\"menu-item ";
        if(this.isActive(currentUrl)) {
            html += "active open";
        }
        html += "\">";
        html += "<a href=\"" + this.getLien() + "\" class=\"menu-link ";
        if(this.hasSubmenu()) {
            html += "menu-toggle";
        }
        html += "\">";
        if(this.hasIcon()) {
            html += "<i class=\"menu-icon tf-icon " + this.getIcon() + "\"></i>";
        }
        html += "<div>" + this.getTitle() + "</div>";
        html += "</a>";

        if(this.hasSubmenu()) {
            html += "<ul class=\"menu-sub\">";
            for(Menu submenu: this.getSubmenus()) {
                html += submenu.getHtml(role, currentUrl);
            }

            html += "</ul>";
        }
        html += "</li>";

        return html;
    }



//    public boolean hasSubmenu() {
//        return !this.submenus.isEmpty();
//    }
}
