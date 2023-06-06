import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

public class prova3 {
    public static void main(String[] args){ //d2r
        Model m = new ModelD2RQ("outfile.ttl");

        String queryString = """
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX owl: <http://www.w3.org/2002/07/owl#>
                PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
                PREFIX vocab: <http://localhost:2020/vocab/resource/>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                PREFIX map: <file:/C:/Users/rocco/OneDrive%20-%20Universit�%20di%20Salerno/Downloads/d2r-server-0.7/d2r-server-0.7/outfile.ttl#>
                PREFIX db: <http://localhost:2020/resource/>
                SELECT DISTINCT * WHERE {
                  ?senatore a vocab:mytable.
                  ?senatore vocab:mytable_nome ?nome.
                  ?senatore vocab:mytable_cognome ?cognome.
                  ?senatore vocab:mytable_tipoMandato ?mandato.
                  ?senatore vocab:mytable_legislatura ?legislatura.
                  ?senatore vocab:mytable_inizioMandato ?inizioMandato.
                  ?senatore vocab:mytable_tipoMandato ?tipoMandato.
                } ORDER BY ?cognome ?nome LIMIT 10""";
        Query q = QueryFactory.create(queryString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        while (rs.hasNext()){
            QuerySolution row = rs.nextSolution();
            System.out.println(row);
        }

       m.close();
    }
}

