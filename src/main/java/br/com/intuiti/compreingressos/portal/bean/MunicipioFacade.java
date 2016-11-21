/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.Estado;
import br.com.intuiti.compreingressos.portal.model.Municipio;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class MunicipioFacade extends AbstractFacade<Municipio> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }
    
    @SuppressWarnings("unchecked")
	public boolean findDesc(Estado estado, String descricao){
        List<Municipio> lista = em.createNamedQuery("Municipio.findDesc").setParameter("idEstado", estado).setParameter("dsMunicipio", descricao).getResultList();
        return lista.size() > 0 ? false : true ;
    }
    
    @SuppressWarnings("unchecked")
    public boolean findDesc(Estado estado, String descricao, Integer id){
    	List<Municipio> lista = getEntityManager().createNamedQuery("Municipio.findDescId").setParameter("idEstado", estado).setParameter("dsMunicipio", descricao).setParameter("idMunicipio", id).getResultList();
    	return lista.size() > 0 ? false : true;
    }
    
}
