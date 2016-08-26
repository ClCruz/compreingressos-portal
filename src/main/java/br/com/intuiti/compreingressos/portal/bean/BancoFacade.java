/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.Banco;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class BancoFacade extends AbstractFacade<Banco> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BancoFacade() {
        super(Banco.class);
    }
    
    public List<Banco> findLazy(int inicio, int tamanho, String sortFilter, SortOrder sortOrder, Map<String, Object> filters){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Banco> query = cb.createQuery(Banco.class);
        Root<Banco> bnc = query.from(Banco.class);
        query.select(bnc);
        
        if(sortOrder == SortOrder.ASCENDING && (sortFilter != null)){
            query.orderBy(cb.asc(bnc.get(sortFilter)));
        } else if(sortOrder == SortOrder.DESCENDING && (sortFilter != null)){
            query.orderBy(cb.desc(bnc.get(sortFilter)));
        }

        List<Predicate> predicates = new ArrayList<>();
        
        for(String s : filters.keySet()){
            if(bnc.get(s) != null){
                predicates.add(cb.like((Expression) bnc.get(s), "%"+filters.get(s)+"%"));
            }
        }
        query.where(predicates.toArray( new Predicate[]{}));
        return getEntityManager().createQuery(query).setFirstResult(inicio).setMaxResults(tamanho).getResultList();
    }
    
    public int countBanco(int inicio, int tamanho, String sortFilter, SortOrder sortOrder, Map<String, Object> filters){
        int contador;
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Banco> query = cb.createQuery(Banco.class);
        Root<Banco> bnc = query.from(Banco.class);
        query.select(bnc);
        
        if(sortOrder == SortOrder.ASCENDING && (sortFilter != null)){
            query.orderBy(cb.asc(bnc.get(sortFilter)));
        } else if(sortOrder == SortOrder.DESCENDING && (sortFilter != null)){
            query.orderBy(cb.desc(bnc.get(sortFilter)));
        }

        List<Predicate> predicates = new ArrayList<>();
        
        for(String s : filters.keySet()){
            if(bnc.get(s) != null){
                predicates.add(cb.like((Expression) bnc.get(s), "%"+filters.get(s)+"%"));
            }
        }
        query.where(predicates.toArray( new Predicate[]{}));
        List<Banco> result = getEntityManager().createQuery(query).getResultList();
        for(contador = 0; contador < result.size(); contador++){}
        return contador;
    }
}
