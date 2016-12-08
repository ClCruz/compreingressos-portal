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
	public boolean findDs(String descricao){
    	List<RegiaoGeografica> lista = em.createNamedQuery("RegiaoGeografica.findByDsRegiaoGeografica").setParameter("dsRegiaoGeografica", descricao).getResultList();
    	return lista.size() > 0 ? false : true;
    }
    
    @SuppressWarnings("unchecked")
	public boolean findDsId(String descricao, int id){
    	List<RegiaoGeografica> lista = em.createNamedQuery("RegiaoGeografica.findByDsRegiaoGeograficaId").setParameter("dsRegiaoGeografica", descricao).setParameter("idRegiaoGeografica", id).getResultList();
    	return lista.size() > 0 ? false : true;
    }
    
}
