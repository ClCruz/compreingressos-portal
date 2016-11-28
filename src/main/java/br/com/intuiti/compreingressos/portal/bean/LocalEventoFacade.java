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

import br.com.intuiti.compreingressos.portal.model.Estado;
import br.com.intuiti.compreingressos.portal.model.LocalEvento;
import br.com.intuiti.compreingressos.portal.model.Municipio;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class LocalEventoFacade extends AbstractFacade<LocalEvento> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalEventoFacade() {
        super(LocalEvento.class);
    }
    
    @SuppressWarnings("unchecked")
    public List<Municipio> findAll(Estado estado){
    	return getEntityManager().createNamedQuery("LocalEvento.findAllMunicipio").setParameter("idEstado", estado).getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<LocalEvento> findAllOrderByDs(){
        return getEntityManager().createNamedQuery("LocalEvento.findAllOrderBy").setParameter("inAtivo", true).getResultList();
    }
}
