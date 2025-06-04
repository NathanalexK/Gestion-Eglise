<%@page pageEncoding="UTF-8" %>

<%@ page import="org.example.fiangonana.model.Code" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.model.CategorieCompte" %>
<%@ page import="org.example.fiangonana.model.GroupeCompteRecap" %>
<%@ page import="java.util.Objects" %>
<%@ page import="org.example.fiangonana.model.TypeCompte" %>
<%
    Code code =((Code) request.getAttribute("code"));
//    List<CategorieCompte> categories = ((List<CategorieCompte>) request.getAttribute("categories"));
    List<TypeCompte> typeComptes = ((List<TypeCompte>) request.getAttribute("type[]"));
//    List<GroupeCompteRecap> groupes = ((List<GroupeCompteRecap>) request.getAttribute("g[]"));
%>

<div class="alert alert-info d-flex align-items-center gap-2">
    <div>
        <img src="/assets/icons/info.png" width="40" alt="">
    </div>

    <div>
        <strong> FR: </strong> Cette page permet de saisir un nouveau compte<br>
        <strong> MG: </strong> Ito pejy ito dia ahafahana mamorona kaonty vaovao
    </div>
</div>


<form action="/compte/save" method="post">
<div class="d-flex justify-content-center">
    <div class="card w-50">
        <div class="card-header">
            <h5>Saisie Compte</h5>
        </div>

        <div class="card-body">
            <input type="hidden" name="id" value="<%=code != null ? code.getId() : ""%>">

            <div class="mb-3">
                <label>Numero de Compte</label>
                <input
                        type="text"
                        name="code"
                        minlength="3"
                        value="<%=code != null ? code.getCode() : ""%>"
                        placeholder="2411"
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
                <select name="categorieCompte" id="" class="form-select">
                    <%
                        for(TypeCompte t: typeComptes) {
                    %>
                    <optgroup label="<%=t.getLibelle()%>">
                        <%
                            for (CategorieCompte categorie: t.getCategorieComptes()) {
                        %>

                            <option value="<%=categorie.getId()%>" <%=code != null && code.getCategorieCompte() != null && Objects.equals(code.getCategorieCompte().getId(), categorie.getId()) ? "selected" : "" %>><%=categorie.getLibelle()%></option>
                        <%
                            }
                        %>


                    </optgroup>
<%--                    <option value="<%=categorie.getId()%>"><%=categorie.getLibelle()%></option>--%>
                    <%
                        }
                    %>
                </select>

            </div>

            <div class="mb-3">
                <label>Groupement Rapport: </label>
                <select name="groupeCompteRecap" class="form-select">
                    <%
                        for(TypeCompte t: typeComptes) {
                    %>
                    <optgroup label="<%=t.getLibelle()%>">
                        <%
                            for(GroupeCompteRecap g: t.getGroupeCompteRecaps()) {
                        %>
                        <option value="<%=g.getId()%>" <%=code != null && code.getGroupeCompteRecap() != null && Objects.equals(code.getGroupeCompteRecap().getId(), g.getId())? "selected" : ""%>><%=g.getLibelle()%></option>

                        <%
                            }
                        %>


                    </optgroup>


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
</form>