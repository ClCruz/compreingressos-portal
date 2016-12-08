package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;

import br.com.intuiti.compreingressos.portal.bean.UsuarioGrupoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.model.Grupo;
import br.com.intuiti.compreingressos.portal.model.Usuario;
import br.com.intuiti.compreingressos.portal.model.UsuarioGrupo;

@ManagedBean(name = "grupoUsuarioController")
@ViewScoped
public class GrupoUsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioGrupoFacade ejbFacade;
	private List<Grupo> items = null;
	private Usuario usuario;
	private List<UsuarioGrupo> selected = null;
	private boolean todos;

	public GrupoUsuarioController() {
		prepareCreate();
	}

	public List<UsuarioGrupo> getSelected() {
		return selected;
	}

	public void setSelected(List<UsuarioGrupo> selected) {
		this.selected = selected;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	public UsuarioGrupoFacade getFacade() {
		return ejbFacade;
	}

	public void prepareCreate() {
		selected = new ArrayList<>();
	}

	public void changeUsuario(ValueChangeEvent event) {
		prepareGrupoSistema((Usuario) event.getNewValue());
	}

	public void prepareGrupoSistema(Usuario user) {
		List<UsuarioGrupo> grupoSistemaTarget;
		List<Grupo> grupos = new ArrayList<>();

		grupoSistemaTarget = getFacade().findSelectedByUsuario(user);

		for (Grupo g : getFacade().findAllByUsuario()) {
			g.setUsuarioGrupo(null);
			g.setSelected(false);
			for (UsuarioGrupo ug : grupoSistemaTarget) {
				if (ug.getIdGrupo().equals(g)) {
					g.setUsuarioGrupo(ug);
					g.setSelected(true);
				}
			}
			grupos.add(g);
		}

		todos = grupoSistemaTarget.size() == grupos.size();
		items = grupos;
	}

	public void save() {
		if (usuario != null) {
			try {
				if (todos) {
					getFacade().remove(usuario);
					for (Grupo grupo : items) {
						UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
						usuarioGrupo.setIdGrupo(grupo);
						usuarioGrupo.setIdUsuario(usuario);
						getFacade().edit(usuarioGrupo);
					}
					prepareGrupoSistema(usuario);
				} else {
					getFacade().remove(usuario);
					prepareGrupoSistema(usuario);
				}
				JsfUtil.addSuccessMessage("Permissão alterada com sucesso.");
			} catch (Exception ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						ex.toString(), ex);
				JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle")
						.getString("PersistenceErrorOccured"));
			}
		} else {
			JsfUtil.addErrorMessage("O Usuário não foi selecionado.");
		}
	}

	public void change(Grupo grupo) {
		try {
			if (grupo.isSelected()) {
				getFacade().edit(new UsuarioGrupo(grupo, usuario));
				todos = true;
				for (Grupo item : items) {
					if (!item.isSelected()) {
						todos = false;
						break;
					}
				}
				JsfUtil.addSuccessMessage("Permissão alterada com sucesso.");
			} else {
				getFacade().remove(grupo, usuario);
				todos = false;
			}
		} catch (Exception ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
					ex.toString(), ex);
			JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle")
					.getString("PersistenceErrorOccured"));
		}
	}

	public List<Grupo> getItems() {
		return items;
	}

	public List<UsuarioGrupo> getItemsAvailableSelectMany() {
		return getFacade().findAll();
	}

	public List<UsuarioGrupo> getItemsAvailableSelectOne() {
		return getFacade().findAll();
	}

	@FacesConverter(forClass = UsuarioGrupo.class)
	public static class UsuarioGrupoControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			UsuarioGrupoController controller = (UsuarioGrupoController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"fusiUsuaController");
			return controller.getFacade().find(getKey(value));
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
			if (object instanceof UsuarioGrupo) {
				UsuarioGrupo o = (UsuarioGrupo) object;
				return getStringKey(o.getIdUsuarioGrupo());
			} else {
				Logger.getLogger(this.getClass().getName()).log(
						Level.SEVERE,
						"object {0} is of type {1}; expected type: {2}",
						new Object[] { object, object.getClass().getName(),
								UsuarioGrupo.class.getName() });
				return null;
			}
		}

	}

}
