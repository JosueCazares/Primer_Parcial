package org.utl.dsm.primer_parcial.controller.mongo;

//import cogodb.client.MongoDatabase;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class Mian {

    public static void main(String[] args) {
        try (MongoClient client = CConexion.getConnection()) {
//            create();
//            read();
//            Document d = new Document();
//            d.append("nombre", "WOW");
//            update("65d4e8c5ece149494b55768c", d);
                delete("65d4e8c5ece149494b55768c");
        }

    }

    public static void read() {
        try (MongoClient client = CConexion.getConnection()) {
            MongoDatabase db = client.getDatabase("dbmongo");
            FindIterable<Document> documents = db.getCollection("usuario").find();
            for (Document document : documents) {
                System.out.println(document);
            }
        }

    }

    public static void create() {
        try (MongoClient client = CConexion.getConnection()) {
            MongoDatabase db = client.getDatabase("dbmongo");
            MongoCollection<Document> collection = db.getCollection("usuario");
           try {
                // Inserts a sample document describing a movie into the collection
                InsertOneResult result = collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("nombre", "Yael")
                        .append("Matricula", 22001778));
//                        .append("genres", Arrays.asList("Documentary", "Comedy")));
                // Prints the ID of the inserted document
                System.out.println("Exito! id del documento: " + result.getInsertedId());
            
            // Prints a message if any exceptions occur during the operation
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
        
    }
    
 public static void update(String id, Document updateData) {
    try (MongoClient client = CConexion.getConnection()) {
        MongoDatabase db = client.getDatabase("dbmongo");
        MongoCollection<Document> collection = db.getCollection("usuario");
        
        Bson filter = Filters.eq("_id", new ObjectId(id)); // Reemplaza "_id" con el campo clave que identifica al documento que deseas actualizar
        
        try {
            UpdateResult result = collection.updateOne(filter, new Document("$set", updateData));
            
            if (result.getModifiedCount() > 0) {
                System.out.println("Éxito! Se actualizó el documento con ID: " + id);
            } else {
                System.out.println("No se encontró ningún documento para actualizar con ID: " + id);
            }
        } catch (MongoException me) {
            System.err.println("No se pudo actualizar debido a un error: " + me.getMessage());
        }
    }
}

    public static void delete(String id){
          try (MongoClient client = CConexion.getConnection()) {
        MongoDatabase db = client.getDatabase("dbmongo");
        MongoCollection<Document> collection = db.getCollection("usuario");
        
        Bson filter = Filters.eq("_id", new ObjectId(id)); // Reemplaza "_id" con el campo clave que identifica al documento que deseas actualizar
        
        try {
            DeleteResult result = collection.deleteOne(filter);
            
            if (result.getDeletedCount()> 0) {
                System.out.println("Éxito! Se elimino el documento con ID: " + id);
            } else {
                System.out.println("No se encontró ningún documento para eliminar con ID: " + id);
            }
        } catch (MongoException me) {
            System.err.println("No se pudo eliminar debido a un error: " + me.getMessage());
        }
    }
    }

}
