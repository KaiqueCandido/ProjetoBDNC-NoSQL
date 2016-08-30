/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.service;

import com.pos.projetobdnc.entity.Veiculo;
import java.util.List;

/**
 *
 * @author kaiqu
 */
public interface ServiceVeiculo {

    public boolean salvar(Veiculo veiculo);

    public boolean atualizar(Veiculo veiculo);

    public boolean excluir(Veiculo veiculo);

    public Veiculo pesquisar(Class<Veiculo> entidade, Object key);

    public List<Veiculo> listar();
}
