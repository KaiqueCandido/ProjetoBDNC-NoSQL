/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.controller;

import com.pos.projetobdnc.entity.Cliente;
import com.pos.projetobdnc.entity.Login;
import com.pos.projetobdnc.service.ServiceCliente;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kaiqu
 */
@ManagedBean
@SessionScoped
public class ControllerCliente implements Serializable {

    @EJB
    private ServiceCliente serviceCliente;

    private Cliente cliente;
    private Login login;

    private String emailLogin;
    private String senhaLogin;

    public ControllerCliente() {
        this.cliente = new Cliente();
        this.login = new Login();
        this.emailLogin = new String();
        this.senhaLogin = new String();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public String getSenhaLogin() {
        return senhaLogin;
    }

    public void setSenhaLogin(String senhaLogin) {
        this.senhaLogin = senhaLogin;
    }

    public String addCliente() {
        cliente.setLogin(login);
        serviceCliente.salvar(cliente);
        cliente = new Cliente();
        return "";
    }

}
