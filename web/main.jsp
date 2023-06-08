<%@ page import="java.util.List" %>
<%@ page import="com.hp.hpl.jena.query.QuerySolution" %>

<!DOCTYPE html>
<html>
<head>
  <title>IntelligentWeb</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <div class="row justify-content-center mt-5">
    <div class="col-lg-8 text-center">
      <img src="semantic.jpg" class="img-fluid" style="height: 200px" alt="semantic">
    </div>
  </div>
</div>
<div class="container">
  <div class="row justify-content-center mt-5">
    <div class="col-lg-6">
      <form action="QueryServlet" method="post">
        <div class="form-group">
          <label for="query">Inserisci la tua query:</label>
          <textarea id="queryArea" name="querySparql" type="text" class="form-control"  style="height: 100px;" id="query" placeholder="Inserisci la tua query"></textarea>
        </div>

       <button id="esegui" type="submit" class="btn btn-primary center" value="Invia">Esegui query</button>
      </form>
    </div>
  </div>
  <div class="row justify-content-center mt-4">
    <div class="col-lg-6">
      <button type="button" class="btn btn-secondary" onclick="addText('Query 1')">Query 1</button>
      <button type="button" class="btn btn-secondary" onclick="addText('Query 2')">Query 2</button>
    </div>
  </div>
  <div class="row justify-content-center mt-4">
    <div class="col-lg-6">
      <h4>Risultati della query:</h4>
      <div class="row justify-content-center ">
        <div class="col-lg-6">
          <div class="container">
            <table class="table">
              <thead>
              <tr>
            <%
              List<String> variabili = (List<String>) request.getAttribute("variabili");
              List<String> risultati = (List<String>) request.getAttribute("risultati");
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
                        if(risultati.get(j).contains("^^http://www.w3.org/2001/XMLSchema#date")){
                            risultati.get(j).replace("http://www.w3.org/2001/XMLSchema#date"," ");
                        }
                        if(risultati.get(j).contains("http://www.w3.org/2001/XMLSchema#int")){
                            risultati.get(j).replace("http://www.w3.org/2001/XMLSchema#int"," ");
                        }
              %>
                    <%if (risultati.get(j).contains("C:/")) {%>
              <td> <a href="/RisorsaServlet?senatore=<%=risultati.get(j).replace("#","%23")%>"> <%=risultati.get(j++)%> </a> </td>
                      <%}else{%>
              <td><%=risultati.get(j++)%></td>
                  <%} }%>
                  </tr>



          </div>
        </div>
      </div>
        <% } } %>
        </tbody>
        </table> <%} %>

    </div>
  </div>

</div>

<script>
  document.getElementById("esegui").addEventListener("click", eseguiQuery);
  function addText(text) {
    document.getElementById('query').value = text;
  }

  document.getElementById('queryForm').addEventListener('submit', function(event) {
    event.preventDefault();
    var query = document.getElementById('query').value;

    // Effettua l'elaborazione della query o l'invio al server
    // ...
    displayQueryResult('Risultato della query: ' + query);
    document.getElementById('query').value = '';
  });

  function inviaContenuto() {
    var contenuto = document.getElementById("queryArea").value;

    // Effettua una richiesta HTTP POST alla servlet
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/XMLServlet", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
        // La richiesta è stata completata con successo
        console.log("Contenuto inviato con successo alla servlet.");
      }
    };
    xhr.send("contenuto=" + encodeURIComponent(contenuto));
  }

</script>
</body>
</html>
