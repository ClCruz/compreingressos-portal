/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.intuiti.compreingressos.portal.model.GrupoAcesso;
import br.com.intuiti.compreingressos.portal.model.Usuario;
import br.com.intuiti.compreingressos.portal.model.UsuarioGrupoFuncao;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class UsuarioGrupoFuncaoFacade extends AbstractFacade<UsuarioGrupoFuncao> {

	@PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UsuarioGrupoFuncaoFacade() {
		super(UsuarioGrupoFuncao.class);
	}

	public List<UsuarioGrupoFuncao> findSelectedByUsuario(Usuario usuario) {
		return getEntityManager().createNamedQuery("UsuarioGrupoFuncao.findByIdUsuario", UsuarioGrupoFuncao.class)
				.setParameter("idUsuario", usuario).getResultList();
	}

	public List<GrupoAcesso> findAllByUsuario() {
		return getEntityManager().createNamedQuery("UsuarioGrupoFuncao.findAllByUsuario", GrupoAcesso.class)
				.getResultList();
	}
	
	public void remove(Usuario usuario){
		getEntityManager().createNativeQuery("DELETE FROM mw_usuario_grupo_funcao WHERE id_usuario = " + usuario.getIdUsuario()).executeUpdate(); 
	}

	public void remove(GrupoAcesso grupoAcesso, Usuario usuario) {
		getEntityManager()
				.createNativeQuery("DELETE FROM mw_usuario_grupo_funcao WHERE " + "id_usuario = "
						+ usuario.getIdUsuario() + " AND id_grupo_acesso = " + grupoAcesso.getIdGrupoAcesso())
				.executeUpdate();
	}

}
