package br.com.intuiti.compreingressos.portal.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.intuiti.compreingressos.portal.model.RestricaoEstorno;


@Stateless
public class RestricaoEstornoFacade extends AbstractFacade<RestricaoEstorno> {

	@PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RestricaoEstornoFacade() {
    	super(RestricaoEstorno.class);
    }

}
