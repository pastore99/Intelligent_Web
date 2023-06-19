import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(name = "TTLServlet", value = "/TTLServlet")
public class TTLServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model m = new ModelD2RQ("C:/Users/rocco/IdeaProjects/Intelligent_Web/outfile.ttl");
        String querySparqlString = req.getParameter("contenuto");
        System.out.println(querySparqlString);
        Query q = QueryFactory.create(querySparqlString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        Model model = ModelFactory.createDefaultModel();
        List<String> variabili = rs.getResultVars();
        variabili.remove(0);
        System.out.println(variabili);

        while (rs.hasNext()) {
            QuerySolution sol = rs.nextSolution();
            System.out.println(sol);
            Resource personResource = sol.getResource("?senatore");
            System.out.println(personResource);
            model.add(personResource, RDF.type, FOAF.Person);
            for (String v : variabili) {
                System.out.println(v);
                String name = sol.getLiteral("?"+ v).getString();
                System.out.println(name);
                // Aggiungi il risultato al modello

                model.add(personResource, RDFS.label, name);
            }
        }
        System.out.println(model);

    // Serializza il modello in formato TTL e salvalo su disco
        try (OutputStream out = new FileOutputStream("C:/Users/rocco/IdeaProjects/Intelligent_Web/src/query.ttl")) {
        model.write(out, "Turtle");
    } catch (Exception e) {
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
