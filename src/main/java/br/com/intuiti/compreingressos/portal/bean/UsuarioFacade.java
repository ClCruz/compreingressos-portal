package br.com.intuiti.compreingressos.portal.bean;

import br.com.intuiti.compreingressos.portal.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public List<Usuario> findLazy(int inicio, int tamanho, String sortFilter, SortOrder sortOrder, Map<String, Object> filters){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
        Root<Usuario> usr = query.from(Usuario.class);
        query.select(usr);
        
        if(sortOrder == SortOrder.ASCENDING && (sortFilter != null)){
            query.orderBy(cb.asc(usr.get(sortFilter)));
        } else if(sortOrder == SortOrder.DESCENDING && (sortFilter != null)){
            query.orderBy(cb.desc(usr.get(sortFilter)));
        }

        List<Predicate> predicates = new ArrayList<>();
        
        for(String s : filters.keySet()){
            if(usr.get(s) != null){
                predicates.add(cb.like((Expression) usr.get(s), "%"+filters.get(s)+"%"));
            }
        }
        query.where(predicates.toArray( new Predicate[]{}));
        return getEntityManager().createQuery(query).setFirstResult(inicio).setMaxResults(tamanho).getResultList();
    }
    
    public int countUsuarios(int inicio, int tamanho, String sortFilter, SortOrder sortOrder, Map<String, Object> filters){        
        int contador = 0;
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
        Root<Usuario> usr = query.from(Usuario.class);
        query.select(usr);
        
        if(sortOrder == SortOrder.ASCENDING && (sortFilter != null)){
            query.orderBy(cb.asc(usr.get(sortFilter)));
        } else if(sortOrder == SortOrder.DESCENDING && (sortFilter != null)){
            query.orderBy(cb.desc(usr.get(sortFilter)));
        }

        List<Predicate> predicates = new ArrayList<>();
        
        for(String s : filters.keySet()){
            if(usr.get(s) != null){
                predicates.add(cb.like((Expression) usr.get(s), "%"+filters.get(s)+"%"));
            }
        }
        query.where(predicates.toArray( new Predicate[]{}));
        List<Usuario> result = getEntityManager().createQuery(query).getResultList();
        for(contador = 0; contador < result.size(); contador++){}
        return contador;
    }
    
    @PreDestroy
    public void destruct()
    {
        em.close();
    }

	public Usuario findUsuario(String userName) {
		try{
			return (Usuario) em.createNamedQuery("Usuario.findByCdLogin").setParameter("cdLogin", userName).getSingleResult();
		}catch(Exception e){
			return null;
		}
	}
}
