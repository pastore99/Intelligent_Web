import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

import java.util.List;

public class query1 {
    public static void main(String[] args){
        Model m = new ModelD2RQ("C://Users//rocco//IdeaProjects//Intelligent_Web//outfile2.ttl");
        //
        String queryString = """
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX owl: <http://www.w3.org/2002/07/owl#>
                PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
                PREFIX vocab: <http://localhost:2020/vocab/resource/>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                SELECT DISTINCT ?property ?hasValue ?isValueOf
                WHERE {
                  { <http://localhost:2020/resource/customers/125> ?property ?hasValue }
                  UNION
                  { ?isValueOf ?property <http://localhost:2020/resource/customers/125> }
                }
                ORDER BY (!BOUND(?hasValue)) ?property ?hasValue ?isValueOf
                """;
        Query q = QueryFactory.create(queryString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        System.out.println(rs.getResultVars() + "  prova variabili");
        List<String> variabili = rs.getResultVars();
        System.out.println(rs.hasNext());
        int i =0;
        while (rs.hasNext()){
            System.out.println(2);
            QuerySolution row = rs.nextSolution();
            for(String e : variabili){
                System.out.println(3);
                System.out.println(e + "    " + row.get(e) + " ********* elemento" + i++);
            }
        }
       m.close();
    }
}

