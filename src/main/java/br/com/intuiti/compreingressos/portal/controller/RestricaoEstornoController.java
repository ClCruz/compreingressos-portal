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

import br.com.intuiti.compreingressos.portal.bean.RestricaoEstornoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.RestricaoEstorno;

@ManagedBean(name = "restricaoEstornoController")
@ViewScoped
public class RestricaoEstornoController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private br.com.intuiti.compreingressos.portal.bean.RestricaoEstornoFacade ejbFacade;
	private List<RestricaoEstorno> items = null;
	private RestricaoEstorno selected;
	
	public RestricaoEstornoController() {
    }

	public RestricaoEstorno getSelected() {
		return selected;
	}

	public void setSelected(RestricaoEstorno selected) {
		this.selected = selected;
	}

	protected void setEmbeddableKeys() {
		selected.getRestricaoEstornoPK().setIdTipoEstorno(selected.getIdTipoEstorno().getIdTipoEstorno());
		selected.getRestricaoEstornoPK().setIdTipoLancamento(selected.getIdTipoLancamento().getIdTipoLancamento());
	}
	
	protected void initializaEmbeddableKey() {
		selected.setRestricaoEstornoPK(new br.com.intuiti.compreingressos.portal.model.RestricaoEstornoPK());
	}
	
	private RestricaoEstornoFacade getFacade() {
		return ejbFacade;
	}
	
	public RestricaoEstorno prepareCreate() {
		selected = new RestricaoEstorno();
		initializaEmbeddableKey();
		return selected;
	}
	
	public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RestricaoEstornoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RestricaoEstornoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RestricaoEstornoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public List<RestricaoEstorno> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
    		}
    	}
    
    }
	
    public RestricaoEstorno getRestricaoEstorno(br.com.intuiti.compreingressos.portal.model.RestricaoEstornoPK id){
    	return getFacade().find(id);
    }
    
    public List<RestricaoEstorno> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<RestricaoEstorno> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    @FacesConverter(forClass = RestricaoEstorno.class)
    public static class RestricaoEstornoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RestricaoEstornoController controller = (RestricaoEstornoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "restricaoEstornoController");
            return controller.getRestricaoEstorno(getKey(value));
        }

        br.com.intuiti.compreingressos.portal.model.RestricaoEstornoPK getKey(String value) {
            br.com.intuiti.compreingressos.portal.model.RestricaoEstornoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new br.com.intuiti.compreingressos.portal.model.RestricaoEstornoPK();
            key.setIdTipoEstorno(Integer.parseInt(values[0]));
            key.setIdTipoLancamento(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(br.com.intuiti.compreingressos.portal.model.RestricaoEstornoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdTipoEstorno());
            sb.append(SEPARATOR);
            sb.append(value.getIdTipoLancamento());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof RestricaoEstorno) {
                RestricaoEstorno o = (RestricaoEstorno) object;
                return getStringKey(o.getRestricaoEstornoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RestricaoEstorno.class.getName()});
                return null;
            }
		}
		
	}
}
