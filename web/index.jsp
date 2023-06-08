<%@ page import="com.hp.hpl.jena.query.QuerySolution" %>
<%@ page import="java.util.List" %>
<%
    List<QuerySolution> elementi = (List<QuerySolution>) request.getAttribute("elementiQuery");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="row justify-content-center mt-4">
    <div class="col-lg-6">
        <h4>Risultati della query:</h4>
        <ul id="queryResults"></ul>
        <%
            for (QuerySolution valore : elementi) {
        %>
        <li><%= valore %></li>
        <% } %>
    </div>
</div>
</form>
</body>
</html>
