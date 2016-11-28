package br.com.intuiti.compreingressos.portal.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.intuiti.compreingressos.portal.model.Usuario;

/**
 *
 * @author Gabriel Queiroz
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "br.com.intuiti_compreingressos-portal_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario findUsuario(String userName) {
        try {
            return (Usuario) em.createNamedQuery("Usuario.findByCdLogin").setParameter("cdLogin", userName).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> findAsc() {
        return em.createNamedQuery("Usuario.findAsc").getResultList();
    }
    
    @SuppressWarnings("unchecked")
   	public boolean findCdLogin(String cdLogin){
           List<Usuario> lista = em.createNamedQuery("Usuario.findCdLogin").setParameter("cdLogin", cdLogin).getResultList();
           return lista.size() > 0 ? false : true;
    }
    
    @SuppressWarnings("unchecked")
	public List<Usuario> findAtivo(){
    	return getEntityManager().createNamedQuery("Usuario.findAtivo").setParameter("inAtivo", 1).getResultList();
    }
}
