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
            items = new ModalidadeContratoLazy(getFacade().findAll(0, 10, null, SortOrder.UNSORTED, filtros));
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
    
    public class ModalidadeContratoLazy extends LazyDataModel<ModalidadeContrato> {
    	
    	private static final long serialVersionUID = 1L;
        private List<ModalidadeContrato> objList = null;

        public ModalidadeContratoLazy(List<ModalidadeContrato> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<ModalidadeContrato> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                ModalidadeContratoFacade objFacade = (ModalidadeContratoFacade) ctx.lookup("java:global/compreingressos-portal/ModalidadeContratoFacade!br.com.intuiti.compreingressos.portal.bean.ModalidadeContratoFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public ModalidadeContrato getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (ModalidadeContrato obj : objList) {
                if (id.equals(obj.getIdModalidadeContrato())) {
                    return obj;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(ModalidadeContrato ob) {
            return ob.getIdModalidadeContrato();
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
