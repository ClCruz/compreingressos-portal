/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import br.com.intuiti.compreingressos.portal.model.ContratoClienteTipoLancamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class ContratoClienteTipoLancamentoFacade extends AbstractFacade<ContratoClienteTipoLancamento> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<ContratoClienteTipoLancamento> findAll(ContratoCliente id){
        return em.createNamedQuery("ContratoClienteTipoLancamento.findByIdContratoCliente", ContratoClienteTipoLancamento.class).setParameter("idContrato", id).getResultList();
    }

    public ContratoClienteTipoLancamentoFacade() {
        super(ContratoClienteTipoLancamento.class);
    }
    
    public void delete(ContratoCliente id){
        em.createQuery("delete from ContratoClienteTipoLancamento c WHERE c.idContratoCliente = :id").setParameter("id", id).executeUpdate();
    }
    
    @Override
    public void remove(ContratoClienteTipoLancamento id) {
    	getEntityManager().createNativeQuery("delete FROM mw_contrato_cliente_tipo_lancamento WHERE id_contrato_cliente_tipo_lancamento = :id").setParameter("id", id).executeUpdate();
    }
}
