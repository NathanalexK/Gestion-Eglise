<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.model.GroupeCompteRecap" %>
<%@page pageEncoding="UTF-8" %>

<%
    List<GroupeCompteRecap> groupes = ((List<GroupeCompteRecap>) request.getAttribute("g[]"));
%>

<div class="alert alert-info d-flex align-items-center gap-2">
    <div>
        <img src="/assets/icons/info.png" width="40" alt="">
    </div>

    <div>
        <strong> FR: </strong> Cette page permet de voir les rubriques de rapports<br>
        <strong> MG: </strong> Ito pejy ito dia ahafahana mahita ny sokajin'ny tatitra
    </div>
</div>

<div class="d-flex justify-content-center">
    <div class="card w-90">
        <div class="card-header">
            <h5>Liste des Groupe de Compte pour le Rapport</h5>
        </div>

        <div class="card-body">
            <table class="table-bordered table table-altered" id="datatable">
                <thead>
                <tr>
                    <th onclick="sortTable('datatable', 0)">ID</th>
                    <th onclick="sortTable('datatable', 1)">Libelle</th>
                    <th onclick="sortTable('datatable', 2)">Type de Groupe</th>
                    <th>Identification</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for(GroupeCompteRecap g: groupes) {
                %>
                <tr>
                    <td><%=g.getId()%></td>
                    <td><%=g.getLibelle()%></td>
                    <td><%=g.getTypeCompteLibelle()%></td>
                    <td><%=g.getIdentification()%></td>
                    <td>
                        <div class="d-flex gap-2">
                        <a href="${pageContext.request.contextPath}/groupe-compte/saisie?id=<%=g.getId()%>" class="action-icon"><i class="bx bx-pencil"></i></a>
                        <a href="${pageContext.request.contextPath}/groupe-compte/details?id=<%=g.getId()%>" class="action-icon"><i class="bx bx-info-circle"></i></a>
                        </div>
                    </td>

                <%
                    }
                %>
                </tbody>

            </table>
        </div>
    </div>
</div>