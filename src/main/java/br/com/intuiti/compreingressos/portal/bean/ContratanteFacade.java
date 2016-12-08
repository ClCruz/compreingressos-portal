/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import java.util.List;

import br.com.intuiti.compreingressos.portal.model.Contratante;
import br.com.intuiti.compreingressos.portal.model.Estado;
import br.com.intuiti.compreingressos.portal.model.Municipio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class ContratanteFacade extends AbstractFacade<Contratante> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratanteFacade() {
        super(Contratante.class);
    }
    
    @Override
    public List<Contratante> findAll(){
    	return getEntityManager().createNamedQuery("Contratante.findAll").getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Municipio> findAll(Estado estado){
    	return em.createNamedQuery("Contratante.findAllMunicipio").setParameter("idEstado", estado).getResultList();
    }
    
}
