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
<div class="container-fluid">
    <form class="row justify-content-center" method="post">
    <div class="col-6">
        <div class="row align-items-end">
            <div class="col-2">
                <button type="button" class="btn btn-secondary w-100" onclick="scriviQuery3()">Acquisto di ogni cliente</button>
                <button type="button" class="btn btn-secondary w-100 mt-3" onclick="scriviQuery4()">Dati ProductLine</button>
            </div>
        <div class="col-8">
          <label for="query" style="font-size: x-large">Inserisci la tua query:</label>
            <div class="container">
                <p style="font-size: xx-small; color: darkgrey">
                    PREFIX rdf:&lt;http://www.w3.org/1999/02/22-rdf-syntax-ns#&gt;<br>
                    PREFIX owl: &lt;http://www.w3.org/2002/07/owl#&gt;<br>
                    PREFIX xsd: &lt;http://www.w3.org/2001/XMLSchema#&gt;<br>
                    PREFIX vocab: &lt;http://localhost:2020/vocab/resource/&gt;<br>
                    PREFIX rdfs: &lt;http://www.w3.org/2000/01/rdf-schema#&gt;<br>
                </p>
            </div>
          <textarea id="queryArea" name="querySparql" type="text" class="form-control"  style="height: 100px;" id="query" placeholder="Inserisci la tua query"></textarea>
        </div>
          <div class="col-2">
              <button type="button" class="btn btn-secondary w-100" onclick="scriviQuery1()">Prodotti pi&ugrave; venduti</button>
              <button type="button" class="btn btn-secondary w-100 mt-3" onclick="scriviQuery2()">Dipendenti con prodotti venduti</button>
          </div>
        </div>
        <div class="col-12 text-center mt-4">
            <button id="esegui" type="submit" class="btn btn-primary center" value="Invia" formaction="QueryServlet">Esegui query</button>
            <button id="btn1" class="btn btn-primary center" onclick="inviaContenuto('/XMLServlet')">XML</button>
            <button id="btn2" class="btn btn-primary center" onclick="inviaContenuto('/JSONServlet')">JSON</button>
<%--            <button id="btn3" class="btn btn-primary center" onclick="inviaContenuto('/TTLServlet')">TTL</button>--%>
        </div>


    </div>
</form>
  <div class="row justify-content-center mt-4">
    <div class="col-lg-6">

    </div>
  </div>
  <div class="row justify-content-center mt-4">
    <div class="col-10">
      <h4>Risultati della query:</h4>
            <table class="table table-striped">
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
//                        if(risultati.get(j).contains("^^http://www.w3.org/2001/XMLSchema#date")){
//                            risultati.get(j).replace("http://www.w3.org/2001/XMLSchema#date"," ");
//                        }
//                        if(risultati.get(j).contains("http://www.w3.org/2001/XMLSchema#int")){
//                            risultati.get(j).replace("http://www.w3.org/2001/XMLSchema#int"," ");
//                        }
              %>
                    <%if (risultati.get(j).contains("C:/")) {%>
              <td> <a href="/RisorsaServlet?senatore=<%=risultati.get(j).replace("#","%23")%>"> <%=risultati.get(j++)%> </a> </td>
                      <%}else{%>
              <td><%=risultati.get(j++)%></td>
                  <%} }%>
                  </tr>
        <% } } %>
        </tbody>
        </table> <%} %>

</div>
  </div>
</div>

<script>
    //1.Ottenere i prodotti più venduti, in ordine decrescente di quantità vendute:
  function scriviQuery1() {
      var testo = "SELECT ?productCode ?productName (SUM(?quantityOrdered) AS ?totalQuantitySold)\n" +
      "WHERE {\n " +
          "?product vocab:products_productCode ?productCode ;\n" +
      "vocab:products_productName ?productName . \n" +
          "?orderDetail vocab:orderdetails_productCode ?product;\n" +
          "vocab:orderdetails_quantityOrdered ?quantityOrdered .\n" +
          "}\n" +
          "GROUP BY ?productCode ?productName\n" +
          "ORDER BY DESC(?totalQuantitySold)\n";
      document.getElementById("queryArea").value = testo;
  }
//2.Ottenere l'elenco dei dipendenti con il numero di prodotti venduti da ciascun dipendente:
    function scriviQuery2() {
        var testo ="SELECT ?employeeNumber ?firstName ?lastName (COUNT(?productCode) AS ?totalProductsSold)\n" +
        "WHERE { \n" +
            "?employee vocab:employees_employeeNumber ?employeeNumber ;\n" +
            "vocab:employees_firstName ?firstName ;\n" +
        "vocab:employees_lastName ?lastName . \n" +
            "?customer vocab:customers_salesRepEmployeeNumber ?employee ;\n" +
        "vocab:customers_customerNumber ?customerNumber . \n" +
            "?order vocab:orders_customerNumber ?customer ;\n" +
        "vocab:orders_orderNumber ?orderNumber . \n" +
            "?orderDetail vocab:orderdetails_orderNumber ?order ;\n" +
            "vocab:orderdetails_productCode ?productCode .\n" +
            "}GROUP BY ?employeeNumber ?firstName ?lastName\n";
        document.getElementById("queryArea").value = testo;
    }
    //3.ottenere acquisto di ogni cliente
    function scriviQuery3() {
        var testo ="SELECT ?customerNumber ?customerName (SUM(?quantity * ?price) AS ?totalPurchases)" +
        "WHERE { " +
            "?customer   vocab:customers_customerNumber ?customerNumber ;\n" +
        "vocab:customers_customerName ?customerName . \n" +
            "?order vocab:orders_customerNumber ?customer;\n" +
        "vocab:orders_orderNumber ?orderNumber . \n" +
            "?orderDetail vocab:orderdetails_orderNumber ?order ;\n" +
            "vocab:orderdetails_quantityOrdered ?quantity \n;" +
            "vocab:orderdetails_priceEach ?price .\n" +
            "}\n" +
            "GROUP BY ?customerNumber ?customerName\n"+
            "ORDER BY DESC(?totalPurchases)";
            document.getElementById("queryArea").value = testo;
    }
    //dati product line
    function scriviQuery4() {
        var testo ="SELECT ?productLine (COUNT(DISTINCT ?product) AS ?totalProducts) (COUNT(DISTINCT ?order) AS ?totalOrders)\n" +
        "WHERE {\n " +
        "?productLine vocab:productlines_productLine ?line .\n " +
            "?product vocab:products_productLine ?productLine ;\n" +
        "vocab:products_productCode ?productCode . \n" +
            "?orderDetail vocab:orderdetails_productCode ?product ;\n" +
        "vocab:orderdetails_orderNumber ?order . \n" +
            "?order vocab:orders_orderNumber ?orderNumber .\n" +
            "}\n" +
            "GROUP BY ?productLine\n" +
            "ORDER BY DESC(?totalOrders)\n";
            document.getElementById("queryArea").value = testo;
    }

  document.getElementById('queryForm').addEventListener('submit', function(event) {
    event.preventDefault();
    var query = document.getElementById('query').value;

    // Effettua l'elaborazione della query o l'invio al server
    // ...
    displayQueryResult('Risultato della query: ' + query);
    document.getElementById('query').value = '';
  });

  function showAlert(x) {
      alert("File" + x + " creato correttamente");
  }

  function inviaContenuto(x) {
    var contenuto = document.getElementById("queryArea").value;

    // Effettua una richiesta HTTP POST alla servlet
    var xhr = new XMLHttpRequest();
    xhr.open("POST", x, true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 || xhr.status === 200) {
        // La richiesta è stata completata con successo
        console.log("Contenuto inviato con successo alla servlet.");
      }
    };
    xhr.send("contenuto=" + encodeURIComponent(contenuto));
      showAlert(x);  // Chiama la funzione showAlert() per mostrare l'alert
  }

</script>
</body>
</html>
