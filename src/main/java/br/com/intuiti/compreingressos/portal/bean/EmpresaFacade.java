/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.Empresa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class EmpresaFacade extends AbstractFacade<Empresa> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Empresa> findAll(){
    	return getEntityManager().createNamedQuery("Empresa.findAll").getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public int findName(String nome){
        List<Empresa> lista = em.createNamedQuery("Empresa.findByDsEmpresa").setParameter("dsEmpresa", nome).getResultList();
        return lista.size();
    }
    
    @SuppressWarnings("unchecked")
	public int findNameId(String nome, int id){
        List<Empresa> lista = em.createNamedQuery("Empresa.findByDsEmpresaId").setParameter("dsEmpresa", nome).setParameter("idEmpresa", id).getResultList();
        return lista.size();
    }
    
    @SuppressWarnings("unchecked")
	public List<Empresa> findAtivo(){
    	return getEntityManager().createNamedQuery("Empresa.findByInAtivo").setParameter("inAtivo", true).getResultList();
    }
    
}
