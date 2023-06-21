import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

import java.util.List;

public class query1 {
    public static void main(String[] args){
        System.out.println(0);//d2r
        Model m = new ModelD2RQ("C://Users//Carmine//IdeaProjects//Intelligent_Web//src//mappingClassicModel.ttl");
        //
        String queryString = """
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX owl: <http://www.w3.org/2002/07/owl#>
                PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
                PREFIX vocab: <http://localhost:2020/vocab/resource/>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                PREFIX map: <file:/C:/Program%20Files/d2r-server-0.7/d2r-server-0.7/mappingClassicModel.ttl#>
                PREFIX db: <http://localhost:2020/resource/>
                                SELECT ?employee ?employees_email ?employees_firstName ?employees_lastName ?offices_city ?offices_officeCode ?employees_officeCode\s
                                WHERE {
                                ?employee rdf:type vocab:employees.
                                ?offices rdf:type vocab:offices.
                                ?employee vocab:employees_email ?employees_email.
                                ?employee vocab:employees_firstName ?employees_firstName.
                                ?employee vocab:employees_lastName ?employees_lastName.
                                ?employee vocab:employees_officeCode ?employees_officeCode.
                                ?offices vocab:offices_officeCode ?offices_officeCode.
                                ?offices vocab:offices_city ?offices_city.
                            
                                }
                """;
        Query q = QueryFactory.create(queryString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        System.out.println(rs.getResultVars() + "  prova variabili");
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

