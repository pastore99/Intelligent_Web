<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Carmine
  Date: 07/06/2023
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="QueryServlet" method="post">
    <textarea name="querySparql" rows="5" cols="40"></textarea>
    <br>
    <input type="submit" value="Invia">
    <h1>Risultati Query</h1>
    <ul>
        <%
            // Ottieni l'attributo dalla richiesta
            List<String> risultati = (List<String>) request.getAttribute("risultati");
            if (risultati != null){

            // Itera sui risultati e visualizzali
            for (String risultato : risultati) {
        %>
        <li><%= risultato %></li>
        <% } }%>
    </ul>
</form>
</body>
</html>
