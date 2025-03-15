<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.model.Code" %>
<%@ page import="org.example.fiangonana.dto.compte.CompteRecherche" %>
<%@ page import="org.example.fiangonana.model.TypeCompte" %>
<%@ page import="org.example.fiangonana.model.CategorieCompte" %>
<%@ page import="java.util.Objects" %>
<%@page pageEncoding="UTF-8" %>

<%
    CompteRecherche recherche = ((CompteRecherche) request.getAttribute("recherche"));
    List<Code> codes = recherche.getCodes();
    List<TypeCompte> types = ((List<TypeCompte>) request.getAttribute("type[]"));
%>

<form method="get">
<div class="d-flex justify-content-center mb-5">
    <div class="card w-90">
        <div class="card-header">
            <h5>Recherche Compte</h5>
        </div>

        <div class="card-body">
            <div class="row">
                <div class="col-lg-3">
                    <label>Numero de Compte</label>
                    <input type="text" class="form-control" name="code" value="<%=recherche.getCode() != null ? recherche.getCode() : ""%>">
                </div>
                <div class="col-lg-3">
                    <label>Libellé</label>
                    <input type="text" class="form-control" name="libelle" value="<%=recherche.getLibelle() != null ? recherche.getLibelle() : ""%>">
                </div>


                <div class="col-lg-3">
                    <label>Type Compte</label>
                    <select class="form-select" name="type">
                        <option value="">Tous</option>
                        <%
                            for(TypeCompte t: types) {
                        %>
                        <option value="<%=t.getId()%>" <%=recherche.getType() != null && Objects.equals(recherche.getType().getId(), t.getId()) ? "selected" : ""%>><%=t.getLibelle()%></option>
                        <%
                            }
                        %>

                    </select>
                </div>

                <div class="col-lg-3">
                    <label>Categorie Compte</label>
                    <select class="form-select" name="categorie">
                        <option value="">Tous</option>
                        <%
                            for(TypeCompte t: types) {
                        %>
                        <optgroup label="<%=t.getLibelle()%>">
                            <%
                                for(CategorieCompte c: t.getCategorieComptes()) {
                            %>
                            <option value="<%=c.getId()%>" <%=recherche.getCategorie() != null && Objects.equals(recherche.getCategorie().getId(), c.getId()) ? "selected" : ""%>><%=c.getLibelle()%></option>

                            <%
                                }
                            %>


                        </optgroup>
                        <%
                            }
                        %>

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
            <h5>Liste des Comptes</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered table-altered" id="datatable">
                <thead>
                <tr>
                    <th onclick="sortTable('datatable', 0)">Numero de Compte</th>
                    <th onclick="sortTable('datatable', 1)">Libellé</th>
                    <th onclick="sortTable('datatable', 2)">Categorie de Compte</th>
                    <th onclick="sortTable('datatable', 3)">Type de Compte</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for(Code code: codes) {
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