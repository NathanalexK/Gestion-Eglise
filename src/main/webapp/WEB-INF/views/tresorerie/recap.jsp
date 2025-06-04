<%@page pageEncoding="UTF-8" %>
<%@ page import="org.example.fiangonana.dto.tresorerie.MvtCaisseRecap" %>
<%@ page import="org.example.fiangonana.dto.tresorerie.MvtCaisseLigne" %>
<%@ page import="org.example.fiangonana.dto.tresorerie.MvtCaisseRecapLigne" %>
<%@ page import="org.example.fiangonana.util.NombreUtils" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="org.example.fiangonana.util.DateUtils" %>
<%@ page import="org.example.fiangonana.util.MoisIntervalleDate" %>
<%@ page import="java.util.List" %>
<%
    MvtCaisseRecap affichage = ((MvtCaisseRecap) request.getAttribute("affichage"));
    LocalDate dmin = affichage.getDateDebut();
    LocalDate dmax = affichage.getDateFin();
    List<MoisIntervalleDate> dates = DateUtils.get12DerniersMois(LocalDate.now().getMonthValue(), LocalDate.now().getYear());

    int num;
%>

<div class="alert alert-info d-flex align-items-center gap-2">
    <div>
        <img src="/assets/icons/info.png" width="40" alt="">
    </div>

    <div>
        <strong> FR: </strong> Cette page permet de voir le rapport de caisse entre 2 dates<br>
        <strong> MG: </strong> Ito pejy ito dia ahafahana mahita ny tatitra anelenalan'ny daty anaikiroa
    </div>
</div>

<form>
    <div class="d-flex justify-content-center mb-5">
        <div class="card w-90 card-style-1">
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

            <div class="card-footer">
                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-warning">Filtrer</button>
                </div>
            </div>


        </div>

    </div>
</form>

<div class="d-flex justify-content-center mt-5 mb-5">
    <div class="card w-90">
        <div class="card-header">
            Raccourcis
        </div>

        <div class="card-body">
            <ul class="d-flex gap-4" style="list-style: none">
                <%
                    for (MoisIntervalleDate moisIntervalleDate : dates) {
                %>
                <li>
                    <a href="/tresorerie/recap?dmin=<%=moisIntervalleDate.getDebutMois()%>&dmax=<%=moisIntervalleDate.getFinMois()%>"><%=moisIntervalleDate.getNom()%> <%=moisIntervalleDate.getAnnee()%>
                    </a>
                </li>

                <%
                    }
                %>


            </ul>


        </div>
    </div>
</div>

<div class="d-flex justify-content-center mb-5">
    <div class="card w-90">
        <div class="card-header green-gradient">
            <h5>Tableau Recapitulatif:  <%=DateUtils.affichageIntervalleDateFrancais(dmin, dmax)%></h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered">
                <tr>
                    <td>Total Entrées (vola niditra)</td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(affichage.getTotalEntrees())%> Ariary</td>
                </tr>

                <tr>
                    <td>Total Sorties (Vola nivoaka)</td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(affichage.getTotalSortie())%> Ariary</td>
                </tr>

                <tr>
                    <td>Solde du <%=affichage.getDateDebut() != null ? DateUtils.getFormatParDefaut(affichage.getDateDebut()) : ""%></td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(affichage.getSoldePrecedent())%> Ariary</td>
                </tr>

                <tr>
                    <td>Solde du <%=affichage.getDateFin() != null ? DateUtils.getFormatParDefaut(affichage.getDateFin()) : ""%></td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(affichage.getSoldeRestant())%> Ariary</td>
                </tr>

            </table>
        </div>
    </div>


</div>

<div class="d-flex justify-content-center mb-5">
    <div class="card w-90">
        <div class="card-header">
            <h5>Liste Revenu (Vola niditra)</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered" id="table1">
                <thead>
                <tr>
                    <th onclick="sortTable('table1', 0)">Numero</th>
                    <th>Libelle</th>
                    <th onclick="sortTable('table1', 2)">Total</th>
                    <th>Action</th>
                </tr>
                </thead>

                <tbody>
                <%
                    num = 0;
                    for (MvtCaisseRecapLigne ligne : affichage.getRecapEntrees()) {
                        num++;
                %>
                <tr>
                    <td><%=num%>
                    </td>
                    <td><%=ligne.getLibelle()%>
                    </td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(ligne.getTotal())%>
                    </td>
                    <td>
                        <a href="/tresorerie/recap/details?idGroupe=<%=ligne.getNumero()%>&dateMin=<%=affichage.getDateDebut()%>&dateMax=<%=affichage.getDateFin()%>&lib=<%=ligne.getLibOrigine()%>" class="action-icon">
                            <i class="bx bx-info-circle"></i>
                            <%--                            <%=ligne.getNumero()%>--%>
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

<div class="d-flex justify-content-center mb-5">
    <div class="card w-90">
        <div class="card-header">
            <h5>Liste Dépenses (Vola nivoaka)</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Numero</th>
                    <th>Libelle</th>
                    <th>Total</th>
                    <th>Action</th>

                </tr>
                </thead>

                <tbody>
                <%
                    num = 0;
                    for (MvtCaisseRecapLigne ligne : affichage.getRecapSortie()) {
                        num++;
                %>
                <tr>
                    <td><%=num%>
                    </td>
                    <td><%=ligne.getLibelle()%>
                    </td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(ligne.getTotal())%>
                    </td>
                    <td>
                        <a href="/tresorerie/recap/details?idGroupe=<%=ligne.getNumero()%>&dateMin=<%=affichage.getDateDebut()%>&dateMax=<%=affichage.getDateFin()%>&lib=<%=ligne.getLibOrigine()%>" class="action-icon">
                            <i class="bx bx-info-circle"></i>
                            <%--                            <%=ligne.getNumero()%>--%>
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


<div class="d-flex justify-content-center mb-5">
    <div class="card w-90">
        <div class="card-header">
            <h5>Exporter</h5>
        </div>

        <div class="card-body">
            <div class="d-flex" style="gap: 3rem">
                <div class="d-flex align-items-center">
                    <input type="radio" class="" value="pdf" checked name="filetype">
                    <img src="${pageContext.request.contextPath}/assets/icons/pdf.png" width="64">
                </div>

                <div class="d-flex align-items-center">
                    <input type="radio" value="csv" name="filetype">
                    <img src="${pageContext.request.contextPath}/assets/icons/excel.png" width="64">
                </div>

                <div class="d-flex align-items-center">
                    <button onclick="genererExcel(<%=dmin != null ? "'" + dmin + "'": "null"%>, <%=dmax != null ? "'" + dmax + "'": "null"%>)" class="btn btn-primary">Exporter</button>
                </div>

            </div>
        </div>
    </div>


</div>


<script>
    function genererExcel(dmin, dmax) {
        const data = {
            dateMin: dmin,
            dateMax: dmax
        }
        $.ajax({
            url: environment.apiUrl + '/api/csv/rapport',
            method: 'GET',
            data: data,
            xhrFields: {
                responseType: 'blob' // Important pour récupérer un fichier binaire
            },
            success: function(data, status, xhr) {
                // Crée un lien temporaire pour le téléchargement
                // const blob = new Blob([data], { type: 'application/pdf' });
                const link = document.createElement('a');
                link.href = window.URL.createObjectURL(data);
                link.download = 'tatitra.xlsx';
                link.click();
            },
            error: function() {
                alert('Erreur lors du téléchargement du Excel.');
            }
        });
    }
</script>