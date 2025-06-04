<%@ page import="org.example.fiangonana.model.Configuration" %><%
    Configuration configuration = request.getAttribute("conf") != null ? (Configuration) request.getAttribute("conf") : new Configuration();
%>

<style>
    .w-90 {
        width: 95%;
    }
    .operation-budget {
        background-color: <%=configuration.getCouleurBudget()%> !important;
    }



</style>