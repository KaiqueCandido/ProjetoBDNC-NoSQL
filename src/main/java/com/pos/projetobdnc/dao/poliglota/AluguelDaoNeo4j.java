package com.pos.projetobdnc.dao.poliglota;

import com.pos.projetobdnc.entity.Aluguel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class AluguelDaoNeo4j {

    private String path;
    GraphDatabaseService graph;
    
    public AluguelDaoNeo4j(){
        path = "/usr/local/neo4j-community-3.0.4/data/databases/graph.db";
        graph = new GraphDatabaseFactory()
                .newEmbeddedDatabase(new File(path));
    }

    public boolean create(Aluguel a){
        
        try(Transaction tx = graph.beginTx()){                        
            
            Node cliente = graph.findNode(Label.label("Cliente"), "id", a.getCliente().getId());
            
            if(cliente==null){
                cliente = graph.createNode(Label.label("Usuario"));
                cliente.setProperty("id", a.getUsuario().getId());
            }
            
            List<Node> produtos = new ArrayList<>();
            
            for(ItemCompra item : a.getItens()){
                Node produto = graph.findNode(Label.label("Produto"), "id", item.getProduto().getId());
                if(produto==null){
                    produto = graph.createNode(Label.label("Produto"));
                    produto.setProperty("id", item.getProduto().getId());
                }
                produtos.add(produto);
            }
            
            for(Node n : produtos){
                cliente.createRelationshipTo(n, RelTypes.COMPROU);
            }
            
            tx.success();
            
        }
        
        return true;
        
    }
    
    public List<Integer> recomendados(Produto p){
       
        List<Integer> recomendados = new ArrayList<>();
        
        try(Transaction tx = graph.beginTx()){
            
            String query = "MATCH (p:Produto)<-[:COMPROU]-(:Usuario)-[:COMPROU]->"
                    + "(p2:Produto) WHERE p.id="+ p.getId() +" RETURN  p2.id";
            Result result = graph.execute(query);
            
            ResourceIterator<Integer> resourceIterator = result.columnAs("p2.id");
            while(resourceIterator.hasNext()){
                recomendados.add(resourceIterator.next());
            }
            
        }
        
        return recomendados;
        
    }
    
    
    
}
