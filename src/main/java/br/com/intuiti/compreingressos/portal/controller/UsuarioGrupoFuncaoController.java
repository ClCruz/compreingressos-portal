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

import br.com.intuiti.compreingressos.portal.bean.UsuarioGrupoFuncaoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.model.GrupoAcesso;
import br.com.intuiti.compreingressos.portal.model.Usuario;
import br.com.intuiti.compreingressos.portal.model.UsuarioGrupoFuncao;

@ManagedBean(name = "usuarioGrupoFuncaoController")
@ViewScoped
public class UsuarioGrupoFuncaoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsuarioGrupoFuncaoFacade ejbFacade;
	private List<GrupoAcesso> items = null;
	private List<UsuarioGrupoFuncao> selected;
	private Usuario usuario;
	private boolean todos;

	public UsuarioGrupoFuncaoController() {
		prepareCreate();
	}

	public List<UsuarioGrupoFuncao> getSelected() {
		return selected;
	}

	public void setSelected(List<UsuarioGrupoFuncao> selected) {
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

	private UsuarioGrupoFuncaoFacade getFacade() {
		return ejbFacade;
	}
	
	public void prepareCreate() {
		selected = new ArrayList<>();
	}
	
	public void changeUsuario(ValueChangeEvent event) {
		prepareGrupoSistema((Usuario) event.getNewValue());
	}
	
	public void prepareGrupoSistema(Usuario user){
		List<UsuarioGrupoFuncao> grupoSistemaTarget;
		List<GrupoAcesso> grupos = new ArrayList<>();
		
		grupoSistemaTarget = getFacade().findSelectedByUsuario(user);
		
		for(GrupoAcesso g : getFacade().findAllByUsuario()){
			g.setUsuarioGrupoFuncao(null);
			g.setSelected(false);
			for(UsuarioGrupoFuncao ugf : grupoSistemaTarget){
				if(ugf.getIdGrupoAcesso().equals(g)){
					g.setUsuarioGrupoFuncao(ugf);
					g.setSelected(true);
				}
			}
			grupos.add(g);
		}
		
		todos = grupoSistemaTarget.size() == grupos.size();
		items = grupos;
	}
	
	public void save(){
		if(usuario != null){
			try {
				if(todos){
					getFacade().remove(usuario);
					for(GrupoAcesso grupoAcesso : items){
						UsuarioGrupoFuncao usuarioGrupoFuncao = new UsuarioGrupoFuncao();
						usuarioGrupoFuncao.setIdGrupoAcesso(grupoAcesso);
						usuarioGrupoFuncao.setIdUsuario(usuario);
						getFacade().edit(usuarioGrupoFuncao);
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
	
	public void change(GrupoAcesso grupoAcesso){
		try{
			if(grupoAcesso.isSelected()){
				getFacade().edit(new UsuarioGrupoFuncao(grupoAcesso, usuario));
				todos = true;
				for(GrupoAcesso item : items){
					if(!item.isSelected()){
						todos = false;
						break;
					}
				}
				JsfUtil.addSuccessMessage("Permissão alterada com sucesso.");
			} else {
				getFacade().remove(grupoAcesso, usuario);
				todos = false;
			}
		}catch (Exception ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
					ex.toString(), ex);
			JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle")
					.getString("PersistenceErrorOccured"));
		}
	}

	public List<GrupoAcesso> getItems() {
		return items;
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
			UsuarioGrupoFuncaoController controller = (UsuarioGrupoFuncaoController) facesContext.getApplication()
					.getELResolver().getValue(facesContext.getELContext(), null, "usuarioGrupoFuncaoController");
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
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						"object {0} is of type {1}; expected type: {2}",
						new Object[] { object, object.getClass().getName(), UsuarioGrupoFuncao.class.getName() });
				return null;
			}
		}

	}

}
