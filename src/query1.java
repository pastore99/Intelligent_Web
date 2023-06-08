import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

import java.util.List;

public class query1 {
    public static void main(String[] args){ //d2r
        Model m = new ModelD2RQ("C://Users//Carmine//Downloads//d2r-server-0.7//d2r-server-0.7//fuori.ttl");

        String queryString = "SELECT DISTINCT * WHERE {\n" +
                "  ?senatore ?proprieta ?oggetto\n" +
                "}\n" +
                "LIMIT 30";
        Query q = QueryFactory.create(queryString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        //System.out.println(rs.getResultVars() + "  prova variabili");
        List<String> variabili = rs.getResultVars();
        int i =0;
        while (rs.hasNext()){
            QuerySolution row = rs.nextSolution();
            for(String e : variabili){
                System.out.println(e + "    " + row.get(e) + " ********* elemento" + i++);
            }
        }
       m.close();
    }
}

