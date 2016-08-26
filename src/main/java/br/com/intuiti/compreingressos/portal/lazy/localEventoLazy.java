package br.com.intuiti.compreingressos.portal.lazy;

import br.com.intuiti.compreingressos.portal.bean.LocalEventoFacade;
import br.com.intuiti.compreingressos.portal.model.LocalEvento;
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
public class localEventoLazy extends LazyDataModel<LocalEvento> {
    private List<LocalEvento> localEvento = null;

    public localEventoLazy(List<LocalEvento> localEvento) {
        this.localEvento = localEvento;
    }

    @Override
    public List<LocalEvento> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        localEvento = new ArrayList<>();
        try{
            Context ctx = new javax.naming.InitialContext();
            LocalEventoFacade localFacade = (LocalEventoFacade) ctx.lookup("java:global/compreingressos-portal/LocalEventoFacade!br.com.intuiti.compreingressos.portal.bean.LocalEventoFacade");
            localEvento = localFacade.findLazy(first, pageSize, sortField, sortOrder, filters);
            setRowCount(localFacade.countLocal(first, pageSize, sortField, sortOrder, filters));
            setPageSize(pageSize);
        } catch(NamingException ex){
            Logger.getLogger(localEventoLazy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return localEvento;
    }
    
    @Override
    public LocalEvento getRowData(String rowKey) {
        Integer id = Integer.valueOf(rowKey);
        for (LocalEvento local : localEvento) {
            if (id.equals(local.getIdLocalEvento())){
                return local;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(LocalEvento lcl) {
        return lcl.getIdLocalEvento();
    }
    
    
}
