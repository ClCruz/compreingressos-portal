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
	public boolean findTipoMeio(String inTipoMeioPagamento, String dsTipoMeioPagamento){
    	List<TipoMeioPagamento> lista = getEntityManager().createNamedQuery("TipoMeioPagamento.findByInDs").setParameter("inTipoMeioPagamento", inTipoMeioPagamento).setParameter("dsTipoMeioPagamento", dsTipoMeioPagamento).getResultList();
    	return lista.size() > 0 ? false : true;
    }
    
}
