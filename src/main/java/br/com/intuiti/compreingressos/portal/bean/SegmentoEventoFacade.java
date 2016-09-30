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

import br.com.intuiti.compreingressos.portal.model.SegmentoEvento;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class SegmentoEventoFacade extends AbstractFacade<SegmentoEvento> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegmentoEventoFacade() {
        super(SegmentoEvento.class);
    }
    
    @SuppressWarnings("unchecked")
	public boolean findDs(String descricao){
    	List<SegmentoEvento> lista = em.createNamedQuery("SegmentoEvento.findByDsSegmentoEvento").setParameter("dsSegmentoEvento", descricao).getResultList();
    	return lista.size() > 0 ? false : true;
    }
    
    @SuppressWarnings("unchecked")
	public boolean findDsId(String descricao, int id){
    	List<SegmentoEvento> lista = em.createNamedQuery("SegmentoEvento.findByDsSegmentoEventoId").setParameter("dsSegmentoEvento", descricao).setParameter("idSegmentoEvento", id).getResultList();
    	return lista.size() > 0 ? false : true;
    }
    
}
