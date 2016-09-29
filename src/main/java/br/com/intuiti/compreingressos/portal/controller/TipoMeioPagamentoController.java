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

import br.com.intuiti.compreingressos.portal.bean.TipoMeioPagamentoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.TipoMeioPagamento;

@ManagedBean(name = "tipoMeioPagamentoController")
@ViewScoped
public class TipoMeioPagamentoController implements Serializable {

	private static final long serialVersionUID = 1L;
    @EJB
    private br.com.intuiti.compreingressos.portal.bean.TipoMeioPagamentoFacade ejbFacade;
    private LazyDataModel<TipoMeioPagamento> items = null;
    private TipoMeioPagamento selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public TipoMeioPagamentoController() {
    }

    public TipoMeioPagamento getSelected() {
        return selected;
    }

    public void setSelected(TipoMeioPagamento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipoMeioPagamentoFacade getFacade() {
        return ejbFacade;
    }

    public TipoMeioPagamento prepareCreate() {
        selected = new TipoMeioPagamento();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TipoMeioPagamentoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TipoMeioPagamentoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TipoMeioPagamentoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public LazyDataModel<TipoMeioPagamento> getItems() {
        if (items == null) {
            items = new TipoMeioPagamentoLazy(getFacade().findAll(0, 10, null, SortOrder.UNSORTED, filtros));
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

    public TipoMeioPagamento getTipoMeioPagamento(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TipoMeioPagamento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TipoMeioPagamento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public class TipoMeioPagamentoLazy extends LazyDataModel<TipoMeioPagamento> {
    	
    	private static final long serialVersionUID = 1L;
        private List<TipoMeioPagamento> objList = null;

        public TipoMeioPagamentoLazy(List<TipoMeioPagamento> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<TipoMeioPagamento> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                TipoMeioPagamentoFacade objFacade = (TipoMeioPagamentoFacade) ctx.lookup("java:global/compreingressos-portal/TipoMeioPagamentoFacade!br.com.intuiti.compreingressos.portal.bean.TipoMeioPagamentoFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public TipoMeioPagamento getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (TipoMeioPagamento obj : objList) {
                if (id.equals(obj.getInTipoMeioPagamento())) {
                    return obj;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(TipoMeioPagamento ob) {
            return ob.getInTipoMeioPagamento();
        }
    }

    @FacesConverter(forClass = TipoMeioPagamento.class)
    public static class TipoMeioPagamentoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoMeioPagamentoController controller = (TipoMeioPagamentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoMeioPagamentoController");
            return controller.getTipoMeioPagamento(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TipoMeioPagamento) {
                TipoMeioPagamento o = (TipoMeioPagamento) object;
                return getStringKey(o.getInTipoMeioPagamento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoMeioPagamento.class.getName()});
                return null;
            }
        }

    }

}
