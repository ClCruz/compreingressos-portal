package br.com.intuiti.compreingressos.portal.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import br.com.intuiti.compreingressos.portal.model.Base;
import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import br.com.intuiti.compreingressos.portal.model.ContratoClientePrazoPagamento;
import br.com.intuiti.compreingressos.portal.model.ContratoClienteTipoLancamento;
import br.com.intuiti.compreingressos.portal.model.Evento;

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
    
    public ContratoCliente findProcesso(Long idProcesso){
    	return (ContratoCliente) getEntityManager().createNamedQuery("ContratoCliente.findByIdProcesso").setParameter("idProcesso", idProcesso).getSingleResult();
    }
    
    @SuppressWarnings("unchecked")
	public List<ContratoClienteTipoLancamento> findProcessoTL(ContratoCliente contratoCliente){
    	return getEntityManager().createNamedQuery("ContratoClienteTipoLancamento.findByIdContratoCliente").setParameter("idContrato", contratoCliente).getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<ContratoClientePrazoPagamento> findProcessoPP(ContratoCliente contratoCliente){
    	return getEntityManager().createNamedQuery("ContratoClientePrazoPagamento.findByIdContratoCliente").setParameter("idContrato", contratoCliente).getResultList();
    }
    
    
    public Long exist(Long id){
    	return (Long) getEntityManager().createNamedQuery("ContratoCliente.findByIdProcessoNum").setParameter("idProcesso", id).getSingleResult();
    }
    
    public String findSituacao(Object id){
    	return (String) getEntityManager().createNamedQuery("ContratoCliente.findSituacao").setParameter("id", Integer.parseInt(id.toString())).getSingleResult();
    }
    
    public List<Evento> findAll(Base base) {
        return getEntityManager().createNamedQuery("Evento.findByBase", Evento.class).setParameter("idBase", base).setParameter("data", new java.util.Date(), TemporalType.TIMESTAMP).getResultList();
    }
    
    public void upStatus(String status, ContratoCliente contratoCliente){
	getEntityManager().createNativeQuery("UPDATE mw_contrato_cliente set in_status_contrato = '"+status+"' WHERE id_contrato_cliente = '"+contratoCliente.getIdContratoCliente()+"'").executeUpdate();
    }
}
