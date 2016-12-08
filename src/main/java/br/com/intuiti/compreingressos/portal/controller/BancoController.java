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

import br.com.intuiti.compreingressos.portal.bean.BancoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.Banco;

@ManagedBean(name = "bancoController")
@ViewScoped
public class BancoController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
    private br.com.intuiti.compreingressos.portal.bean.BancoFacade ejbFacade;
    private LazyDataModel<Banco> items = null;
    private Banco selected;

    public BancoController() {
    }
    
    @PostConstruct
    public void init() {
    	items = new Lazy(getFacade().findAll());
    }

    public Banco getSelected() {
        return selected;
    }

    public void setSelected(Banco selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BancoFacade getFacade() {
        return ejbFacade;
    }

    public Banco prepareCreate() {
        selected = new Banco();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("BancoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("BancoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("BancoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public LazyDataModel<Banco> getItems() {
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

    public Banco getBanco(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Banco> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Banco> getItemsAvailableSelectOne() {
        return getFacade().findAtivo();
    }
    
    public class Lazy extends LazyDataModel<Banco> {
    	
    	private static final long serialVersionUID = 1L;
    	private List<Banco> banco = null;
    	
    	public Lazy(List<Banco> banco) {
    		this.banco = banco;
    	}
    	
    	@Override
    	public List<Banco> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    		List<Banco> data = new ArrayList<Banco>();
    		for(Banco bc : banco){
    			
    			boolean match = true;
    			if(filters != null){
    				for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();){
    					try{
    						String filterProperty = it.next();
    						Object filterValue = filters.get(filterProperty);
    						Field field = bc.getClass().getDeclaredField(filterProperty);
    						field.setAccessible(true);
    						String fieldValue = String.valueOf(field.get(bc));
    						if(filterValue == null || fieldValue.startsWith(filterValue.toString())){
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
    				data.add(bc);
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
    				return data.subList(first, first + pageSize);
    			}
    		} else {
    			return data;
    		}
    	}
    	
    	@Override
    	public Object getRowKey(Banco object) {
    		return object.getIdBanco();
    	}
    	
    	@Override
    	public Banco getRowData(String rowKey) {
    		Integer id = Integer.valueOf(rowKey);
    		for(Banco b : banco){
    			if(id.equals(b.getIdBanco())){
    				return b;
    			}
    		}
    		return null;
    	}
    }
    
    public class LazySorter implements Comparator<Banco> {
    	private String sortField;
    	private SortOrder sortOrder;
    	
    	public LazySorter(String sortField, SortOrder sortOrder){
    		this.sortField = sortField;
    		this.sortOrder = sortOrder;
    	}
    	
    	@SuppressWarnings({ "rawtypes", "unchecked" })
		public int compare(Banco object1, Banco object2){
    		try{
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
    
    
    @FacesConverter(forClass = Banco.class)
    public static class BancoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BancoController controller = (BancoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "bancoController");
            return controller.getBanco(getKey(value));
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
            if (object instanceof Banco) {
                Banco o = (Banco) object;
                return getStringKey(o.getIdBanco());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Banco.class.getName()});
                return null;
            }
        }

    }

}
