package br.com.intuiti.compreingressos.portal.controller;

import static org.primefaces.model.SortOrder.UNSORTED;

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

import br.com.intuiti.compreingressos.portal.bean.UsuarioFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.Usuario;

@ManagedBean(name = "usuarioController")
@ViewScoped
public class UsuarioController extends LazyDataModel<Usuario> implements
		Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private br.com.intuiti.compreingressos.portal.bean.UsuarioFacade ejbFacade;
	private LazyDataModel<Usuario> items = null;
	private Usuario selected;
	private final Map<String, Object> filtros = new HashMap<>();

	public UsuarioController() {
	}

	public Usuario getSelected() {
		return selected;
	}

	public void setSelected(Usuario selected) {
		this.selected = selected;
	}

	protected void setEmbeddableKeys() {
	}

	protected void initializeEmbeddableKey() {
	}

	public UsuarioFacade getFacade() {
		return ejbFacade;
	}

	public Usuario prepareCreate() {
		selected = new Usuario();
		initializeEmbeddableKey();
		return selected;
	}

	public void create() {
		persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle")
				.getString("UsuarioCreated"));
		if (!JsfUtil.isValidationFailed()) {
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public void update() {
		persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle")
				.getString("UsuarioUpdated"));
	}

	public void destroy() {
		persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle")
				.getString("UsuarioDeleted"));
		if (!JsfUtil.isValidationFailed()) {
			selected = null; // Remove selection
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public LazyDataModel<Usuario> getItems() {
		if (items == null) {
			items = new UsuarioLazyModel(getFacade().findAll(0, 10, null,
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

	public Usuario getUsuario(java.lang.Integer id) {
		return getFacade().find(id);
	}

	public List<Usuario> getItemsAvailableSelectMany() {
		return getFacade().findAll();
	}

	public List<Usuario> getItemsAvailableSelectOne() {
		return getFacade().findAsc();
	}

	public class UsuarioLazyModel extends LazyDataModel<Usuario> {

		private static final long serialVersionUID = 1L;
		private List<Usuario> usuarios = null;

		public UsuarioLazyModel() {
		}

		public UsuarioLazyModel(List<Usuario> usuarios) {
			this.usuarios = usuarios;
		}

		@Override
		public Usuario getRowData(String rowKey) {
			Integer id = Integer.valueOf(rowKey);
			for (Usuario user : usuarios) {
				if (id.equals(user.getIdUsuario())) {
					return user;
				}
			}
			return null;
		}

		@Override
		public Object getRowKey(Usuario usr) {
			return usr.getIdUsuario();
		}

		@Override
		public List<Usuario> load(int first, int pageSize, String sortField,
				SortOrder sortOrder, Map<String, Object> filters) {
			usuarios = new ArrayList<>();
			try {
				Context ctx = new javax.naming.InitialContext();
				UsuarioFacade usuarioFacade = (UsuarioFacade) ctx
						.lookup("java:global/compreingressos-portal-1.0.0/UsuarioFacade!br.com.intuiti.compreingressos.portal.bean.UsuarioFacade");
				usuarios = usuarioFacade.findAll(first, pageSize, sortField,
						sortOrder, filters);
				setRowCount(usuarioFacade.count(first, pageSize,
						sortField, sortOrder, filters));
				setPageSize(pageSize);
			} catch (NamingException ex) {
				Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			return usuarios;
		}
	}

	@FacesConverter(forClass = Usuario.class)
	public static class UsuarioControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			UsuarioController controller = (UsuarioController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"usuarioController");
			return controller.getUsuario(getKey(value));
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
			if (object instanceof Usuario) {
				Usuario o = (Usuario) object;
				return getStringKey(o.getIdUsuario());
			} else {
				Logger.getLogger(this.getClass().getName()).log(
						Level.SEVERE,
						"object {0} is of type {1}; expected type: {2}",
						new Object[] { object, object.getClass().getName(),
								Usuario.class.getName() });
				return null;
			}
		}
	}
}
