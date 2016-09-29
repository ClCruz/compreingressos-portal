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
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.intuiti.compreingressos.portal.bean.SegmentoEventoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.SegmentoEvento;
import br.com.intuiti.compreingressos.portal.model.Usuario;

@ManagedBean(name = "segmentoEventoController")
@ViewScoped
public class SegmentoEventoController implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @EJB
    private br.com.intuiti.compreingressos.portal.bean.SegmentoEventoFacade ejbFacade;
    private LazyDataModel<SegmentoEvento> items = null;
    private SegmentoEvento selected;
    private Usuario usuario;
    private final Map<String, Object> filtros = new HashMap<>();

    public SegmentoEventoController() {
    }

    public SegmentoEvento getSelected() {
        return selected;
    }

    public void setSelected(SegmentoEvento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SegmentoEventoFacade getFacade() {
        return ejbFacade;
    }

    public SegmentoEvento prepareCreate() {
        selected = new SegmentoEvento();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SegmentoEventoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SegmentoEventoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SegmentoEventoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public LazyDataModel<SegmentoEvento> getItems() {
        if (items == null) {
            items = new SegmentoEventoLazy(getFacade().findAll(0, 10, null, SortOrder.UNSORTED, filtros));
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                	FacesContext facesContext = FacesContext.getCurrentInstance();
            		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            		usuario = (Usuario) session.getAttribute("usuario");
                    selected.setIdUsuario(usuario);
                    if(persistAction == PersistAction.CREATE){
                    	if(getFacade().findDs(selected.getDsSegmentoEvento())){
                    		getFacade().edit(selected);
                    		JsfUtil.addSuccessMessage(successMessage);
                    	} else {
                    		JsfUtil.addErrorMessage("Já existe um segmento cadastrado com essa descrição.");
                    	}
                    } else if(persistAction == PersistAction.UPDATE){
                    	if(getFacade().findDsId(selected.getDsSegmentoEvento(), selected.getIdSegmentoEvento())){
                    		getFacade().edit(selected);
                    		JsfUtil.addSuccessMessage(successMessage);
                    	} else {
                    		JsfUtil.addErrorMessage("Já existe um segmento cadastrado com essa descrição.");
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

    public SegmentoEvento getSegmentoEvento(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<SegmentoEvento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SegmentoEvento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public class SegmentoEventoLazy extends LazyDataModel<SegmentoEvento> {
    	
    	private static final long serialVersionUID = 1L;
        private List<SegmentoEvento> objList = null;

        public SegmentoEventoLazy(List<SegmentoEvento> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<SegmentoEvento> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                SegmentoEventoFacade objFacade = (SegmentoEventoFacade) ctx.lookup("java:global/compreingressos-portal/SegmentoEventoFacade!br.com.intuiti.compreingressos.portal.bean.SegmentoEventoFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public SegmentoEvento getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (SegmentoEvento obj : objList) {
                if (id.equals(obj.getIdSegmentoEvento())) {
                    return obj;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(SegmentoEvento ob) {
            return ob.getIdSegmentoEvento();
        }
    }

    @FacesConverter(forClass = SegmentoEvento.class)
    public static class SegmentoEventoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SegmentoEventoController controller = (SegmentoEventoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "segmentoEventoController");
            return controller.getSegmentoEvento(getKey(value));
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
            if (object instanceof SegmentoEvento) {
                SegmentoEvento o = (SegmentoEvento) object;
                return getStringKey(o.getIdSegmentoEvento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SegmentoEvento.class.getName()});
                return null;
            }
        }

    }

}
