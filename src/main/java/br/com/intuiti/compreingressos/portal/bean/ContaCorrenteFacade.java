/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.ContaCorrente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gabrielqueiroz
 */
@Stateless
public class ContaCorrenteFacade extends AbstractFacade<ContaCorrente> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContaCorrenteFacade() {
        super(ContaCorrente.class);
    }

	public int maxIdTrasacao() {
		return (int) getEntityManager().createNamedQuery("ContaCorrente.maxIdTransacao").getSingleResult();
	}
    
}
