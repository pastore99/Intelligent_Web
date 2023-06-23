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

@WebServlet(name = "QueryServlet", value = "/QueryServlet")
public class QueryServlet extends HttpServlet {
    Model m = new ModelD2RQ("C:/Users/rocco/IdeaProjects/Intelligent_Web/Mapping.ttl");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String querySparqlString = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX vocab: <http://localhost:2020/vocab/resource/>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + req.getParameter("querySparql");

        Query q = QueryFactory.create(querySparqlString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();

        List<String> risultati = new ArrayList<>();
        List<String> variabili = rs.getResultVars();

        while (rs.hasNext()){
            QuerySolution row = rs.nextSolution();
            for(String e : variabili){
                if(row.get(e).toString().contains("^^http://www.w3.org/2001/XMLSchema#integer")) {
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
        req.setAttribute("risultati", risultati);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
