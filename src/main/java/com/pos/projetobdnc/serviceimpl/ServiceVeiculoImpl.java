/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.serviceimpl;

import com.pos.projetobdnc.dao.VeiculoDao;
import com.pos.projetobdnc.entity.Veiculo;
import com.pos.projetobdnc.service.ServiceVeiculo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kaiqu
 */
@Stateless
public class ServiceVeiculoImpl implements ServiceVeiculo {

    @EJB
    private VeiculoDao dao;

    @Override
    public boolean salvar(Veiculo veiculo) {
        return dao.salvar(veiculo);
    }

    @Override
    public boolean atualizar(Veiculo veiculo) {
        return dao.atualizar(veiculo);
    }

    @Override
    public boolean excluir(Veiculo veiculo) {
        return dao.excluir(veiculo);
    }

    @Override
    public Veiculo pesquisar(Class<Veiculo> entidade, Object key) {
        return dao.pesquisar(entidade, key);
    }

    @Override
    public List<Veiculo> listar() {
        return dao.listar();
    }
}
