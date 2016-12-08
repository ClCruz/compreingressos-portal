package br.com.intuiti.compreingressos.portal.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.intuiti.compreingressos.portal.model.TipoTransacao;

/**
 *
 * @author Intuiti 04
 */
@Stateless
public class TipoTransacaoFacade extends AbstractFacade<TipoTransacao> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoTransacaoFacade() {
        super(TipoTransacao.class);
    }
	    
    @Override
    public List<TipoTransacao> findAll() {
    	return getEntityManager().createNamedQuery("TipoTransacao.findAll").getResultList();
    }
    
}
