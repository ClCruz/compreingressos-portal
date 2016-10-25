package br.com.intuiti.compreingressos.portal.controller;

import br.com.intuiti.compreingressos.portal.model.GrupoAcesso;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.bean.GrupoAcessoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("grupoAcessoController")
@SessionScoped
public class GrupoAcessoController implements Serializable {

    @EJB
    private br.com.intuiti.compreingressos.portal.bean.GrupoAcessoFacade ejbFacade;
    private List<GrupoAcesso> items = null;
    private GrupoAcesso selected;

    public GrupoAcessoController() {
    }

    public GrupoAcesso getSelected() {
        return selected;
    }

    public void setSelected(GrupoAcesso selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private GrupoAcessoFacade getFacade() {
        return ejbFacade;
    }

    public GrupoAcesso prepareCreate() {
        selected = new GrupoAcesso();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle3").getString("GrupoAcessoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle3").getString("GrupoAcessoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle3").getString("GrupoAcessoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<GrupoAcesso> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle3").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle3").getString("PersistenceErrorOccured"));
            }
        }
    }

    public GrupoAcesso getGrupoAcesso(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<GrupoAcesso> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<GrupoAcesso> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = GrupoAcesso.class)
    public static class GrupoAcessoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GrupoAcessoController controller = (GrupoAcessoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "grupoAcessoController");
            return controller.getGrupoAcesso(getKey(value));
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
            if (object instanceof GrupoAcesso) {
                GrupoAcesso o = (GrupoAcesso) object;
                return getStringKey(o.getIdGrupoAcesso());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), GrupoAcesso.class.getName()});
                return null;
            }
        }

    }

}
