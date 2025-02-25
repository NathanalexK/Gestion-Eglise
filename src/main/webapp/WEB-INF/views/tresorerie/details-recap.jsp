<%@ page import="org.example.fiangonana.dto.tresorerie.DetailsRecapTresorerie" %>
<%@ page import="org.example.fiangonana.util.DateUtils" %>
<%@ page import="org.example.fiangonana.dto.tresorerie.MvtCaisseLigne" %>
<%@ page import="org.example.fiangonana.model.MvtCaisse" %>
<%@ page import="org.example.fiangonana.util.NombreUtils" %>
<%@page pageEncoding="UTF-8" %>

<%
    DetailsRecapTresorerie affichage = ((DetailsRecapTresorerie) request.getAttribute("affichage"));
%>

<div class="alert alert-info d-flex align-items-center gap-2">
    <div>
        <img src="/assets/icons/info.png" width="40" alt="">
    </div>

    <div>
        <strong> FR: </strong> Cette page permet consulter les détails et opérations dún rubrique <br>
        <strong> MG: </strong> Ito pejy ito dia ahafahana mijery ny mombamomba
    </div>
</div>

<div class="d-flex justify-content-center mt-5 mb-5">
    <div class="card w-90">
        <div class="card-header green-gradient">
            <h5>Tableau Recapitulatif</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <td>Total Entrée</td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(affichage.getTotalEntree())%> Ariary</td>
                </tr>

                <tr>
                    <td>Total Sortie</td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(affichage.getTotalSortie())%> Ariary</td>
                </tr>

                <tr>
                    <td>Total</td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(affichage.getTotal())%> Ariary</td>
                </tr>
                </tbody>
            </table>

        </div>

    </div>
</div>


<div class="d-flex justify-content-center mt-5">
    <div class="card w-90">
        <div class="card-header">
            <h5>Liste des mouvements caisses</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered" id="tri-table">
                <thead>
                <tr>
                    <%--                    <th>ID</th>--%>
                    <th onclick="sortTable('tri-table', 0)">Date</th>
                    <th onclick="sortTable('tri-table',1)">Numero Compte</th>
                    <th onclick="sortTable('tri-table',2)">Libelle</th>
                    <th onclick="sortTable('tri-table',3)">Entree</th>
                    <th onclick="sortTable('tri-table',4)">Sortie</th>
                    <%--                    <th onclick="sortTable('tri-table',5)">Soldes</th>--%>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (MvtCaisse mvtCaisse : affichage.getMvtCaisses()) {
                %>
                <tr>
                    <%--                    <td><%=mvtCaisse.getId()%></td>--%>
                    <td><%=DateUtils.getFormatParDefaut(mvtCaisse.getDate())%>
                    </td>
                    <td><%=mvtCaisse.getCode()%>
                    </td>
                    <td><%=mvtCaisse.getLibelle()%>
                    </td>
                    <td class="text-right"><%=mvtCaisse.getEntree()%>
                    </td>
                    <td class="text-right"><%=mvtCaisse.getSortie()%>
                    </td>

                    <td class="d-flex gap-3">
                        <%
                            if (mvtCaisse.getId() != null) {
                        %>
                        <a href="${pageContext.request.contextPath}/tresorerie/saisie-ligne?id=<%=mvtCaisse.getId()%>"
                           class="action-icon">
                            <i class="bx bx-pencil"></i>
                        </a>

                        <a href="${pageContext.request.contextPath}/tresorerie/supprimer?id=<%=mvtCaisse.getId()%>"
                           class="action-icon">
                            <i class="bx bx-trash"></i>
                        </a>
                        <%
                            }
                        %>

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