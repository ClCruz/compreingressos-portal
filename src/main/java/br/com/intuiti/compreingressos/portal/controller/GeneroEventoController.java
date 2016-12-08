package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.intuiti.compreingressos.portal.bean.GeneroEventoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.GeneroEvento;
import br.com.intuiti.compreingressos.portal.model.Usuario;

@ManagedBean(name = "generoEventoController")
@ViewScoped
public class GeneroEventoController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private br.com.intuiti.compreingressos.portal.bean.GeneroEventoFacade ejbFacade;
	private LazyDataModel<GeneroEvento> items = null;
	private GeneroEvento selected;
	private Usuario usuario;

	public GeneroEventoController() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		usuario = (Usuario) session.getAttribute("usuario");
	}
	
	@PostConstruct
	public void initi() {
		items = new Lazy(getFacade().findAll());
	}

	public GeneroEvento getSelected() {
		return selected;
	}

	public void setSelected(GeneroEvento selected) {
		this.selected = selected;
	}

	protected void setEmbeddableKeys() {
	}

	protected void initializeEmbeddableKey() {
	}

	private GeneroEventoFacade getFacade() {
		return ejbFacade;
	}

	public GeneroEvento prepareCreate() {
		selected = new GeneroEvento();
		initializeEmbeddableKey();
		return selected;
	}

	public void create() {
		persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle")
				.getString("GeneroEventoCreated"));
		if (!JsfUtil.isValidationFailed()) {
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public void update() {
		persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle")
				.getString("GeneroEventoUpdated"));
	}

	public void destroy() {
		persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle")
				.getString("GeneroEventoDeleted"));
		if (!JsfUtil.isValidationFailed()) {
			selected = null; // Remove selection
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public LazyDataModel<GeneroEvento> getItems() {
		if (items == null) {
			items = new Lazy(getFacade().findAll());
		}
		return items;
	}

	private void persist(PersistAction persistAction, String successMessage) {
		if (selected != null) {
			setEmbeddableKeys();
			try {
				if (persistAction != PersistAction.DELETE) {
					selected.setIdUsuario(usuario);
					if (persistAction == PersistAction.CREATE) {
						if (getFacade().findDsSg(selected.getDsGeneroEvento(), selected.getIdSegmentoEvento())) {
							getFacade().edit(selected);
							JsfUtil.addSuccessMessage(successMessage);
						} else {
							JsfUtil.addErrorMessage("Já existe um gênero com essa descrição e segmento cadastrado.");
						}
					} else if (persistAction == PersistAction.UPDATE) {
						if(getFacade().findDsSgId(selected.getDsGeneroEvento(), selected.getIdSegmentoEvento(), selected.getIdGeneroEvento())){
							getFacade().edit(selected);
							JsfUtil.addSuccessMessage(successMessage);
						} else {
							JsfUtil.addErrorMessage("Já existe um gênero com essa descrição e segmento cadastrado.");
						}
					}
				} else {
					getFacade().remove(selected);
					JsfUtil.addSuccessMessage(successMessage);
				}
			} catch (EJBException ex) {
				String msg = "";
				Throwable cause = ex.getCause();
				if (cause != null) {
					msg = cause.getLocalizedMessage();
				}
				if (msg.length() > 0) {
					JsfUtil.addErrorMessage(msg);
				} else {
					JsfUtil.addErrorMessage(
							ex,
							ResourceBundle.getBundle("/Bundle").getString(
									"PersistenceErrorOccured"));
				}
			} catch (Exception ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						null, ex);
				JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle")
						.getString("PersistenceErrorOccured"));
			}
		}
	}

	public GeneroEvento getGeneroEvento(java.lang.Integer id) {
		return getFacade().find(id);
	}

	public List<GeneroEvento> getItemsAvailableSelectMany() {
		return getFacade().findAll();
	}

	public List<GeneroEvento> getItemsAvailableSelectOne() {
		return getFacade().findAll();
	}
	
	public class Lazy extends LazyDataModel<GeneroEvento> {

    	private static final long serialVersionUID = 1L;

    	private List<GeneroEvento> generoEvento = null;

    	public Lazy(List<GeneroEvento> generoEvento) {
    		this.generoEvento = generoEvento;
    	}

    	@Override
    	public List<GeneroEvento> load(int first, int pageSize, String sortField, SortOrder sortOrder,
    			Map<String, Object> filters) {
    		List<GeneroEvento> data = new ArrayList<GeneroEvento>();
    		for(GeneroEvento ge : generoEvento){
    			
    			boolean match = true;
    			if(filters != null){
    				for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();){
    					try{
    						String filterProperty = it.next();
    						Object filterValue = filters.get(filterProperty);
    						Field field = ge.getClass().getDeclaredField(filterProperty);
    						field.setAccessible(true);
    						String fieldValue = String.valueOf(field.get(ge));
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
    				data.add(ge);
    			}
    		}
    		
    		//sort
    		if(sortField != null) {
    			Collections.sort(data, new LazySorter(sortField, sortOrder));
    		}
    		
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
    	public Object getRowKey(GeneroEvento object) {
    		return object.getIdGeneroEvento();
    	}
    	
    	@Override
    	public GeneroEvento getRowData(String rowKey) {
    		Integer id = Integer.valueOf(rowKey);
    		for(GeneroEvento g : generoEvento){
    			if(id.equals(g.getIdGeneroEvento())){
    				return g;
    			}
    		}
    		return null;
    	}
    }
    
    public class LazySorter implements Comparator<GeneroEvento> {
    	private String sortField;
    	private SortOrder sortOrder;
    	
    	public LazySorter(String sortField, SortOrder sortOrder){
    		this.sortField = sortField;
    		this.sortOrder = sortOrder;
    	}
    	
    	@SuppressWarnings({ "unchecked", "rawtypes" })
    	public int compare(GeneroEvento object1, GeneroEvento  object2){
    		try {
    			Field field1 = object1.getClass().getDeclaredField(this.sortField);
    			Field field2 = object2.getClass().getDeclaredField(this.sortField);
    			field1.setAccessible(true);
    			field2.setAccessible(true);
    			Object value1 = field1.get(object1);
    			Object value2 = field2.get(object2);
    			
    			int value = ((Comparable)value1).compareTo(value2);
    			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
    		}
    		catch(Exception e) {
    			throw new RuntimeException();
    		}
    	}
    }

	@FacesConverter(forClass = GeneroEvento.class)
	public static class GeneroEventoControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			GeneroEventoController controller = (GeneroEventoController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"generoEventoController");
			return controller.getGeneroEvento(getKey(value));
		}

		java.lang.Integer getKey(String value) {
			java.lang.Integer key;
			key = Integer.valueOf(value);
			return key;
		}

		String getStringKey(java.lang.Integer value) {
			StringBuilder sb = new StringBuilder();
			sb.append(value);
			return sb.toString();
		}

		@Override
		public String getAsString(FacesContext facesContext,
				UIComponent component, Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof GeneroEvento) {
				GeneroEvento o = (GeneroEvento) object;
				return getStringKey(o.getIdGeneroEvento());
			} else {
				Logger.getLogger(this.getClass().getName()).log(
						Level.SEVERE,
						"object {0} is of type {1}; expected type: {2}",
						new Object[] { object, object.getClass().getName(),
								GeneroEvento.class.getName() });
				return null;
			}
		}

	}

}
