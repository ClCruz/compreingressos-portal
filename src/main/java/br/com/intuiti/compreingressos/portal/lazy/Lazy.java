/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.lazy;

import br.com.intuiti.compreingressos.portal.bean.AbstractFacade;
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
public class Lazy<T> extends LazyDataModel<T> {

    private Class<T> entityClass;

    public Lazy(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private List<T> lista = null;

    public Lazy() {
    }
//
//    @Override
//    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
//        lista = new ArrayList<>();
//        try {
//            Context ctx = new javax.naming.InitialContext();
//            AbstractFacade<entityClass.getClass()> objFacade = (AbstractFacade<entityClass.getClass()>) ctx.lookup("java:global/compreingressos-portal/BancoFacade!br.com.intuiti.compreingressos.portal.bean.BancoFacade");
//            lista = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
//            setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
//            setPageSize(pageSize);
//        } catch (NamingException ex) {
//            Logger.getLogger(UsuarioLazyModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }
//
//    @Override
//    public T getRowData(String rowKey) {
//        Integer id = Integer.valueOf(rowKey);
//        for (entityClass bank : banco) {
//            if (id.equals(bank.getIdBanco())) {
//                return bank;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Object getRowKey(T bnc) {
//        return bnc.getIdBanco();
//    }
}
