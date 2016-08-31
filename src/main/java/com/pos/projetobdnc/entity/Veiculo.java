/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.entity;

import com.pos.projetobdnc.enums.TipoVeiculo;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author kaiqu
 */
@Entity
public class Veiculo implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String placa;
    private String fabricante;
    private String nome;
    @OneToOne(cascade = CascadeType.ALL)
    private Aluguel aluguel;
    private String foto;
    private String descricao;
    private TipoVeiculo tipo;

    public Veiculo() {
    }

    public Veiculo(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public Veiculo(String placa, String fabricante, String nome, Aluguel aluguel, String foto, String descricao, TipoVeiculo tipo) {
        this.placa = placa;
        this.fabricante = fabricante;
        this.nome = nome;
        this.aluguel = aluguel;
        this.foto = foto;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Veiculo(long id, String placa, String fabricante, String nome, Aluguel aluguel, String foto, String descricao, TipoVeiculo tipo) {
        this.id = id;
        this.placa = placa;
        this.fabricante = fabricante;
        this.nome = nome;
        this.aluguel = aluguel;
        this.foto = foto;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "id=" + id + ", placa=" + placa + ", fabricante=" + fabricante + ", nome=" + nome + ", aluguel=" + aluguel + ", foto=" + foto + ", descricao=" + descricao + ", tipo=" + tipo + '}';
    }

}
