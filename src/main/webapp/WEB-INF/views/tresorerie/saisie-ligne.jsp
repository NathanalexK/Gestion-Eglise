<%@page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.fiangonana.model.Code" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="org.example.fiangonana.model.MvtCaisse" %>
<%@ page import="org.example.fiangonana.model.CategorieCompte" %>
<%@ page import="org.example.fiangonana.model.immutable.VBudgetCpl" %>
<%@ page import="org.example.fiangonana.model.Budget" %>
<%@ page import="org.example.fiangonana.util.NombreUtils" %>
<%@ page import="java.util.Objects" %>
<%
//    List<Code> codesEntree = ((List<Code>) request.getAttribute("codesEntree"));
//    List<Code> codesSortie = ((List<Code>) request.getAttribute("codesSortie"));
    List<CategorieCompte> categories = ((List<CategorieCompte>) request.getAttribute("categories"));
    List<VBudgetCpl> budgets = ((List<VBudgetCpl>) request.getAttribute("budget[]"));
    MvtCaisse mvtCaisse = ((MvtCaisse) request.getAttribute("mvtCaisse"));
%>
<div class="alert alert-info d-flex align-items-center gap-2">
    <div>
        <img src="/assets/icons/info.png" width="40" alt="">
    </div>

    <div>
        <strong> FR: </strong> Cette page permet de saisir et d'enregistrer une operation(entrée ou sortie d'argent)
        effectué dans la tresorerie <br>
        <strong> MG: </strong> Ito pejy ito dia ahafahana mampiditra sy mitahiry ny vola miditra na mivoaka (ampidirina
        tsirairay)
    </div>
</div>

<div class="d-flex justify-content-center">

    <div class="card w-50">
        <form action="${pageContext.request.contextPath}/tresorerie/saisie-ligne/confirmer" method="post">

            <div class="card-header">
                <h5>Saisie Operation Caisse</h5>
            </div>


            <input type="hidden" name="id" value="<%=mvtCaisse != null ? mvtCaisse.getId() : ""%>">

            <div class="card-body">
                <div class="mb-2">
                    <div class="mb-2">
                        <label>Date</label>

                        <input
                                type="date"
                                class="form-control"
                                name="date"
                                value="<%=mvtCaisse != null ? mvtCaisse.getDate() : LocalDate.now()%>"
                        >

                    </div>

                    <div class="mb-2">
                        <label for="categorie-input">Categorie: </label>
                        <select
                                id="categorie-input"
                                class="form-select"
                                name="compte"
                        >

                            <%
                                for (CategorieCompte categorie : categories) {
                            %>
                            <optgroup label="<%=categorie.getLibelleAvecType()%>">
                                <%
                                    for (Code code : categorie.getCodes()) {
                                %>
                                <option value="<%=code.getId()%>"
                                        data-libelle="<%=code.getLibelle()%>"
                                        <%=mvtCaisse != null &&  mvtCaisse.getCompte() != null && Objects.equals( mvtCaisse.getCompte().getId(), code.getId()) ? "selected": "" %>
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

                            <%--                        <optgroup label="Miditra (Recette)">--%>
                            <%--                            <%--%>
                            <%--                                for (Code code : codesEntree) {--%>
                            <%--                            %>--%>
                            <%--                            <option value="<%=code.getId()%>"><%=code.getCode()%> - <%=code.getLibelle()%>--%>
                            <%--                            </option>--%>
                            <%--                            <%--%>
                            <%--                                }--%>
                            <%--                            %>--%>

                            <%--                        </optgroup>--%>


                            <%--                        <optgroup label="Mivoaka (Depense)">--%>
                            <%--                            <%--%>
                            <%--                                for (Code code : codesSortie) {--%>
                            <%--                            %>--%>
                            <%--                            <option value="<%=code.getId()%>"><%=code.getCode()%> - <%=code.getLibelle()%>--%>
                            <%--                            </option>--%>
                            <%--                            <%--%>
                            <%--                                }--%>
                            <%--                            %>--%>

                            </optgroup>

                        </select>
                    </div>

                </div>

<%--                <div class="mb-2">--%>
<%--                    <label>Numero Compte:</label>--%>

<%--                    <input--%>
<%--                            type="text"--%>
<%--                            class="form-control"--%>
<%--                            name="code"--%>
<%--                            id="numero-input"--%>
<%--                            maxlength="10"--%>
<%--                            value="<%=mvtCaisse != null ? mvtCaisse.getCode() : ""%>"--%>
<%--                    >--%>

<%--                </div>--%>

                <div class="mb-2">
                    <label>Libelle</label>

                    <input
                            type="text"
                            class="form-control"
                            id="libelle-input"
                            name="libelle"
                            placeholder="ex: Fiarahabana eveka sy diakra"
                            value="<%=mvtCaisse != null ? mvtCaisse.getLibelle() : ""%>"
                    >


                </div>

                <div class="mb-2">
                    <label>Entree</label>

                    <input
                            type="number"
                            class="form-control"
                            name="entree"
                            value="<%=mvtCaisse != null ? mvtCaisse.getEntree() : "0.00"%>"
                            step="0.01"
                    >

                </div>

                <div class="mb-2">
                    <label>Sortie</label>

                    <input
                            type="number"
                            class="form-control"
                            name="sortie"
                            step="0.01"
                            value="<%=mvtCaisse != null ? mvtCaisse.getSortie() : "0.00"%>"
                    >

                </div>

                <div class="mb-2">
                   <label>Budget</label>
                    <select name="budget" class="form-select" id="">
                        <option value="">Ne pas utiliser de budget</option>

                        <%
                            for(VBudgetCpl b: budgets) {
                        %>
                        <option value="<%=b.getId()%>" <%=mvtCaisse != null && mvtCaisse.getBudget() != null && Objects.equals(mvtCaisse.getBudget().getId(), b.getId()) ? "selected" : ""%>>
                            <%=b.getLibelle()%> | reste: <%=NombreUtils.affichageMonetaire(b.getReste())%> Ar
                        </option>
                        <%
                            }
                        %>

                    </select>
                </div>

                <div class="mb-2">
                    <label>Observation <small>(Facultatif)</small></label>

                    <textarea
                            class="form-control"
                            name="observation"
                            rows="3"

                    ></textarea>

                </div>


            </div>

            <div class="card-footer">
                <div class="d-flex justify-content-end gap-2">
                    <button type="reset" class="btn btn-warning">Reinitisaliser</button>
                    <button type="submit" class="btn btn-primary"><%=mvtCaisse != null ? "Modifier" : "Enregistrer"%>
                    </button>

                </div>
            </div>
        </form>

    </div>


</div>

<script>
    $(document).ready(() => {
        const categorieInput = $('#categorie-input');
        const libelleInput = $('#libelle-input');
        const codeInput = $('#numero-input');

        const selected = categorieInput.find(':selected');
        codeInput.val(selected.val());
        libelleInput.val(selected.data('libelle'));

        categorieInput.on('change', function () {
            const selected = categorieInput.find(':selected');
            codeInput.val(selected.val());
            libelleInput.val(selected.data('libelle'));
        })

        // console.log("Hello World");
        // $(document).find('#numero-input').val($(document).find('#categorie-input').find(':selected').val())
        //
        //
        // const form = $('form');
        // form.on('change', '#categorie-input', () => {
        //     $(document).find('#numero-input').val($(document).find('#categorie-input').find(':selected').val())
        //     $('#libelle-input').val($('#categorie-input').data("libelle"))
        // });
        //
        //
        // form.on('keyup', '#libelle-input', function () {
        //     const libelleInput = $(this);
        //     $.getJSON(environment.apiUrl + '/api/autocomplete/libelle/' + $(this).val(), function (data) {
        //         $('.autocomplete').remove();
        //
        //         libelleInput.after(creerSuggestionAutocomplete(data, libelleInput));
        //     });
        // });
        //
        // form.on('blur', '#libelle-input', function () {
        //     setTimeout(() => {
        //         $('.autocomplete').remove();
        //     }, 1000);
        //
        // });

        $('#categorie-input').select2({

            // width: '900px',
            placeholder: "Sélectionnez une option",
            allowClear: true
        });


        // function updateNumero() {
        //
        // }
    });


</script>