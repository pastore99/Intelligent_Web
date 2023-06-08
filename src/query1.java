import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

import java.util.List;

public class query1 {
    public static void main(String[] args){
        System.out.println(0);//d2r
        Model m = new ModelD2RQ("C:/Users/Carmine/IdeaProjects/Intelligent_Web/outfile.ttl");
        //
        String queryString = "SELECT DISTINCT ?property ?hasValue ?isValueOf\n" +
                "WHERE {\n" +
                "  {  <C:/Users/rocco/IdeaProjects/Intelligent_Web/outfile.ttl#mytable/http%3A%2F%2Fdati.senato.it%2Fsenatore%2F1103> ?property ?hasValue }\n" +
                "  UNION\n" +
                "  { ?isValueOf ?property <C:/Users/rocco/IdeaProjects/Intelligent_Web/outfile.ttl#mytable/http%3A%2F%2Fdati.senato.it%2Fsenatore%2F1103>}\n" +
                "}\n" +
                "ORDER BY (!BOUND(?hasValue)) ?property ?hasValue ?isValueOf";
        Query q = QueryFactory.create(queryString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        //System.out.println(rs.getResultVars() + "  prova variabili");
        List<String> variabili = rs.getResultVars();
        System.out.println(1);
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

