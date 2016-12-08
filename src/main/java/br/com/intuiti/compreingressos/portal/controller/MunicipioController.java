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

import org.jgroups.util.UUID;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.intuiti.compreingressos.portal.bean.MunicipioFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.Estado;
import br.com.intuiti.compreingressos.portal.model.Municipio;

@ManagedBean(name = "municipioController")
@ViewScoped
public class MunicipioController implements Serializable {

	private static final long serialVersionUID = 1L;
    @EJB
    private br.com.intuiti.compreingressos.portal.bean.MunicipioFacade ejbFacade;
    private LazyDataModel<Municipio> items = null;
    private Municipio selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public MunicipioController() {
    }
    
    @PostConstruct
    public void init(){
    	items = new Lazy(getFacade().findAll());
    }

    public Municipio getSelected() {
        return selected;
    }

    public void setSelected(Municipio selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public MunicipioFacade getFacade() {
        return ejbFacade;
    }

    public Municipio prepareCreate() {
        selected = new Municipio();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MunicipioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MunicipioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MunicipioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public boolean verificaMunicipio(Estado estado, String municipio){
    	if(getFacade().findDesc(estado, municipio)){
    		return true;
    	} else {
    		selected = null;
    		return false;
    	}
    }
    
    public boolean verificaMunicipio(Estado estado, String municipio, Integer id){
    	if(getFacade().findDesc(estado, municipio, id)){
    		return true;
    	} else {
    		selected = null;
    		return false;
    	}
    }

    public LazyDataModel<Municipio> getItems() {
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
                    if (persistAction == PersistAction.CREATE) {
                        if(verificaMunicipio(selected.getIdEstado(), selected.getDsMunicipio())){
                        	getFacade().edit(selected);
                            JsfUtil.addSuccessMessage(successMessage);
                        } else {
                        	JsfUtil.addErrorMessage("Já existe uma empresa cadastrada com essa descrição.");
                        }
                    } else {
                    	if(verificaMunicipio(selected.getIdEstado(), selected.getDsMunicipio(), selected.getIdMunicipio())){
                        	getFacade().edit(selected);
                            JsfUtil.addSuccessMessage(successMessage);
                        } else {
                        	JsfUtil.addErrorMessage("Já existe uma empresa cadastrada com essa descrição.");
                        }
                    }
                } else {
                	System.out.println("MUNICIPIO" + selected.getIdMunicipio());
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

    public Municipio getMunicipio(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Municipio> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Municipio> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
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
    						Field field = mun.getClass().getDeclaredField(filterProperty);
    						field.setAccessible(true);
    						String fieldValue = String.valueOf(field.get(mun));
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
    	public Object getRowKey(Municipio object) {
    		System.out.println("idMunicipio" + object.getIdMunicipio());
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
    
    public class LazySorter implements Comparator<Municipio> {
    	private String sortField;
    	private SortOrder sortOrder;
    	
    	public LazySorter(String sortField, SortOrder sortOrder){
    		this.sortField = sortField;
    		this.sortOrder = sortOrder;
    	}
    	
    	public int compare(Municipio object1, Municipio object2){
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
    			System.out.println(e.getMessage());
    			return 0;
    		}
    	}
        }

    @FacesConverter(forClass = Municipio.class)
    public static class MunicipioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MunicipioController controller = (MunicipioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "municipioController");
            return controller.getMunicipio(getKey(value));
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
            if (object instanceof Municipio) {
                Municipio o = (Municipio) object;
                return getStringKey(o.getIdMunicipio());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Municipio.class.getName()});
                return null;
            }
        }

    }

}
