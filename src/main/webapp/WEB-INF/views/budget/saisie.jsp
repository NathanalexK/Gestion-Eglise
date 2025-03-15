<%@ page import="org.example.fiangonana.model.Budget" %>
<%@ page import="java.time.LocalDate" %>
<%@page pageEncoding="UTF-8" %>

<%
    Budget b = ((Budget) request.getAttribute("budget"));
%>

<form action="${pageContext.request.contextPath}/budget/save" method="post">
    <div class="d-flex justify-content-center">
        <div class="card w-50">
            <div class="card-header">
                <h5>Formualaire saisie budget</h5>
            </div>

            <div class="card-body">
                <div class="mb-3">
                    <input type="hidden" name="id" value="<%=b != null ? b.getId() : ""%>">
                    <label>Libellé</label>
                    <input type="text" name="libelle" class="form-control" value="<%=b != null ? b.getLibelle() : ""%>"
                           placeholder="ex: Budget du 01 au 15 Fev 2025">
                </div>

                <div class="mb-3">
                    <label>Montant</label>
                    <input type="number" step="0.01" name="montant" class="form-control"
                           value="<%=b != null ? b.getMontant() : "0.00"%>">
                </div>

                <div class="mb-3">
                    <label>Date debut</label>
                    <input type="date" name="dateDebut" class="form-control"
                           value="<%=b != null ? b.getDateDebut() : LocalDate.now()%>">

                </div>

                <div class="mb-3">
                    <label>Date Prévu Fin</label>
                    <input type="date" name="dateFin" class="form-control"
                           value="<%=b != null ? b.getDateFin() : ""%>"/>
                </div>

                <div class="mb-3">
                    <label>description <small>(Facultative)</small></label>
                    <textarea rows="3" name="description" placeholder="Aucune description..." class="form-control">
                    <%=b != null ? b.getDescription() : ""%>
                </textarea>

                </div>


            </div>

            <div class="card-footer">
                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                </div>
            </div>

        </div>

    </div>
</form>