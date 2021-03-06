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
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Usuario> findAll(){
    	return getEntityManager().createNamedQuery("Usuario.findAll").getResultList();
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
   	public int findCdLogin(String cdLogin){
           List<Usuario> lista = em.createNamedQuery("Usuario.findCdLogin").setParameter("cdLogin", cdLogin).getResultList();
           return lista.size();
    }
    
    @SuppressWarnings("unchecked")
   	public int findLoginId(String cdLogin, Integer idUsuario){
           List<Usuario> lista = em.createNamedQuery("Usuario.findLoginId").setParameter("cdLogin", cdLogin).setParameter("idUsuario", idUsuario).getResultList();
           return lista.size();
    }
    
    @SuppressWarnings("unchecked")
   	public int findByDsEmail(String dsEmail){
           List<Usuario> lista = em.createNamedQuery("Usuario.findByDsEmail").setParameter("dsEmail", dsEmail).getResultList();
           return lista.size();
    }
    
    @SuppressWarnings("unchecked")
   	public int findDsEmailId(String dsEmail, Integer idUsuario){
           List<Usuario> lista = em.createNamedQuery("Usuario.findDsEmailId").setParameter("dsEmail", dsEmail).setParameter("idUsuario", idUsuario).getResultList();
           return lista.size();
    }
    
    @SuppressWarnings("unchecked")
	public List<Usuario> findAtivo(){
    	return getEntityManager().createNamedQuery("Usuario.findAtivo").setParameter("inAtivo", 1).getResultList();
    }
}
