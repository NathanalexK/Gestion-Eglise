<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.dto.tresorerie.MvtCaisseRechercheGroupe" %>
<%@ page import="org.example.fiangonana.util.NombreUtils" %>
<%@ page import="java.math.BigDecimal" %>
<%@page pageEncoding="UTF-8" %>
<%
    List<Object[]> resultats = ((List<Object[]>) request.getAttribute("resultats"));
    MvtCaisseRechercheGroupe rg = (MvtCaisseRechercheGroupe) request.getAttribute("rg");
%>


<form action="${pageContext.request.contextPath}/tresorerie/recherche-groupe" method="GET">
    <div class="d-flex justify-content-center">
        <div class="card w-90 card-style-1">
            <div class="card-header">
                Paramètres de la Recherche
            </div>


            <div class="card-body">
                <div class="row">
                    <div class="col-lg-3">
                        <label>Date Min</label>
                        <input type="date" name="dateMin" class="form-control" value="<%=rg.getDateMin()%>">
                    </div>

                    <div class="col-lg-3">
                        <label>Date Max</label>
                        <input type="date" name="dateMax" class="form-control" value="<%=rg.getDateMax()%>">
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-3">
                        <label>Colonne Clé</label>
                        <select name="colonneCle" class="form-select" id="" data-selected="<%=rg.getColonneCle()%>">
                            <option value="m.date">Date</option>
                            <option value="to_char(date_trunc('week',m.date),'YYYY-MM-DD')">Semaine</option>
                            <option value="to_char(date_trunc('month',m.date),'YYYY-MM-DD')">Mois</option>
                            <option value="m.libelle">Libellé</option>
                            <option value="m.code">Numero de Compte</option>
                            <option value="c.libelle">Libellé Compte</option>
                        </select>
                    </div>

                    <div class="col-lg-3">
                        <label>Colonne Valeur</label>
                        <select class="form-select" name="colonneValeur" data-selected="<%=rg.getColonneValeur()%>">
                            <option value="m.entree - m.sortie">Montant</option>
                            <option value="abs(m.entree - m.sortie)">Montant Absolue</option>
                            <option value="m.entree">Entrée</option>
                            <option value="m.sortie">Sortie</option>
                        </select>
                    </div>

                    <div class="col-lg-3">
                        <label>Fonction</label>
                        <select class="form-select" name="fonction" data-selected="<%=rg.getFonction()%>">
                            <option value="SUM">Somme</option>
                            <option value="COUNT">Nombre</option>
                            <option value="AVG">Moyenne</option>
                        </select>
                    </div>

                    <div class="col-lg-3">
                        <label>Valeur Clé</label>
                        <input type="text" name="valeurCle" class="form-control" value="<%=rg.getValeurCle() != null ? rg.getValeurCle() : ""%>">
                    </div>
                </div>
            </div>

            <div class="card-footer">
                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-warning" name="applique" value="true">Appliquer</button>
                </div>
            </div>
        </div>

    </div>
</form>

<%
    if(rg.isApplique()) {
%>
<div class="d-flex justify-content-center mt-5">
    <div class="card w-90">
        <div class="card-header">
            Resultats
        </div>

        <div class="card-body">
            <div id="loading" class="d-flex justify-content-center py-2">
                 <div class="spinner-border" style="border-radius: 50% !important;"></div>
            </div>

            <table class="table table-bordered table-altered" id="table">
                <thead id="tableheader">
                <tr>
                    <th id="colonneCleHead"><%=rg.getColonneCle()%>
                    </th>
                    <th id="colonneValeurHead"><%=rg.getColonneValeur()%>
                    </th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (Object[] res : resultats) {
                %>
                <tr>
                    <td><%=res[0]%>
                    </td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(((BigDecimal) res[1]).doubleValue())%>
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
<%
    }
%>



<script>
    const table = $('#table');
    const loading = $('#loading');
    loading.css('display', 'none');
    table.css('display', 'none');
    $(document).ready(function (){
        // table.css('display', 'none');

        setTimeout(function () {
            const colonneCle = $('select[name=colonneCle]');
            const colonneValeur = $('select[name=colonneValeur]');

            const headerCle = colonneCle.find(':selected').html();
            const headerValeur = colonneValeur.find(':selected').html();

            $('#colonneCleHead').html(headerCle);
            $('#colonneValeurHead').html(headerValeur);
            loading[0].style.setProperty('display', 'none', 'important');
            table.css('display', 'table');

            // table.display = 'block';
        }, 200)
    })



</script>