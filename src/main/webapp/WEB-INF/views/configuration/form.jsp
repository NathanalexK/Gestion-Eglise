<%@page pageEncoding="UTF-8" %>
<%@ page import="org.example.fiangonana.model.Configuration" %>
<%
    Configuration conf = ((Configuration) request.getAttribute("conf"));
%>


<form action="${pageContext.request.contextPath}/configuration/save" method="post">
    <input type="hidden" name="id" value="1">
    <div class="d-flex justify-content-center">
        <div class="card w-90 p-2">
            <div class="d-flex justify-content-end gap-3">
                <button type="button" class="btn btn-warning" onclick="window.location.href = '/configuration/form'">Annuler Changement</button>
                <button type="submit" class="btn btn-primary">Sauveguarder</button>
            </div>
        </div>
    </div>

    <div class="d-flex justify-content-center mt-4">
        <div class="card w-90">
            <div class="card-header">
                Configuration Date
            </div>


            <div class="card-body">
                <div class="row">
                    <div class="col-lg-4">
                        <label>Date Min Par Défaut</label>
                        <input type="date" class="form-control" name="dateMinDefaut" value="<%=conf.getDateMinDefaut()%>">
                    </div>


                    <div class="col-lg-4">
                        <label>Date Max Par Défaut</label>
                        <input type="date" class="form-control" name="dateMaxDefaut" value="<%=conf.getDateMaxDefaut()%>">

                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="d-flex justify-content-center mt-4">
        <div class="card w-90">
            <div class="card-header">
                Style et Couleurs
            </div>


            <div class="card-body">
                <div class="row">
                    <div class="col-lg-4">
                        <label>Couleur Operation avec Budget</label>
                        <input type="color" class="form-control form-control-color" name="couleurBudget" value="<%=conf.getCouleurBudget()%>">
                    </div>


                </div>
            </div>
        </div>
    </div>
</form>


<%--<div style="position: fixed; bottom: 0">--%>

<%--    asdasldk--%>
<%--</div>--%>