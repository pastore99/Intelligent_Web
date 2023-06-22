import com.google.gson.Gson;
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

@WebServlet(name = "JSONServlet", value = "/JSONServlet")
public class JSONServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model m = new ModelD2RQ("C:/Users/rocco/IdeaProjects/Intelligent_Web/outfile2.ttl");
        String querySparqlString ="PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX vocab: <http://localhost:2020/vocab/resource/>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + req.getParameter("contenuto");
        System.out.println(querySparqlString);
        Query q = QueryFactory.create(querySparqlString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        List<String> risultati = new ArrayList<>();
        List<String> variabili = rs.getResultVars();
        int i =0;
        while (rs.hasNext()) {
            QuerySolution row = rs.nextSolution();
            for (String v : variabili) {
                //System.out.println(e + "    " + row.get(e) + " ********* elemento" + i++);
                if (row.get(v).toString().contains("^^http://www.w3.org/2001/XMLSchema#date")) {
                    risultati.add(row.get(v).toString().replace("^^http://www.w3.org/2001/XMLSchema#date", " "));
                } else if (row.get(v).toString().contains("^^http://www.w3.org/2001/XMLSchema#int")) {
                    risultati.add(row.get(v).toString().replace("^^http://www.w3.org/2001/XMLSchema#int", " "));
                } else {
                    risultati.add(row.get(v).toString());
                }
            }
        }

                String json = new Gson().toJson(risultati);
                System.out.println(json);

                File f = new File("C://Users//rocco//IdeaProjects//Intelligent_Web//query.json");
                try (FileWriter fileWriter = new FileWriter(f)) {
            fileWriter.write(json);
            System.out.println("File JSON creato correttamente.");
                } catch (IOException e) {
            e.printStackTrace();
        }








//        String xmlString = ResultSetFormatter;
//        File f = new File("C://Users//rocco//IdeaProjects//Intelligent_Web//src//query.json");
//        try (FileWriter fileWriter = new FileWriter(f)) {
//            fileWriter.write(xmlString);
//            System.out.println("File XML creato correttamente.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        m.close();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
