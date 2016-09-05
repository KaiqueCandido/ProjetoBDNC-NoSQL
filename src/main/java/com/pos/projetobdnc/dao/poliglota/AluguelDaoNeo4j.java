package com.pos.projetobdnc.dao.poliglota;

import com.pos.projetobdnc.entity.Aluguel;
import com.pos.projetobdnc.entity.Cliente;
import com.pos.projetobdnc.entity.Veiculo;
import com.pos.projetobdnc.enums.RelTypes;
import com.pos.projetobdnc.enums.TipoVeiculo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class AluguelDaoNeo4j {

    private final String path;
    GraphDatabaseService graph;

    public AluguelDaoNeo4j() {

//        path = "/usr/local/neo4j-community-3.0.4/data/databases/graph.db";
        path = "C:\\Users\\kaiqu\\OneDrive\\Documentos\\Neo4j";
        graph = new GraphDatabaseFactory()
                .newEmbeddedDatabase(new File(path));
    }

    public boolean create(Veiculo v) {

        try (Transaction tx = graph.beginTx()) {
            System.out.println("Entrou");

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
        System.out.println("Saiu");
        return true;
    }

    public List<Long> recomendados(Veiculo v) {

        List<Long> recomendados = new ArrayList<>();

        try (Transaction tx = graph.beginTx()) {

            String query = "MATCH (v:Veiculo)<-[:ALUGOU]-(:Cliente)-[:ALUGOU]->"
                    + "(v2:Veiculo) WHERE v.id=" + v.getId() + " RETURN  v2.id";
            Result result = graph.execute(query);

            ResourceIterator<Long> resourceIterator = result.columnAs("v2.id");
            while (resourceIterator.hasNext()) {
                recomendados.add(resourceIterator.next());
            }
        }
        return recomendados;
    }

    public static void main(String[] args) {        
        AluguelDaoNeo4j adn = new AluguelDaoNeo4j();
        Veiculo v = new Veiculo(0, "Placa", "Fabricante", "Nome", new Aluguel(0, 10, 10, new Cliente(100, "Nome", "Email", null), 200, 0), null, null, TipoVeiculo.SUV);
        adn.create(v);
        List<Long> recomendados = adn.recomendados(v);
        for (Long recomendado : recomendados) {
            System.out.println(recomendado.toString());
            
        }
    }
    
}
