/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.dao;

import com.pos.projetobdnc.entity.Veiculo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author kaiqu
 */
@Stateless
public class VeiculoDao {

    @PersistenceContext
    EntityManager em;

    public boolean salvar(Veiculo veiculo) {
        em.persist(veiculo);
        return true;
    }

    public boolean atualizar(Veiculo veiculo) {
        em.merge(veiculo);
        return true;
    }

    public boolean excluir(Veiculo veiculo) {
        em.remove(em.merge(veiculo));
        return true;
    }

    public Veiculo pesquisar(Class<Veiculo> entidade, Object key) {
        return em.find(entidade, key);
    }

    public List<Veiculo> listar() {
        TypedQuery<Veiculo> createQuery = em.createQuery("SELECT v FROM Veiculo v ORDER BY v.fabricante", Veiculo.class);
        List<Veiculo> veiculos = createQuery.getResultList();
        if (veiculos.size() > 0) {
            return veiculos;
        }
        return null;
    }
}
