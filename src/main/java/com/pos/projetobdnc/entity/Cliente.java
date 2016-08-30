/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.entity;

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
public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String email;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Login login;

    public Cliente() {
    }

    public Cliente(String nome, String email, Login login) {
        this.nome = nome;
        this.email = email;
        this.login = login;
    }

    public Cliente(long id, String nome, String email, Login login) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", login=" + login + '}';
    }

}
