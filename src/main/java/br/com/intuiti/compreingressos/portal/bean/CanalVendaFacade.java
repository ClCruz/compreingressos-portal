/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.CanalVenda;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class CanalVendaFacade extends AbstractFacade<CanalVenda> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CanalVendaFacade() {
        super(CanalVenda.class);
    }

    public boolean findDesc(String descricao) {
        List<CanalVenda> lista = em.createNamedQuery("CanalVenda.findByDsCanalVenda", CanalVenda.class).setParameter("dsCanalVenda", descricao).getResultList();
        return lista.size() > 0 ? false : true;
    }

	public boolean findDescId(String dsCanalVenda, Integer idCanalVenda) {
        List<CanalVenda> lista = em.createNamedQuery("CanalVenda.findByDsCanalVendaId", CanalVenda.class).setParameter("dsCanalVenda", dsCanalVenda).setParameter("idCanalVenda", idCanalVenda).getResultList();
		return lista.size() > 0 ? false : true;
	}

}
