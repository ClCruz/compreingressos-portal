package br.com.intuiti.compreingressos.portal.lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.naming.Context;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.intuiti.compreingressos.portal.bean.AbstractFacade;

public class Lazy<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;
	private Class<T> entityClass;
	private List<T> objList = null;
	private AbstractFacade<T> ejbFacade;
	
	public Lazy(List<T> objList){
		this.objList = objList;
	}
	
	@PostConstruct
	public void init(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	public AbstractFacade<T> getFacade() {
		return ejbFacade;
	}
	
	@Override
	public List<T> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		objList = new ArrayList<>();
		try {
			Context ctx = new javax.naming.InitialContext();
			AbstractFacade<T> objFacade = (AbstractFacade<T>) ctx.lookup("java:global/compreingressos-portal/BancoFacade!br.com.intuiti.compreingressos.portal.bean.BancoFacade");
			objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
			setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
			setPageSize(pageSize);
		} catch (Exception e) {
			System.out.println("Erro: "+e);
		}
		return objList;
	}
	
	@Override
	public T getRowData(String rowKey) {
		String id = String.valueOf(rowKey);
		for(T obj : objList){
			if(id.equals(obj)){
				return obj;
			}
		}
		return null;
	}
	
	@Override
	public Object getRowKey(T object) {
		return object;
	}

	
}
