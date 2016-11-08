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

import br.com.intuiti.compreingressos.portal.model.FuncaoSistema;
import br.com.intuiti.compreingressos.portal.model.Usuario;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class FuncaoSistemaFacade extends AbstractFacade<FuncaoSistema> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FuncaoSistemaFacade() {
        super(FuncaoSistema.class);
    }
    
    @SuppressWarnings("unchecked")
	public List<Object[]> findMenu(Usuario usuario){
        return getEntityManager().createNativeQuery("select fs.* from mw_funcao_sistema fs inner join mw_grupo_funcao gf on gf.id_funcao_sistema = fs.id_funcao_sistema inner join mw_grupo_acesso ga on ga.id_grupo_acesso = gf.id_grupo_acesso inner join mw_usuario_grupo_funcao ugf on ugf.id_grupo_acesso = gf.id_grupo_acesso inner join mw_usuario u on u.id_usuario = ugf.id_usuario where u.id_usuario = " + usuario.getIdUsuario() + " order by fs.id_ordem_exibicao ").getResultList();
    }
    
}
