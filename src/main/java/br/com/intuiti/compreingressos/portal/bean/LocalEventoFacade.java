/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.LocalEvento;
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
public class LocalEventoFacade extends AbstractFacade<LocalEvento> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalEventoFacade() {
        super(LocalEvento.class);
    }
    
    public List<LocalEvento> findAllOrderByDs(){
        return em.createNamedQuery("LocalEvento.findAllOrderBy", LocalEvento.class).getResultList();
    }

    public List<LocalEvento> findLazy(int inicio, int tamanho, String sortFilter, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<LocalEvento> query = cb.createQuery(LocalEvento.class);
        Root<LocalEvento> lclEv = query.from(LocalEvento.class);
        query.select(lclEv);
        
        if(sortOrder == SortOrder.ASCENDING && (sortFilter != null)){
            query.orderBy(cb.asc(lclEv.get(sortFilter)));
        } else if(sortOrder == SortOrder.DESCENDING && (sortFilter != null)){
            query.orderBy(cb.desc(lclEv.get(sortFilter)));
        }

        List<Predicate> predicates = new ArrayList<>();
        
        for(String s : filters.keySet()){
            if(lclEv.get(s) != null){
                predicates.add(cb.like((Expression) lclEv.get(s), "%"+filters.get(s)+"%"));
            }
        }
        query.where(predicates.toArray( new Predicate[]{}));

        return getEntityManager().createQuery(query).setFirstResult(inicio).setMaxResults(tamanho).getResultList();
    }

    public int countLocal(int inicio, int tamanho, String sortFilter, SortOrder sortOrder, Map<String, Object> filters) {
        int contador;
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<LocalEvento> query = cb.createQuery(LocalEvento.class);
        Root<LocalEvento> lclEv = query.from(LocalEvento.class);
        query.select(lclEv);

        List<Predicate> predicates = new ArrayList<>();
        
        for(String s : filters.keySet()){
            if(lclEv.get(s) != null){
                predicates.add(cb.like((Expression) lclEv.get(s), "%"+filters.get(s)+"%"));
            }
        }
        query.where(predicates.toArray( new Predicate[]{}));

        List<LocalEvento> result = getEntityManager().createQuery(query).getResultList();
        for (contador = 0; contador < result.size(); contador++) {
        }
        return contador;
    }

}
