package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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

import br.com.intuiti.compreingressos.portal.bean.ModalidadeContratoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.ModalidadeContrato;

@ManagedBean(name = "modalidadeContratoController")
@ViewScoped
public class ModalidadeContratoController implements Serializable {

	private static final long serialVersionUID = 1L;
    @EJB
    private br.com.intuiti.compreingressos.portal.bean.ModalidadeContratoFacade ejbFacade;
    private LazyDataModel<ModalidadeContrato> items = null;
    private ModalidadeContrato selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public ModalidadeContratoController() {
    }

    @PostConstruct
    public void init() {
    	items = new Lazy(getFacade().findAll());
    }
    
    public ModalidadeContrato getSelected() {
        return selected;
    }

    public void setSelected(ModalidadeContrato selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ModalidadeContratoFacade getFacade() {
        return ejbFacade;
    }

    public ModalidadeContrato prepareCreate() {
        selected = new ModalidadeContrato();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ModalidadeContratoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ModalidadeContratoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ModalidadeContratoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public LazyDataModel<ModalidadeContrato> getItems() {
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
                	if(persistAction == PersistAction.CREATE){
                		if(getFacade().findDs(selected.getDsModalidadeContrato())){
                			getFacade().edit(selected);
                			JsfUtil.addSuccessMessage(successMessage);
                		} else {
                			JsfUtil.addErrorMessage("Já existe uma modalide de contrato com essa descrição.");
                		}
                	} else if(persistAction == PersistAction.UPDATE){
                		if(getFacade().findDsId(selected.getDsModalidadeContrato(), selected.getIdModalidadeContrato())){
                			getFacade().edit(selected);
                			JsfUtil.addSuccessMessage(successMessage);
                		} else {
                			JsfUtil.addErrorMessage("Já existe uma modalide de contrato com essa descrição.");
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ModalidadeContrato getModalidadeContrato(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ModalidadeContrato> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ModalidadeContrato> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public class Lazy extends LazyDataModel<ModalidadeContrato> {

    	private static final long serialVersionUID = 1L;

    	private List<ModalidadeContrato> modalidadeContrato = null;

    	public Lazy(List<ModalidadeContrato> modalidadeContrato) {
    		this.modalidadeContrato = modalidadeContrato;
    	}

    	@Override
    	public List<ModalidadeContrato> load(int first, int pageSize, String sortField, SortOrder sortOrder,
    			Map<String, Object> filters) {
    		List<ModalidadeContrato> data = new ArrayList<ModalidadeContrato>();
    		for(ModalidadeContrato mc : modalidadeContrato){
    			
    			boolean match = true;
    			if(filters != null){
    				for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();){
    					try{
    						String filterProperty = it.next();
    						Object filterValue = filters.get(filterProperty);
    						Field field = mc.getClass().getDeclaredField(filterProperty);
    						field.setAccessible(true);
    						String fieldValue = String.valueOf(field.get(mc));
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
    				data.add(mc);
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
    	public Object getRowKey(ModalidadeContrato object) {
    		return object.getIdModalidadeContrato();
    	}
    	
    	@Override
    	public ModalidadeContrato getRowData(String rowKey) {
    		Integer id = Integer.valueOf(rowKey);
    		for(ModalidadeContrato m : modalidadeContrato){
    			if(id.equals(m.getIdModalidadeContrato())){
    				return m;
    			}
    		}
    		return null;
    	}
    }
    
    public class LazySorter implements Comparator<ModalidadeContrato> {
    	private String sortField;
    	private SortOrder sortOrder;
    	
    	public LazySorter(String sortField, SortOrder sortOrder){
    		this.sortField = sortField;
    		this.sortOrder = sortOrder;
    	}
    	
    	public int compare(ModalidadeContrato object1, ModalidadeContrato  object2){
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
    
    @FacesConverter(forClass = ModalidadeContrato.class)
    public static class ModalidadeContratoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ModalidadeContratoController controller = (ModalidadeContratoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "modalidadeContratoController");
            return controller.getModalidadeContrato(getKey(value));
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
            if (object instanceof ModalidadeContrato) {
                ModalidadeContrato o = (ModalidadeContrato) object;
                return getStringKey(o.getIdModalidadeContrato());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ModalidadeContrato.class.getName()});
                return null;
            }
        }

    }

}
