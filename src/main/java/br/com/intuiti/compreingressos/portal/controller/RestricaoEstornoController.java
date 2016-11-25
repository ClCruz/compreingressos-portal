package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.intuiti.compreingressos.portal.bean.RestricaoEstornoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.RestricaoEstorno;

@ManagedBean(name = "resticaoEstornoController")
@ViewScoped
public class RestricaoEstornoController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private br.com.intuiti.compreingressos.portal.bean.RestricaoEstornoFacade ejbFacade;
	private LazyDataModel<RestricaoEstorno> items = null;
	private RestricaoEstorno selected;
	private final Map<String, Object> filtros = new HashMap<>();
	
	public RestricaoEstornoController(){
	}

	public RestricaoEstorno getSelected() {
		return selected;
	}

	public void setSelected(RestricaoEstorno selected) {
		this.selected = selected;
	}

	protected void setEmbeddableKeys() {
	}
	
	protected void initializaEmbeddableKey() {
	}
	
	private RestricaoEstornoFacade getFacade() {
		return ejbFacade;
	}
	
	public RestricaoEstorno prepareCreate() {
		selected = new RestricaoEstorno();
		initializaEmbeddableKey();
		return selected;
	}
	
	public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RestricaoEstornoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RestricaoEstornoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RestricaoEstornoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public LazyDataModel<RestricaoEstorno> getItems() {
    	if (items == null) {
    		items = new RestricaoEstornoLazy(getFacade().findAll(0, 10, null, SortOrder.ASCENDING, filtros));
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
	
    public List<RestricaoEstorno> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<RestricaoEstorno> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
	
    public class RestricaoEstornoLazy extends LazyDataModel<RestricaoEstorno> {
    	
    	private static final long serialVersionUID = 1L;
    	private List<RestricaoEstorno> objList = null;
    	
    	public RestricaoEstornoLazy(List<RestricaoEstorno> objList){
    		this.objList = objList;
    	}
    	
    	@Override
    	public List<RestricaoEstorno> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    		objList = new ArrayList<>();
    		try {
    			Context ctx = new javax.naming.InitialContext();
    			RestricaoEstornoFacade objFacade = (RestricaoEstornoFacade) ctx.lookup("java:global/compreigressos-portal-1.0.0/RestricaoEstornoFacade!br.com.intuiti.compreingressos.portal.bean.RestricaoEstornoFacade");
    			objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
    			setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
    			setPageSize(pageSize);
    		} catch (NamingException ex) {
    			System.out.println(ex);
    		}
    		return objList;
    	}
    	
    	@Override
    	public RestricaoEstorno getRowData(String rowKey) {
    		Integer id = Integer.valueOf(rowKey);
    		for(RestricaoEstorno obj : objList) {
    			if (id.equals(obj.getIdTipoEstorno().getDsTipoEstorno())) {
    				return obj;
    			}
    		}
    		return null;
    	}
    	
    	@Override
    	public Object getRowKey(RestricaoEstorno ob) {
    		return ob.getIdTipoEstorno().getDsTipoEstorno();
    	}
    }
    
	
}
