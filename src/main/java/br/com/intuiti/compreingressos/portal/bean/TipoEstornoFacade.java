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

import br.com.intuiti.compreingressos.portal.model.TipoEstorno;

/**
 *
 * @author Intuiti 04
 */
@Stateless
public class TipoEstornoFacade extends AbstractFacade<TipoEstorno> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEstornoFacade() {
        super(TipoEstorno.class);
    }
    
    @SuppressWarnings("unchecked")
    public boolean findDsTipoEstorno(String dsTipoEstorno){
		List<TipoEstorno> lista = getEntityManager().createNamedQuery("TipoEstorno.findByDsTipoEstorno").setParameter("dsTipoEstorno", dsTipoEstorno).getResultList();
    	return lista.size() > 0 ? false : true;
    }
    
    @SuppressWarnings("unchecked")
    public boolean findDsTipoEstorno(String dsTipoEstorno, Integer idTipoEstorno){
		List<TipoEstorno> lista = getEntityManager().createNamedQuery("TipoEstorno.findByDsIdTipoEstorno").setParameter("dsTipoEstorno", dsTipoEstorno).setParameter("idTipoEstorno", idTipoEstorno).getResultList();
    	return lista.size() > 0 ? false : true;
    }
    
}
