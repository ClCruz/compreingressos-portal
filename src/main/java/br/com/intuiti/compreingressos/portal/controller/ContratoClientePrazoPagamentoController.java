package br.com.intuiti.compreingressos.portal.controller;

import br.com.intuiti.compreingressos.portal.model.ContratoClientePrazoPagamento;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.bean.ContratoClientePrazoPagamentoFacade;
import java.io.Serializable;
import java.util.List;
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

@ManagedBean(name = "contratoClientePrazoPagamentoController")
@ViewScoped
public class ContratoClientePrazoPagamentoController implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @EJB
    private br.com.intuiti.compreingressos.portal.bean.ContratoClientePrazoPagamentoFacade ejbFacade;
    private List<ContratoClientePrazoPagamento> items = null;
    private ContratoClientePrazoPagamento selected;

    public ContratoClientePrazoPagamentoController() {
    }

    public ContratoClientePrazoPagamento getSelected() {
        return selected;
    }

    public void setSelected(ContratoClientePrazoPagamento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public ContratoClientePrazoPagamentoFacade getFacade() {
        return ejbFacade;
    }

    public ContratoClientePrazoPagamento prepareCreate() {
        selected = new ContratoClientePrazoPagamento();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleNv").getString("ContratoClientePrazoPagamentoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleNv").getString("ContratoClientePrazoPagamentoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleNv").getString("ContratoClientePrazoPagamentoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ContratoClientePrazoPagamento> getItems() {
        if (items != null) {
            items = getFacade().findAll();
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleNv").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleNv").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ContratoClientePrazoPagamento getContratoClientePrazoPagamento(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ContratoClientePrazoPagamento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ContratoClientePrazoPagamento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ContratoClientePrazoPagamento.class)
    public static class ContratoClientePrazoPagamentoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContratoClientePrazoPagamentoController controller = (ContratoClientePrazoPagamentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contratoClientePrazoPagamentoController");
            return controller.getContratoClientePrazoPagamento(getKey(value));
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
            if (object instanceof ContratoClientePrazoPagamento) {
                ContratoClientePrazoPagamento o = (ContratoClientePrazoPagamento) object;
                return getStringKey(o.getIdContratoClientePrazoPagamento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ContratoClientePrazoPagamento.class.getName()});
                return null;
            }
        }

    }

}
