package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import br.com.intuiti.compreingressos.portal.bean.TipoMeioPagamentoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.TipoMeioPagamento;

@ManagedBean(name = "tipoMeioPagamentoController")
@ViewScoped
public class TipoMeioPagamentoController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
    private br.com.intuiti.compreingressos.portal.bean.TipoMeioPagamentoFacade ejbFacade;
    private LazyDataModel<TipoMeioPagamento> items = null;
    private TipoMeioPagamento selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public TipoMeioPagamentoController() {
    }
    
    @PostConstruct
    public void init() {
    	items = new Lazy(getFacade().findAll());
    }

    public TipoMeioPagamento getSelected() {
        return selected;
    }

    public void setSelected(TipoMeioPagamento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipoMeioPagamentoFacade getFacade() {
        return ejbFacade;
    }

    public TipoMeioPagamento prepareCreate() {
        selected = new TipoMeioPagamento();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TipoMeioPagamentoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TipoMeioPagamentoUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TipoMeioPagamentoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public boolean verificaMP(String inTipoMeioPagamento, String dsTipoMeioPagamento){
    	if(getFacade().findTipoMeio(inTipoMeioPagamento, dsTipoMeioPagamento)){
    		return true;
    	} else {
    		selected = null;
    		return false;
    	}
    }

    public LazyDataModel<TipoMeioPagamento> getItems() {
        if (items == null) {
            items = new Lazy(getFacade().findAll());
        }
        return items;
    }
    
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                	if(getFacade().findTipoMeio(selected.getInTipoMeioPagamento(), selected.getDsTipoMeioPagamento())){
                		getFacade().edit(selected);
                		JsfUtil.addSuccessMessage(successMessage);
                	} else {
                		JsfUtil.addErrorMessage("Já existe um tipo de meio de pagamento cadastrado com essa descrição");
                	}
                    
                } else {
                    getFacade().remove(selected);
                    JsfUtil.addSuccessMessage(successMessage);
                }
                
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

    public TipoMeioPagamento getTipoMeioPagamento(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TipoMeioPagamento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TipoMeioPagamento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

   public class Lazy extends LazyDataModel<TipoMeioPagamento> {
	   
	   private static final long serialVersionUID = 1L;
	   
	   private List<TipoMeioPagamento> tipoMeioPagamento = null;
	   
	   public Lazy(List<TipoMeioPagamento> tipoMeioPagamento) {
		   this.tipoMeioPagamento = tipoMeioPagamento;
	   }
	   
	   @Override
	   public List<TipoMeioPagamento> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
		   List<TipoMeioPagamento> data = new ArrayList<TipoMeioPagamento>();
		   for(TipoMeioPagamento tmp : tipoMeioPagamento){
			   
			   boolean match = true;
			   if(filters != null){
				   for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();){
					   try{
						   String filterProperty = it.next();
						   Object filterValue = filters.get(filterProperty);
						   Field field = tmp.getClass().getDeclaredField(filterProperty);
						   field.setAccessible(true);
						   String fieldValue = String.valueOf(field.get(tmp));
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
				   data.add(tmp);
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
	   public Object getRowKey(TipoMeioPagamento object) {
		   return object.getInTipoMeioPagamento();
	   }
	   
	   @Override
	   public TipoMeioPagamento getRowData(String rowKey){
		   String id = String.valueOf(rowKey);
		   for(TipoMeioPagamento t : tipoMeioPagamento){
			   if(id.equals(t.getInTipoMeioPagamento())){
				   return t;
			   }
		   }
		   return null;
	   }
   }
   
   public class LazySorter implements Comparator<TipoMeioPagamento> {
	   private String sortField;
	   private SortOrder sortOrder;
	   
	   public LazySorter(String sortField, SortOrder sortOrder){
		   this.sortField = sortField;
		   this.sortOrder = sortOrder;
	   }
   
    
   public int compare(TipoMeioPagamento object1, TipoMeioPagamento object2){
	   try{
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
    @FacesConverter(forClass = TipoMeioPagamento.class)
    public static class TipoMeioPagamentoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoMeioPagamentoController controller = (TipoMeioPagamentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoMeioPagamentoController");
            return controller.getTipoMeioPagamento(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TipoMeioPagamento) {
                TipoMeioPagamento o = (TipoMeioPagamento) object;
                return getStringKey(o.getInTipoMeioPagamento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoMeioPagamento.class.getName()});
                return null;
            }
        }

    }

}