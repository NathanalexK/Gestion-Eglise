<%@ page import="org.example.fiangonana.model.MvtCaisse" %>
<%@ page import="org.example.fiangonana.dto.tresorerie.MvtCaisseRechercheAffichage" %>
<%@ page import="org.example.fiangonana.util.DateUtils" %>
<%@ page import="org.example.fiangonana.util.NombreUtils" %>
<%@ page import="components.table.HtmlPagination" %>
<%@page pageEncoding="UTF-8" %>

<%
    MvtCaisseRechercheAffichage affichage = ((MvtCaisseRechercheAffichage) request.getAttribute("affichage"));
    Integer numeroPage = affichage.getPageNavigation().getNumeroPage();
    Integer totalPage = affichage.getPageNavigation().getTotalPage();
//    HtmlPagination pagination = new HtmlPagination(affichage.getPageNavigation().getTaillePage(), affichage.getPageNavigation().getTotalPage());
%>

<div class="alert alert-info d-flex align-items-center gap-2">
    <div>
        <img src="/assets/icons/info.png" width="40" alt="">
    </div>

    <div>
        <strong> FR: </strong> Cette page permet de rechercher la liste des operations dans la tresorerie<br>
        <strong> MG: </strong> Ito pejy ito dia ahafahana maitady ny vola miditra sy mivoaka (toe-bolan'ny fiangonana)
    </div>
</div>

<form action="" method="GET">
    <div class="d-flex justify-content-center mb-5">
        <div class="card w-90">
            <div class="card-header">
                <h5>Filtre recherche</h5>
            </div>

            <div class="card-body">
                <div class="row">
                    <div class="col-lg-3">
                        <label>Date Min</label>
                        <input type="date" class="form-control" name="dateMin"
                               value="<%=affichage.getDateMin() != null ? affichage.getDateMin() : ""%>">
                    </div>

                    <div class="col-lg-3">
                        <label> Date Max</label>
                        <input type="date" class="form-control" name="dateMax"
                               value="<%=affichage.getDateMax() != null ? affichage.getDateMax() : ""%>">
                    </div>

                    <div class="col-lg-3">
                        <label> Categorie</label>
                        <select class="form-select" disabled>
                            <option value=""> -- Selectionner --</option>
                        </select>
                    </div>

                    <div class="col-lg-3">
                        <label> Numero Compte</label>
                        <input type="text" class="form-control" name="numeroCompte"
                               value="<%=affichage.getNumeroCompte() != null ? affichage.getNumeroCompte() : ""%>"
                               placeholder="ex: 4111">
                    </div>

                    <div class="col-lg-3">
                        <label>Entrée Min</label>
                        <input type="number" step="0.01" class="form-control" name="entreeMin"
                               value="<%=affichage.getEntreeMin() != null ? affichage.getEntreeMin() : ""%>">
                    </div>


                    <div class="col-lg-3">
                        <label>Entrée Max</label>
                        <input type="number" step="0.01" class="form-control" name="entreeMax"
                               value="<%=affichage.getEntreeMax() != null ? affichage.getEntreeMax() : ""%>">
                    </div>


                    <div class="col-lg-3">
                        <label>Sorite Min</label>
                        <input type="number" step="0.01" class="form-control" name="sortieMin"
                               value="<%=affichage.getSortieMin() != null ? affichage.getSortieMin() : ""%>">
                    </div>


                    <div class="col-lg-3">
                        <label>Sortie Max</label>
                        <input type="number" step="0.01" class="form-control" name="sortieMax"
                               value="<%=affichage.getSortieMax() != null ? affichage.getSortieMax() : ""%>">
                    </div>


                    <div class="col-lg-3">
                        <label>Libellé</label>
                        <input type="text" class="form-control" name="libelle"
                               value="<%=affichage.getLibelle() != null ? affichage.getLibelle() : ""%>">
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

<div class="d-flex justify-content-center mt-5 mb-5">
    <div class="card w-90">
        <div class="card-header green-gradient">
            <h5>Tableau Recapitulatif</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered">
                <tbody>

                <tr>
                    <td>Resultats trouvés</td>
                    <td class="text-right"><%=affichage.getPageNavigation().getNombreElements()%></td>
                </tr>

                <tr>
                    <td>Total Entrée</td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(affichage.getTotalEntree())%> Ariary</td>
                </tr>

                <tr>
                    <td>Total Sortie</td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(affichage.getTotalSortie())%> Ariary</td>
                </tr>

                <tr>
                    <td>Total</td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(affichage.getTotal())%> Ariary</td>
                </tr>
                </tbody>
            </table>

        </div>

    </div>
</div>

<div class="d-flex justify-content-center">
    <div class="card w-90">
        <div class="card-header">
            <h5>Liste des Opérations</h5>
        </div>

        <div class="card-body">
            <table class="table table-bordered  table-altered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Numero Compte</th>
                    <th>Libellé</th>
                    <th>Entree</th>
                    <th>Sortie</th>
                    <th>Observation</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (MvtCaisse mvtCaisse : affichage.getMvtCaisses()) {
                %>
                <tr>
                    <td><%=mvtCaisse.getId()%>
                    </td>
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
                    <td><%=mvtCaisse.getObservation()%>
                    </td>
                    <td>
                        <%
                            if (mvtCaisse.getId() != null) {
                        %>
                        <a href="${pageContext.request.contextPath}/tresorerie/saisie-ligne?id=<%=mvtCaisse.getId()%>"
                           class="action-icon">
                            <i class="bx bx-pencil"></i>
                        </a>

                        <a href="${pageContext.request.contextPath}/tresorerie/supprimer?id=<%=mvtCaisse.getId()%>"
                           class="action-icon">
                            <i class="bx bx-trash"></i>
                        </a>
                        <%
                            }
                        %>
                    </td>
                </tr>
                <%
                    }
                %>

                </tbody>
            </table>
        </div>

        <div class="card-footer">
            <div class="d-flex justify-content-between align-items-center">
                <div>Resultat trouvés: <%=affichage.getPageNavigation().getNombreElements()%> </div>

                <form action="">
                <div class="d-flex gap-2 align-items-center">
                    <form action="" method="GET">
                        <input type="hidden" name="dateMin" value="<%=affichage.getDateMin() != null ? affichage.getDateMin() : ""%>">
                        <input type="hidden" name="dateMax" value="<%=affichage.getDateMax() != null ? affichage.getDateMax() : ""%>">
                        <input type="hidden" name="numeroCompte" value="<%=affichage.getNumeroCompte() != null ? affichage.getNumeroCompte() : ""%>">
                        <input type="hidden" name="entreeMin" value="<%=affichage.getEntreeMin() != null ? affichage.getEntreeMin() : ""%>">
                        <input type="hidden" name="entreeMax" value="<%=affichage.getEntreeMax() != null ? affichage.getEntreeMax() : ""%>">
                        <input type="hidden" name="sortieMin" value="<%=affichage.getSortieMin() != null ? affichage.getSortieMin() : "" %>">
                        <input type="hidden" name="libelle" value="<%=affichage.getLibelle() != null ? affichage.getLibelle() : ""%>">

                        <span>Page:</span> <input type="number" name="pageNavigation.numeroPage" value="<%=affichage.getPageNavigation().getNumeroPage() != null ? affichage.getPageNavigation().getNumeroPage() : "0"%>" class="form-control" style="width: 60px"><span>/ <%=affichage.getPageNavigation().getTotalPage() - 1%></span>
                        <button type="submit" class="btn btn-primary">Aller</button>
                    </form>
                </div>
                </form>


                <div class="d-flex align-items-center gap-2">
                    <%
                        if(affichage.getPageNavigation().getNumeroPage() > 0) {
                    %>
                    <a href="?dateMin=<%=affichage.getDateMin() != null ? affichage.getDateMin() : ""%>&dateMax=<%=affichage.getDateMax() != null ? affichage.getDateMax() : ""%>&numeroCompte=<%=affichage.getNumeroCompte() != null ? affichage.getNumeroCompte() : ""%>&entreeMin=<%=affichage.getEntreeMin() != null ? affichage.getEntreeMin() : ""%>&entreeMax=<%=affichage.getEntreeMax() != null ? affichage.getEntreeMax() : ""%>&sortieMin=<%=affichage.getSortieMin() != null ? affichage.getSortieMin() : ""%>&sortieMax=<%=affichage.getSortieMax() != null ? affichage.getSortieMax() : ""%>&libelle=<%=affichage.getLibelle() != null ? affichage.getLibelle() : ""%>&pageNavigation.numeroPage=<%=affichage.getPageNavigation().getNumeroPage() - 1%>" >
                        <button class="btn btn-basic" >
                            <i class="bx bx-left-arrow"></i>
<%--                            Précédent--%>
                        </button>
                    </a>
                    <%
                        }
                    %>
                    <a href="?dateMin=<%=affichage.getDateMin() != null ? affichage.getDateMin() : ""%>&dateMax=<%=affichage.getDateMax() != null ? affichage.getDateMax() : ""%>&numeroCompte=<%=affichage.getNumeroCompte() != null ? affichage.getNumeroCompte() : ""%>&entreeMin=<%=affichage.getEntreeMin() != null ? affichage.getEntreeMin() : ""%>&entreeMax=<%=affichage.getEntreeMax() != null ? affichage.getEntreeMax() : ""%>&sortieMin=<%=affichage.getSortieMin() != null ? affichage.getSortieMin() : ""%>&sortieMax=<%=affichage.getSortieMax() != null ? affichage.getSortieMax() : ""%>&libelle=<%=affichage.getLibelle() != null ? affichage.getLibelle() : ""%>&pageNavigation.numeroPage=<%=affichage.getPageNavigation().getNumeroPage() + 1%>" >
                        <button class="btn btn-basic">
<%--                            <span>Suivant</span>--%>
                            <i class="bx bx-right-arrow"></i>

                        </button>
                    </a>
                </div>

<%--                    </div>--%>

<%--                </div>--%>
            </div>
        </div>
    </div>


</div>

