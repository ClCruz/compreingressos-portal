package br.com.intuiti.compreingressos.portal.controller;

import br.com.intuiti.compreingressos.portal.model.ContratoClienteTipoLancamento;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.bean.ContratoClienteTipoLancamentoFacade;
import br.com.intuiti.compreingressos.portal.model.ContratoCliente;

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

@ManagedBean(name = "contratoClienteTipoLancamentoController")
@ViewScoped
public class ContratoClienteTipoLancamentoController implements Serializable {

    @EJB
    private br.com.intuiti.compreingressos.portal.bean.ContratoClienteTipoLancamentoFacade ejbFacade;
    private List<ContratoClienteTipoLancamento> items = null;
    private ContratoClienteTipoLancamento selected;

    public ContratoClienteTipoLancamentoController() {
    }

    public ContratoClienteTipoLancamento getSelected() {
        return selected;
    }

    public void setSelected(ContratoClienteTipoLancamento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public ContratoClienteTipoLancamentoFacade getFacade() {
        return ejbFacade;
    }

    public ContratoClienteTipoLancamento prepareCreate() {
        selected = new ContratoClienteTipoLancamento();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleNv").getString("ContratoClienteTipoLancamentoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleNv").getString("ContratoClienteTipoLancamentoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleNv").getString("ContratoClienteTipoLancamentoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ContratoClienteTipoLancamento> getItems() {
        if (items == null) {
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

    public ContratoClienteTipoLancamento getContratoClienteTipoLancamento(java.lang.Integer id) {
        return getFacade().find(id);
    }
    
    public List<ContratoClienteTipoLancamento> getItemAvailableSelectIdContrato(Integer id){
        return getFacade().findAll(new ContratoCliente(id));
    }

    public List<ContratoClienteTipoLancamento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ContratoClienteTipoLancamento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ContratoClienteTipoLancamento.class)
    public static class ContratoClienteTipoLancamentoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContratoClienteTipoLancamentoController controller = (ContratoClienteTipoLancamentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contratoClienteTipoLancamentoController");
            return controller.getContratoClienteTipoLancamento(getKey(value));
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
            if (object instanceof ContratoClienteTipoLancamento) {
                ContratoClienteTipoLancamento o = (ContratoClienteTipoLancamento) object;
                return getStringKey(o.getIdContratoClienteTipoLancamento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ContratoClienteTipoLancamento.class.getName()});
                return null;
            }
        }

    }

}
