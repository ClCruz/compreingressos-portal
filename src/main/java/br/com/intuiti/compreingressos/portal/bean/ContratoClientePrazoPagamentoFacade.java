/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import br.com.intuiti.compreingressos.portal.model.ContratoClientePrazoPagamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class ContratoClientePrazoPagamentoFacade extends AbstractFacade<ContratoClientePrazoPagamento> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoClientePrazoPagamentoFacade() {
        super(ContratoClientePrazoPagamento.class);
    }
    
     public List<ContratoClientePrazoPagamento> findAll(ContratoCliente idContrato){
        return em.createNamedQuery("ContratoClientePrazoPagamento.findByIdContratoCliente", ContratoClientePrazoPagamento.class).setParameter("idContrato", idContrato).getResultList();
    }
}
