<%@page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.model.CategorieCompte" %><%
    List<CategorieCompte> categorieComptes = ((List<CategorieCompte>) request.getAttribute("cc[]"));
%>

<div class="alert alert-info d-flex align-items-center gap-2">
    <div>
        <img src="/assets/icons/info.png" width="40" alt="">
    </div>

    <div>
        <strong> FR: </strong> Cette page permet de categoriser les comptes pour faciliter l'affichage des listes de comptes<br>
        <strong> MG: </strong> Ito pejy ito dia ahafahana manasokajy ny kaonty mba anamorana ny fijerana ao amin'ny lisitry ny kaonty
    </div>
</div>

<div class="d-flex justify-content-center">
    <div class="card w-90">
        <div class="card-header">
            <h5>Liste des categories de comptes</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Libellé</th>
                    <th>Type de Compte</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for(CategorieCompte c: categorieComptes) {
                %>
                <tr>
                    <td><%=c.getId()%></td>
                    <td><%=c.getLibelle()%></td>
                    <td><%=c.getTypeCompteLibelle()%></td>
                    <td>
                        <a href="" class="action-icon">
                            <i class="bx bx-info-circle"></i>
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