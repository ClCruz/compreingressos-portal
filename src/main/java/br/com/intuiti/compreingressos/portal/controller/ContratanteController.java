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

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.intuiti.compreingressos.portal.bean.ContratanteFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.Contratante;
import br.com.intuiti.compreingressos.portal.model.Municipio;

@ManagedBean(name = "contratanteController")
@ViewScoped
public class ContratanteController implements Serializable {

	private static final long serialVersionUID = 1L;
    @EJB
    private br.com.intuiti.compreingressos.portal.bean.ContratanteFacade ejbFacade;
    private LazyDataModel<Contratante> items = null;
    private Contratante selected;

    public ContratanteController() {
    }
    
    @PostConstruct
    public void init() {
    	items =  new Lazy(getFacade().findAll());
    }

    public Contratante getSelected() {
        return selected;
    }

    public void setSelected(Contratante selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ContratanteFacade getFacade() {
        return ejbFacade;
    }

    public Contratante prepareCreate() {
        selected = new Contratante();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ContratanteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ContratanteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ContratanteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public LazyDataModel<Contratante> getItems() {
        if (items == null) {
            items = new Lazy(getFacade().findAll());
        }
        return items;
    }
    
    public List<Municipio> listaMunicipios(){
		return getFacade().findAll(selected.getIdEstado());
	}
    
    public List<Municipio> listaMunicipioRepresLegal(){
		return getFacade().findAll(selected.getIdEstadoRepresLegal());
	}

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Contratante getContratante(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Contratante> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Contratante> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public class Lazy extends LazyDataModel<Contratante> {

    	private static final long serialVersionUID = 1L;

    	private List<Contratante> contratante = null;

    	public Lazy(List<Contratante> contratante) {
    		this.contratante = contratante;
    	}

    	@Override
    	public List<Contratante> load(int first, int pageSize, String sortField, SortOrder sortOrder,
    			Map<String, Object> filters) {
    		List<Contratante> data = new ArrayList<Contratante>();
    		for(Contratante ct : contratante){
    			
    			boolean match = true;
    			if(filters != null){
    				for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();){
    					try{
    						String filterProperty = it.next();
    						Object filterValue = filters.get(filterProperty);
    						Field field = ct.getClass().getDeclaredField(filterProperty);
    						field.setAccessible(true);
    						String fieldValue = String.valueOf(field.get(ct));
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
    				data.add(ct);
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
    	public Object getRowKey(Contratante object) {
    		return object.getIdContratante();
    	}
    	
    	@Override
    	public Contratante getRowData(String rowKey) {
    		Integer id = Integer.valueOf(rowKey);
    		for(Contratante c : contratante){
    			if(id.equals(c.getIdContratante())){
    				return c;
    			}
    		}
    		return null;
    	}
    }
    
    public class LazySorter implements Comparator<Contratante> {
    	private String sortField;
    	private SortOrder sortOrder;
    	
    	public LazySorter(String sortField, SortOrder sortOrder){
    		this.sortField = sortField;
    		this.sortOrder = sortOrder;
    	}
    	
    	@SuppressWarnings({ "unchecked", "rawtypes" })
    	public int compare(Contratante object1, Contratante  object2){
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
    
    @FacesConverter(forClass = Contratante.class)
    public static class ContratanteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContratanteController controller = (ContratanteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contratanteController");
            return controller.getContratante(getKey(value));
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
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Contratante) {
                Contratante o = (Contratante) object;
                return getStringKey(o.getIdContratante());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Contratante.class.getName()});
                return null;
            }
        }

    }

}
