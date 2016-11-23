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

import br.com.intuiti.compreingressos.portal.bean.TipoContratoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.TipoContrato;

@ManagedBean(name = "tipoContratoController")
@ViewScoped
public class TipoContratoController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private br.com.intuiti.compreingressos.portal.bean.TipoContratoFacade ejbFacade;
	private LazyDataModel<TipoContrato> items = null;
	private TipoContrato selected;
	private final Map<String, Object> filtros = new HashMap<>();


	public TipoContratoController() {
	}

	public TipoContrato getSelected() {
		return selected;
	}

	public void setSelected(TipoContrato selected) {
		this.selected = selected;
	}

	protected void setEmbeddableKeys() {
	}

	protected void initializeEmbeddableKey() {
	}

	private TipoContratoFacade getFacade() {
		return ejbFacade;
	}

	public TipoContrato prepareCreate() {
		selected = new TipoContrato();
		initializeEmbeddableKey();
		return selected;
	}

	public void create() {
		persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle")
				.getString("TipoContratoCreated"));
		if (!JsfUtil.isValidationFailed()) {
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public void update() {
		persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle")
				.getString("TipoContratoUpdated"));
	}

	public void destroy() {
		persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle")
				.getString("TipoContratoDeleted"));
		if (!JsfUtil.isValidationFailed()) {
			selected = null; // Remove selection
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public LazyDataModel<TipoContrato> getItems() {
		if (items == null) {
			items = new TipoContratoLazy(getFacade().findAll(0, 10, null, SortOrder.ASCENDING, filtros));
		}
		return items;
	}

	private void persist(PersistAction persistAction, String successMessage) {
		if (selected != null) {
			setEmbeddableKeys();
			try {
				if (persistAction != PersistAction.DELETE) {
					if (persistAction == PersistAction.CREATE) {
						if (getFacade().findDs(selected.getDsTipoContrato())) {
							getFacade().edit(selected);
							JsfUtil.addSuccessMessage(successMessage);
						} else {
							JsfUtil.addErrorMessage("Já existe um tipo de contrato cadastrado com essa descrição.");
						}
					} else if (persistAction == PersistAction.UPDATE) {
						if(getFacade().findDsId(selected.getDsTipoContrato(), selected.getIdTipoContrato())){
							getFacade().edit(selected);
							JsfUtil.addSuccessMessage(successMessage);
						} else {
							JsfUtil.addErrorMessage("Já existe um tipo de contrato cadastrado com essa descrição.");
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

	public TipoContrato getTipoContrato(java.lang.Integer id) {
		return getFacade().find(id);
	}

	public List<TipoContrato> getItemsAvailableSelectMany() {
		return getFacade().findAll();
	}

	public List<TipoContrato> getItemsAvailableSelectOne() {
		return getFacade().findAll();
	}
	
    public class TipoContratoLazy extends LazyDataModel<TipoContrato> {
    	
    	private static final long serialVersionUID = 1L;
        private List<TipoContrato> objList = null;

        public TipoContratoLazy(List<TipoContrato> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<TipoContrato> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                TipoContratoFacade objFacade = (TipoContratoFacade) ctx.lookup("java:global/compreingressos-portal-1.0.0/TipoContratoFacade!br.com.intuiti.compreingressos.portal.bean.TipoContratoFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public TipoContrato getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (TipoContrato obj : objList) {
                if (id.equals(obj.getIdTipoContrato())) {
                    return obj;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(TipoContrato ob) {
            return ob.getIdTipoContrato();
        }
    }

	@FacesConverter(forClass = TipoContrato.class)
	public static class TipoContratoControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			TipoContratoController controller = (TipoContratoController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"tipoContratoController");
			return controller.getTipoContrato(getKey(value));
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
			if (object instanceof TipoContrato) {
				TipoContrato o = (TipoContrato) object;
				return getStringKey(o.getIdTipoContrato());
			} else {
				Logger.getLogger(this.getClass().getName()).log(
						Level.SEVERE,
						"object {0} is of type {1}; expected type: {2}",
						new Object[] { object, object.getClass().getName(),
								TipoContrato.class.getName() });
				return null;
			}
		}

	}

}
