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

import br.com.intuiti.compreingressos.portal.bean.EstadoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.Estado;

@ManagedBean(name = "estadoController")
@ViewScoped
public class EstadoController implements Serializable {

	private static final long serialVersionUID = 1L;
    @EJB
    private br.com.intuiti.compreingressos.portal.bean.EstadoFacade ejbFacade;
    private LazyDataModel<Estado> items = null;
    private Estado selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public EstadoController() {
    }

    public Estado getSelected() {
        return selected;
    }

    public void setSelected(Estado selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EstadoFacade getFacade() {
        return ejbFacade;
    }

    public Estado prepareCreate() {
        selected = new Estado();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EstadoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EstadoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EstadoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public LazyDataModel<Estado> getItems() {
        if (items == null) {
            items = new EstadoLazy(getFacade().findAll(0, 10, null, SortOrder.UNSORTED, filtros));
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if(persistAction == PersistAction.CREATE){
                        if(getFacade().findES(selected.getDsEstado(), selected.getSgEstado()) == 0){
                            getFacade().edit(selected);
                            JsfUtil.addSuccessMessage(successMessage);
                        } else {
                            JsfUtil.addErrorMessage("Já existe um estado cadastrado com essa descrição e sigla.");
                        }
                    } else {
                        getFacade().edit(selected);
                        JsfUtil.addSuccessMessage(successMessage);
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

    public Estado getEstado(java.lang.Short id) {
        return getFacade().find(id);
    }

    public List<Estado> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Estado> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public class EstadoLazy extends LazyDataModel<Estado> {
    	
    	private static final long serialVersionUID = 1L;
        private List<Estado> objList = null;

        public EstadoLazy(List<Estado> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<Estado> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                EstadoFacade objFacade = (EstadoFacade) ctx.lookup("java:global/compreingressos-portal-1.0.0/EstadoFacade!br.com.intuiti.compreingressos.portal.bean.EstadoFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
                System.out.println(filters);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public Estado getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (Estado obj : objList) {
                if (id.equals(obj.getIdEstado())) {
                    return obj;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(Estado ob) {
            return ob.getIdEstado();
        }
    }

    @FacesConverter(forClass = Estado.class)
    public static class EstadoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoController controller = (EstadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadoController");
            return controller.getEstado(getKey(value));
        }

        java.lang.Short getKey(String value) {
            java.lang.Short key;
            key = Short.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Short value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Estado) {
                Estado o = (Estado) object;
                return getStringKey(o.getIdEstado());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Estado.class.getName()});
                return null;
            }
        }

    }

}
