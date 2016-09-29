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

import br.com.intuiti.compreingressos.portal.bean.RegiaoGeograficaFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.RegiaoGeografica;

@ManagedBean(name = "regiaoGeograficaController")
@ViewScoped
public class RegiaoGeograficaController implements Serializable {

	private static final long serialVersionUID = 1L;
    @EJB
    private br.com.intuiti.compreingressos.portal.bean.RegiaoGeograficaFacade ejbFacade;
    private LazyDataModel<RegiaoGeografica> items = null;
    private RegiaoGeografica selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public RegiaoGeograficaController() {
    }

    public RegiaoGeografica getSelected() {
        return selected;
    }

    public void setSelected(RegiaoGeografica selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RegiaoGeograficaFacade getFacade() {
        return ejbFacade;
    }

    public RegiaoGeografica prepareCreate() {
        selected = new RegiaoGeografica();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RegiaoGeograficaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RegiaoGeograficaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RegiaoGeograficaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public LazyDataModel<RegiaoGeografica> getItems() {
        if (items == null) {
            items = new RegiaoGeograficaLazy(getFacade().findAll(0, 10, null, SortOrder.UNSORTED, filtros));
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                	if(persistAction == PersistAction.CREATE){
                		if(getFacade().findDs(selected.getDsRegiaoGeografica())){
                			getFacade().edit(selected);
                			JsfUtil.addSuccessMessage(successMessage);
                		} else {
                			JsfUtil.addErrorMessage("Já existe uma região cadastrada com essa descrição.");
                		}
                	} else if(persistAction == PersistAction.UPDATE){
                		if(getFacade().findDsId(selected.getDsRegiaoGeografica(), selected.getIdRegiaoGeografica())){
                			getFacade().edit(selected);
                			JsfUtil.addSuccessMessage(successMessage);
                		} else {
                			JsfUtil.addErrorMessage("Já existe uma região cadastrada com essa descrição.");
                		}
                	}
                    
                } else {
                    getFacade().remove(selected);
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

    public RegiaoGeografica getRegiaoGeografica(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<RegiaoGeografica> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<RegiaoGeografica> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public class RegiaoGeograficaLazy extends LazyDataModel<RegiaoGeografica> {
    	
    	private static final long serialVersionUID = 1L;
        private List<RegiaoGeografica> objList = null;

        public RegiaoGeograficaLazy(List<RegiaoGeografica> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<RegiaoGeografica> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                RegiaoGeograficaFacade objFacade = (RegiaoGeograficaFacade) ctx.lookup("java:global/compreingressos-portal/RegiaoGeograficaFacade!br.com.intuiti.compreingressos.portal.bean.RegiaoGeograficaFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public RegiaoGeografica getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (RegiaoGeografica obj : objList) {
                if (id.equals(obj.getIdRegiaoGeografica())) {
                    return obj;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(RegiaoGeografica ob) {
            return ob.getIdRegiaoGeografica();
        }
    }

    @FacesConverter(forClass = RegiaoGeografica.class)
    public static class RegiaoGeograficaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RegiaoGeograficaController controller = (RegiaoGeograficaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "regiaoGeograficaController");
            return controller.getRegiaoGeografica(getKey(value));
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
            if (object instanceof RegiaoGeografica) {
                RegiaoGeografica o = (RegiaoGeografica) object;
                return getStringKey(o.getIdRegiaoGeografica());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RegiaoGeografica.class.getName()});
                return null;
            }
        }

    }

}
