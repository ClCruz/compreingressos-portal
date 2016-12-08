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

import br.com.intuiti.compreingressos.portal.model.TipoLocal;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class TipoLocalFacade extends AbstractFacade<TipoLocal> {

	@PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TipoLocalFacade() {
		super(TipoLocal.class);
	}

	@Override
	public List<TipoLocal> findAll(){
		return getEntityManager().createNamedQuery("TipoLocal.findAll").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public boolean findDs(String descricao) {
		List<TipoLocal> lista = em
				.createNamedQuery("TipoLocal.findByDsTipoLocal")
				.setParameter("dsTipoLocal", descricao).getResultList();
		return lista.size() > 0 ? false : true;
	}

	@SuppressWarnings("unchecked")
	public boolean findDsId(String descricao, Integer id) {
		List<TipoLocal> lista = em
				.createNamedQuery("TipoLocal.findByDsTipoLocalId")
				.setParameter("dsTipoLocal", descricao)
				.setParameter("idTipoLocal", id).getResultList();
		return lista.size() > 0 ? false : true;
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoLocal> findAtivo(){
		return getEntityManager().createNamedQuery("TipoLocal.findByInAtivo").setParameter("inAtivo", true).getResultList();
	}
}
