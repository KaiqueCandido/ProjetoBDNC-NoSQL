/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.dao.filter;

import com.pos.projetobdnc.dao.poliglota.SessionDaoRedis;
import com.pos.projetobdnc.entity.Cliente;
import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kaiqu
 */
public class FilterSessionRedis implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession httpSession = ((HttpServletRequest) request).getSession(true);

        if (httpSession.getAttribute("ID_USUARIO") != null) {
            String idSession = httpSession.getId();
            long idUsuarioSession = (long) httpSession.getAttribute("ID_USUARIO");
            String idusuario = String.valueOf(idUsuarioSession);
            new SessionDaoRedis().createKey(idSession, idusuario);
        }

        String keyClienteRedis = new SessionDaoRedis().getKey(httpSession.getId());

        if (keyClienteRedis.length() > 0) {
            System.out.println("Key Cliente do Banco - " + keyClienteRedis);

            long value = (long) httpSession.getAttribute("ID_USUARIO");
            String keyClienteLogado = String.valueOf(value);

            if (keyClienteLogado.length() > 0) {
                System.out.println("Key Cliente Logado - " + keyClienteLogado);

                if (!keyClienteRedis.equalsIgnoreCase(keyClienteLogado)) {
                    System.out.println("Não são iguais tem que parar a aplicação");
                    request.getRequestDispatcher("erroCadastreseOuLogue.xhtml").forward(request, response);
                } else {
                    System.out.println("São iguais");
                    chain.doFilter(request, response);
                }
            } else {
                request.getRequestDispatcher("erroCadastreseOuLogue.xhtml").forward(request, response);
            }

        } else {
//            request.getRequestDispatcher("erroCadastreseOuLogue.xhtml").forward(request, response);
        }

        chain.doFilter(request, response);

        System.out.println("Metodo do Filter - " + httpSession.getId() + "\n\n\n\n\n\n\n\n\n\n\n");
//        System.out.println("Id usuario da seção - " + httpSession.getAttribute("ID_USUARIO"));
//        System.out.println(new SessionDaoRedis().getKey(httpSession.getId()));
    }

    @Override
    public void destroy() {

    }

}
