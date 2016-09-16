/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.Base;
import br.com.intuiti.compreingressos.portal.model.Evento;
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
public class EventoFacade extends AbstractFacade<Evento> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Evento> findAll(){
        return em.createNamedQuery("Evento.findAll", Evento.class).getResultList();
    }

    public List<Evento> findAll(Base base) {
        return em.createNamedQuery("Evento.findByBase", Evento.class).setParameter("idBase", base).setParameter("data", new java.util.Date(), TemporalType.TIMESTAMP).getResultList();
    }

    public EventoFacade() {
        super(Evento.class);
    }

}
