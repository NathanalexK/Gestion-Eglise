<%@ page import="components.misc.swal.SweetAlert" %>
<%@page pageEncoding="UTF-8" %>

<%
    if (session.getAttribute("u") == null) {
        response.sendRedirect("/login");
        return;
    }
    String contentPage = (String) request.getAttribute("content");
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>EKAR Saint Michel Itaosy</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/fonts/boxicons.css"/>

    <%--    <link rel="icon" href="${pageContext.request.contextPath}/assets/img/logo.png">--%>


    <%--    <!-- Core CSS -->--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/css/core.css" class="template-customizer-core-css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/css/theme-default.css" class="template-customizer-theme-css"/>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/demo.css"/>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/css/select2.min.css"/>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/libs/apex-charts/apex-charts.css"/>
    <script src="${pageContext.request.contextPath}/assets/vendor/libs/chartjs/chartjs.js"></script>

    <script src="${pageContext.request.contextPath}/assets/vendor/js/helpers.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/config.js"></script>

    <script src="${pageContext.request.contextPath}/assets/vendor/libs/jquery/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/assets/vendor/js/environment.js"></script>
    <script src="${pageContext.request.contextPath}/assets/vendor/js/script.js"></script>

    <script src="${pageContext.request.contextPath}/assets/js/sweetalert2.js"></script>
    <%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.10.1/gsap.min.js"></script>--%>


    <jsp:include page="style.jsp"/>


</head>
<body>

<%--<div class="wrapper"> --%>
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
        <jsp:include page="sidebar.jsp"/>

        <div class="layout-page">
            <nav class="layout-navbar px-4 navbar align-items-center" id="layout-navbar" style="position: sticky; top: 0; z-index: 1000; min-height: 64px">
                <%--                    <div id="nav-logo" class="nav-header d-flex gap-1 border-bottom align-items-center" style="margin-right: 2rem; display: none" onclick="window.location.href = '/'">--%>
                <img id="nav-logo" src="/assets/img/logo.png" style="margin-right: 2rem; display: none" alt="" height="54px">
                <%--                    </div>--%>

                <div class="d-flex left-toolbar gap-3">
                    <i class="bx bx-sidebar toolbar-icon" onclick="toggleSidebar()"></i>
                    <i class="bx bxs-grid toolbar-icon" onclick="window.location.href.endsWith('/menu') ? history.back() : window.location.href='${pageContext.request.contextPath}/menu'"></i>
                    <i class="bx bx-chevron-left toolbar-icon" onclick="history.back()"></i>
                    <i class="bx bx-chevron-right toolbar-icon" onclick="history.forward()"></i>
                    <i class="bx bx-download toolbar-icon" onclick="backup()"></i>

                </div>
                <%--                    <div class="btn-simple" onclick="history.back()">--%>
                <%--                        <i class="bx bx-arrow-back fw-bold" style="color: white!important;"></i>--%>
                <%--                        <span class="fw-bold">--%>
                <%--                                                    Retour--%>

                <%--                        </span>--%>

                <%--                    </div>--%>
                <%--                    <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">--%>
                <%--                        <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">--%>
                <%--                            <i class="bx bx-menu bx-sm"></i>--%>
                <%--                        </a>--%>
                <%--                    </div>--%>

                <%--                    <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">--%>

                <ul class="navbar-nav flex-row align-items-center ms-auto">
                    <!-- Place this tag where you want the button to render. -->

                    <li class="nav-item lh-1 me-3">
                        <%--                                <%=u.getRole().name()%>--%>
                    </li>

                    <!-- User -->
                    <li class="nav-item navbar-dropdown dropdown-user dropdown">
                        <a class="nav-link dropdown-toggle hide-arrow btn-simple px-3 fw-bold" href="javascript:void(0);" data-bs-toggle="dropdown">
                            ADMIN
                            <%--                                    <div class="avatar avatar-online">--%>
                            <%--                                        <img src="/assets/img/avatars/1.png" alt="" class="w-px-40 h-auto rounded-circle">--%>
                            <%--                                    </div>--%>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li>
                                <a class="dropdown-item" href="#">
                                    <div class="d-flex">
                                        <div class="flex-shrink-0 me-3">
                                            <%--                                                    <div class="avatar avatar-online">--%>
                                            <%--                                                        <img src="/assets/img/avatars/1.png" alt="" class="w-px-40 h-auto rounded-circle">--%>
                                            <%--                                                    </div>--%>
                                        </div>
                                        <div class="flex-grow-1">
                                            <span class="fw-semibold d-block">Admin</span>
                                            <%--                                                    <small class="text-muted">Admin</small>--%>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <div class="dropdown-divider"></div>
                            </li>


                            <li>
                                <a class="dropdown-item" href="/logout">
                                    <i class="bx bx-power-off me-2"></i>
                                    <span class="align-middle">Se Deconnecter</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <!--/ User -->
                </ul>
                <%--                    </div>--%>
            </nav>
            <div class=" container-p-y px-4">
                <jsp:include page="<%=contentPage%>"/>

            </div>

            <div class="bg-white px-3 py-1 flex-grow-1 mt-2">
                Copyright © 2025 Nathanalex | Tous droits reservés.<br>
                Version: 2025.06.04
            </div>


        </div>
    </div>
</div>
<%--</div>--%>

</body>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/script.js"></script>

<%--<script src="/assets/vendor/libs/jquery/jquery.js"></script>--%>
<script src="/assets/vendor/libs/popper/popper.js"></script>
<script src="/assets/vendor/js/bootstrap.js"></script>
<script src="/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>
<script src="/assets/vendor/js/menu.js"></script>
<script src="/assets/vendor/libs/apex-charts/apexcharts.js"></script>
<script src="/assets/vendor/libs/jquery/jquery.js"></script>
<script src="/assets/js/main.js"></script>
<script src="/assets/js/dashboards-analytics.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/js/select2.min.js"></script>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>--%>


<%

    Object sessionErrorObj = session.getAttribute("eswal");
    if (sessionErrorObj != null) {
        SweetAlert swal = new SweetAlert();
        swal.setIcon(SweetAlert.Icon.ERROR);
        swal.setTitle("Erreur");
        swal.setMessage(String.valueOf(sessionErrorObj));
        out.print(swal.fire());
        session.removeAttribute("eswal");
    }

    Object sessionSuccessObj = session.getAttribute("sswal");
    if (sessionSuccessObj != null) {
        SweetAlert swal = new SweetAlert();
        swal.setIcon(SweetAlert.Icon.SUCCESS);
        swal.setTitle("Success");
        swal.setMessage(String.valueOf(sessionSuccessObj));
        out.print(swal.fire());
        session.removeAttribute("sswal");
    }

    Object sessionInfoObj = session.getAttribute("swal");
    if (sessionInfoObj != null) {
        SweetAlert swal = new SweetAlert();
        swal.setIcon(SweetAlert.Icon.INFO);
        swal.setTitle("Info");
        swal.setMessage(String.valueOf(sessionInfoObj));
        out.print(swal.fire());
        session.removeAttribute("swal");
    }
%>

<script>
    function fixSelectSelectedAttribute() {
        $('select[data-selected]').each(function () {
            var selectedValue = $(this).data('selected');
            // var selectedValue = $(this).attr('selected');

            console.log("adsad " + selectedValue)
            // Remove the invalid 'selected' attribute from <select>
            $(this).removeAttr('selected');

            // Set the correct option as selected
            $(this).find('option').each(function () {
                if ($(this).val() == selectedValue) {
                    $(this).prop('selected', true);
                }
            });
        });
    }

    fixSelectSelectedAttribute();


    $(document).ready(function () {
        console.log("ready")
    })

    function toggleSidebar() {
        const layoutMenu = $('#layout-menu');
        const isHide = layoutMenu.css('display') === 'none';
        layoutMenu.css('display', isHide ? 'block' : 'none');
        $('#nav-logo').css('display', isHide ? 'none' : 'block');
    }

    // window.

    function backup() {
        $.ajax({
            url: "${environment.apiUrl}/api/backup/download", // Remplacez par l'URL de votre API
            method: "GET",
            xhrFields: {
                responseType: 'blob' // Permet de traiter la réponse comme un fichier binaire
            },
            success: function (data, status, xhr) {
                // let filename = xhr.getResponseHeader("Content-Disposition").split("filename=")[1].replace(/"/g, '');
                // let blob = new Blob([data], { type: "application/octet-stream" });

                let link = document.createElement("a");
                link.href = window.URL.createObjectURL(data);
                link.download = "backup.dmp";
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            },
            error: function (xhr, status, error) {
                console.error("Erreur lors du téléchargement :", error);
            }
        });


    }

</script>

</html>