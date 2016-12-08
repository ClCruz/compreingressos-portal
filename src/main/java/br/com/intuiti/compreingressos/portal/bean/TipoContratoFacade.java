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

import br.com.intuiti.compreingressos.portal.model.TipoContrato;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class TipoContratoFacade extends AbstractFacade<TipoContrato> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoContratoFacade() {
        super(TipoContrato.class);
    }
    
    @Override
    public List<TipoContrato> findAll(){
    	return getEntityManager().createNamedQuery("TipoContrato.findAll").getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public boolean findDs(String descricao){
    	List<TipoContrato> lista = em.createNamedQuery("TipoContrato.findByDsTipoContrato").setParameter("dsTipoContrato", descricao).getResultList();
    	return lista.size() > 0 ? false : true;
    }

	@SuppressWarnings("unchecked")
	public boolean findDsId(String dsTipoContrato, Integer idTipoContrato) {
		List<TipoContrato> lista = em.createNamedQuery("TipoContrato.findByDsTipoContratoId").setParameter("dsTipoContrato", dsTipoContrato).setParameter("idTipoContrato", idTipoContrato).getResultList();
    	return lista.size() > 0 ? false : true;
	}
    
}
