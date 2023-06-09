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
  <title>IntelligentWeb</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
  <div class="row justify-content-center mt-2">
    <div class="col-lg-8 text-center">
      <img src="semantic.jpg" class="img-fluid" style="height: 200px" alt="semantic">
    </div>
<div class="col-10">
  <h5 class="text-center my-2">La risorsa ha le seguenti propriet&agrave;:</h5>
  <div class="row justify-content-center ">
    <div class="col-lg-10">
        <table class="table">
          <thead>
          <tr>
            <%
              List<String> variabili = (List<String>) request.getAttribute("variabili");
              List<String> risultati = (List<String>) request.getAttribute("risultatisenatore");
              System.out.println(variabili + "jsp var*********");
              System.out.println(risultati + "jsp ris************");
              if (variabili != null){
                for(String temp : variabili){
            %>
            <th><%=temp%></th>

            <%}%>
          </tr>
          </thead>
          <tbody>
            <%

                if (risultati != null){
                    int j=0;
                  while (j < risultati.size()){
                      int i = 0  ;%>
          <tr>
            <%

              while( i++ < variabili.size()){
            %>
            <td><%=risultati.get(j++)%></td>
            <%} }%>
          </tr>


  <% }  %>
  </tbody>
  </table> <%} %>
    </div>
</div>
</body>
</html>
