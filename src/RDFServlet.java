import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "RDFServlet", value = "/RDFServlet")
public class RDFServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random random = new Random();
        int numeroRandom = random.nextInt(587456);
        Model m = new ModelD2RQ("C:/Users/Carmine/IdeaProjects/Intelligent_Web/outfile.ttl");
        String querySparqlString = req.getParameter("contenuto");
        //System.out.println(querySparqlString);
        Query q = QueryFactory.create(querySparqlString);
        ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
        String outputFile = "C:/Users/Carmine/IdeaProjects/Intelligent_Web/src/queryRDF" +  numeroRandom + ".rdf";
        Model outputModel = ResultSetFormatter.toModel(rs);
        outputModel.write(new FileOutputStream(outputFile), "RDF/XML");
        //FileOutputStream f = new FileOutputStream(outputFile);
        //ResultSetFormatter.outputAsRDF(f,"RDF", rs);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
