package br.com.intuiti.compreingressos.portal.lazy;

import br.com.intuiti.compreingressos.portal.bean.BancoFacade;
import br.com.intuiti.compreingressos.portal.model.Banco;
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
public class BancoLazy extends LazyDataModel<Banco>{
    private List<Banco> banco = null;

    public BancoLazy(List<Banco> banco) {
        this.banco = banco;
    }
    
    
    @Override
    public List<Banco> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        banco = new ArrayList<>();
        try {
            Context ctx = new javax.naming.InitialContext();
            BancoFacade bancoFacade = (BancoFacade) ctx.lookup("java:global/compreingressos-portal/BancoFacade!br.com.intuiti.compreingressos.portal.bean.BancoFacade");
            banco = bancoFacade.findLazy(first, pageSize, sortField, sortOrder, filters);
            setRowCount(bancoFacade.countBanco(first, pageSize, sortField, sortOrder, filters));
            setPageSize(pageSize);
        } catch (NamingException ex) {
            Logger.getLogger(UsuarioLazyModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return banco;
    }
    
    @Override
    public Banco getRowData(String rowKey) {
        Integer id = Integer.valueOf(rowKey);
        for (Banco bank : banco) {
            if (id.equals(bank.getIdBanco())){
                return bank;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(Banco bnc) {
        return bnc.getIdBanco();
    }
    
}
