package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.intuiti.compreingressos.portal.bean.TipoTransacaoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.TipoTransacao;

	@ManagedBean(name = "tipoTransacaoController")
	@ViewScoped
	public class TipoTransacaoController implements Serializable {

		private static final long serialVersionUID = 1L;
		@EJB
		private br.com.intuiti.compreingressos.portal.bean.TipoTransacaoFacade ejbFacade;
	    private LazyDataModel<TipoTransacao> items = null;
	    private TipoTransacao selected;
	    private final Map<String, Object> filtros = new HashMap<>();

	    public TipoTransacaoController() {
	    }

	    public TipoTransacao getSelected() {
	    	return selected;
	    }
	    
	    public void setSelected(TipoTransacao selected) {
	    	this.selected = selected;
	    }
	    
	    protected void setEmbeddableKeys() {
	    }

	    protected void initializeEmbeddableKey() {
	    }
	    
	    private TipoTransacaoFacade getFacade(){
	    	return ejbFacade;
	    }

	    public TipoTransacao prepareCreate(){
	    	selected = new TipoTransacao();
	    	initializeEmbeddableKey();
	    	return selected;
	    }
	    
	    public void create() {
	        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PaisCreated"));
	        if (!JsfUtil.isValidationFailed()) {
	            items = null;    // Invalidate list of items to trigger re-query.
	        }
	    }

	    public void update() {
	        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PaisUpdated"));
	    }

	    public void destroy() {
	        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PaisDeleted"));
	        if (!JsfUtil.isValidationFailed()) {
	            selected = null; // Remove selection
	            items = null;    // Invalidate list of items to trigger re-query.
	        }
	    }
	    
	    public LazyDataModel<TipoTransacao> getItems() {
	    	if (items == null) {
	    		items = new TipoTransacaoLazy(getFacade().findAll(0, 10, null, SortOrder.ASCENDING, filtros));
	    	}
	    	return items;
	    }
	    
	    private void persist(PersistAction persistAction, String successMessage) {
	    	if (selected != null) {
	    		setEmbeddableKeys();
	    		try {
	    			if (persistAction != PersistAction.DELETE) {
	    				getFacade().edit(selected);
	    			} else {
	    				getFacade().remove(selected);
	    			}
	    			JsfUtil.addSuccessMessage(successMessage);
	    		} catch (EJBException ex) {
	                String msg = "";
	                Throwable cause = ex.getCause();
	                if (cause != null) {
	                    msg = cause.getLocalizedMessage();
	                }
	                if (msg.length() > 0) {
	                    JsfUtil.addErrorMessage(msg);
	                } else {
	                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
	                }
	            } catch (Exception ex) {
	                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
	                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
	    		}
	    	}
	    }

	    public TipoTransacao getTipoTransacao(java.lang.Integer id) {
	        return getFacade().find(id);
	    }

	    public List<TipoTransacao> getItemsAvailableSelectMany() {
	        return getFacade().findAll();
	    }

	    public List<TipoTransacao> getItemsAvailableSelectOne() {
	        return getFacade().findAll();
	    }
	    
	    public class TipoTransacaoLazy extends LazyDataModel<TipoTransacao> {
	    	
	    	private static final long serialVersionUID = 1L;
	    	private List<TipoTransacao> objList = null;
	    	
	    	public TipoTransacaoLazy(List<TipoTransacao> objList){
	    		this.objList = objList;
	    	}
	    	
	    	@Override
	    	public List<TipoTransacao> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
	    		objList = new ArrayList<>();
	    		try {
	    			Context ctx = new javax.naming.InitialContext();
	    			TipoTransacaoFacade objFacade = (TipoTransacaoFacade) ctx.lookup("java:global/compreigressos-portal-1.0.0/TipoTransacaoFacade!br.com.intuiti.compreingressos.portal.bean.TipoTransacaoFacade");
	    			objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
	    			setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
	    			setPageSize(pageSize);
	    		} catch (NamingException ex) {
	    			System.out.println(ex);
	    		}
	    		return objList;
	    	}
	    	
	    	@Override
	    	public TipoTransacao getRowData(String rowKey) {
	    		Integer id = Integer.valueOf(rowKey);
	    		for(TipoTransacao obj : objList) {
	    			if (id.equals(obj.getIdTipoTransacao())) {
	    				return obj;
	    			}
	    		}
	    		return null;
	    	}
	    	
	    	@Override
	    	public Object getRowKey(TipoTransacao ob) {
	    		return ob.getIdTipoTransacao();
	    	}
	    }
	    
	    @FacesConverter(forClass = TipoTransacao.class)
	    public static class TipoTransacaoControllerConverter implements Converter {

	        @Override
	        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
	            if (value == null || value.length() == 0) {
	                return null;
	            }
	            TipoTransacaoController controller = (TipoTransacaoController) facesContext.getApplication().getELResolver().
	                    getValue(facesContext.getELContext(), null, "tipoTransacaoController");
	            return controller.getTipoTransacao(getKey(value));
	        }

	        java.lang.Integer getKey(String value) {
	            java.lang.Integer key;
	            key = Integer.valueOf(value);
	            return key;
	        }

	        String getStringKey(java.lang.Integer integer) {
	            StringBuilder sb = new StringBuilder();
	            sb.append(integer);
	            return sb.toString();
	        }

	        @Override
	        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
	            if (object == null) {
	                return null;
	            }
	            if (object instanceof TipoTransacao) {
	                TipoTransacao o = (TipoTransacao) object;
	                return getStringKey(o.getIdTipoTransacao());
	            } else {
	                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoTransacao.class.getName()});
	                return null;
	            }
	        }

	    }
	    
	    
	}