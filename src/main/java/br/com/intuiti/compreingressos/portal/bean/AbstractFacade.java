package br.com.intuiti.compreingressos.portal.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Id;
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
public abstract class AbstractFacade<T> {

	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	public void create(T entity) {
		getEntityManager().persist(entity);
	}

	public void edit(T entity) {
		getEntityManager().merge(entity);
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public T find(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	public boolean exist(Object s) {
		return (getEntityManager().find(entityClass, s) == null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
				.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll(int inicio, int tamanho, String sortFilter,
			SortOrder sortOrder, Map<String, Object> filters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query;
		query = (CriteriaQuery<T>) cb.createQuery(entityClass.getClass());
		Root<T> bnc;
		bnc = (Root<T>) query.from(entityClass);
		query.select(bnc);

		if (sortOrder == SortOrder.ASCENDING && (sortFilter != null)) {
			query.orderBy(cb.asc(bnc.get(sortFilter)));
		} else if (sortOrder == SortOrder.DESCENDING && (sortFilter != null)) {
			query.orderBy(cb.desc(bnc.get(sortFilter)));
		}

		List<Predicate> predicates = new ArrayList<>();

		for (String s : filters.keySet()) {
			if (bnc.get(s) != null) {
				if (filters.get(s) instanceof Boolean) {
					predicates.add(cb.equal((Expression) bnc.get(s),
							filters.get(s)));
				} else {
					predicates.add(cb.like((Expression) bnc.get(s), "%"
							+ filters.get(s) + "%"));
				}
			}
		}
		query.where(predicates.toArray(new Predicate[] {}));
		return getEntityManager().createQuery(query).setFirstResult(inicio)
				.setMaxResults(tamanho).getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int count(int inicio, int tamanho, String sortFilter,
			SortOrder sortOrder, Map<String, Object> filters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = (CriteriaQuery<T>) cb.createQuery(entityClass
				.getClass());
		Root<T> bnc;
		bnc = (Root<T>) query.from(entityClass);
		query.select(bnc);

		if (sortOrder == SortOrder.ASCENDING && (sortFilter != null)) {
			query.orderBy(cb.asc(bnc.get(sortFilter)));
		} else if (sortOrder == SortOrder.DESCENDING && (sortFilter != null)) {
			query.orderBy(cb.desc(bnc.get(sortFilter)));
		}

		List<Predicate> predicates = new ArrayList<>();

		for (String s : filters.keySet()) {
			if (bnc.get(s) != null) {
				if (filters.get(s) instanceof Boolean) {
					predicates.add(cb.equal((Expression) bnc.get(s),
							filters.get(s)));
				} else {
					predicates.add(cb.like((Expression) bnc.get(s), "%"
							+ filters.get(s) + "%"));
				}
			}
		}
		query.where(predicates.toArray(new Predicate[] {}));
		List<T> result = getEntityManager().createQuery(query).getResultList();
		return (result == null) ? 0 : result.size();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findId(int inicio, int tamanho, String sortFilter,
			SortOrder sortOrder, Map<String, Object> filters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query;
		query = (CriteriaQuery<T>) cb.createQuery(entityClass.getClass());
		Root<T> bnc;
		bnc = (Root<T>) query.from(entityClass);
		query.select(bnc);
		
		if (sortOrder == SortOrder.ASCENDING && (sortFilter != null)) {
			query.orderBy(cb.asc(bnc.get(sortFilter)));
		} else if (sortOrder == SortOrder.DESCENDING && (sortFilter != null)) {
			query.orderBy(cb.desc(bnc.get(sortFilter)));
		}

		List<Predicate> predicates = new ArrayList<>();

		for (String s : filters.keySet()) {
			if (bnc.get(s) != null) {
				if (filters.get(s) instanceof Boolean) {
					predicates.add(cb.equal((Expression) bnc.get(s),
							filters.get(s)));
				} else {
					predicates.add(cb.like((Expression) bnc.get(s), "%"
							+ filters.get(s) + "%"));
				}
			}
		}
		query.where(predicates.toArray(new Predicate[] {}));
		return getEntityManager().createQuery(query).setFirstResult(inicio)
				.setMaxResults(tamanho).getResultList();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(int[] range) {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
				.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0] + 1);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int count() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
				.getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
	
	public Class<?> getPrimaryKeyDeclaringClass(){
		   Class<?> type = entityClass.getClass();
		    for (Field f : type.getDeclaredFields()) {
		        if (f.getAnnotation(Id.class) != null) {
		        	System.out.println(f.getDeclaringClass());
		        	return f.getDeclaringClass();
		        }
		    }
		    return null;		
	}
}
