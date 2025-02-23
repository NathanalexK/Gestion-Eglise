<%@page pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.model.MvtCaisse" %>
<%
    List<MvtCaisse> mvtCaisses = ((List<MvtCaisse>) request.getAttribute("mvtCaisse[]"));
%>

<div class="alert alert-info d-flex align-items-center gap-2">
    <div>
        <img src="/assets/icons/info.png" width="40" alt="">
    </div>

    <div>
        <strong> FR: </strong> Cette page permet de verifier et de confirmer ou annuler la saisie avant l'enregistrement<br>
        <strong> MG: </strong> Ito pejy ito dia ahafahana mijery sy manaiky na tsia ny zavatra nampidirina
    </div>
</div>


<div class="d-flex justify-content-center">
    <div class="card w-100">
        <form action="/tresorerie/valider" method="POST">
            <div class="card-header">
                <h5>Confirmer la saisie</h5>
            </div>

            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Numero Compte</th>
                        <th>Libelle</th>
                        <th>Entrée</th>
                        <th>Sortie</th>
                        <th>Observation</th>
                    </tr>

                    </thead>

                    <tbody>
                    <%
                        int index = 0;
                        for (MvtCaisse mvtCaisse : mvtCaisses) {
                    %>
                    <tr>
                        <td>
                            <input type="text" class="form-readonly" name="mvtCaisses[<%=index%>].date" value="<%=mvtCaisse.getDate()%>" readonly>
                        </td>

                        <td>
                            <input type="text" name="mvtCaisses[<%=index%>].code" value="<%=mvtCaisse.getCode()%>" class="form-readonly" readonly>
                        </td>

                        <td>
                            <input type="text" name="mvtCaisses[<%=index%>].libelle" value="<%=mvtCaisse.getLibelle()%>" class="form-readonly" readonly>
                        </td>

                        <td>
                            <input type="text" name="mvtCaisses[<%=index%>].entree" value="<%=mvtCaisse.getEntree()%>" class="form-readonly" readonly>
                        </td>

                        <td>
                            <input type="text" name="mvtCaisses[<%=index%>].sortie" value="<%=mvtCaisse.getSortie()%>" class="form-readonly" readonly>
                        </td>

                        <td>
                            <input type="text" name="mvtCaisses[<%=index%>].observation" value="<%=mvtCaisse.getObservation()%>" class="form-readonly" readonly>
                        </td>

                    </tr>

                    <%
                            index++;
                        }
                    %>

                    </tbody>
                </table>


            </div>


            <div class="card-footer">
                <div class="d-flex justify-content-end gap-2">
                    <a href="">
                        <button class="btn btn-danger">Annuler</button>
                    </a>
                    <button type="button" class="btn btn-warning" onclick="window.history.back()">Retour</button>
                    <button type="submit" class="btn btn-primary">Valider</button>

                </div>

            </div>
        </form>


    </div>


</div>