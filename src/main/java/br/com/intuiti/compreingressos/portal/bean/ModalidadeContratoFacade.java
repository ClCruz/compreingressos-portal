/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.intuiti.compreingressos.portal.model.ModalidadeContrato;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class ModalidadeContratoFacade extends AbstractFacade<ModalidadeContrato> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModalidadeContratoFacade() {
        super(ModalidadeContrato.class);
    }
    
    
    @SuppressWarnings("unchecked")
	public boolean findDs(String descricao){
    	List<ModalidadeContrato> lista = em.createNamedQuery("ModalidadeContrato.findByDsModalidadeContrato").setParameter("dsModalidadeContrato", descricao).getResultList();
    	return lista.size() > 0 ? false : true;
    }

	@SuppressWarnings("unchecked")
	public boolean findDsId(String dsModalidadeContrato,
			Integer idModalidadeContrato) {
		List<ModalidadeContrato> lista = em.createNamedQuery("ModalidadeContrato.findByDsModalidadeContratoId").setParameter("dsModalidadeContrato", dsModalidadeContrato).setParameter("idModalidadeContrato", idModalidadeContrato).getResultList();
    	return lista.size() > 0 ? false : true;
	}
}
