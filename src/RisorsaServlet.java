import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RisorsaServlet", value = "/RisorsaServlet")
public class RisorsaServlet extends HttpServlet {

    Model m = new ModelD2RQ("C:/Users/Carmine/IdeaProjects/Intelligent_Web/Mapping.ttl");

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dettagli = req.getParameter("dettagli");

        String querySparqlString = "SELECT DISTINCT ?property ?hasValue ?isValueOf\n" +
                "WHERE {\n" +
                "  {  <"+ dettagli +"> ?property ?hasValue }\n" +
                "  UNION\n" +
                "  { ?isValueOf ?property <"+ dettagli +"> }\n" +
                "}\n" +
                "ORDER BY (!BOUND(?hasValue)) ?property ?hasValue ?isValueOf";

        Query q = QueryFactory.create(querySparqlString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();

        List<String> risultati = new ArrayList<>();
        List<String> variabili = rs.getResultVars();

        while (rs.hasNext()){
            QuerySolution row = rs.nextSolution();
            for(String e : variabili) {
                if(row.get(e) == null){
                    risultati.add("-");
                }
                else if(row.get(e).toString().contains("^^http://www.w3.org/2001/XMLSchema#integer")) {
                    risultati.add(row.get(e).toString().replace("^^http://www.w3.org/2001/XMLSchema#integer", " "));
                } else if(row.get(e).toString().contains("^^http://www.w3.org/2001/XMLSchema#int")){
                    risultati.add(row.get(e).toString().replace("^^http://www.w3.org/2001/XMLSchema#int"," "));
                }else if(row.get(e).toString().contains("^^http://www.w3.org/2001/XMLSchema#decimal")) {
                    risultati.add(row.get(e).toString().replace("^^http://www.w3.org/2001/XMLSchema#decimal", " "));
                } else {
                    risultati.add(row.get(e).toString());
                }
            }
        }

        req.setAttribute("variabili", variabili);
        req.setAttribute("risultatidettagli", risultati);
        req.getRequestDispatcher("dettagli.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
