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

import br.com.intuiti.compreingressos.portal.model.Vendedor;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class VendedorFacade extends AbstractFacade<Vendedor> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendedorFacade() {
        super(Vendedor.class);
    }
    
    @SuppressWarnings("unchecked")
	public boolean findDsVendedor(String nome){
    	List<Vendedor> lista = getEntityManager().createNamedQuery("Vendedor.findByDsVendedor").setParameter("dsVendedor", nome).getResultList();
    	return lista.size() > 0 ? false : true;
    }
   
    @SuppressWarnings("unchecked")
    public boolean findDsVendedorId(Integer id, String nome){
		List<Vendedor> lista = getEntityManager().createNamedQuery("Vendedor.findByDsVendedorId").setParameter("idVendedor", id).setParameter("dsVendedor", nome).getResultList();
    	return lista.size() > 0 ? false : true;
    }
    
}
