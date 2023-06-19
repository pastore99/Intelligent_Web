//import com.hp.hpl.jena.rdf.model.Model;
//
//import de.fuberlin.wiwiss.d2rq.jena.ModelD2RQ;
//
//
//
//public class Prova2ttl {
//
//    public static void main(String[] args) {
//
//        // Carica il modello D2RQ
//
//        ModelD2RQ model = new ModelD2RQ("your_mapping_file.ttl");
//
//
//
//        // Esegui il DESCRIBE sull'intero modello
//
//        Model rdfModel = model.execDescribe();
//
//
//
//        // Stampa il modello RDF in formato Turtle (TTL)
//
//        rdfModel.listStatements().forEachRemaining(statement -> {
//
//            System.out.println(statement.toString());
//
//        });
//
//    }
//
//}
