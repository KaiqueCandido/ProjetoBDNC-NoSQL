/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.controller;

import com.pos.projetobdnc.entity.Cliente;
import com.pos.projetobdnc.service.ServiceCliente;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kaiqu
 */
@ManagedBean
@SessionScoped
public class ControllerServidor implements Serializable {
        
    @EJB
    private ServiceCliente serviceCliente;

    private Cliente cliente;

    private String emailLogin;
    private String senhaLogin;

    public ControllerServidor() {
        cliente = new Cliente();
        emailLogin = new String();
        senhaLogin = new String();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public void logarCliente() {
        Cliente c = serviceCliente.login(emailLogin, senhaLogin);

        if (c != null) {
            cliente = c;
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("ID_USUARIO", this.cliente.getId());
        }
    }

    public String logout() {
        cliente = new Cliente();
        return "index.xhtml";
    }

    public String retornarAPaginaInicial() {
        return "index.xhtml";
    }

    public String retornarCadastroVeiculo() {
        return "cadastroVeiculo.xhtml";
    }

    public String retornarMinhasLocacoes() {
        return "minhasLocacoes.xhtml";
    }

    public String retornarCadastroCliente() {
        return "cadastroCliente.xhtml";
    }        
}
