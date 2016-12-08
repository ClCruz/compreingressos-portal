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

import br.com.intuiti.compreingressos.portal.model.TipoLancamento;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class TipoLancamentoFacade extends AbstractFacade<TipoLancamento> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoLancamentoFacade() {
        super(TipoLancamento.class);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<TipoLancamento> findAll(){
    	return getEntityManager().createNamedQuery("TipoLancamento.findAll").getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<TipoLancamento> findAtivo(){
    	return getEntityManager().createNamedQuery("TipoLancamento.findByInAtivo").setParameter("inAtivo", true).getResultList();
    }
    
    public void remove(TipoLancamento tipoLancamento){
    	getEntityManager().createNamedQuery("delete FROM mw_tipo_lancamento WHERE id_tipo_lancamento = " + tipoLancamento.getIdTipoLancamento()).executeUpdate();
    }
}
