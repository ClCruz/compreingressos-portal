package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.intuiti.compreingressos.portal.bean.TipoLancamentoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.TipoLancamento;

@ManagedBean(name = "tipoLancamentoController")
@ViewScoped
public class TipoLancamentoController implements Serializable {

	private static final long serialVersionUID = 1L;
    @EJB
    private br.com.intuiti.compreingressos.portal.bean.TipoLancamentoFacade ejbFacade;
    private LazyDataModel<TipoLancamento> items = null;
    private TipoLancamento selected;
    private final Map<String, Object> filtros = new HashMap<>();
    private boolean editavel = true;

    public TipoLancamentoController() {
    }
    
    @PostConstruct
    public void init() {
    	items = new Lazy(getFacade().findAll());
    }

    public TipoLancamento getSelected() {
        return selected;
    }

    public void setSelected(TipoLancamento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipoLancamentoFacade getFacade() {
        return ejbFacade;
    }

    public TipoLancamento prepareCreate() {
        selected = new TipoLancamento();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TipoLancamentoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TipoLancamentoUpdated"));
    }

    public void destroy() {
    	if(selected.getDtInsert().compareTo(new Date()) <= 0){
    		JsfUtil.addErrorMessage("Não é possível remover esse tipo de lançamento, pois a data de validade é menor que a data atual.");
    	} else {
    		persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TipoLancamentoDeleted"));
            if (!JsfUtil.isValidationFailed()) {
                selected = null; // Remove selection
                items = null;    // Invalidate list of items to trigger re-query.
            }
    	}
        
    }

    public LazyDataModel<TipoLancamento> getItems() {
        if (items == null) {
            items = new Lazy(getFacade().findAll());
        }
        return items;
    }
    
    public boolean isEditavel() {
		return editavel;
	}
    
    public String getNextDate(){
        Calendar data1 = Calendar.getInstance();
        data1.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        return formatoData.format(data1.getTime());
    }
    
    public void getDataVl(){
    	if(selected.getDtInsert().compareTo(new Date()) <= 0){
    		editavel = false;
    	} else {
    		editavel = true;
    	}
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

    public TipoLancamento getTipoLancamento(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TipoLancamento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TipoLancamento> getItemsAvailableSelectOne() {
        return getFacade().findAtivo();
    }
    
    public class Lazy extends LazyDataModel<TipoLancamento> {

    	private static final long serialVersionUID = 1L;

    	private List<TipoLancamento> tipoLancamento = null;

    	public Lazy(List<TipoLancamento> tipoLancamento) {
    		this.tipoLancamento = tipoLancamento;
    	}

    	@Override
    	public List<TipoLancamento> load(int first, int pageSize, String sortField, SortOrder sortOrder,
    			Map<String, Object> filters) {
    		List<TipoLancamento> data = new ArrayList<TipoLancamento>();
    		for(TipoLancamento tl : tipoLancamento){
    			
    			boolean match = true;
    			if(filters != null){
    				for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();){
    					try{
    						String filterProperty = it.next();
    						Object filterValue = filters.get(filterProperty);
    						Field field = tl.getClass().getDeclaredField(filterProperty);
    						field.setAccessible(true);
    						String fieldValue = String.valueOf(field.get(tl));
    						if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
    							match = true;
    						} else {
    							match = false;
    							break;
    						}
    					} catch (Exception e) {
    						e.printStackTrace();
    						match = false;
    					}
    				}
    			}
    			
    			if(match){
    				data.add(tl);
    			}
    		}
    		
    		//sort
    		if(sortField != null) {
    			Collections.sort(data, new LazySorter(sortField, sortOrder));
    		}
    		
    		//rowCount
    		int dataSize = data.size();
    		this.setRowCount(dataSize);
    		
    		//paginate
    		if(dataSize > pageSize){
    			try{
    				return data.subList(first, first + pageSize);
    			} catch (IndexOutOfBoundsException e) {
    				return data.subList(first, first + (dataSize % pageSize));
    			}
    		} else {
    			return data;
    		}
    	}
    	
    	@Override
    	public Object getRowKey(TipoLancamento object) {
    		return object.getIdTipoLancamento();
    	}
    	
    	@Override
    	public TipoLancamento getRowData(String rowKey) {
    		Integer id = Integer.valueOf(rowKey);
    		for(TipoLancamento t : tipoLancamento){
    			if(id.equals(t.getIdTipoLancamento())){
    				return t;
    			}
    		}
    		return null;
    	}
    }
    
    public class LazySorter implements Comparator<TipoLancamento> {
    	private String sortField;
    	private SortOrder sortOrder;
    	
    	public LazySorter(String sortField, SortOrder sortOrder){
    		this.sortField = sortField;
    		this.sortOrder = sortOrder;
    	}
    	
    	public int compare(TipoLancamento object1, TipoLancamento object2){
    		try {
    			Field field1 = object1.getClass().getDeclaredField(this.sortField);
    			Field field2 = object2.getClass().getDeclaredField(this.sortField);
    			field1.setAccessible(true);
    			field2.setAccessible(true);
    			Object value1 = field1.get(object1);
    			Object value2 = field2.get(object2);
    			
    			int value = ((Comparable)value1).compareTo(value2);
    			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
    		}
    		catch(Exception e) {
    			throw new RuntimeException();
    		}
    	}
    }

    @FacesConverter(forClass = TipoLancamento.class)
    public static class TipoLancamentoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoLancamentoController controller = (TipoLancamentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoLancamentoController");
            return controller.getTipoLancamento(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TipoLancamento) {
                TipoLancamento o = (TipoLancamento) object;
                return getStringKey(o.getIdTipoLancamento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoLancamento.class.getName()});
                return null;
            }
        }

    }

}
