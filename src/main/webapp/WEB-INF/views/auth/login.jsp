<%
    String alert = (String) request.getParameter("alert");
    if (alert != null) {
//        out.print("AAAA");
        out.print("<script>alert(\"" + alert + "\") </script>");
    }
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/fonts/boxicons.css"/>

    <link rel="icon" href="${pageContext.request.contextPath}/assets/img/logo.png">


    <!-- Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/css/core.css"
          class="template-customizer-core-css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/css/theme-default.css"
          class="template-customizer-theme-css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/demo.css"/>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css"/>
</head>
<body>

<form action="" class="h-100" method="post">
    <div class="d-flex h-100 justify-content-center align-items-center">
        <div class="bg-white w-25 pb-4 pt-4 px-4 shadow login">
            <div class="d-flex justify-content-center mb-2">
                <img src="/assets/img/logo.png" class="rounded-circle depth" alt="" style="object-fit: cover">
            </div>

            <div class="d-flex justify-content-center mb-3 " style="font-size: 18px">
                Se Connecter
            </div>

            <div class="mb-3 d-flex align-items-center gap-2">
                <i class="bx bx-user" style="font-size: 24px!important;"></i>
                <input type="text" class="form-control" name="identifiant" value="admin">
            </div>

            <div class="mb-3 d-flex align-items-center gap-2">
                <i class="bx bx-key" style="font-size: 24px!important;"></i>
                <input type="password" class="form-control" name="mdp" value="test">
            </div>

            <div class="d-flex justify-content-end">
                <button type="submit" class="btn-simple">Se connecter</button>
            </div>
        </div>
    </div>
</form>


</body>
</html>