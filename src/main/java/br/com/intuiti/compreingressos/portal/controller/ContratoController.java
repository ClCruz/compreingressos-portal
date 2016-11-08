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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.manager.audit.VariableInstanceLog;
import org.kie.api.task.TaskService;

import br.com.intuiti.compreingressos.portal.bean.ContratoClienteFacade;
import br.com.intuiti.compreingressos.portal.bpm.TaskBPM;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import br.com.intuiti.compreingressos.portal.model.ContratoClientePrazoPagamento;
import br.com.intuiti.compreingressos.portal.model.ContratoClienteTipoLancamento;
import br.com.intuiti.compreingressos.portal.model.Tarefa;

@ManagedBean(name = "contratoController")
@ViewScoped
public class ContratoController implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private br.com.intuiti.compreingressos.portal.bean.ContratoClienteFacade ejbFacade;
    private List<ContratoCliente> items = null;
    private List<ContratoClientePrazoPagamento> itemsPP = null;
    private List<ContratoClienteTipoLancamento> itemsTL = null;
    private ContratoCliente selected;
    private ContratoClientePrazoPagamento selectedPP;
    private ContratoClienteTipoLancamento selectedTL;
    private Tarefa tarefa;
    private boolean editavel = true;
    private String stats = "P";

    @ManagedProperty(name = "contratoClientePrazoPagamentoController", value = "#{contratoClientePrazoPagamentoController}")
    private ContratoClientePrazoPagamentoController contratoClientePrazoPagamentoController = new ContratoClientePrazoPagamentoController();

    @ManagedProperty(name = "contratoClienteTipoLancamentoController", value = "#{contratoClienteTipoLancamentoController}")
    private ContratoClienteTipoLancamentoController contratoClienteTipoLancamentoController = new ContratoClienteTipoLancamentoController();

    public ContratoController() {
	itemsPP = new ArrayList<>();
	itemsTL = new ArrayList<>();
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public ContratoCliente prepareCreate() {
	selected = new ContratoCliente();
	initializeEmbeddableKey();
	return selected;
    }

    public ContratoClientePrazoPagamento prepareCreatePP() {
	selectedPP = new ContratoClientePrazoPagamento();
	initializeEmbeddableKey();
	return selectedPP;
    }

    public ContratoClienteTipoLancamento prepareCreateTL() {
	selectedTL = new ContratoClienteTipoLancamento();
	initializeEmbeddableKey();
	return selectedTL;
    }

    // GETTERS E SETTERS
    public ContratoClienteFacade getFacade() {
	return ejbFacade;
    }

    public List<ContratoCliente> getItems() {
	return items;
    }

    public List<ContratoClientePrazoPagamento> getItemsPP() {
	return itemsPP;
    }

    public List<ContratoClienteTipoLancamento> getItemsTL() {
	return itemsTL;
    }

    public ContratoCliente getSelected() {
	return selected;
    }

    public void setSelected(ContratoCliente selected) {
	this.selected = selected;
    }

    public ContratoClientePrazoPagamento getSelectedPP() {
	return selectedPP;
    }

    public void setSelectedPP(ContratoClientePrazoPagamento selectedPP) {
	this.selectedPP = selectedPP;
    }

    public ContratoClienteTipoLancamento getSelectedTL() {
	return selectedTL;
    }

    public void setSelectedTL(ContratoClienteTipoLancamento selectedTL) {
	this.selectedTL = selectedTL;
    }

    public ContratoClientePrazoPagamentoController getContratoClientePrazoPagamentoController() {
	return contratoClientePrazoPagamentoController;
    }

    public void setContratoClientePrazoPagamentoController(
	    ContratoClientePrazoPagamentoController contratoClientePrazoPagamentoController) {
	this.contratoClientePrazoPagamentoController = contratoClientePrazoPagamentoController;
    }

    public ContratoClienteTipoLancamentoController getContratoClienteTipoLancamentoController() {
	return contratoClienteTipoLancamentoController;
    }

    public void setContratoClienteTipoLancamentoController(
	    ContratoClienteTipoLancamentoController contratoClienteTipoLancamentoController) {
	this.contratoClienteTipoLancamentoController = contratoClienteTipoLancamentoController;
    }

    public Tarefa getTarefa() {
	return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
	this.tarefa = tarefa;
    }

    public boolean isEditavel() {
	return editavel;
    }
    
    public void setStatus(){
	this.stats = selected.getInStatusContrato();
    }

    // Métodos para adicionas nas listas.
    public void addPP() {
	itemsPP.add(selectedPP);
    }

    public void addTL() {
	itemsTL.add(selectedTL);
    }

    // Métodos do BPM
    @SuppressWarnings("unchecked")
    public String getVariable(Long processInstanceId, String variable) {
	AuditService auditService = TaskBPM.getRuntimeEngine().getAuditService();
	List<VariableInstanceLog> variablesLog = (List<VariableInstanceLog>) auditService
		.findVariableInstances(processInstanceId, variable);
	String value = null;
	for (VariableInstanceLog variableLog : variablesLog) {
	    value = variableLog.getValue();
	}
	return value;
	
    }

    public void obtemContrato() {
	if (tarefa != null) {
	    try {
		Long processInstanceId = tarefa.getProcessInstanceId();
		Long qtd = getFacade().exist(processInstanceId);
		if (qtd > 0) {
		    editavel = new Boolean(getVariable(tarefa.getProcessInstanceId(), "editavel"));
		    selected = getFacade().findProcesso(processInstanceId);
		    List<ContratoClientePrazoPagamento> listaContratoClientePrazoPagamento = getFacade()
			    .findProcessoPP(selected);
		    List<ContratoClienteTipoLancamento> listaContratoClienteTipoLancamento = getFacade()
			    .findProcessoTL(selected);

		    itemsPP = new ArrayList<>();
		    for (ContratoClientePrazoPagamento listaCPP : listaContratoClientePrazoPagamento) {
			itemsPP.add(listaCPP);
		    }

		    itemsTL = new ArrayList<>();
		    for (ContratoClienteTipoLancamento listaCTL : listaContratoClienteTipoLancamento) {
			itemsTL.add(listaCTL);
		    }

		} else {
		    selected = new ContratoCliente();
		    selected.setIdProcesso(processInstanceId);
		}
	    } catch (EJBException ex) {
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "EJB do Processo indisponível", ex);
	    } catch (Exception ex) {
		Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    public void releaseTask() {
	TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
	taskService.release(tarefa.getId(), JsfUtil.getLogin().getCdLogin());
	JsfUtil.addSuccessMessage("Tarefa " + tarefa.getName() + " liberada.");
    }

    public void completeTask() {
	getFacade().upStatus(stats, selected);
	TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
	Map<String, Object> map = new HashMap<>();
	map.put("aprovado_", stats.equals("A"));
	map.put("contratoId_", selected.getIdContratoCliente());
	taskService.complete(tarefa.getId(), JsfUtil.getLogin().getCdLogin(), map);
	JsfUtil.addSuccessMessage("Tarefa " + tarefa.getName() + " concluída.");
    }

    public void create() {
	persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ContratoClienteCreated"));
	if (!JsfUtil.isValidationFailed()) {
	    items = null; // Invalidate list of items to trigger re-query.
	}
    }

    private void persist(PersistAction persistAction, String successMessage) {
	if (selected != null) {
	    setEmbeddableKeys();
	    try {
		if (persistAction != PersistAction.DELETE) {
		    selected.setIdProcesso(tarefa.getProcessInstanceId());
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
		    JsfUtil.addErrorMessage(ex,
			    ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
		}
	    } catch (Exception ex) {
		Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
	    }
	}
    }

    public void persist() {
	if (itemsPP.size() > 0) {
	    for (ContratoClientePrazoPagamento ipp : itemsPP) {
		ipp.setIdContratoCliente(selected);
		contratoClientePrazoPagamentoController.getFacade().edit(ipp);
	    }
	}

	if (itemsTL.size() > 0) {
	    for (ContratoClienteTipoLancamento itl : itemsTL) {
		itl.setIdContratoCliente(selected);
		itl.setIdUsuarioInsert(JsfUtil.getLogin());
		contratoClienteTipoLancamentoController.getFacade().edit(itl);
	    }
	}
    }

    public ContratoCliente getContratoCliente(java.lang.Integer id) {
	return getFacade().find(id);
    }

    @FacesConverter(forClass = ContratoCliente.class)
    public static class ContratoClienteControllerConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
	    if (value == null || value.length() == 0) {
		return null;
	    }
	    ContratoClienteController controller = (ContratoClienteController) facesContext.getApplication()
		    .getELResolver().getValue(facesContext.getELContext(), null, "contratoClienteController");
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
		Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
			"object {0} is of type {1}; expected type: {2}",
			new Object[] { object, object.getClass().getName(), ContratoCliente.class.getName() });
		return null;
	    }
	}

    }

}