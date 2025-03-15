<%@page pageEncoding="UTF-8" %>
<%@ page import="org.example.fiangonana.model.Budget" %>
<%@ page import="org.example.fiangonana.util.DateUtils" %>
<%@ page import="org.example.fiangonana.util.NombreUtils" %>
<%@ page import="java.time.LocalDate" %>
<%
    Budget budget = ((Budget) request.getAttribute("budget"));
%>
<form method="post">

<div class="d-flex justify-content-center">
    <div class="card w-50">
        <div class="card-header">
            <h5>Arreter un Budget</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <td>Libellé</td>
                    <td><%=budget.getLibelle()%></td>
                </tr>

                <tr>
                    <td>Date budget prévu</td>
                    <td>Du  <%=DateUtils.getFormatParDefaut(budget.getDateDebut()) %> au <%=DateUtils.getFormatParDefaut(budget.getDateFin())%></td>
                </tr>

                <tr>
                    <td>Montant initial du budget</td>
                    <td><%=NombreUtils.affichageMonetaire(budget.getMontant())%> Ar</td>
                </tr>

                </tbody>
            </table>

            <div class="mt-2 mb-2 px-3">
                    <input type="hidden" name="idBudget" value="<%=budget.getId()%>">
                <label> Date Arret </label>
                <input type="date" name="dateArret" class="form-control" value="<%=LocalDate.now()%>">
            </div>
        </div>

        <div class="card-footer">
            <div class="d-flex justify-content-end gap-2">
                <button onclick="history.back()" class="btn btn-basic">Retour</button>
                <button type="submit" class="btn btn-primary">Confirmer l'arrêt</button>
            </div>
        </div>
    </div>
</div>
</form>
