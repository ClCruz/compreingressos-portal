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

import br.com.intuiti.compreingressos.portal.bean.ContaCorrenteFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.ContaCorrente;

@ManagedBean(name = "contaCorrenteController")
@ViewScoped
public class ContaCorrenteController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
    private br.com.intuiti.compreingressos.portal.bean.ContaCorrenteFacade ejbFacade;
    private List<ContaCorrente> items = null;
    private ContaCorrente selected;

    public ContaCorrenteController() {
    }

    public ContaCorrente getSelected() {
        return selected;
    }

    public void setSelected(ContaCorrente selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    	selected.getContaCorrentePK().setIdTransacao(getFacade().maxIdTrasacao());
        selected.getContaCorrentePK().setIdContratoCliente(selected.getContratoCliente().getIdContratoCliente());
    }

    protected void initializeEmbeddableKey() {
        selected.setContaCorrentePK(new br.com.intuiti.compreingressos.portal.model.ContaCorrentePK());
    }

    private ContaCorrenteFacade getFacade() {
        return ejbFacade;
    }

    public ContaCorrente prepareCreate() {
        selected = new ContaCorrente();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle2").getString("ContaCorrenteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle2").getString("ContaCorrenteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle2").getString("ContaCorrenteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ContaCorrente> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ContaCorrente getContaCorrente(br.com.intuiti.compreingressos.portal.model.ContaCorrentePK id) {
        return getFacade().find(id);
    }

    public List<ContaCorrente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ContaCorrente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ContaCorrente.class)
    public static class ContaCorrenteControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContaCorrenteController controller = (ContaCorrenteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contaCorrenteController");
            return controller.getContaCorrente(getKey(value));
        }

        br.com.intuiti.compreingressos.portal.model.ContaCorrentePK getKey(String value) {
            br.com.intuiti.compreingressos.portal.model.ContaCorrentePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new br.com.intuiti.compreingressos.portal.model.ContaCorrentePK();
            key.setIdTransacao(Integer.parseInt(values[0]));
            key.setIdContratoCliente(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(br.com.intuiti.compreingressos.portal.model.ContaCorrentePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdTransacao());
            sb.append(SEPARATOR);
            sb.append(value.getIdContratoCliente());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ContaCorrente) {
                ContaCorrente o = (ContaCorrente) object;
                return getStringKey(o.getContaCorrentePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ContaCorrente.class.getName()});
                return null;
            }
        }

    }

}
