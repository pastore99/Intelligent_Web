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
      <form id="queryForm">
        <div class="form-group">
          <label for="query">Inserisci la tua query:</label>
          <textarea type="text" class="form-control"  style="height: 100px;" id="query" placeholder="Inserisci la tua query"></textarea>
        </div>
        <button type="submit" class="btn btn-primary center">Esegui query</button>
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
      <ul id="queryResults"></ul>
    </div>
  </div>

</div>

<script>
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

</script>
</body>
</html>
