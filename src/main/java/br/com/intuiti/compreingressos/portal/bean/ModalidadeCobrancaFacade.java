/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.ModalidadeCobranca;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class ModalidadeCobrancaFacade extends AbstractFacade<ModalidadeCobranca> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModalidadeCobrancaFacade() {
        super(ModalidadeCobranca.class);
    }
 
    @Override
    public List<ModalidadeCobranca> findAll(){
    	return getEntityManager().createNamedQuery("ModalidadeCobranca.findAll").getResultList();
    }
    
}
