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
  <title>Tabella</title>
</head>
<body>
<form action="QueryServlet" method="post">
  <h1>Risultati Query</h1>
  <ul>
    <%
      // Ottieni l'attributo dalla richiesta
      List<String> risultatisenatore = (List<String>) request.getAttribute("risultatisenatore");
      if (risultatisenatore != null){

        // Itera sui risultati e visualizzali
        for (String risultato : risultatisenatore) {
    %>
    <li><%= risultato %></li>
    <% } }%>
  </ul>
</form>
</body>
</html>
