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

import br.com.intuiti.compreingressos.portal.model.TipoMeioPagamento;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class TipoMeioPagamentoFacade extends AbstractFacade<TipoMeioPagamento> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoMeioPagamentoFacade() {
        super(TipoMeioPagamento.class);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<TipoMeioPagamento> findAll(){
    	return getEntityManager().createNamedQuery("TipoMeioPagamento.findAll").getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public int findByDsTipoMeioPagamento(String dsTipoMeioPagamento) {
    	List<TipoMeioPagamento> lista = getEntityManager().createNamedQuery("TipoMeioPagamento.findByDsTipoMeioPagamento").setParameter("dsTipoMeioPagamento", dsTipoMeioPagamento).getResultList();
    	return lista.size();
    }
    
    @SuppressWarnings("unchecked")
   	public int findByInTipoMeioPagamento(String inTipoMeioPagamento) {
       	List<TipoMeioPagamento> lista = getEntityManager().createNamedQuery("TipoMeioPagamento.findByInTipoMeioPagamento").setParameter("inTipoMeioPagamento", inTipoMeioPagamento).getResultList();
       	return lista.size();
    }
    
    @SuppressWarnings("unchecked")
   	public int findDsTipoMeioPagamentoId(String dsTipoMeioPagamento, String inTipoMeioPagamento) {
       	List<TipoMeioPagamento> lista = getEntityManager().createNamedQuery("TipoMeioPagamento.findDsTipoMeioPagamentoId").setParameter("dsTipoMeioPagamento", dsTipoMeioPagamento).setParameter("inTipoMeioPagamento", inTipoMeioPagamento).getResultList();
       	return lista.size();
       }
}
