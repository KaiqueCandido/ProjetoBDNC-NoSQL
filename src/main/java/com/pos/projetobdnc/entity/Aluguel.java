/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.entity;

import com.pos.projetobdnc.interfaces.MongoDBObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.bson.Document;

/**
 *
 * @author kaiqu
 */
@Entity
public class Aluguel implements Serializable, MongoDBObject<Aluguel> {
    
    @Id
    @GeneratedValue
    private long id;
    private int saida;
    private int chegada;
    @OneToOne
    private Cliente cliente;
    private double valor;
    private long idVeiculo;
    
    public Aluguel() {
    }
    
    public Aluguel(int saida, int chegada, Cliente cliente, double valor, long veiculo) {
        this.saida = saida;
        this.chegada = chegada;
        this.cliente = cliente;
        this.valor = valor;
        this.idVeiculo = veiculo;
    }
    
    public Aluguel(long id, int saida, int chegada, Cliente cliente, double valor, long veiculo) {
        this.id = id;
        this.saida = saida;
        this.chegada = chegada;
        this.cliente = cliente;
        this.valor = valor;
        this.idVeiculo = veiculo;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public int getSaida() {
        return saida;
    }
    
    public void setSaida(int saida) {
        this.saida = saida;
    }
    
    public int getChegada() {
        return chegada;
    }
    
    public void setChegada(int chegada) {
        this.chegada = chegada;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public long getIdVeiculo() {
        return idVeiculo;
    }
    
    public void setIdVeiculo(long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
    
    @Override
    public String toString() {
        return "Aluguel{" + "id=" + id + ", saida=" + saida + ", chegada=" + chegada + ", cliente=" + cliente + ", valor=" + valor + ", idVeiculo=" + idVeiculo + '}';
    }
    
    @Override
    public Document toDocument() {
        Document doc = new Document();
        doc.append("id", id);
        doc.append("saida", saida);
        doc.append("chegada", chegada);
        doc.append("valor", valor);
        doc.append("cliente", cliente.toDocument());
        doc.append("idVeiculo", idVeiculo);
        
        return doc;
    }
    
    @Override
    public Aluguel fromDocument(Document next) {
        id = next.getLong("id");
        saida = next.getInteger("saida");
        chegada = next.getInteger("chegada");
        valor = next.getDouble("valor");
        cliente = new Cliente().fromDocument(next.get("cliente", Document.class));
        idVeiculo = next.getLong("idVeiculo");
        
        return this;
    }
    
}
