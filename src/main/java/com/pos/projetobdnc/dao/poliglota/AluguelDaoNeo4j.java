package com.pos.projetobdnc.dao.poliglota;

import com.pos.projetobdnc.entity.Veiculo;
import com.pos.projetobdnc.enums.RelTypes;
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

    private final String path;
    GraphDatabaseService graph;

    public AluguelDaoNeo4j() {

//        path = "/usr/local/neo4j-community-3.0.4/data/databases/graph.db";
        path = "C:\\Users\\kaiqu\\OneDrive\\Documentos\\Neo4j\\default.graphdb";
        graph = new GraphDatabaseFactory()
                .newEmbeddedDatabase(new File(path));
    }

    public boolean create(Veiculo v) {

        try (Transaction tx = graph.beginTx()) {

            Node cliente = graph.findNode(Label.label("Cliente"), "id", v.getAluguel().getCliente().getId());
            if (cliente == null) {
                cliente = graph.createNode(Label.label("Cliente"));
                cliente.setProperty("id", v.getAluguel().getCliente().getId());
            }

            Node veiculo = graph.findNode(Label.label("Veiculo"), "id", v.getId());
            if (veiculo == null) {
                veiculo = graph.createNode(Label.label("Veiculo"));
                veiculo.setProperty("id", v.getId());
            }

            cliente.createRelationshipTo(veiculo, RelTypes.ALUGOU);
            tx.success();
        }
        return true;
    }

    public List<Integer> recomendados(Veiculo v) {

        List<Integer> recomendados = new ArrayList<>();

        try (Transaction tx = graph.beginTx()) {

            String query = "MATCH (v:Veiculo)<-[:ALUGOU]-(:Cliente)-[:ALUGOU]->"
                    + "(p2:Veiculo) WHERE p.id=" + v.getId() + " RETURN  p2.id";
            Result result = graph.execute(query);

            ResourceIterator<Integer> resourceIterator = result.columnAs("p2.id");
            while (resourceIterator.hasNext()) {
                recomendados.add(resourceIterator.next());
            }
        }
        return recomendados;
    }
}
