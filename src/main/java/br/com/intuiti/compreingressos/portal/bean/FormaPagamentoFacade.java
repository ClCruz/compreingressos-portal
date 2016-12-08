/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.intuiti.compreingressos.portal.model.FormaPagamento;
import br.com.intuiti.compreingressos.portal.model.TipoMeioPagamento;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class FormaPagamentoFacade extends AbstractFacade<FormaPagamento> {

	@PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public FormaPagamentoFacade() {
		super(FormaPagamento.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FormaPagamento> findAll(){
		return getEntityManager().createNamedQuery("FormaPagamento.findAll").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public boolean findDsMp(String dsFormaPagamento,
			TipoMeioPagamento inTipoMeioPagamento) {
		List<FormaPagamento> lista = em
				.createNamedQuery("FormaPagamento.findByDsMp")
				.setParameter("dsFormaPagamento", dsFormaPagamento)
				.setParameter("inTipoMeioPagamento", inTipoMeioPagamento)
				.getResultList();
		return lista.size() > 0 ? false : true;
	}

	@SuppressWarnings("unchecked")
	public boolean findDsMpId(String dsFormaPagamento,
			TipoMeioPagamento inTipoMeioPagamento, Integer idFormaPagamento) {
		List<FormaPagamento> lista = em
				.createNamedQuery("FormaPagamento.findByDsMpId")
				.setParameter("dsFormaPagamento", dsFormaPagamento)
				.setParameter("inTipoMeioPagamento", inTipoMeioPagamento)
				.setParameter("idFormaPagamento", idFormaPagamento)
				.getResultList();
		return lista.size() > 0 ? false : true;
	}
	
	@SuppressWarnings("unchecked")
	public List<FormaPagamento> findAtivo(){
		return getEntityManager().createNamedQuery("FormaPagamento.findByInAtivo").setParameter("inAtivo", true).getResultList();
	}
	
	public void remove(FormaPagamento formaPagamento){
		getEntityManager().createNamedQuery("delete FROM mw_forma_pagamento WHERE id_forma_pagamento = " + formaPagamento.getIdFormaPagamento()).executeUpdate();
	}
}
