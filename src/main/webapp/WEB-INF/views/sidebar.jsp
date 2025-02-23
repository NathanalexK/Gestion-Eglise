<%@ page import="org.example.fiangonana.webComponent.Sidebar" %>
<%@ page import="org.example.fiangonana.webComponent.Menu" %>

<aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme sidebar" data-bg-class="bg-menu-theme" style="visibility: visible;">
    <div class="nav-header d-flex gap-1 border-bottom align-items-center" onclick="window.location.href = '/'">
        <img src="/assets/img/logo.png" alt="">

        <span class="nav-title text-center">EKAR SAINT MICHEL ITAOSY</span>
        
    </div>

    <div class="nav-list">
        <div class="nav-list-header">
            <i class="bx bx-spreadsheet"></i>
            <span>Comptes</span>
        </div>

        <ul class="nav-list-body">
            <li><a href="">Saisie compte</a></li>
            <li><a href="">Lister  comptes</a></li>
        </ul>
    </div>

    <div class="nav-list">
        <div class="nav-list-header">
            <i class="bx bx-coin-stack"></i>
            <span>Tresorerie</span>
        </div>

        <ul class="nav-list-body">
            <li><a href="${pageContext.request.contextPath}/tresorerie/saisie-ligne">Saisie Operation par ligne</a></li>
            <li><a href="${pageContext.request.contextPath}/tresorerie/saisie-ensemble">Saisie Operations ensemble</a></li>
            <li><a href="${pageContext.request.contextPath}/tresorerie/liste/date">Lister par date</a></li>
            <li><a href="${pageContext.request.contextPath}/tresorerie/recherche">Recherche</a></li>
            <li><a href="${pageContext.request.contextPath}/tresorerie/recap">Rapport</a></li>


        </ul>
    </div>


</aside>


<%--<%--%>
<%--    String current_url = ((String) request.getAttribute("jakarta.servlet.forward.request_uri"));--%>
<%--    Sidebar sidebar = new Sidebar("/assets/img/logo.png", "ESMI")--%>
<%--            .withLien("/")--%>
<%--            .addMenu(--%>
<%--                    new Menu("Comptes")--%>
<%--                            .withIcon("bx bx-spreadsheet")--%>
<%--                            .addSubmenu(--%>
<%--                                    new Menu("Saisie compte")--%>
<%--                            )--%>
<%--                            .addSubmenu(--%>
<%--                                    new Menu("Lister comptes")--%>
<%--                            )--%>
<%--            )--%>
<%--            .addMenu(--%>
<%--                    new Menu("Tresorerie")--%>
<%--                            .withIcon("bx bx-coin-stack")--%>
<%--                            .addSubmenu(--%>
<%--                                    new Menu("Saisie Operation Caisse ")--%>
<%--                                            .addSubmenu(--%>
<%--                                                    new Menu("Saisie par Ligne")--%>
<%--                                                            .withLien("/tresorerie/saisie-ligne")--%>
<%--                                            )--%>
<%--                                            .addSubmenu(--%>
<%--                                                    new Menu("Saisie Ensemble")--%>
<%--                                                            .withLien("/tresorerie/saisie-ensemble")--%>
<%--                                            )--%>
<%--                            )--%>
<%--                            .addSubmenu(--%>
<%--                                    new Menu("Lister Operation Caisse")--%>
<%--                                            .addSubmenu(--%>
<%--                                                    new Menu("Lister par Date")--%>
<%--                                                            .withLien("/tresorerie/liste/date")--%>
<%--                                            )--%>
<%--                                            .addSubmenu(--%>
<%--                                                    new Menu("Lister par mois")--%>
<%--                                            )--%>
<%--                                            .addSubmenu(--%>
<%--                                                    new Menu("Lister par annee")--%>
<%--                                            )--%>
<%--                            )--%>
<%--                            .addSubmenu(--%>
<%--                                    new Menu("Recapitulation")--%>
<%--                            )--%>
<%--            );--%>


<%--%>--%>

<%--<%=sidebar.getHtml("", current_url)%>--%>