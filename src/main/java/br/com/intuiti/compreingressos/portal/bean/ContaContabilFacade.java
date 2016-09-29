/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.ContaContabil;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class ContaContabilFacade extends AbstractFacade<ContaContabil> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContaContabilFacade() {
        super(ContaContabil.class);
    }
    
    public boolean findNumero(String numero){
        List<ContaContabil> lista = em.createNamedQuery("ContaContabil.findByNrContaContabil").setParameter("nrContaContabil", numero).getResultList();
        if(lista.size() > 0){
        	return false;
        } else {
        	return true;
        }
    }
    
    public boolean findNumeroId(String numero, int id){
        List<ContaContabil> lista = em.createNamedQuery("ContaContabil.findByNrContaContabilId").setParameter("nrContaContabil", numero).setParameter("idContaContabil", id).getResultList();
        if(lista.size() > 0){
        	return false;
        } else {
        	return true;
        }
    }
    
}
