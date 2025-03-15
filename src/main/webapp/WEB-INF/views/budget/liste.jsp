<%@page pageEncoding="UTF-8" %>

<%@ page import="org.example.fiangonana.model.Budget" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.util.DateUtils" %>
<%@ page import="org.example.fiangonana.util.NombreUtils" %>
<%@ page import="org.example.fiangonana.model.immutable.VBudgetCpl" %>
<%@ page import="org.example.fiangonana.dto.budget.BudgetRecherche" %>
<%@ page import="java.util.Objects" %>
<%
    BudgetRecherche recherche = ((BudgetRecherche) request.getAttribute("budget[]"));
    List<VBudgetCpl> budgets = recherche.getBudgets();
%>

<form method="get">
    <div class="d-flex justify-content-center mb-5">
        <div class="card w-90">
            <div class="card-header">
                <h5>Recherche Budget</h5>
            </div>

            <div class="card-body">
                <div class="row">
                    <div class="col-lg-3">
                        <label>Date Min</label>
                        <input type="date" name="dateMin" class="form-control" value="<%=recherche.getDateMin() != null ? recherche.getDateMin() : ""%>">
                    </div>

                    <div class="col-lg-3">
                        <label>Date Max</label>
                        <input type="date" name="dateMax" class="form-control" value="<%=recherche.getDateMax() != null ? recherche.getDateMax() : ""%>">
                    </div>

                    <div class="col-lg-3">
                        <label>Libellé</label>
                        <input type="text" name="libelle" class="form-control" value="<%=recherche.getLibelle() != null ? recherche.getLibelle() : ""%>">
                    </div>

                    <div class="col-lg-3">
                        <label>Etat Budget</label>
                        <select name="isArret" class="form-select">
                            <option value="">Tous</option>
                            <option value="0" <%=recherche.getIsArret() != null && Objects.equals(recherche.getIsArret(), 0) ? "selected" : ""%>>Budget Arreté</option>
                            <option value="1" <%=recherche.getIsArret() != null && Objects.equals(recherche.getIsArret(), 1) ? "selected" : ""%>>Budget Non Arreté</option>

                        </select>
                    </div>


                </div>
            </div>

            <div class="card-footer">
                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-warning">Rechercher</button>
                </div>
            </div>

        </div>

    </div>
</form>


<div class="d-flex justify-content-center">
    <div class="card w-90">

        <div class="card-header">
            <h5>Liste des Budgets</h5>

        </div>


        <div class="card-body">
            <table class="table table-bordered table-altered">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Libelle</th>
                    <th>Montant</th>
                    <th>Date fin prévu</th>
                    <th>Reste</th>
                    <th>Arreté le</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (VBudgetCpl b : budgets) {
                %>
                <tr>
                    <td><%=DateUtils.getFormatParDefaut(b.getDateDebut())%>
                    </td>
                    <td><%=b.getLibelle()%>
                    </td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(b.getMontant())%>
                    </td>
                    <td><%=DateUtils.getFormatParDefaut(b.getDateFin())%>
                    </td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(b.getReste())%>
                    </td>
                    <td><%=DateUtils.getFormatParDefaut(b.getDateArret())%>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/budget/saisie?id=<%=b.getId()%>"
                           class="action-icon">
                            <i class="bx bx-pencil"></i>
                        </a>

                        <a href="${pageContext.request.contextPath}/budget/details?id=<%=b.getId()%>"
                           class="action-icon">
                            <i class="bx bx-info-circle"></i>
                        </a>

                    </td>
                </tr>

                <%
                    }
                %>

                </tbody>

            </table>
        </div>
    </div>


</div>