<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.dto.tresorerie.MvtCaisseLigneDTO" %>
<%@ page import="org.example.fiangonana.model.MvtCaisse" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="org.example.fiangonana.util.DateUtils" %>
<%@page pageEncoding="UTF-8" %>
<%
    List<MvtCaisseLigneDTO> mvtCaisses = (List<MvtCaisseLigneDTO>) request.getAttribute("mvtCaisse[]");
    LocalDate dmin = ((LocalDate) request.getAttribute("dmin"));
    LocalDate dmax = ((LocalDate) request.getAttribute("dmax"));
    mvtCaisses.forEach(System.out::println);
%>

<form>
    <div class="d-flex justify-content-center">
        <div class="card w-90">
            <div class="card-header">
                <h5>Filtre Recherche</h5>
            </div>

            <div class="card-body">
                <div class="row">
                    <div class="col-lg-6">
                        <label>Date debut</label>
                        <input type="date" class="form-control" name="dmin" value="<%=dmin != null ? dmin : ""%>">

                    </div>

                    <div class="col-lg-6">
                        <label>Date fin</label>
                        <input type="date" class="form-control" name="dmax" value="<%=dmax != null ? dmax : ""%>">

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
            <table class="table table-bordered" id="tri-table">
                <thead>
                <tr>
                    <%--                    <th>ID</th>--%>
                    <th onclick="sortTable(0)">Date</th>
                    <th onclick="sortTable(1)">Numero Compte</th>
                    <th onclick="sortTable(2)">Libelle</th>
                    <th onclick="sortTable(3)">Entree</th>
                    <th onclick="sortTable(4)">Sortie</th>
                    <th onclick="sortTable(5)">Soldes</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (MvtCaisseLigneDTO mvtCaisse : mvtCaisses) {
                %>
                <tr>
                    <%--                    <td><%=mvtCaisse.getId()%></td>--%>
                    <td><%=DateUtils.getFormatParDefaut(mvtCaisse.getDate())%>
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

<div class="d-flex justify-content-center mt-5">
    <div class="card w-90">
        <div class="card-header">
            <h5>Exporter</h5>
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
                    <button onclick="genererPDF(<%=dmin != null ? "'" + dmin + "'": "null"%>, <%=dmax != null ? "'" + dmax + "'": "null"%>)" class="btn btn-primary">Exporter</button>
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

<script>
    function genererPDF(dmin, dmax) {
        const data = {
            dateMin: dmin,
            dateMax: dmax
        }
        $.ajax({
            url: environment.apiUrl + '/api/pdf/tresorerie/date',
            method: 'GET',
            data: data,
            xhrFields: {
                responseType: 'blob' // Important pour récupérer un fichier binaire
            },
            success: function(data, status, xhr) {
                // Crée un lien temporaire pour le téléchargement
                const blob = new Blob([data], { type: 'application/pdf' });
                const link = document.createElement('a');
                link.href = window.URL.createObjectURL(blob);
                link.download = 'toe-bola.pdf';
                link.click();
            },
            error: function() {
                alert('Erreur lors du téléchargement du PDF.');
            }
        });
    }

</script>