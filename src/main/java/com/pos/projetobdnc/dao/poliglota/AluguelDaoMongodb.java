package com.pos.projetobdnc.dao.poliglota;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.pos.projetobdnc.entity.Aluguel;
import com.pos.projetobdnc.entity.Cliente;
import com.pos.projetobdnc.entity.Login;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class AluguelDaoMongodb {

    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    public AluguelDaoMongodb() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("bdnc");
        collection = database.getCollection("Alugueis");
    }

    public boolean create(Aluguel a) {
        collection.insertOne(a.toDocument());
        System.out.println("Criou" + a.toDocument().toJson());
        return true;
    }

    public Aluguel read(int id) {
//        MongoCursor<Document> cursor = collection.find(eq("_id", id)).iterator();
        MongoCursor<Document> cursor = collection.find().iterator();
        Aluguel aluguel = null;
        List<Aluguel> alugueis = new ArrayList<>();
        if (cursor.hasNext()) {
            aluguel = new Aluguel().fromDocument(cursor.next());
            alugueis.add(aluguel);
        }
        alugueis.forEach(a -> System.out.println(a.getId()));
        return aluguel;
    }

    public List<Aluguel> listAll() {
        List<Aluguel> alugueis = new ArrayList<>();
        FindIterable<Document> find = collection.find();
        for (Document find1 : find) {
            System.out.println(find1.toJson());
            Aluguel fromDocument = new Aluguel().fromDocument(find1);
            alugueis.add(fromDocument);
        }
        return alugueis;
    }

    public static void main(String[] args) {

//        MongoClient mongoClient;
//        MongoDatabase database;
//        MongoCollection<Document> collection;
//
//        mongoClient = new MongoClient("localhost", 27017);
//        database = mongoClient.getDatabase("bdnc");
//        database.createCollection("Alugueis");
//        collection = database.getCollection("Aluguel");
        
        
        AluguelDaoMongodb adm = new AluguelDaoMongodb();
        
        adm.listAll().forEach(a -> System.out.println(a.toString()));
    }
}
