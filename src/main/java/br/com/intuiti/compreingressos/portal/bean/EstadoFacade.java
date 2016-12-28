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
	@Override
    public List<Estado> findAll(){
    	return getEntityManager().createNamedQuery("Estado.findAll").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public int findByIdEstado(Short idEstado){
    	List<Estado> lista = em.createNamedQuery("Estado.findByIdEstado").setParameter("idEstado", idEstado).getResultList();
    	return lista.size();
    }

    @SuppressWarnings("unchecked")
	public int findByDsEstado(String dsEstado){
        List<Estado> lista = em.createNamedQuery("Estado.findByDsEstado").setParameter("dsEstado", dsEstado).getResultList();
        return lista.size();
    }
    
    @SuppressWarnings("unchecked")
	public int findBySgEstado(String sgEstado){
        List<Estado> lista = em.createNamedQuery("Estado.findBySgEstado").setParameter("sgEstado", sgEstado).getResultList();
        return lista.size();
    }
    
    @SuppressWarnings("unchecked")
	public int findDsEstadoId(String dsEstado, Short idEstado){
        List<Estado> lista = em.createNamedQuery("Estado.findDsEstadoId").setParameter("dsEstado", dsEstado).setParameter("idEstado", idEstado).getResultList();
        return lista.size();
    }
    
    @SuppressWarnings("unchecked")
	public int findSgEstadoId(String sgEstado, Short idEstado){
        List<Estado> lista = em.createNamedQuery("Estado.findSgEstadoId").setParameter("sgEstado", sgEstado).setParameter("idEstado", idEstado).getResultList();
        return lista.size();
    }
    
    public void remove(Estado estado){
    	getEntityManager().createNativeQuery("delete FROM mw_estado WHERE id_estado = " + estado.getIdEstado()).executeUpdate();
    }
        
}
