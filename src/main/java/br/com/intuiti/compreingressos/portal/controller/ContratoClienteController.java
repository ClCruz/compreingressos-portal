package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.intuiti.compreingressos.portal.bean.ContratoClienteFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.Base;
import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import br.com.intuiti.compreingressos.portal.model.ContratoClientePrazoPagamento;
import br.com.intuiti.compreingressos.portal.model.ContratoClienteTipoLancamento;
import br.com.intuiti.compreingressos.portal.model.Evento;
import br.com.intuiti.compreingressos.portal.model.Usuario;

@ManagedBean(name = "contratoClienteController")
@ViewScoped
public class ContratoClienteController implements Serializable {

	private static final long serialVersionUID = 1L;
    @EJB
    private br.com.intuiti.compreingressos.portal.bean.ContratoClienteFacade ejbFacade;
    private LazyDataModel<ContratoCliente> items = null;
    private List<ContratoClientePrazoPagamento> itemsEditPP = null;
    private List<ContratoClientePrazoPagamento> itemsPP = null;
    private List<ContratoClienteTipoLancamento> itemsTL = null;
    private List<ContratoClienteTipoLancamento> itemsEditTL = null;
    private ContratoCliente selected;
    private ContratoClientePrazoPagamento selectedPP;
    private ContratoClienteTipoLancamento selectedTL;
    private Base selectedB;
    private final Map<String, Object> filtros = new HashMap<>();
    private Usuario usuario;
    
    @ManagedProperty(name = "contratoClientePrazoPagamentoController", value = "#{contratoClientePrazoPagamentoController}")
    private ContratoClientePrazoPagamentoController contratoClientePrazoPagamentoController = new ContratoClientePrazoPagamentoController();

    @ManagedProperty(name = "contratoClienteTipoLancamentoController", value = "#{contratoClienteTipoLancamentoController}")
    private ContratoClienteTipoLancamentoController contratoClienteTipoLancamentoController = new ContratoClienteTipoLancamentoController();
    
    public ContratoClienteController() {
        itemsPP = new ArrayList<>();
        itemsTL = new ArrayList<>();
        itemsEditPP = new ArrayList<>();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		usuario = (Usuario) session.getAttribute("usuario");
    }
    
    @PostConstruct
    public void init() {
        
        selectedPP = new ContratoClientePrazoPagamento();
        selectedTL = new ContratoClienteTipoLancamento();
        selectedB = new Base();
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ContratoClienteFacade getFacade() {
        return ejbFacade;
    }

    public ContratoClienteTipoLancamentoController getContratoClienteTipoLancamentoController() {
        return contratoClienteTipoLancamentoController;
    }

    public void setContratoClienteTipoLancamentoController(ContratoClienteTipoLancamentoController contratoClienteTipoLancamentoController) {
        this.contratoClienteTipoLancamentoController = contratoClienteTipoLancamentoController;
    }

    public void setContratoClientePrazoPagamentoController(ContratoClientePrazoPagamentoController contratoClientePrazoPagamentoController) {
        this.contratoClientePrazoPagamentoController = contratoClientePrazoPagamentoController;
    }

    public ContratoClientePrazoPagamentoController getContratoClientePrazoPagamentoController() {
        return contratoClientePrazoPagamentoController;
    }
    
    public void listaItens() {
        if (selected.getIdContratoCliente() != null) {
            itemsEditPP = new ArrayList<>();
            List<ContratoClientePrazoPagamento> listaTemporariaE = getContratoClientePrazoPagamentoController().getFacade().findAll(new ContratoCliente(selected.getIdContratoCliente()));
            if (listaTemporariaE != null) {
                for (ContratoClientePrazoPagamento lista : listaTemporariaE) {
                    itemsEditPP.add(new ContratoClientePrazoPagamento(lista.getIdContratoClientePrazoPagamento(), lista.getIdPrazoPagamento(), lista.getIdFormaPagamento()));
                }
            }
            
            itemsEditTL = new ArrayList<>();
            List<ContratoClienteTipoLancamento> listaTemporariaTLE = getContratoClienteTipoLancamentoController().getFacade().findAll(new ContratoCliente(selected.getIdContratoCliente()));
            if(listaTemporariaTLE != null){
                for(ContratoClienteTipoLancamento listaTL : listaTemporariaTLE){
                    itemsEditTL.add(new ContratoClienteTipoLancamento(listaTL.getIdTipoLancamento(), listaTL.getDtInicioVigencia(), listaTL.getVlAplicacaoTipoLancamento(), listaTL.getVlMinimoTipoLancamento(), listaTL.getIdModalidadeCobranca()));
                }
            }
        }
    }

    public void addPP() {
        itemsPP.add(new ContratoClientePrazoPagamento(selectedPP.getIdPrazoPagamento(), selectedPP.getIdFormaPagamento()));
    }

    public void addEditPP() {
        itemsEditPP.add(new ContratoClientePrazoPagamento(selectedPP.getIdPrazoPagamento(), selectedPP.getIdFormaPagamento()));
    }
    
    public void addTL() {
        itemsTL.add(new ContratoClienteTipoLancamento(selectedTL.getIdTipoLancamento(), selectedTL.getDtInicioVigencia(), selectedTL.getVlAplicacaoTipoLancamento(), selectedTL.getVlMinimoTipoLancamento(), selectedTL.getIdModalidadeCobranca()));
    }
    
    public void addEditTL(){
        itemsEditTL.add(new ContratoClienteTipoLancamento(selectedTL.getIdTipoLancamento(), selectedTL.getDtInicioVigencia(), selectedTL.getVlAplicacaoTipoLancamento(), selectedTL.getVlMinimoTipoLancamento(), selectedTL.getIdModalidadeCobranca()));
    }

    public String mostraData() {
        Calendar data1 = Calendar.getInstance();
        data1.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        return formatoData.format(data1.getTime());
    }

    public List<ContratoClienteTipoLancamento> getItemsTL() {
        return itemsTL;
    }

    public void setItemsTL(List<ContratoClienteTipoLancamento> itemsTL) {
        this.itemsTL = itemsTL;
    }
    
    public List<ContratoClienteTipoLancamento> getItemsEditTL() {
        return itemsEditTL;
    }

    public void setItemsEditTL(List<ContratoClienteTipoLancamento> itemsEditTL) {
        this.itemsEditTL = itemsEditTL;
    }

    public List<ContratoClientePrazoPagamento> getItemsPP() {
        return itemsPP;
    }
    
    public void setItemsPP(List<ContratoClientePrazoPagamento> itemsPP) {
        this.itemsPP = itemsPP;
    }

    public ContratoClientePrazoPagamento getSelectedPP() {
        return selectedPP;
    }

    public ContratoClienteTipoLancamento getSelectedTL() {
        return selectedTL;
    }

    public Base getSelectedB() {
        return selectedB;
    }

    public ContratoCliente getSelected() {
        return selected;
    }

    public void setSelected(ContratoCliente selected) {
        this.selected = selected;
    }

    public List<ContratoClientePrazoPagamento> getItemsEditPP() {
        return itemsEditPP;
    }

    public void setItemsEditPP(List<ContratoClientePrazoPagamento> itemsEditPP) {
        this.itemsEditPP = itemsEditPP;
    }

    public ContratoCliente prepareCreate() {
        selected = new ContratoCliente();
        initializeEmbeddableKey();
        return selected;
    }

    public List<Evento> listaEventos() {
        return getFacade().findAll(selectedTL.getIdBase());
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ContratoClienteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ContratoClienteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ContratoClienteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public LazyDataModel<ContratoCliente> getItems() {
        if (items == null) {
            items = new ContratoClienteLazy(getFacade().findAll(0, 10, null, SortOrder.UNSORTED, filtros));
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                	selected.setInStatusContrato("P");
                    selected = getFacade().update(selected);
                    
                    persist();
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

    private void persist() {
        try {
            if (itemsPP != null) {
                for (ContratoClientePrazoPagamento ccpp : itemsPP) {
                    ccpp.setIdContratoCliente(selected);
                    getContratoClientePrazoPagamentoController().getFacade().edit(ccpp);
                }
            }
            
            if(itemsEditPP != null){
                getContratoClientePrazoPagamentoController().getFacade().delete(selected);
                for(ContratoClientePrazoPagamento cccpe : itemsEditPP){
                    cccpe.setIdContratoCliente(selected);
                    getContratoClientePrazoPagamentoController().getFacade().edit(cccpe);
                }
            }

            if (itemsTL != null) {
                for (ContratoClienteTipoLancamento cctl : itemsTL) {
                    cctl.setIdUsuarioInsert(usuario);
                    cctl.setIdContratoCliente(selected);
                    getContratoClienteTipoLancamentoController().getFacade().edit(cctl);
                }
            }
            
            if(itemsEditTL != null){
                getContratoClienteTipoLancamentoController().getFacade().delete(selected);
                for(ContratoClienteTipoLancamento cccte : itemsEditTL){
                    cccte.setIdUsuarioInsert(usuario);
                    cccte.setIdContratoCliente(selected);
                    getContratoClienteTipoLancamentoController().getFacade().edit(cccte);
                }
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

    public ContratoCliente getContratoCliente(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ContratoCliente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ContratoCliente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<ContratoClientePrazoPagamento> getItemsAvailableSelectIdContratoPP(Integer id) {
        itemsPP = getContratoClientePrazoPagamentoController().getFacade().findAll(new ContratoCliente(id));
        return getContratoClientePrazoPagamentoController().getFacade().findAll(new ContratoCliente(id));
    }

    public class ContratoClienteLazy extends LazyDataModel<ContratoCliente> {
    	
    	private static final long serialVersionUID = 1L;
        private List<ContratoCliente> objList = null;

        public ContratoClienteLazy(List<ContratoCliente> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<ContratoCliente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                ContratoClienteFacade objFacade = (ContratoClienteFacade) ctx.lookup("java:global/compreingressos-portal-1.0.0/ContratoClienteFacade!br.com.intuiti.compreingressos.portal.bean.ContratoClienteFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public ContratoCliente getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (ContratoCliente obj : objList) {
                if (id.equals(obj.getIdContratoCliente())) {
                    return obj;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(ContratoCliente ob) {
            return ob.getIdContratoCliente();
        }
    }
    
    @FacesConverter(forClass = ContratoCliente.class)
    public static class ContratoClienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContratoClienteController controller = (ContratoClienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contratoClienteController");
            return controller.getContratoCliente(getKey(value));
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
            if (object instanceof ContratoCliente) {
                ContratoCliente o = (ContratoCliente) object;
                return getStringKey(o.getIdContratoCliente());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ContratoCliente.class.getName()});
                return null;
            }
        }
    }
}
