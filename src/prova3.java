
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

import java.sql.SQLException;

public class prova3 {
    public static void main(String[] args) throws SQLException { //d2r
        Model m = new ModelD2RQ("C:/Users/Carmine/Downloads/outfile2.ttl");
/*
        String queryString = "PREFIX osr: <http://dati.senato.it/osr/>\n" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                "\n" +
                "SELECT DISTINCT ?senatore ?nome ?cognome ?inizioMandato ?legislatura ?tipoMandato\n" +
                "WHERE {\n" +
                "    ?senatore a osr:Senatore.\n" +
                "    ?senatore foaf:firstName ?nome.\n" +
                "    ?senatore foaf:lastName ?cognome.\n" +
                "    ?senatore osr:mandato ?mandato.\n" +
                "    ?mandato osr:legislatura ?legislatura.\n" +
                "    ?mandato osr:inizio ?inizioMandato.\n" +
                "    ?mandato osr:tipoMandato ?tipoMandato.\n" +
                "    OPTIONAL { ?mandato osr:fine ?df. }\n" +
                "    FILTER(!bound(?df))\n" +
                "} ORDER BY ?cognome ?nome";
        Query q = QueryFactory.create(queryString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        while (rs.hasNext()){
            QuerySolution row = rs.nextSolution();
            System.out.println(row);
        }
*/
       m.close();
    }
}

