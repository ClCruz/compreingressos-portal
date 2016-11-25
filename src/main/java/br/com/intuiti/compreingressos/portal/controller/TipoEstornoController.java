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

import br.com.intuiti.compreingressos.portal.bean.TipoEstornoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.TipoEstorno;

@ManagedBean(name = "tipoEstornoController")
@ViewScoped
public class TipoEstornoController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
    private br.com.intuiti.compreingressos.portal.bean.TipoEstornoFacade ejbFacade;
    private LazyDataModel<TipoEstorno> items = null;
    private TipoEstorno selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public TipoEstornoController() {
    }

    public TipoEstorno getSelected() {
        return selected;
    }

    public void setSelected(TipoEstorno selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipoEstornoFacade getFacade() {
        return ejbFacade;
    }

    public TipoEstorno prepareCreate() {
        selected = new TipoEstorno();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TipoEstornoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TipoEstornoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TipoEstornoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public LazyDataModel<TipoEstorno> getItems() {
        if (items == null) {
            items = new TipoEstornoLazy(getFacade().findAll(0, 10, null, SortOrder.ASCENDING, filtros));
        }
        return items;
    }
    
    public boolean validaDsTipoEstorno(String dsTipoEstorno){
    	if(getFacade().findDsTipoEstorno(dsTipoEstorno)){
    		return true;
    	} else {
    		selected = null;
    		return false;
    	}
    }
    
    public boolean validaDsTipoEstorno(String dsTipoEstorno, Integer idTipoEstorno){
    	if(getFacade().findDsTipoEstorno(dsTipoEstorno, idTipoEstorno)){
    		return true;
    	} else {
    		selected = null;
    		return false;
    	}
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                	if(persistAction == PersistAction.CREATE){
                		if(validaDsTipoEstorno(selected.getDsTipoEstorno())){
                			getFacade().edit(selected);
                			JsfUtil.addSuccessMessage(successMessage);
                		} else {
                			JsfUtil.addErrorMessage("Já existe um tipo de estorno cadastrado com essa descrição");
                		}
                	} else {
                		if(validaDsTipoEstorno(selected.getDsTipoEstorno(), selected.getIdTipoEstorno())){
                			getFacade().edit(selected);
                			JsfUtil.addSuccessMessage(successMessage);
                		} else {
                			JsfUtil.addErrorMessage("Já existe um tipo de estorno cadastrado com essa descrição");
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

    public TipoEstorno getTipoEstorno(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TipoEstorno> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TipoEstorno> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public class TipoEstornoLazy extends LazyDataModel<TipoEstorno> {
    	
    	private static final long serialVersionUID = 1L;
        private List<TipoEstorno> objList = null;

        public TipoEstornoLazy(List<TipoEstorno> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<TipoEstorno> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                TipoEstornoFacade objFacade = (TipoEstornoFacade) ctx.lookup("java:global/compreingressos-portal-1.0.0/TipoEstornoFacade!br.com.intuiti.compreingressos.portal.bean.TipoEstornoFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public TipoEstorno getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (TipoEstorno obj : objList) {
                if (id.equals(obj.getIdTipoEstorno())) {
                    return obj;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(TipoEstorno ob) {
            return ob.getIdTipoEstorno();
        }
    }

    @FacesConverter(forClass = TipoEstorno.class)
    public static class TipoEstornoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoEstornoController controller = (TipoEstornoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoEstornoController");
            return controller.getTipoEstorno(getKey(value));
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
            if (object instanceof TipoEstorno) {
                TipoEstorno o = (TipoEstorno) object;
                return getStringKey(o.getIdTipoEstorno());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoEstorno.class.getName()});
                return null;
            }
        }

    }

}