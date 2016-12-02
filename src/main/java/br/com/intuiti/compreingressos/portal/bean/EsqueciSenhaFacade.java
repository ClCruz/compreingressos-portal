/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.bean.AbstractFacade;
import br.com.intuiti.compreingressos.portal.model.EsqueciSenha;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gabrielqueiroz
 */
@Stateless
public class EsqueciSenhaFacade extends AbstractFacade<EsqueciSenha> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EsqueciSenhaFacade() {
        super(EsqueciSenha.class);
    }

	public EsqueciSenha findCode(String codigoE) {
		return (EsqueciSenha) getEntityManager().createNamedQuery("EsqueciSenha.findByCdEsqueciSenha").setParameter("cdEsqueciSenha", codigoE).getSingleResult();
	}
    
}
