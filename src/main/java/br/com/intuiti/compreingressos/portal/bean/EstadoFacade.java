/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.Estado;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class EstadoFacade extends AbstractFacade<Estado> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoFacade() {
        super(Estado.class);
    }
    
    @SuppressWarnings("unchecked")
	public boolean findES(String estado, String sigla){
        List<Estado> lista = em.createNamedQuery("Estado.findES").setParameter("estado", estado).setParameter("sigla", sigla).getResultList();
        return lista.size() > 0 ? false : true;
    }
    
    @SuppressWarnings("unchecked")
	public boolean findES(String estado, String sigla, Short id){
        List<Estado> lista = em.createNamedQuery("Estado.findESID").setParameter("estado", estado).setParameter("sigla", sigla).setParameter("id", id).getResultList();
        return lista.size() > 0 ? false : true;
    }
        
}
