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

import br.com.intuiti.compreingressos.portal.model.GeneroEvento;
import br.com.intuiti.compreingressos.portal.model.SegmentoEvento;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class GeneroEventoFacade extends AbstractFacade<GeneroEvento> {

	@PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public GeneroEventoFacade() {
		super(GeneroEvento.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GeneroEvento> findAll(){
		return getEntityManager().createNamedQuery("GeneroEvento.findAll").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public boolean findDsSg(String dsGeneroEvento,
			SegmentoEvento idSegmentoEvento) {
		List<GeneroEvento> lista = em.createNamedQuery("GeneroEvento.findDsSg")
				.setParameter("dsGeneroEvento", dsGeneroEvento)
				.setParameter("idSegmentoEvento", idSegmentoEvento)
				.getResultList();
		return lista.size() > 0 ? false : true;
	}

	@SuppressWarnings("unchecked")
	public boolean findDsSgId(String dsGeneroEvento,
			SegmentoEvento idSegmentoEvento, Integer idGeneroEvento) {
		List<GeneroEvento> lista = em.createNamedQuery("GeneroEvento.findDsSgId")
				.setParameter("dsGeneroEvento", dsGeneroEvento)
				.setParameter("idSegmentoEvento", idSegmentoEvento)
				.setParameter("idGeneroEvento", idGeneroEvento)
				.getResultList();
		return lista.size() > 0 ? false : true;
	}
	
	public void remove(GeneroEvento generoEvento){
		getEntityManager().createNativeQuery("delete FROM mw_genero_evento WHERE id_genero_evento = " + generoEvento.getIdGeneroEvento()).executeUpdate();
	}

}
