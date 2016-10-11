package br.com.intuiti.compreingressos.portal.lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.intuiti.compreingressos.portal.bean.AbstractFacade;

public class Lazy<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;
	private Class<T> entity;
	private List<T> list = null;
	private AbstractFacade<T> ejbFacade;
	
	public Lazy(List<T> list, Class<T> entity){
		this.list = list;
		this.entity = entity;
	}
	
	public AbstractFacade<T> getFacade() {
		return ejbFacade;
	}
	
	@Override
	public List<T> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		list = new ArrayList<>();
		try {
			Context ctx = new javax.naming.InitialContext();
			@SuppressWarnings("unchecked")
			AbstractFacade<T> objFacade = (AbstractFacade<T>) ctx.lookup("java:global/compreingressos-portal/"+entity.getSimpleName()+"Facade!br.com.intuiti.compreingressos.portal.bean."+entity.getSimpleName()+"Facade");
			list = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
			setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
			setPageSize(pageSize);
		} catch (Exception ex) {
            Logger.getLogger(entity.getName()).log(Level.SEVERE, null, ex);
        }
		return list;
	}
	
	@Override
	public T getRowData(String rowKey) {
		String id = String.valueOf(rowKey);
		for(T obj : list){
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
