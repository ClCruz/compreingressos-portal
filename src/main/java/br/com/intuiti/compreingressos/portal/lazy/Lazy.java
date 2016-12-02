package br.com.intuiti.compreingressos.portal.lazy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.intuiti.compreingressos.portal.model.Municipio;

public class Lazy extends LazyDataModel<Municipio> {

	private static final long serialVersionUID = 1L;
	// private Class<T> entity;
	// private List<T> list = null;
	// private AbstractFacade<T> ejbFacade;
	//
	// public AbstractFacade<T> getFacade() {
	// return ejbFacade;
	// }

	private List<Municipio> municipio = null;

	public Lazy(List<Municipio> municipio) {
		this.municipio = municipio;
	}

	@Override
	public List<Municipio> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Municipio> data = new ArrayList<Municipio>();
		for(Municipio mun : municipio){
			
			boolean match = true;
			if(filters != null){
				for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();){
					try{
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						String fieldValue = String.valueOf(mun.getClass().getDeclaredField(filterProperty).get(mun));
						
						System.out.println("filterProperty "+filterProperty);
						System.out.println("filterValue "+filterValue);
						System.out.println("fieldValue "+fieldValue);
						
						if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
							match = true;
						} else {
							match = false;
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
						match = false;
					}
				}
			}
			
			if(match){
				data.add(mun);
			}
		}
		
		//sort
		
		//rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);
		
		//paginate
		if(dataSize > pageSize){
			try{
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}
	
	@Override
	public Object getRowKey(Municipio object) {
		return object.getIdMunicipio();
	}
	
	@Override
	public Municipio getRowData(String rowKey) {
		Integer id = Integer.valueOf(rowKey);
		for(Municipio m : municipio){
			if(id.equals(m.getIdMunicipio())){
				return m;
			}
		}
		return null;
	}
}
