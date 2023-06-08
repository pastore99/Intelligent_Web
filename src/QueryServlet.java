import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(name = "QueryServlet", value = "/QueryServlet")
public class QueryServlet extends HttpServlet {
    List<QuerySolution> elementiQuery = new ArrayList<>();
    int i = 45;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model m = new ModelD2RQ("C://Users//Carmine//Downloads//d2r-server-0.7//d2r-server-0.7//fuori.ttl");
        String querySparqlString = req.getParameter("querySparql");
        System.out.println(querySparqlString);
        Query q = QueryFactory.create(querySparqlString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        //String xmlString = ResultSetFormatter.asXMLString(rs);
        while (rs.hasNext()){
            QuerySolution row = rs.nextSolution();
            elementiQuery.add(row);
            System.out.println(row);
        }

       /* File f = new File("C://Users//Carmine//IdeaProjects//Intelligent_Web//src//query.xml");
        try (FileWriter fileWriter = new FileWriter(f)) {
            fileWriter.write(xmlString);
            System.out.println("File XML creato correttamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        m.close();
        req.setAttribute("elementiQuery", elementiQuery);
       RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
       dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
