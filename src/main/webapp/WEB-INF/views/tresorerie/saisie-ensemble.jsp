<%@ page pageEncoding="UTF-8" %>
<%@ page import="org.example.fiangonana.model.Code" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="org.example.fiangonana.model.immutable.VBudgetCpl" %>
<%@ page import="org.example.fiangonana.model.CategorieCompte" %>
<%@ page import="java.util.Objects" %>
<%@ page import="org.example.fiangonana.util.NombreUtils" %>
<%
    List<Code> codesEntree = ((List<Code>) request.getAttribute("codesEntree"));
    List<Code> codesSortie = ((List<Code>) request.getAttribute("codesSortie"));
    List<CategorieCompte> categories = ((List<CategorieCompte>) request.getAttribute("categories"));
    List<VBudgetCpl> budgets = ((List<VBudgetCpl>) request.getAttribute("budget[]"));
    int index = 0;


%>

<div class="alert alert-info d-flex align-items-center gap-2">
    <div>
        <img src="/assets/icons/info.png" width="40" alt="">
    </div>

    <div>
        <strong> FR: </strong> Cette page permet de saisir et d'enregistrer plusieurs operations entrées et sorties
        d'argent en une fois <br>
        <strong> MG: </strong> Ito pejy ito dia ahafahana mampiditra sy mitahiry ny vola miditra na mivoaka maromaro
        amin'ny daty iray
    </div>
</div>

<form class="form" action="${pageContext.request.contextPath}/tresorerie/saisie-ensemble/confirmer" method="post">
    <div class="d-flex justify-content-center">
        <div class="card w-50">
            <div class="card-header">
                <h5>Saisie Operations Caisse</h5>
            </div>

            <div class="card-body">
                <div class="mb-2">
                    <label>Date</label>
                    <input
                            type="date"
                            name="date"
                            class="form-control"
                            value="<%=LocalDate.now()%>"
                    >
                </div>
            </div>

            <div class="card-footer">
                <div class="d-flex justify-content-end gap-2">
                    <button type="reset" class="btn btn-warning">Reinitialiser</button>
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                </div>
            </div>
        </div>
    </div>

    <div class="d-flex justify-content-center mt-3">
        <div class="card w-90">
            <div class="card-header">
                <h5>Ajout des Operations</h5>
            </div>

            <div class="card-body table-responsive text-nowrap">
                <table class="table table-bordered ">
                    <thead>
                    <tr>
                        <th>Categorie</th>
                        <%--                        <th>Numero Compte</th>--%>
                        <th style="min-width: 300px">Libellé</th>
                        <th style="min-width: 200px">Entrée (Ariary)</th>
                        <th style="min-width: 200px">Sortie (Ariary)</th>
                        <th style="min-width: 400px">Budget</th>
                        <th style="min-width: 500px">Observation</th>
                    </tr>
                    </thead>

                    <tbody id="form-table">
                    <%--                    <%--%>
                    <%--                        for (; index < 10; index++) {--%>
                    <%--                    %>--%>
                    <%--                    <tr>--%>
                    <%--                        <td>--%>
                    <%--                            <select--%>
                    <%--                                    id="categorie-input"--%>
                    <%--                                    class="form-select form-select-sm categorie-input"--%>
                    <%--                            >--%>
                    <%--                                <option value=""></option>--%>

                    <%--                                <optgroup label="Miditra">--%>
                    <%--                                    <%--%>
                    <%--                                        for (Code code : codesEntree) {--%>
                    <%--                                    %>--%>
                    <%--                                    <option value="<%=code.getCode()%>"><%=code.getCode()%> - <%=code.getLibelle()%>--%>
                    <%--                                    </option>--%>
                    <%--                                    <%--%>
                    <%--                                        }--%>
                    <%--                                    %>--%>

                    <%--                                </optgroup>--%>


                    <%--                                <optgroup label="Mivoaka">--%>
                    <%--                                    <%--%>
                    <%--                                        for (Code code : codesSortie) {--%>
                    <%--                                    %>--%>
                    <%--                                    <option value="<%=code.getCode()%>"><%=code.getCode()%> - <%=code.getLibelle()%>--%>
                    <%--                                    </option>--%>
                    <%--                                    <%--%>
                    <%--                                        }--%>
                    <%--                                    %>--%>

                    <%--                                </optgroup>--%>

                    <%--                            </select>--%>
                    <%--                        </td>--%>

                    <%--                        <td>--%>
                    <%--                            <input--%>
                    <%--                                    type="text"--%>
                    <%--                                    class="form-control code-input"--%>
                    <%--                                    name="mvtCaisses[<%=index%>].code"--%>
                    <%--                                    id="numero-input"--%>
                    <%--                                    maxlength="10"--%>
                    <%--                            >--%>
                    <%--                        </td>--%>

                    <%--                        <td>--%>
                    <%--                            <input--%>
                    <%--                                    type="text"--%>
                    <%--                                    class="form-control libelle-input"--%>
                    <%--                                    name="mvtCaisses[<%=index%>].libelle"--%>
                    <%--                            &lt;%&ndash;                                placeholder="ex: Fiarahabana eveka sy diakra"&ndash;%&gt;--%>
                    <%--                            >--%>
                    <%--                        </td>--%>

                    <%--                        <td>--%>
                    <%--                            <input--%>
                    <%--                                    type="number"--%>
                    <%--                                    class="form-control text-right"--%>
                    <%--                                    name="mvtCaisses[<%=index%>].entree"--%>
                    <%--                                    value="0.00"--%>
                    <%--                                    step="0.01"--%>
                    <%--                            >--%>
                    <%--                        </td>--%>

                    <%--                        <td>--%>
                    <%--                            <input--%>
                    <%--                                    type="number"--%>
                    <%--                                    class="form-control text-right"--%>
                    <%--                                    name="mvtCaisses[<%=index%>].sortie"--%>
                    <%--                                    step="0.01"--%>
                    <%--                                    value="0.00"--%>
                    <%--                            >--%>
                    <%--                        </td>--%>

                    <%--                        <td>--%>
                    <%--                            <input--%>
                    <%--                                    type="text"--%>
                    <%--                                    class="form-control"--%>
                    <%--                                    name="mvtCaisses[<%=index%>].observation"--%>

                    <%--                            >--%>
                    <%--                        </td>--%>


                    <%--                    </tr>--%>
                    <%--                    <%--%>
                    <%--                        }--%>
                    <%--                    %>--%>

                    </tbody>


                </table>

            </div>

            <div class="card-footer">
                <button type="button" class="btn btn-primary" onclick="ajouterUneLigne()">Ajouter une ligne</button>
            </div>


        </div>

    </div>

</form>


<script>
    $(document).ready(() => {
        function changerNumeroCompte(ligne) {
            const categorieSelect = ligne.find('.categorie-input');
            const numeroCompteInput = ligne.find('.code-input');
            // console.log(categorieSelect)
            // console.log(numeroCompteInput.val())
            // console.log(categorieSelect.find(':selected').val())

            numeroCompteInput.val(categorieSelect.find(':selected').val());
        }

        const form = $('.form');
        form.on('change', '.categorie-input', function () {
            const ligne = $(this).closest('tr');
            ligne.find('.libelleInput').val($(this).find(':selected').data('libelle'))
        });


        initSelect2()

        // $('.categorie-input').each(function() {
        //     console.log("a")
        //     $(this).select2({
        //         placeholder: "Select an option",
        //         allowClear: true
        //     });
        // });

        // $(document).on('select2:open', '.categorie-input', function() {
        //     console.log("asc")
        //     $('.categorie-input').select2();
        // });

        // const libelleInputs = $('.libelle-input');
        // libelleInputs.on('keyup', function () {
        //     const libelleInput = $(this);
        //     $.getJSON(environment.apiUrl + '/api/autocomplete/libelle/' + $(this).val(), function (data) {
        //         $('.autocomplete').remove();
        //
        //         libelleInput.after(creerSuggestionAutocomplete(data, libelleInput));
        //     })
        //     // console.log('Hello World');
        //     // getSuggestionLibelle($(this).val());
        // });
        //
        // libelleInputs.on('blur', function () {
        //     setTimeout(() => {
        //         $('.autocomplete').remove();
        //     }, 1000);
        // })


    });

    <%

    %>

    function ajouterUneLigne() {
        // const ligneForm = $(document).find('#ligne-form');
        const formTable = $(document).find('#form-table');
        // formTable.find('tbody')
        formTable.append(ligneHtml);
        updateIndices();
        initSelect2()
    }

    function updateIndices() {
        var rows = document.querySelectorAll("#form-table tr");
        rows.forEach((row, index) => {
            row.querySelectorAll("input, select").forEach(input => {
                var name = input.name;
                var newName = name.replace(/\[\d+\]/, "[" + index + "]");
                input.name = newName;
            });
        });
    }

    function initSelect2() {
        console.log("as")
        $('.categorie-input').select2({
            width: '300px',
            // dropdownCssClass: 'custom-selection',
            // minWidth: '500px',
            placeholder: "Select an option",
            allowClear: true
        })

    }


    const ligneHtml = `
    <tr>
    <td>
        <select
                class="form-select form-select-sm categorie-input" name="mvtCaisses[<%=index%>].compte"
        >
            <option value=""></option>

             <%
                                for (CategorieCompte categorie : categories) {
                            %>
                            <optgroup label="<%=categorie.getLibelleAvecType()%>">
                                <%
                                    for (Code code : categorie.getCodes()) {
                                %>
                                <option value="<%=code.getId()%>"
                                        data-libelle="<%=code.getLibelle()%>"
                                >
                                    <%=code.getCode()%> - <%=code.getLibelle()%>
                                </option>
                                <%
                                    }
                                %>


                            </optgroup>
                            <% } %>

        </select>
    </td>


    <td>
        <input
                type="text"
                class="form-control libelleInput"
                name="mvtCaisses[<%=index%>].libelle"
        <%--                                placeholder="ex: Fiarahabana eveka sy diakra"--%>
        >
    </td>

    <td>
        <input
                type="number"
                class="form-control text-right"
                name="mvtCaisses[<%=index%>].entree"
                value="0.00"
                step="0.01"
        >
    </td>

    <td>
        <input
                type="number"
                class="form-control text-right"
                name="mvtCaisses[<%=index%>].sortie"
                step="0.01"
                value="0.00"
        >
    </td>

    <td>
     <select name="budget" class="form-select" id="">
                        <option value="">Ne pas utiliser de budget</option>

                        <%
                            for(VBudgetCpl b: budgets) {
                        %>
                        <option value="<%=b.getId()%>">
                            <%=b.getLibelle()%> | reste: <%=NombreUtils.affichageMonetaire(b.getReste())%> Ar
                        </option>
                        <%
                            }
                        %>

                    </select>
    </td>

    <td>
        <input
                type="text"
                class="form-control"
                name="mvtCaisses[<%=index%>].observation"

        >
    </td>


</tr>
    `

</script>

<%
    for (int i = 0; i < 10; i++) {
        out.print("<script>ajouterUneLigne()</script>");
    }
%>