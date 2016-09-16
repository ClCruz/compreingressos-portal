package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.Base;
import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import br.com.intuiti.compreingressos.portal.model.Evento;
import br.com.intuiti.compreingressos.portal.model.LocalEvento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class ContratoClienteFacade extends AbstractFacade<ContratoCliente> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoClienteFacade() {
        super(ContratoCliente.class);
    }

    public ContratoCliente update(ContratoCliente entity) {
        return (ContratoCliente) getEntityManager().merge(entity);
    }
    
    public List<Evento> findAll(Base base) {
        return em.createNamedQuery("Evento.findByBase", Evento.class).setParameter("idBase", base).setParameter("data", new java.util.Date(), TemporalType.TIMESTAMP).getResultList();
    }
}
