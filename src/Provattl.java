import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

public class Provattl {

    public static void main(String[] args) {

        // Carica il modello D2RQ

        ModelD2RQ model = new ModelD2RQ("outfile.ttl");

        // Query SPARQL

        String sparqlQuery = """
                PREFIX vocab: <http://localhost:2020/vocab/resource/>
                SELECT DISTINCT ?senatore ?nome ?cognome ?inizioMandato ?legislatura ?tipoMandato
                WHERE {
                    ?senatore a vocab:mytable.
                    ?senatore vocab:mytable_nome ?nome.
                    ?senatore vocab:mytable_cognome ?cognome.
                    ?senatore vocab:mytable_legislatura ?legislatura.
                    ?senatore vocab:mytable_inizioMandato ?inizioMandato.
                    ?senatore vocab:mytable_tipoMandato ?tipoMandato.
                } ORDER BY ?cognome ?nome
                """;



        // Esegui la query e ottieni i risultati

        ResultSet results = QueryExecutionFactory.create(sparqlQuery, model).execSelect();



            // Stampa i risultati in formato Turtle (TTL)

            results.forEachRemaining(solution -> {

                System.out.println(solutionToTurtle(solution));

            });

        }




    // Converte una soluzione di query in formato Turtle (TTL)

    private static String solutionToTurtle(QuerySolution solution) {

        return "<" + solution.get("senatore") + "> <" + solution.get("nome") + "> " +
                solution.get("cognome") + " .";

    }

}
