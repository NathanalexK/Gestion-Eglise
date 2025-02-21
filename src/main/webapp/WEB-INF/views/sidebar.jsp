<%@ page import="org.example.fiangonana.webComponent.Sidebar" %>
<%@ page import="org.example.fiangonana.webComponent.Menu" %><%
    String current_url = ((String) request.getAttribute("jakarta.servlet.forward.request_uri"));
    Sidebar sidebar = new Sidebar("/assets/img/logo.png", "ESMI")
            .withLien("/")
            .addMenu(
                    new Menu("Comptes")
                            .withIcon("bx bx-spreadsheet")
                            .addSubmenu(
                                    new Menu("Saisie compte")
                            )
                            .addSubmenu(
                                    new Menu("Lister comptes")
                            )
            )
            .addMenu(
                    new Menu("Tresorerie")
                            .withIcon("bx bx-coin-stack")
                            .addSubmenu(
                                    new Menu("Saisie Operation Caisse ")
                                            .addSubmenu(
                                                    new Menu("Saisie par Ligne")
                                                            .withLien("/tresorerie/saisie-ligne")
                                            )
                                            .addSubmenu(
                                                    new Menu("Saisie Ensemble")
                                                            .withLien("/tresorerie/saisie-ensemble")
                                            )
                            )
                            .addSubmenu(
                                    new Menu("Lister Operation Caisse")
                                            .addSubmenu(
                                                    new Menu("Lister par Date")
                                                            .withLien("/tresorerie/liste/date")
                                            )
                                            .addSubmenu(
                                                    new Menu("Lister par mois")
                                            )
                                            .addSubmenu(
                                                    new Menu("Lister par annee")
                                            )
                            )
                            .addSubmenu(
                                    new Menu("Recapitulation")
                            )
            );


%>

<%=sidebar.getHtml("", current_url)%>