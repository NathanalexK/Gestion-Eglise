<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.dto.tresorerie.MvtCaisseLigneDTO" %>
<%@ page import="org.example.fiangonana.model.MvtCaisse" %>
<%@page pageEncoding="UTF-8" %>
<%
    List<MvtCaisseLigneDTO> mvtCaisses = (List<MvtCaisseLigneDTO>) request.getAttribute("mvtCaisse[]");
    mvtCaisses.forEach(System.out::println);
%>

<form>
    <div class="d-flex justify-content-center">
        <div class="card w-75">
            <div class="card-header">
                <h5>Filtre Recherche</h5>
            </div>

            <div class="card-body">
                <div class="row">
                    <div class="col-lg-6">
                        <label>Date debut</label>
                        <input type="date" class="form-control" name="dmin">

                    </div>

                    <div class="col-lg-6">
                        <label>Date fin</label>
                        <input type="date" class="form-control" name="dmax">

                    </div>
                </div>
            </div>

            <div class="card-footer p-3">
                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-warning">Filtrer</button>
                </div>
            </div>


        </div>

    </div>
</form>


<div class="d-flex justify-content-center mt-5">
    <div class="card w-90">
        <div class="card-header">
            <h5>Liste des mouvements caisses</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <%--                    <th>ID</th>--%>
                    <th>Date</th>
                    <th>Numero Compte</th>
                    <th>Libelle</th>
                    <th>Entree</th>
                    <th>Sortie</th>
                    <th>Soldes</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (MvtCaisseLigneDTO mvtCaisse : mvtCaisses) {
                %>
                <tr>
                    <%--                    <td><%=mvtCaisse.getId()%></td>--%>
                    <td><%=mvtCaisse.getDate()%>
                    </td>
                    <td><%=mvtCaisse.getCode()%>
                    </td>
                    <td><%=mvtCaisse.getLibelle()%>
                    </td>
                    <td class="text-right"><%=mvtCaisse.getEntree()%>
                    </td>
                    <td class="text-right"><%=mvtCaisse.getSortie()%>
                    </td>
                    <td class="text-right"><%=mvtCaisse.getSoldes()%>
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

<div class="d-flex justify-content-center mt-4">
    <div class="card w-90">
        <div class="card-header">
            <h5>Expoter</h5>
        </div>

        <div class="card-body">
            <div class="d-flex" style="gap: 3rem">
                <div class="d-flex align-items-center">
                    <input type="radio" class="" value="pdf" checked name="filetype">
                    <img src="/assets/icons/pdf.png" width="64">
                </div>

                <div class="d-flex align-items-center">
                    <input type="radio" value="csv" name="filetype">
                    <img src="/assets/icons/excel.png" width="64">
                </div>

                <div class="d-flex align-items-center">
                    <button type="submit" class="btn btn-primary">Exporter</button>
                </div>

            </div>
        </div>

        <%--        <div class="card-footer">--%>
        <%--            <div class="d-flex justify-content-end">--%>
        <%--                <button type="submit" class="btn btn-primary">Exporter</button>--%>
        <%--            </div>--%>
        <%--        </div>--%>
    </div>


</div>