package br.com.intuiti.compreingressos.portal.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.intuiti.compreingressos.portal.model.Banco;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class BancoFacade extends AbstractFacade<Banco> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BancoFacade() {
        super(Banco.class);
    }
    
    @SuppressWarnings("unchecked")
	public List<Banco> findAtivo(){
    	return getEntityManager().createNamedQuery("Banco.findByInAtivo").setParameter("inAtivo", true).getResultList();
    }
}
