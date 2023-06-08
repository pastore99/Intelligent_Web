<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Risultati Query</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="row justify-content-center mt-4">
  <div class="col-lg-6">
    <div class="container">
      <h4>Risultati Query</h4>
      <table class="table table-striped">
        <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Cognome</th>
          <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <%-- Esempio di dati recuperati dalla query --%>
        <%--<%
          List<Utente> utenti = /* codice per eseguire la query e recuperare i dati */;
          for (Utente utente : utenti) {
        %>--%>
        <tr>
          <td><%--<%= utente.getId() %>--%></td>
          <td><%--<%= utente.getNome() %>--%></td>
          <td><%--<%= utente.getCognome() %>--%></td>
          <td><%--<%= utente.getEmail() %>--%></td>
        </tr>
        <% /*}*/ %>
        </tbody>
      </table>
    </div>

  </div>
</div> <%--tabella --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
