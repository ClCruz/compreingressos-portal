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

import br.com.intuiti.compreingressos.portal.bean.TipoLocalFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.TipoLocal;

@ManagedBean(name = "tipoLocalController")
@ViewScoped
public class TipoLocalController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private br.com.intuiti.compreingressos.portal.bean.TipoLocalFacade ejbFacade;
	private LazyDataModel<TipoLocal> items = null;
	private TipoLocal selected;
	private final Map<String, Object> filtros = new HashMap<>();

	public TipoLocalController() {
	}

	public TipoLocal getSelected() {
		return selected;
	}

	public void setSelected(TipoLocal selected) {
		this.selected = selected;
	}

	protected void setEmbeddableKeys() {
	}

	protected void initializeEmbeddableKey() {
	}

	private TipoLocalFacade getFacade() {
		return ejbFacade;
	}

	public TipoLocal prepareCreate() {
		selected = new TipoLocal();
		initializeEmbeddableKey();
		return selected;
	}

	public void create() {
		persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle")
				.getString("TipoLocalCreated"));
		if (!JsfUtil.isValidationFailed()) {
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public void update() {
		persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle")
				.getString("TipoLocalUpdated"));
	}

	public void destroy() {
		persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle")
				.getString("TipoLocalDeleted"));
		if (!JsfUtil.isValidationFailed()) {
			selected = null; // Remove selection
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public LazyDataModel<TipoLocal> getItems() {
		if (items == null) {
			items = new TipoLocalLazy(getFacade().findAll(0, 10, null, SortOrder.UNSORTED, filtros));
		}
		return items;
	}

	private void persist(PersistAction persistAction, String successMessage) {
		if (selected != null) {
			setEmbeddableKeys();
			try {
				if (persistAction != PersistAction.DELETE) {
					if (persistAction == PersistAction.CREATE) {
						if (getFacade().findDs(selected.getDsTipoLocal())) {
							getFacade().edit(selected);
							JsfUtil.addSuccessMessage(successMessage);
						} else {
							JsfUtil.addErrorMessage("Já existe um tipo de local cadastrado com essa descrição.");
						}
					} else if (persistAction == PersistAction.UPDATE) {
						if (getFacade().findDsId(selected.getDsTipoLocal(),
								selected.getIdTipoLocal())) {
							getFacade().edit(selected);
							JsfUtil.addSuccessMessage(successMessage);
						} else {
							JsfUtil.addErrorMessage("Já existe um tipo de local cadastrado com essa descrição.");
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
					JsfUtil.addErrorMessage(
							ex,
							ResourceBundle.getBundle("/Bundle").getString(
									"PersistenceErrorOccured"));
				}
			} catch (Exception ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						null, ex);
				JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle")
						.getString("PersistenceErrorOccured"));
			}
		}
	}

	public TipoLocal getTipoLocal(java.lang.Integer id) {
		return getFacade().find(id);
	}

	public List<TipoLocal> getItemsAvailableSelectMany() {
		return getFacade().findAll();
	}

	public List<TipoLocal> getItemsAvailableSelectOne() {
		return getFacade().findAll();
	}
	
    public class TipoLocalLazy extends LazyDataModel<TipoLocal> {
    	
    	private static final long serialVersionUID = 1L;
        private List<TipoLocal> objList = null;

        public TipoLocalLazy(List<TipoLocal> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<TipoLocal> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                TipoLocalFacade objFacade = (TipoLocalFacade) ctx.lookup("java:global/compreingressos-portal/TipoLocalFacade!br.com.intuiti.compreingressos.portal.bean.TipoLocalFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public TipoLocal getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (TipoLocal obj : objList) {
                if (id.equals(obj.getIdTipoLocal())) {
                    return obj;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(TipoLocal ob) {
            return ob.getIdTipoLocal();
        }
    }

	@FacesConverter(forClass = TipoLocal.class)
	public static class TipoLocalControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			TipoLocalController controller = (TipoLocalController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"tipoLocalController");
			return controller.getTipoLocal(getKey(value));
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
		public String getAsString(FacesContext facesContext,
				UIComponent component, Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof TipoLocal) {
				TipoLocal o = (TipoLocal) object;
				return getStringKey(o.getIdTipoLocal());
			} else {
				Logger.getLogger(this.getClass().getName()).log(
						Level.SEVERE,
						"object {0} is of type {1}; expected type: {2}",
						new Object[] { object, object.getClass().getName(),
								TipoLocal.class.getName() });
				return null;
			}
		}

	}

}
