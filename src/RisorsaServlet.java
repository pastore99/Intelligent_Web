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

    Model m = new ModelD2RQ("C:/Users/rocco/IdeaProjects/Intelligent_Web/outfile.ttl");

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String senatore = req.getParameter("senatore");
        String querySparqlString = "SELECT DISTINCT ?property ?hasValue\n" +
                "WHERE {\n" +
                "  {  <"+ senatore +"> ?property ?hasValue }\n" +
                "  UNION\n" +
                "  { ?isValueOf ?property <"+ senatore +"> }\n" +
                "}\n" +
                "ORDER BY (!BOUND(?hasValue)) ?property ?hasValue ?isValueOf";
        System.out.println(senatore);
        Query q = QueryFactory.create(querySparqlString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        List<String> risultati = new ArrayList<>();
        List<String> variabili = rs.getResultVars();
        while (rs.hasNext()){
            QuerySolution row = rs.nextSolution();
            for(String e : variabili){
                System.out.println(e + "    " + row.get(e) + " ********* elemento");
                if(row.get(e) == null)
                    risultati.add("");
                else
                    risultati.add(row.get(e).toString());
            }}
//        while (rs.hasNext()){
//            QuerySolution row = rs.nextSolution();
//            System.out.println(row);
//            risultati.add(row.toString());
//        }

        req.setAttribute("variabili", variabili);
        req.setAttribute("risultatisenatore", risultati);
        req.getRequestDispatcher("senatore.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
