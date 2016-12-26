package br.com.intuiti.compreingressos.portal.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.com.intuiti.compreingressos.portal.bean.FuncaoSistemaFacade;
import br.com.intuiti.compreingressos.portal.model.FuncaoSistema;
import br.com.intuiti.compreingressos.portal.model.Usuario;
import java.util.ArrayList;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {

    @EJB
    private FuncaoSistemaFacade ejbFacadeFuncaoSistema;
    private Usuario usuario;
    private MenuModel menu;
    private List<FuncaoSistema> funcaoSistemaList;
    private FuncaoSistema selected;
    
    public FuncaoSistemaFacade getFacadeFuncaoSistema() {
        return ejbFacadeFuncaoSistema;
    }

    public LoginController() {
        
    }

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext()
                .getSession(false);
        usuario = (Usuario) session.getAttribute("usuario");
        
        funcaoSistemaList = new ArrayList<>();
        List<Object[]> listFuncao = getFacadeFuncaoSistema().findMenu(usuario);
        
        for (Object[] lis : listFuncao) {
            selected = getFacadeFuncaoSistema().find((Integer) lis[0]);
            funcaoSistemaList.add(selected);
        }
    }
    
    public void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();
	}

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext()
                .getSession(false);
        session.setAttribute("usuario", null);
        session.invalidate();
        this.refresh();
        return "/pages/tarefa?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public MenuModel getMenu() {
        menu = new DefaultMenuModel();
        for (FuncaoSistema funcao : funcaoSistemaList) {
            if (funcao.getDsUrl().isEmpty() && funcao.getIdParent() == null) {
                DefaultSubMenu subMenu = new DefaultSubMenu(funcao.getDsPrograma());
                subMenu.setIcon(funcao.getDsIcone());
                for (FuncaoSistema subFuncao : funcaoSistemaList) {
                    if (subFuncao.getIdParent() != null && subFuncao.getIdParent().getIdFuncaoSistema().equals(funcao.getIdFuncaoSistema())) {
                        DefaultMenuItem item = new DefaultMenuItem();
                        if (subFuncao.getDsUrl() == null || subFuncao.getDsUrl().equals("")) {
                            DefaultSubMenu subMenu2 = new DefaultSubMenu(subFuncao.getDsPrograma());
                            subMenu2.setIcon(subFuncao.getDsIcone());
                            for(FuncaoSistema subFunc : funcaoSistemaList){
                                if(subFunc.getIdParent() != null && subFunc.getIdParent().getIdFuncaoSistema().equals(subFuncao.getIdFuncaoSistema())){
                                    DefaultMenuItem item2 = new DefaultMenuItem();
                                    item2.setValue(subFunc.getDsPrograma());
                                    item2.setIcon(subFunc.getDsIcone());
                                    item2.setOutcome(subFunc.getDsUrl());
                                    subMenu2.addElement(item2);
                                }
                            }
                            subMenu.addElement(subMenu2);
                        } else {
                            item.setValue(subFuncao.getDsPrograma());
                            item.setIcon(subFuncao.getDsIcone());
                            item.setOutcome(subFuncao.getDsUrl());
                            subMenu.addElement(item);
                        }
                    }
                }
                menu.addElement(subMenu);
            } else if (funcao.getIdParent() == null) {
                DefaultMenuItem menuItem = new DefaultMenuItem();
                menuItem.setValue(funcao.getDsPrograma());
                menuItem.setIcon(funcao.getDsIcone());
                menuItem.setOutcome(funcao.getDsUrl());
                menu.addElement(menuItem);
            }
        }
        menu.generateUniqueIds();
        return menu;
    }
}
