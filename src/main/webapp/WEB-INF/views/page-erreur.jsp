<%@ page import="org.example.fiangonana.util.HtmlTemplate" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
        <script src="${pageContext.request.contextPath}/assets/js/sweetalert2.js"></script>

</head>
<body>
<%--    <script>--%>
<%--        console.log("retour");--%>
<%--        history.back();--%>
<%--    </script>--%>
<script>
    function afficherErreurAvecCallback(html) {
        Swal.fire({
            icon: 'error',
            title: 'Oups...',
            html: html,
            confirmButtonText: 'OK',
        }).then((result) => {
            if (result.isConfirmed) {
                // Exécuter le callback ici
                console.log("L'utilisateur a cliqué sur OK");
                history.back();
                // Vous pouvez mettre ici la logique que vous souhaitez déclencher après le clic sur OK
            }
        });
    }

    <%
        Object eswal = session.getAttribute("eswal");
        if(eswal != null) {
            out.print("afficherErreurAvecCallback('"+ eswal + "');");
            session.removeAttribute("eswal");
        }
    %>

</script>
</body>
</html>


<%--<%@ page import="components.misc.swal.SweetAlert" %>--%>
<%--<!doctype html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport"--%>
<%--          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="ie=edge">--%>
<%--    <title>EKAR Saint Michel Itaosy</title>--%>
<%--    <script src="${pageContext.request.contextPath}/assets/js/sweetalert2.js"></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--&lt;%&ndash;<script>&ndash;%&gt;--%>
<%--    <%--%>
<%--    Object sessionErrorObj = session.getAttribute("eswal");--%>
<%--    if (sessionErrorObj != null) {--%>
<%--        SweetAlert swal = new SweetAlert();--%>
<%--        swal.setIcon(SweetAlert.Icon.ERROR);--%>
<%--        swal.setTitle("Erreur");--%>
<%--        swal.setMessage(String.valueOf(sessionErrorObj));--%>
<%--        out.print(swal.fire());--%>
<%--        out.print("<script>history.back()</script>");--%>
<%--        session.removeAttribute("eswal");--%>
<%--    }--%>

<%--    Object sessionSuccessObj = session.getAttribute("sswal");--%>
<%--    if (sessionSuccessObj != null) {--%>
<%--        SweetAlert swal = new SweetAlert();--%>
<%--        swal.setIcon(SweetAlert.Icon.SUCCESS);--%>
<%--        swal.setTitle("Success");--%>
<%--        swal.setMessage(String.valueOf(sessionSuccessObj));--%>
<%--        out.print(swal.fire());--%>
<%--        session.removeAttribute("sswal");--%>
<%--    }--%>

<%--    Object sessionInfoObj = session.getAttribute("swal");--%>
<%--    if (sessionInfoObj != null) {--%>
<%--        SweetAlert swal = new SweetAlert();--%>
<%--        swal.setIcon(SweetAlert.Icon.INFO);--%>
<%--        swal.setTitle("Info");--%>
<%--        swal.setMessage(String.valueOf(sessionInfoObj));--%>
<%--        out.print(swal.fire());--%>

<%--        session.removeAttribute("swal");--%>
<%--    }--%>
<%--    %>--%>



<%--&lt;%&ndash;</script>&ndash;%&gt;--%>
<%--</body>--%>
<%--</html>--%>