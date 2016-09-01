package com.pos.projetobdnc.dao.poliglota;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.pos.projetobdnc.entity.Aluguel;
import org.bson.Document;

public class AluguelDaoMongodb {
    
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;
    
    public AluguelDaoMongodb(){
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("testes");
        collection = database.getCollection("Aluguel");
    }
    
    public boolean create(Aluguel a){
        collection.insertOne(a.toDocument());
        return true;
    }
    
    public Aluguel read(int id){
        MongoCursor<Document> cursor = collection.find(eq("_id",id)).iterator();
        Aluguel aluguel = null;
        if(cursor.hasNext()){
            aluguel = new Aluguel().fromDocument(cursor.next());
        }
        return aluguel;
    }
    
}
