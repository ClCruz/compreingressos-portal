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

import br.com.intuiti.compreingressos.portal.model.RegiaoGeografica;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class RegiaoGeograficaFacade extends AbstractFacade<RegiaoGeografica> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegiaoGeograficaFacade() {
        super(RegiaoGeografica.class);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<RegiaoGeografica> findAll(){
    	return getEntityManager().createNamedQuery("RegiaoGeografica.findAll").getResultList();
    }
    
    @SuppressWarnings("unchecked")
   	public int findByIdRegiaoGeografica(Integer idRegiaoGeografica){
       	List<RegiaoGeografica> lista = em.createNamedQuery("RegiaoGeografica.findByIdRegiaoGeografica").setParameter("idRegiaoGeografica", idRegiaoGeografica).getResultList();
       	return lista.size();
    }
    
    @SuppressWarnings("unchecked")
	public int findDs(String descricao){
    	List<RegiaoGeografica> lista = em.createNamedQuery("RegiaoGeografica.findByDsRegiaoGeografica").setParameter("dsRegiaoGeografica", descricao).getResultList();
    	return lista.size();
    }
}
