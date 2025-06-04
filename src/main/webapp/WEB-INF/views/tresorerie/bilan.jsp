<%@ page import="org.example.fiangonana.dto.tresorerie.BilanTresorerie" %>
<%@ page import="org.example.fiangonana.util.NombreUtils" %>
<%@ page import="org.example.fiangonana.util.DateUtils" %>
<%@ page import="org.example.fiangonana.util.Mois" %>
<%@ page import="java.time.LocalDate" %>
<%@page pageEncoding="UTF-8" %>

<%
    BilanTresorerie bilan1 = ((BilanTresorerie) request.getAttribute("bilan1"));
    BilanTresorerie bilan2 = ((BilanTresorerie) request.getAttribute("bilan2"));
    BilanTresorerie bilan3 = ((BilanTresorerie) request.getAttribute("bilan3"));
    LocalDate date = ((LocalDate) request.getAttribute("date"));
%>


<form action="${pageContext.request.contextPath}/tresorerie/bilan" method="get">
<div class="card card-style-1">
    <div class="card-header">
        Recherche Bilan
    </div>

    <div class="card-body">
        <div class="row">
            <div class="col-lg-3">
                <label>Mois</label>
                <select class="form-select" name="mois" data-selected="<%=date.getMonthValue()%>">
                    <%
                        for(Mois mois: Mois.getAllMois()) {
                    %>
                    <option value="<%=mois.getIntVal()%>"><%=mois.getStrVal()%></option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="col-lg-3">
                <label>Année</label>
                <input type="number" name="annee"  class="form-control" value="<%=LocalDate.now().getYear()%>">
            </div>

        </div>

    </div>

    <div class="card-footer">
        <div class="d-flex justify-content-end">
            <button class="btn btn-warning" type="submit">Voir Bilan</button>
        </div>
    </div>
</div>
</form>


<div class="card mt-3">
    <div class="card-header d-flex justify-content-between">
        <span>
             Bilan du: <%=DateUtils.convertirMoisEnString(bilan1.getMois()) %> <%=bilan1.getAnnee()%>
        </span>

        <span class="d-flex gap-2">
            <a class="lien-header" href="${pageContext.request.contextPath}/tresorerie/liste/date?dmin=<%=bilan1.getIntervalleDates()[0]%>&dmax=<%=bilan1.getIntervalleDates()[1]%>">Voir Details</a>
            /
            <a class="lien-header" href="${pageContext.request.contextPath}/tresorerie/recap?dmin=<%=bilan1.getIntervalleDates()[0]%>&dmax=<%=bilan1.getIntervalleDates()[1]%>">Voir Rapport</a>
        </span>
    </div>
    <div class="card-body p-3">
        <div class="row">
            <div class="col-lg-6">
                Solde Precedente:
            </div>

            <div class="col-lg-6 text-right">
                <%=NombreUtils.affichageMonetaire(bilan1.getSoldePrecedent())%>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-6">
                Total Entrées:
            </div>

            <div class="col-lg-6 text-right">
                <%=NombreUtils.affichageMonetaire(bilan1.getTotalEntree())%>
            </div>

        </div>

        <div class="row">
            <div class="col-lg-6">
                Total sorties
            </div>

            <div class="col-lg-6 text-right">
                <%=NombreUtils.affichageMonetaire(bilan1.getTotalSortie())%>
            </div>
        </div>

        <hr>

        <div class="row">
            <div class="col-lg-6">
                Solde
            </div>

            <div class="col-lg-6 text-right fw-bold">
                <%=NombreUtils.affichageMonetaire(bilan1.getSoldeActuel())%>
            </div>
        </div>
    </div>
</div>

<div class="row mt-3">
    <div class="col-lg-6">
        <div class="card card-style-1">
            <div class="card-header d-flex justify-content-between">
                <span>
                Bilan du: <%=DateUtils.convertirMoisEnString(bilan2.getMois()) %> <%=bilan2.getAnnee()%>
                    </span>

                <span class="d-flex gap-2">
            <a class="lien-header" href="${pageContext.request.contextPath}/tresorerie/liste/date?dmin=<%=bilan2.getIntervalleDates()[0]%>&dmax=<%=bilan2.getIntervalleDates()[1]%>">Voir Details</a>
            /
            <a class="lien-header" href="${pageContext.request.contextPath}/tresorerie/recap?dmin=<%=bilan2.getIntervalleDates()[0]%>&dmax=<%=bilan2.getIntervalleDates()[1]%>">Voir Rapport</a>
        </span>
            </div>
            <div class="card-body p-3">
                <div class="row">
                    <div class="col-lg-6">
                        Solde Precedente:
                    </div>

                    <div class="col-lg-6 text-right">
                        <%=NombreUtils.affichageMonetaire(bilan2.getSoldePrecedent())%>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-6">
                        Total Entrées:
                    </div>

                    <div class="col-lg-6 text-right">
                        <%=NombreUtils.affichageMonetaire(bilan2.getTotalEntree())%>
                    </div>

                </div>

                <div class="row">
                    <div class="col-lg-6">
                        Total sorties
                    </div>

                    <div class="col-lg-6 text-right">
                        <%=NombreUtils.affichageMonetaire(bilan2.getTotalSortie())%>
                    </div>
                </div>

                <hr>

                <div class="row">
                    <div class="col-lg-6">
                        Solde
                    </div>

                    <div class="col-lg-6 text-right">
                        <%=NombreUtils.affichageMonetaire(bilan2.getSoldeActuel())%>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div class="card card-style-1">
            <div class="card-header d-flex justify-content-between">
                <span>
                Bilan du: <%=DateUtils.convertirMoisEnString(bilan3.getMois()) %> <%=bilan3.getAnnee()%>
                    </span>

                <span class="d-flex gap-2">
            <a class="lien-header" href="${pageContext.request.contextPath}/tresorerie/liste/date?dmin=<%=bilan3.getIntervalleDates()[0]%>&dmax=<%=bilan3.getIntervalleDates()[1]%>">Voir Details</a>
            /
            <a class="lien-header" href="${pageContext.request.contextPath}/tresorerie/recap?dmin=<%=bilan3.getIntervalleDates()[0]%>&dmax=<%=bilan3.getIntervalleDates()[1]%>">Voir Rapport</a>
        </span>
            </div>
            <div class="card-body p-3">
                <div class="row">
                    <div class="col-lg-6">
                        Solde Precedente:
                    </div>

                    <div class="col-lg-6 text-right">
                        <%=NombreUtils.affichageMonetaire(bilan3.getSoldePrecedent())%>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-6">
                        Total Entrées:
                    </div>

                    <div class="col-lg-6 text-right">
                        <%=NombreUtils.affichageMonetaire(bilan3.getTotalEntree())%>
                    </div>

                </div>

                <div class="row">
                    <div class="col-lg-6">
                        Total sorties
                    </div>

                    <div class="col-lg-6 text-right">
                        <%=NombreUtils.affichageMonetaire(bilan3.getTotalSortie())%>
                    </div>
                </div>

                <hr>

                <div class="row">
                    <div class="col-lg-6">
                        Solde
                    </div>

                    <div class="col-lg-6 text-right">
                        <%=NombreUtils.affichageMonetaire(bilan3.getSoldeActuel())%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="row mt-3">
    <%--    <div class="row">--%>
    <div class="col-12">
        <div class="card card-style-1">
            <div class="card-header green-gradient">
                Graphique
            </div>
            <div class="card-body p-3">
                <canvas id="canvas"></canvas>
            </div>
        </div>


    </div>

    <%--    </div>--%>

</div>

<script>
    const data = {
        labels: ['<%=bilan3.getLibelle()%>', '<%=bilan2.getLibelle()%>', '<%=bilan1.getLibelle()%>'],
        datasets: [{
            label: 'Entrée',
            data: ['<%=bilan3.getTotalEntree()%>', '<%=bilan2.getTotalEntree()%>', '<%=bilan1.getTotalEntree()%>'],
            backgroundColor: [
                // 'rgba(255, 99, 132, 0.2)',
                // 'rgba(255, 159, 64, 0.2)',
                // 'rgba(255, 205, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                // 'rgba(54, 162, 235, 0.2)',
                // 'rgba(153, 102, 255, 0.2)',
                // 'rgba(201, 203, 207, 0.2)'
            ],
            borderColor: [
                // 'rgb(255, 99, 132)',
                // 'rgb(255, 159, 64)',
                // 'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                // 'rgb(54, 162, 235)',
                // 'rgb(153, 102, 255)',
                // 'rgb(201, 203, 207)'
            ],
            borderWidth: 1
        }, {
            label: 'Sortie',
            data: ['<%=bilan3.getTotalSortie()%>', '<%=bilan2.getTotalSortie()%>', '<%=bilan1.getTotalSortie()%>'],
            borderColor: ['rgb(255, 99, 132)'],
            backgroundColor: ['rgba(255, 99, 132, 0.2)']
        }]
    };
    const conf = {
        type: 'bar',
        data: data,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        },
    };

    // const labels = Utils.months({count: 7});


    new Chart(document.getElementById('canvas'), conf)
</script>