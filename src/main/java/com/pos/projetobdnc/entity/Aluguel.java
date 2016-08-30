/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author kaiqu
 */
@Entity
public class Aluguel implements Serializable {

    @Id
    @GeneratedValue
    private long id;    
    private int saida;    
    private int chegada;
    @OneToOne
    private Cliente cliente;
    private double valor;

    public Aluguel() {
    }
    

    public Aluguel(int saida, int chegada, Cliente cliente, double valor) {
        this.saida = saida;
        this.chegada = chegada;
        this.cliente = cliente;
        this.valor = valor;
    }

    public Aluguel(long id, int saida, int chegada, Cliente cliente, double valor) {
        this.id = id;
        this.saida = saida;
        this.chegada = chegada;
        this.cliente = cliente;
        this.valor = valor;
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

    @Override
    public String toString() {
        return "Aluguel{" + "id=" + id + ", saida=" + saida + ", chegada=" + chegada + ", cliente=" + cliente + ", valor=" + valor + '}';
    }

}
