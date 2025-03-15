<%@page pageEncoding="UTF-8" %>
<%@ page import="org.example.fiangonana.model.CategorieCompte" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.model.TypeCompte" %>
<%
    CategorieCompte categ = ((CategorieCompte) request.getAttribute("cc"));
    List<TypeCompte> types = ((List<TypeCompte>) request.getAttribute("type[]"));
%>


<form action="${pageContext.request.contextPath}/categorie-compte/save" method="post">
    <div class="d-flex justify-content-center">
        <div class="card w-50">
            <div class="card-header">
                <h5>Saisie Categorie de Compte</h5>
            </div>

            <div class="card-body">
                <div class="mb-3">
                    <label>Libellé</label>
                    <input
                            type="text"
                            minlength="4"
                            name="libelle"
                            class="form-control"
                    >
                </div>

                <div class="mb-3">
                    <label>Type de Compte</label>
                    <select name="typeCompte" class="form-select" id="">
                        <%
                            for (TypeCompte typeCompte : types) {
                        %>
                        <option value="<%=typeCompte.getId()%>"><%=typeCompte.getLibelle()%></option>
                        <%
                            }
                        %>

                    </select>
                </div>

                <div class="mb-3">
                    <label>Description <small>(facultative)</small></label>
                    <textarea rows="3" name="description" class="form-control"></textarea>
                </div>

            </div>

            <div class="card-footer">
                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                </div>
            </div>
        </div>
    </div>
</form>