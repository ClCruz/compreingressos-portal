package br.com.intuiti.compreingressos.portal.lazy;

import br.com.intuiti.compreingressos.portal.bean.UsuarioFacade;
import br.com.intuiti.compreingressos.portal.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingException;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Gabriel Queiroz
 */
public class UsuarioLazyModel extends LazyDataModel<Usuario> {

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
    public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        usuarios = new ArrayList<>();
        try {
            Context ctx = new javax.naming.InitialContext();
            UsuarioFacade usuarioFacade = (UsuarioFacade) ctx.lookup("java:global/compreingressos-portal/UsuarioFacade!br.com.intuiti.compreingressos.portal.bean.UsuarioFacade");
            usuarios = usuarioFacade.findLazy(first, pageSize, sortField, sortOrder, filters);
            setRowCount(usuarioFacade.countUsuarios(first, pageSize, sortField, sortOrder, filters));
            setPageSize(pageSize);
        } catch (NamingException ex) {
            Logger.getLogger(UsuarioLazyModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
}
