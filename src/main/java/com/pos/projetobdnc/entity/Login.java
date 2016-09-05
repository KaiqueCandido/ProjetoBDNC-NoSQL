/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.entity;

import com.pos.projetobdnc.interfaces.MongoDBObject;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.bson.Document;

/**
 *
 * @author kaiqu
 */
@Entity
public class Login implements Serializable, MongoDBObject<Object> {
    
    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String senha;
    
    public Login() {
    }
    
    public Login(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
    
    public Login(long id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Override
    public String toString() {
        return "Login{" + "id=" + id + ", login=" + login + ", senha=" + senha + '}';
    }
    
    @Override
    public Document toDocument() {
        Document doc = new Document();
        doc.append("id", id);
        doc.append("login", login);
        doc.append("senha", senha);
        
        return doc;
    }
    
    @Override
    public Login fromDocument(Document next) {
        id = next.getLong("id");
        login = next.getString("login");
        senha = next.getString("senha");
        
        return this;
    }
    
}
