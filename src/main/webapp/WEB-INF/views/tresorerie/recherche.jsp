<%@ page import="org.example.fiangonana.model.MvtCaisse" %>
<%@ page import="org.example.fiangonana.dto.tresorerie.MvtCaisseRechercheAffichage" %>
<%@ page import="org.example.fiangonana.util.DateUtils" %>
<%@ page import="org.example.fiangonana.util.NombreUtils" %>
<%@ page import="components.table.HtmlPagination" %>
<%@ page import="org.example.fiangonana.model.CategorieCompte" %>
<%@ page import="org.example.fiangonana.model.Code" %>
<%@ page import="java.util.Objects" %>
<%@ page import="org.example.fiangonana.model.Budget" %>
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
        <div class="card w-90 card-style-1">
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
                        <label> Compte</label>
                        <select class="form-select" id="i-compte">
                            <option value=""> -- Selectionner --</option>

                            <%
                                for (CategorieCompte categorie : affichage.getCategorieComptes()) {
                            %>
                            <optgroup label="<%=categorie.getLibelleAvecType()%>">
                                <%
                                    for (Code code : categorie.getCodes()) {
                                %>
                                <option value="<%=code.getId()%>"
                                        data-code="<%=code.getCode()%>"
                                        <%=affichage.getNumeroCompte() != null && Objects.equals(affichage.getNumeroCompte(), code.getCode()) ? "selected" : "" %>
                                >
                                    <%=code.getCode()%> - <%=code.getLibelle()%>
                                </option>
                                <%
                                    }
                                %>


                            </optgroup>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="col-lg-3">
                        <label> Numero Compte</label>
                        <input type="text" class="form-control" name="numeroCompte" id="i-numero-compte"
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

                    <div class="col-lg-3">
                        <label>Colonne Tri</label>
                        <select name="ordreColonne" id="" class="form-select">
                            <option value="date" <%="date".equalsIgnoreCase(affichage.getOrdreColonne()) ? "selected" : ""%>  >Date</option>
                            <option value="libelle" <%="libelle".equalsIgnoreCase(affichage.getOrdreColonne()) ? "selected" : ""%>>Libellé</option>
                            <option value="entree" <%="entree".equalsIgnoreCase(affichage.getOrdreColonne()) ? "selected" : ""%>>Entrée</option>
                            <option value="sortie" <%="sortie".equalsIgnoreCase(affichage.getOrdreColonne()) ? "selected" : ""%>>Sortie</option>
                        </select>

                    </div>

                    <div class="col-lg-3">
                        <label for="">Ordre Tri</label>
                        <select class="form-select" name="ordre">
                            <option value="ASC" <%="asc".equalsIgnoreCase(affichage.getOrdre()) ? "selected" : ""%>>Croissant</option>
                            <option value="DESC" <%="desc".equalsIgnoreCase(affichage.getOrdre()) ? "selected" : ""%>>Decroissant</option>
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

<%--    <div class="card w-90">--%>
<%--        <div class="card-header">--%>
<%--            Tri des Colonnes--%>
<%--        </div>--%>
<%--        --%>
<%--        <div class="card-body ">--%>
<%--            <div class="row">--%>
<%--                    <div class="col-lg-3">--%>
<%--                        <label>Colonne</label>--%>
<%--                        <select name="" id="" class="form-select">--%>
<%--                            <option value="date">Date</option>--%>
<%--                            <option value="libelle">Libellé</option>--%>
<%--                            <option value="entree">Entrée</option>--%>
<%--                            <option value="sortie">Sortie</option>--%>
<%--                        </select>--%>

<%--                    </div>--%>

<%--                    <div class="col-lg-3">--%>
<%--                        <label for="">Ordre</label>--%>
<%--                        <select class="form-select">--%>
<%--                            <option value="ASC">Croissant</option>--%>
<%--                            <option value="DESC">Decroissant</option>--%>
<%--                    </select>--%>
<%--                </div>--%>
<%--                --%>
<%--            </div>--%>
<%--            --%>
<%--        </div>--%>

<%--    </div>--%>


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
                    <td class="text-right"><%=affichage.getPageNavigation().getNombreElements()%>
                    </td>
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
                    <th>Budget</th>
                    <th>Observation</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (MvtCaisse mvtCaisse : affichage.getMvtCaisses()) {
                %>
                <tr class="<%=mvtCaisse.getClasseCouleur()%>">
                    <td><%=mvtCaisse.getId()%>
                    </td>
                    <td><%=DateUtils.getFormatParDefaut(mvtCaisse.getDate())%>
                    </td>
                    <td><%=mvtCaisse.getCode()%>
                    </td>
                    <td><%=mvtCaisse.getLibelle()%>
                    </td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(mvtCaisse.getEntree())%>
                    </td>
                    <td class="text-right"><%=NombreUtils.affichageMonetaire(mvtCaisse.getSortie())%>
                    </td>
                    <td>
                        <%
                            if (mvtCaisse.getBudget() != null) {
                        %>
                         <a class="lien" href="${pageContext.request.contextPath}/budget/details?id=<%=mvtCaisse.getBudget().getId()%>"><%=mvtCaisse.getBudget().getLibelle()%>
                        </a></td>
                        <%
                            }
                        %>

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
                <div>Resultat trouvés: <%=affichage.getPageNavigation().getNombreElements()%>
                </div>

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

                            <span>Page:</span> <input type="number" name="pageNavigation.numeroPageAffiche"
                                                      value="<%=affichage.getPageNavigation().getNumeroPage() != null ? affichage.getPageNavigation().getNumeroPage() + 1 : "1"%>" class="form-control"
                                                      style="width: 60px"><span>/ <%=affichage.getPageNavigation().getTotalPage()%></span>
                            <button type="submit" class="btn btn-primary">Aller</button>
                        </form>
                    </div>
                </form>


                <div class="d-flex align-items-center gap-2">
                    <%
                        if (affichage.getPageNavigation().hasPrev()) {
                    %>
                    <a href="?dateMin=<%=affichage.getDateMin() != null ? affichage.getDateMin() : ""%>&dateMax=<%=affichage.getDateMax() != null ? affichage.getDateMax() : ""%>&numeroCompte=<%=affichage.getNumeroCompte() != null ? affichage.getNumeroCompte() : ""%>&entreeMin=<%=affichage.getEntreeMin() != null ? affichage.getEntreeMin() : ""%>&entreeMax=<%=affichage.getEntreeMax() != null ? affichage.getEntreeMax() : ""%>&sortieMin=<%=affichage.getSortieMin() != null ? affichage.getSortieMin() : ""%>&sortieMax=<%=affichage.getSortieMax() != null ? affichage.getSortieMax() : ""%>&libelle=<%=affichage.getLibelle() != null ? affichage.getLibelle() : ""%>&pageNavigation.numeroPage=<%=affichage.getPageNavigation().getNumeroPage() - 1%>">
                        <button class="btn btn-basic">
                            <i class="bx bx-left-arrow"></i>
                            <%--                            Précédent--%>
                        </button>
                    </a>
                    <%
                        }
                    %>
                    <%

                    %>
                    <%
                        if(affichage.getPageNavigation().hasNext()){
                    %>
                    <a href="?dateMin=<%=affichage.getDateMin() != null ? affichage.getDateMin() : ""%>&dateMax=<%=affichage.getDateMax() != null ? affichage.getDateMax() : ""%>&numeroCompte=<%=affichage.getNumeroCompte() != null ? affichage.getNumeroCompte() : ""%>&entreeMin=<%=affichage.getEntreeMin() != null ? affichage.getEntreeMin() : ""%>&entreeMax=<%=affichage.getEntreeMax() != null ? affichage.getEntreeMax() : ""%>&sortieMin=<%=affichage.getSortieMin() != null ? affichage.getSortieMin() : ""%>&sortieMax=<%=affichage.getSortieMax() != null ? affichage.getSortieMax() : ""%>&libelle=<%=affichage.getLibelle() != null ? affichage.getLibelle() : ""%>&pageNavigation.numeroPage=<%=affichage.getPageNavigation().getNumeroPage() + 1%>">
                        <button class="btn btn-basic">
                            <%--                            <span>Suivant</span>--%>
                            <i class="bx bx-right-arrow"></i>

                        </button>
                    </a>
                    <%
                        }
                    %>
                </div>

                <%--                    </div>--%>

                <%--                </div>--%>
            </div>
        </div>
    </div>


</div>

<script>
    const compteInput = $('#i-compte');
    const numCompteInput = $('#i-numero-compte');

    compteInput.on('change', function () {
        const selected = compteInput.find(':selected');
        numCompteInput.val(selected.data('code'));
    })

</script>

