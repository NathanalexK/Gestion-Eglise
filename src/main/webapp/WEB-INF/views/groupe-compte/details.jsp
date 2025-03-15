<%@ page import="org.example.fiangonana.model.GroupeCompteRecap" %>
<%@ page import="org.example.fiangonana.model.Code" %>
<%@page pageEncoding="UTF-8" %>

<%
    GroupeCompteRecap g = ((GroupeCompteRecap) request.getAttribute("g"));
%>

<div class="d-flex justify-content-center mb-5">
    <div class="card w-50">
        <div class="card-header">
            <h5>Details Groupe Compte de Rapport</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <td>ID</td>
                    <td><%=g.getId()%></td>
                </tr>

                <tr>
                    <td>Libellé</td>
                    <td><%=g.getLibelle()%></td>
                </tr>

                <tr>
                    <td>Type</td>
                    <td><%=g.getType().getLibelle()%></td>
                </tr>

                <tr>
                    <td>Identification Libellé Rapport</td>
                    <td><%=g.getIdentification()%></td>
                </tr>

                <tr>
                    <td>Description</td>
                    <td><%=g.getDescription()%></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="card-footer">
            <div class="d-flex justify-content-end gap-2">
                <button class="btn btn-basic" onclick="history.back()">Retour</button>
                <a href="${pageContext.request.contextPath}/groupe-compte/saisie?id=<%=g.getId()%>">
                    <button class="btn btn-warning"> Modifier </button>
                </a>
            </div>
        </div>
    </div>
</div>

<div class="d-flex justify-content-center">
    <div class="card w-90">
        <div class="card-header">
            <h5>Liste des Comptes du groupe</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered table-altered">
                <thead>
                <tr>
                    <th>Numero de Compte</th>
                    <th>Libellé</th>
                    <th>Categorie de Compte</th>
                    <th>Type de Compte</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for(Code code: g.getComptes()) {
                %>
                <tr>
                    <td><%=code.getCode()%></td>
                    <td><%=code.getLibelle()%></td>
                    <td><%=code.getCategorieLibelle()%></td>
                    <td><%=code.getTypeCompteLibelle()%></td>

                    <td>
                        <a href="${pageContext.request.contextPath}/compte/saisie?id=<%=code.getId()%>" class="action-icon"><i class="bx bx-pencil"></i></a>
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