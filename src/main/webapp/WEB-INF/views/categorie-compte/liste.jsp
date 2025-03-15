<%@page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.model.CategorieCompte" %><%
    List<CategorieCompte> categorieComptes = ((List<CategorieCompte>) request.getAttribute("cc[]"));
%>

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