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

import br.com.intuiti.compreingressos.portal.bean.PrazoPagamentoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.PrazoPagamento;

@ManagedBean(name = "prazoPagamentoController")
@ViewScoped
public class PrazoPagamentoController implements Serializable {

	private static final long serialVersionUID = 1L;
    @EJB
    private br.com.intuiti.compreingressos.portal.bean.PrazoPagamentoFacade ejbFacade;
    private LazyDataModel<PrazoPagamento> items = null;
    private PrazoPagamento selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public PrazoPagamentoController() {
    }

    public PrazoPagamento getSelected() {
        return selected;
    }

    public void setSelected(PrazoPagamento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PrazoPagamentoFacade getFacade() {
        return ejbFacade;
    }

    public PrazoPagamento prepareCreate() {
        selected = new PrazoPagamento();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PrazoPagamentoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PrazoPagamentoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PrazoPagamentoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public LazyDataModel<PrazoPagamento> getItems() {
        if (items == null) {
            items = new PrazoPagamentoLazy(getFacade().findAll(0, 10, null, SortOrder.UNSORTED, filtros));
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

    public PrazoPagamento getPrazoPagamento(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PrazoPagamento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PrazoPagamento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public class PrazoPagamentoLazy extends LazyDataModel<PrazoPagamento> {
    	
    	private static final long serialVersionUID = 1L;
        private List<PrazoPagamento> objList = null;

        public PrazoPagamentoLazy(List<PrazoPagamento> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<PrazoPagamento> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                PrazoPagamentoFacade objFacade = (PrazoPagamentoFacade) ctx.lookup("java:global/compreingressos-portal/PrazoPagamentoFacade!br.com.intuiti.compreingressos.portal.bean.PrazoPagamentoFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public PrazoPagamento getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (PrazoPagamento obj : objList) {
                if (id.equals(obj.getIdPrazoPagamento())) {
                    return obj;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(PrazoPagamento ob) {
            return ob.getIdPrazoPagamento();
        }
    }

    @FacesConverter(forClass = PrazoPagamento.class)
    public static class PrazoPagamentoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PrazoPagamentoController controller = (PrazoPagamentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "prazoPagamentoController");
            return controller.getPrazoPagamento(getKey(value));
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
            if (object instanceof PrazoPagamento) {
                PrazoPagamento o = (PrazoPagamento) object;
                return getStringKey(o.getIdPrazoPagamento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PrazoPagamento.class.getName()});
                return null;
            }
        }

    }

}
