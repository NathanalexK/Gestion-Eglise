<%@page pageEncoding="UTF-8" %>

<%@ page import="org.example.fiangonana.model.Code" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.model.CategorieCompte" %><%
    Code code =((Code) request.getAttribute("code"));
    List<CategorieCompte> categories = ((List<CategorieCompte>) request.getAttribute("categories"));
%>


<div class="d-flex justify-content-center">
    <div class="card w-50">
        <div class="card-header">
            <h5>Saisie Compte</h5>
        </div>

        <div class="card-body">
            <div class="mb-3">
                <label>Numero de Compte</label>
                <input
                        type="text"
                        name="code"
                        minlength="3"
                        value="<%=code != null ? code.getCode() : ""%>"
                        placeholder="241"
                        class="form-control"
                >
            </div>

            <div class="mb-3">
                <label>libellé</label>
                <input
                        type="text"
                        name="libelle"
                        minlength="4"
                        placeholder="Ezaka samihafa"
                        class="form-control"
                        value="<%=code != null ? code.getLibelle() : ""%>"
                >
            </div>

            <div class="mb-3">
                <label>Categorie</label>
                <select name="categorieCompte" id="">
                    <%
                        for(CategorieCompte categorie: categories) {
                    %>
                    <option value="<%=categorie.getId()%>"><%=categorie.getLibelle()%></option>
                    <%
                        }
                    %>
                </select>

            </div>

            <div class="mb-3">
                <label> Description <small>facultatif</small></label>
                <textarea
                        name="description"
                        class="form-control"
                        rows="3">

                </textarea>
            </div>

        </div>

        <div class="card-footer">
            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary"><%=code != null ? "Modifier": "Enregistrer"%></button>
            </div>

        </div>

    </div>
</div>