package br.com.intuiti.compreingressos.portal.controller;

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

import br.com.intuiti.compreingressos.portal.bean.UsuarioGrupoFuncaoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.UsuarioGrupoFuncao;

@ManagedBean(name = "usuarioGrupoFuncaoController")
@ViewScoped
public class UsuarioGrupoFuncaoController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
    private br.com.intuiti.compreingressos.portal.bean.UsuarioGrupoFuncaoFacade ejbFacade;
    private List<UsuarioGrupoFuncao> items = null;
    private UsuarioGrupoFuncao selected;

    public UsuarioGrupoFuncaoController() {
    }

    public UsuarioGrupoFuncao getSelected() {
        return selected;
    }

    public void setSelected(UsuarioGrupoFuncao selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioGrupoFuncaoFacade getFacade() {
        return ejbFacade;
    }

    public UsuarioGrupoFuncao prepareCreate() {
        selected = new UsuarioGrupoFuncao();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle3").getString("UsuarioGrupoFuncaoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle3").getString("UsuarioGrupoFuncaoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle3").getString("UsuarioGrupoFuncaoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UsuarioGrupoFuncao> getItems() {
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

    public UsuarioGrupoFuncao getUsuarioGrupoFuncao(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<UsuarioGrupoFuncao> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UsuarioGrupoFuncao> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UsuarioGrupoFuncao.class)
    public static class UsuarioGrupoFuncaoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioGrupoFuncaoController controller = (UsuarioGrupoFuncaoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioGrupoFuncaoController");
            return controller.getUsuarioGrupoFuncao(getKey(value));
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
            if (object instanceof UsuarioGrupoFuncao) {
                UsuarioGrupoFuncao o = (UsuarioGrupoFuncao) object;
                return getStringKey(o.getIdUsuarioGrupoFuncao());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UsuarioGrupoFuncao.class.getName()});
                return null;
            }
        }

    }

}
