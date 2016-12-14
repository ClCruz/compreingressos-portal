package br.com.intuiti.compreingressos.portal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.manager.audit.VariableInstanceLog;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.intuiti.compreingressos.portal.bean.ContratoClienteFacade;
import br.com.intuiti.compreingressos.portal.bean.ContratoGedFacade;
import br.com.intuiti.compreingressos.portal.bpm.TaskBPM;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import br.com.intuiti.compreingressos.portal.model.ContratoClientePrazoPagamento;
import br.com.intuiti.compreingressos.portal.model.ContratoClienteTipoLancamento;
import br.com.intuiti.compreingressos.portal.model.ContratoGed;
import br.com.intuiti.compreingressos.portal.model.Tarefa;

@ManagedBean(name = "contratoController")
@ViewScoped
public class ContratoController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private br.com.intuiti.compreingressos.portal.bean.ContratoClienteFacade ejbFacade;
	@EJB
	private br.com.intuiti.compreingressos.portal.bean.ContratoGedFacade ejbContratoGed;
	private List<ContratoCliente> items = null;
	private List<ContratoClientePrazoPagamento> itemsPP = null;
	private List<ContratoClienteTipoLancamento> itemsTL = null;
	private ContratoCliente selected;
	private ContratoClientePrazoPagamento selectedPP;
	private ContratoClienteTipoLancamento selectedTL;
	private Tarefa tarefa;
	private boolean editavel = true;
	private String stats = "P";
	private ContratoGed contratoGed;
	private List<ContratoGed> listaContratoGed;
	private FileUpload dsArquivo;

	@ManagedProperty(name = "contratoClientePrazoPagamentoController", value = "#{contratoClientePrazoPagamentoController}")
	private ContratoClientePrazoPagamentoController contratoClientePrazoPagamentoController = new ContratoClientePrazoPagamentoController();

	@ManagedProperty(name = "contratoClienteTipoLancamentoController", value = "#{contratoClienteTipoLancamentoController}")
	private ContratoClienteTipoLancamentoController contratoClienteTipoLancamentoController = new ContratoClienteTipoLancamentoController();

	public ContratoController() {
		itemsPP = new ArrayList<>();
		itemsTL = new ArrayList<>();
		listaContratoGed = new ArrayList<>();
		FacesContext f = FacesContext.getCurrentInstance();
        Task task = TaskBPM.getRuntimeEngine().getTaskService().getTaskById(Long.valueOf(getTarefaParam(f)));
        tarefa = new Tarefa();
        tarefa.setId(task.getId());
        tarefa.setProcessInstanceId(task.getTaskData().getProcessInstanceId());
        tarefa.setName(task.getName());
	}
	
	@PostConstruct
	public void init() {
		prepareCreate();
	}
	
	public String getTarefaParam(FacesContext f) {
		Map<String, String> params = f.getExternalContext().getRequestParameterMap();
		return params.get("tarefa");
	}
	
	protected void setEmbeddableKeys() {
	}

	protected void initializeEmbeddableKey() {
	}

	public ContratoCliente prepareCreate() {
		selected = new ContratoCliente();
		contratoGed = new ContratoGed();
		selectedPP = new ContratoClientePrazoPagamento();
		selectedTL = new ContratoClienteTipoLancamento();
		obtemContrato();
		initializeEmbeddableKey();
		return selected;
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

	public void setStatus() {
		this.stats = selected.getInStatusContrato();
	}
	
	public ContratoGed getContratoGed() {
		return contratoGed;
	}

	public void setContratoGed(ContratoGed contratoGed) {
		this.contratoGed = contratoGed;
	}

	public List<ContratoGed> getListaContratoGed() {
		return listaContratoGed;
	}

	public FileUpload getDsArquivo() {
		return dsArquivo;
	}

	public void setDsArquivo(FileUpload dsArquivo) {
		this.dsArquivo = dsArquivo;
	}
	
	public ContratoGedFacade getContratoGedFacade() {
		return ejbContratoGed;
	}

	// Métodos para adicionas nas listas.
	public void addPP() {
		itemsPP.add(selectedPP);
	}

	public void addTL() {
		itemsTL.add(selectedTL);
	}
	
	public void addContratoGed() {
		listaContratoGed.add(contratoGed);
		contratoGed = new ContratoGed();
	}

	public void removePP(ContratoClientePrazoPagamento selectedPP){
		itemsPP.remove(selectedPP);
		if (selectedPP.getIdContratoCliente() != null) {
			contratoClientePrazoPagamentoController.getFacade().remove(selectedPP);
		}
	}
	
	public void removeTL(ContratoClienteTipoLancamento selectedTL){
		itemsTL.remove(selectedTL);
		if (selectedTL.getIdContratoCliente() != null) {
		contratoClienteTipoLancamentoController.getFacade().remove(selectedTL);
		}
	}
	
	public void removeContratoGed(ContratoGed contratoGed) {
		listaContratoGed.remove(contratoGed);
		if(contratoGed.getIdContrato() != null){
			getContratoGedFacade().remove(contratoGed);
		}
	}
	
//	Método upload
	public void upload(FileUploadEvent evento) throws IOException {
		Date date = new Date();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		
		FacesContext aFacesContext = FacesContext.getCurrentInstance();
		ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
		String fileName = date.getTime() + "-" + evento.getFile().getFileName();
		
		byte[] arq = evento.getFile().getContents();
		saveFileDisk(evento.getFile(), fileName);
		contratoGed.setDsArquivo(fileName);
		listaContratoGed.add(contratoGed);
	}
	
	public void saveFileDisk(UploadedFile file, String fileName) throws IOException {
		String filePath = "/compreingressos-portal/uploads/";
		InputStream in = file.getInputstream();
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		int content;
		while ((content = in.read()) > -1) {
			out.write(content);
		}
		in.close();
		out.close();
	}
	
	public InputStream getArquivo() throws FileNotFoundException {
		return new FileInputStream(new File("/compreingressos-portal/uploads/", contratoGed.getDsArquivo()));
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
					List<ContratoGed> listContratoGed = getFacade().findProcessoCG(selected);

					/*if(selected.getIdContratoCliente() == null){*/
						itemsPP = new ArrayList<>();
						for (ContratoClientePrazoPagamento listaCPP : listaContratoClientePrazoPagamento) {
							itemsPP.add(listaCPP);
						}
	
						itemsTL = new ArrayList<>();
						for (ContratoClienteTipoLancamento listaCTL : listaContratoClienteTipoLancamento) {
							itemsTL.add(listaCTL);
						}
						listaContratoGed = new ArrayList<>();
						for (ContratoGed listaCG : listContratoGed) {
							listaContratoGed.add(listaCG);
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
		System.out.println(tarefa.getActualOwner());
		System.out.println(tarefa.getId());
		System.out.println(tarefa.getSituacao());
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
		
		if(listaContratoGed.size() > 0) {
			for (ContratoGed cGed : listaContratoGed) {
				cGed.setIdContrato(selected);
				getContratoGedFacade().edit(cGed);
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