import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

public class prova3 {
    public static void main(String[] args){ //d2r
        Model m = new ModelD2RQ("outfile.ttl");

        String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX vocab: <http://localhost:2020/vocab/resource/>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX map: <file:/C:/Users/rocco/OneDrive%20-%20Universit�%20di%20Salerno/Downloads/d2r-server-0.7/d2r-server-0.7/outfile.ttl#>\n" +
                "PREFIX db: <http://localhost:2020/resource/>\n" +
                "SELECT DISTINCT * WHERE {\n" +
                "  ?senatore a vocab:mytable.\n" +
                "  ?senatore vocab:mytable_nome ?nome.\n" +
                "  ?senatore vocab:mytable_cognome ?cognome.\n" +
                "  ?senatore vocab:mytable_tipoMandato ?mandato.\n" +
                "  ?senatore vocab:mytable_legislatura ?legislatura.\n" +
                "  ?senatore vocab:mytable_inizioMandato ?inizioMandato.\n" +
                "  ?senatore vocab:mytable_tipoMandato ?tipoMandato.\n" +
                "} ORDER BY ?cognome ?nome LIMIT 10";
        Query q = QueryFactory.create(queryString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        while (rs.hasNext()){
            QuerySolution row = rs.nextSolution();
            System.out.println(row);
        }

       m.close();
    }
}

