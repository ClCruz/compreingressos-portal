package br.com.intuiti.compreingressos.portal.controller;

import br.com.intuiti.compreingressos.portal.model.LocalEvento;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.bean.LocalEventoFacade;

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
import static org.primefaces.model.SortOrder.UNSORTED;

@ManagedBean(name = "localEventoController")
@ViewScoped
public class LocalEventoController extends LazyDataModel<LocalEvento> implements
		Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private br.com.intuiti.compreingressos.portal.bean.LocalEventoFacade ejbFacade;
	private LazyDataModel<LocalEvento> items = null;
	private LocalEvento selected;
	private final Map<String, Object> filtros = new HashMap<>();

	public LocalEventoController() {
	}

	public LocalEvento getSelected() {
		return selected;
	}

	public void setSelected(LocalEvento selected) {
		this.selected = selected;
	}

	protected void setEmbeddableKeys() {
	}

	protected void initializeEmbeddableKey() {
	}

	private LocalEventoFacade getFacade() {
		return ejbFacade;
	}

	public LocalEvento prepareCreate() {
		selected = new LocalEvento();
		initializeEmbeddableKey();
		return selected;
	}

	public void create() {
		persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle")
				.getString("LocalEventoCreated"));
		if (!JsfUtil.isValidationFailed()) {
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public void update() {
		persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle")
				.getString("LocalEventoUpdated"));
	}

	public void destroy() {
		persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle")
				.getString("LocalEventoDeleted"));
		if (!JsfUtil.isValidationFailed()) {
			selected = null; // Remove selection
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public LazyDataModel<LocalEvento> getItems() {
		if (items == null) {
			items = new localEventoLazy(getFacade().findAll(0, 10, null,
					UNSORTED, filtros));
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

	public LocalEvento getLocalEvento(java.lang.Integer id) {
		return getFacade().find(id);
	}

	public List<LocalEvento> getItemsAvailableSelectMany() {
		return getFacade().findAll();
	}

	public List<LocalEvento> getItemsAvailableSelectOne() {
		return getFacade().findAll();
	}

	public List<LocalEvento> getItemsAvailableSelectOneOrderBy() {
		return getFacade().findAllOrderByDs();
	}

	public class localEventoLazy extends LazyDataModel<LocalEvento> {

		private static final long serialVersionUID = 1L;
		private List<LocalEvento> localEvento = null;

		public localEventoLazy(List<LocalEvento> localEvento) {
			this.localEvento = localEvento;
		}

		@Override
		public List<LocalEvento> load(int first, int pageSize,
				String sortField, SortOrder sortOrder,
				Map<String, Object> filters) {
			localEvento = new ArrayList<>();
			try {
				Context ctx = new javax.naming.InitialContext();
				LocalEventoFacade localFacade = (LocalEventoFacade) ctx
						.lookup("java:global/compreingressos-portal/LocalEventoFacade!br.com.intuiti.compreingressos.portal.bean.LocalEventoFacade");
				localEvento = localFacade.findLazy(first, pageSize, sortField,
						sortOrder, filters);
				setRowCount(localFacade.countLocal(first, pageSize, sortField,
						sortOrder, filters));
				setPageSize(pageSize);
			} catch (NamingException ex) {
				Logger.getLogger(LocalEvento.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			return localEvento;
		}

		@Override
		public LocalEvento getRowData(String rowKey) {
			Integer id = Integer.valueOf(rowKey);
			for (LocalEvento local : localEvento) {
				if (id.equals(local.getIdLocalEvento())) {
					return local;
				}
			}
			return null;
		}

		@Override
		public Object getRowKey(LocalEvento lcl) {
			return lcl.getIdLocalEvento();
		}

	}

	@FacesConverter(forClass = LocalEvento.class)
	public static class LocalEventoControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			LocalEventoController controller = (LocalEventoController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"localEventoController");
			return controller.getLocalEvento(getKey(value));
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
			if (object instanceof LocalEvento) {
				LocalEvento o = (LocalEvento) object;
				return getStringKey(o.getIdLocalEvento());
			} else {
				Logger.getLogger(this.getClass().getName()).log(
						Level.SEVERE,
						"object {0} is of type {1}; expected type: {2}",
						new Object[] { object, object.getClass().getName(),
								LocalEvento.class.getName() });
				return null;
			}
		}

	}

}
