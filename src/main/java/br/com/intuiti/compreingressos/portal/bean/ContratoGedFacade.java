package br.com.intuiti.compreingressos.portal.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import br.com.intuiti.compreingressos.portal.model.ContratoGed;

@Stateless
public class ContratoGedFacade extends AbstractFacade<ContratoGed> {
	
	@PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public ContratoGedFacade() {
		super(ContratoGed.class);
	}

	@SuppressWarnings("unchecked")
	public List<ContratoGed> findAll(ContratoCliente idContrato) {
		return em.createNamedQuery("ContratoGed.findByIdContratoCliente", ContratoGed.class).setParameter("idContrato", idContrato).getResultList();
	}
	
	public ContratoGed update(ContratoGed entity){
		return (ContratoGed) getEntityManager().merge(entity);
	}
	
	@Override
	public List<ContratoGed> findAll() {
		return getEntityManager().createNamedQuery("ContratoGed.findAll").getResultList();
	}
	
}
