<%@page pageEncoding="UTF-8" %>

<%@ page import="org.example.fiangonana.model.GroupeCompteRecap" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.model.TypeCompte" %>
<%@ page import="java.util.Objects" %>
<%
    GroupeCompteRecap g = ((GroupeCompteRecap) request.getAttribute("g"));
    List<TypeCompte> types = ((List<TypeCompte>) request.getAttribute("type[]"));
%>

<div class="alert alert-info d-flex align-items-center gap-2">
    <div>
        <img src="/assets/icons/info.png" width="40" alt="">
    </div>

    <div>
        <strong> FR: </strong> Cette page permet de saisir un groupement de compte pour le rapport<br>
        <strong> MG: </strong> Ito pejy ito dia ahafahana mamorona sokajy ho an'ny tatitra
    </div>
</div>

<form action="${pageContext.request.contextPath}/groupe-compte/save" method="post">

<div class="d-flex justify-content-center">
    <div class="card w-50">
        <div class="card-header">
            <h5>Saisie Groupement de Compte Rapport</h5>
        </div>

        <div class="card-body">
            <input type="hidden" name="id" value="<%=g != null ? g.getId() : ""%>">

            <div class="mb-3">
                <label>libellé</label>

                <input
                        type="text"
                        class="form-control"
                        name="libelle"
                        value="<%=g != null ? g.getLibelle() : ""%>"
                        placeholder="ex: Karama"
                >
            </div>

            <div class="mb-3">
                <label>Type de Compte</label>
                <select name="type" class="form-control">
                    <%
                        for(TypeCompte t: types) {
                    %>
                    <option value="<%=t.getId()%>" <%=g != null && g.getType() != null && Objects.equals(g.getType().getId(), t.getId()) ? "selected" : "" %>><%=t.getLibelle()%></option>
                    <%
                        }
                    %>

                </select>
            </div>

            <div class="mb-3">
                <label>Identification <small>(Facultatif)</small></label>
                <input type="text" name="identification" value="<%=g != null ? g.getIdentification() : ""%>" class="form-control">
            </div>

            <div class="mb-3">
                <label>Description <small>(Facultatif)</small></label>
                <textarea name="description" class="form-control"  rows="3"></textarea>
            </div>
        </div>

        <div class="card-footer">
            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary"><%=g != null ? "Modifier" : "Enregistrer" %></button>
            </div>
        </div>

    </div>

</div>

</form>