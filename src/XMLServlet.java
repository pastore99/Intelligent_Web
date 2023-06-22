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

@WebServlet(name = "XMLServlet", value = "/XMLServlet")
public class XMLServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model m = new ModelD2RQ("C:/Users/Carmine/IdeaProjects/Intelligent_Web/outfile2.ttl");
        String querySparqlString = req.getParameter("contenuto");
        System.out.println(querySparqlString);
        Query q = QueryFactory.create(querySparqlString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        String xmlString = ResultSetFormatter.asXMLString(rs);
        File f = new File("C://Users//Carmine//IdeaProjects//Intelligent_Web//query.xml");
        try (FileWriter fileWriter = new FileWriter(f)) {
            fileWriter.write(xmlString);
            System.out.println("File XML creato correttamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        m.close();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
