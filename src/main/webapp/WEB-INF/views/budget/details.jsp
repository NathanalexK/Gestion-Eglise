<%@page pageEncoding="UTF-8" %>

<%@ page import="org.example.fiangonana.model.Budget" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.model.MvtCaisse" %>
<%@ page import="org.example.fiangonana.util.DateUtils" %>
<%@ page import="org.example.fiangonana.util.NombreUtils" %>
<%@ page import="org.example.fiangonana.model.immutable.VBudgetCpl" %>
<%
    VBudgetCpl budget = ((VBudgetCpl) request.getAttribute("budget"));
    List<MvtCaisse> mvtCaisses = ((List<MvtCaisse>) request.getAttribute("mc[]"));
%>

<div class="d-flex justify-content-center mb-5">
    <div class="card w-50">
        <div class="card-header">
            <h5>Details Budget</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <td>ID</td>
                    <td><%=budget.getId()%></td>
                </tr>
                <tr>
                    <td>Libellé</td>
                    <td><%=budget.getLibelle()%></td>
                </tr>
                <tr>
                    <td>Date </td>
                    <td><%=DateUtils.getFormatParDefaut(budget.getDateDebut())%></td>
                </tr>
                <tr>
                    <td>Date Fin prévu</td>
                    <td><%=DateUtils.getFormatParDefaut(budget.getDateFin())%></td>
                </tr>
                <tr>
                    <td>Montant Initial</td>
                    <td><%=NombreUtils.affichageMonetaire(budget.getMontant())%> Ar</td>
                </tr>

                <tr>
                    <td>Montant Utilisé</td>
                    <td><%=NombreUtils.affichageMonetaire(budget.getMontantUse())%> Ar</td>
                </tr>
                <tr>
                    <td>Montant Restant</td>
                    <td><%=NombreUtils.affichageMonetaire(budget.getReste())%> Ar</td>
                </tr>

                <tr>
                    <td>Arreté le</td>
                    <td><%=budget.getDateArret() != null ? DateUtils.getFormatParDefaut(budget.getDateArret()) : "Pas encore arreté"%></td>
                </tr>
                <tr>
                    <td>Observation</td>
                    <td><%=budget.getDescription()%></td>
                </tr>

                </tbody>
            </table>

        </div>

        <div class="card-footer">
            <div class="d-flex justify-content-end gap-2">
                <button class="btn btn-basic" onclick="history.back()">Retour</button>
                <a href="${pageContext.request.contextPath}/budget/saisie?id=<%=budget.getId()%>">
                    <button class="btn btn-warning">Modifier</button>
                </a>
                <%
                    if(!budget.estArrete()) {
                %>
                <a href="${pageContext.request.contextPath}/budget/arret?id=<%=budget.getId()%>">
                <button class="btn btn-primary">Arreter</button>
                </a>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>

<div class="d-flex justify-content-center">
    <div class="card w-90">
        <div class="card-header">
            <h5>
                 Liste des Dépenses
            </h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered table-altered">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Numero Compte</th>
                    <th>Libellé</th>
                    <th>Montant</th>
                    <th>Observation</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for(MvtCaisse m: mvtCaisses) {
                %>
                <tr>
                    <td><%=DateUtils.getFormatParDefaut(m.getDate())%></td>
                    <td><%=m.getCompte().getCode()%></td>
                    <td><%=m.getLibelleFormu()%></td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(m.getSortie())%></td>
                    <td><%=m.getObservation()%></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/tresorerie/saisie-ligne?id=<%=m.getId()%>" class="action-icon">
                            <i class="bx bx-pencil"></i>
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