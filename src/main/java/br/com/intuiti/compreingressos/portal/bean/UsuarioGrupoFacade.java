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

import br.com.intuiti.compreingressos.portal.model.Grupo;
import br.com.intuiti.compreingressos.portal.model.Usuario;
import br.com.intuiti.compreingressos.portal.model.UsuarioGrupo;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class UsuarioGrupoFacade extends AbstractFacade<UsuarioGrupo> {

	@PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UsuarioGrupoFacade() {
		super(UsuarioGrupo.class);
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioGrupo> findSelectedByUsuario(Usuario usuaId) {
		return em.createNamedQuery("UsuarioGrupo.findByIdUsuario")
				.setParameter("idUsuario", usuaId).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Grupo> findAllByUsuario() {
		return em.createNamedQuery("UsuarioGrupo.findAllByUsuario")
				.getResultList();
	}

	public void remove(Usuario usuario) {
		em.createNativeQuery(
				"DELETE FROM mw_usuario_grupo WHERE id_usuario = "
						+ usuario.getIdUsuario()).executeUpdate();
	}

	public void remove(Grupo grupo, Usuario usuario) {
		em.createNativeQuery(
				"DELETE FROM mw_usuario_grupo WHERE id_usuario = "
						+ usuario.getIdUsuario() + " AND id_grupo = "
						+ grupo.getIdGrupo()).executeUpdate();
	}
}
