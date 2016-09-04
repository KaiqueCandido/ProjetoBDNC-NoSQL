/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.dao.filter;

import com.pos.projetobdnc.dao.poliglota.SessionDaoRedis;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author kaiqu
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Sessão criada " + se.getSession().getId());
//        new SessionDaoRedis().
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        String ultimoAcesso = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date(event.getSession().getLastAccessedTime()));
           System.out.println("Sessão expirada "+event.getSession().getId()+". Ultimo Acesso = "+ultimoAcesso);
    }
    
}
