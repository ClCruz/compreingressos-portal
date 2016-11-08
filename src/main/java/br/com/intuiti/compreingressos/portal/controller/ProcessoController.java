package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.kie.api.task.TaskService;

import br.com.intuiti.compreingressos.portal.bean.ContratoClienteFacade;
import br.com.intuiti.compreingressos.portal.bpm.TaskBPM;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.model.Processo;
import br.com.intuiti.compreingressos.portal.model.Tarefa;

@ManagedBean(name = "processoController")
@ViewScoped
public class ProcessoController implements Serializable {

	@EJB
	private ContratoClienteFacade processoEJB;

	private static final long serialVersionUID = 1L;
	private List<Processo> listaProcessos = null;
	private Processo processo = new Processo();
	private Tarefa tarefa;
	private Boolean inSolicitarProtocoloDocumentacaoPendente;
	private Boolean inValidadeDefinitiva;
	private Boolean inProcessoProtocolado;
	private Boolean inConcluirHabilitado;
	private Date dtInicioRenovacao;
	private int tentativa;
	private Date currentDate = new Date();

	public ProcessoController() {
	}

	public Processo getProcesso() {
		return processo;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Date getDtInicioRenovacao() {
		return dtInicioRenovacao;
	}

	public void setDtInicioRenovacao(Date dtInicioRenovacao) {
		this.dtInicioRenovacao = dtInicioRenovacao;
	}

	public Boolean getInSolicitarProtocoloDocumentacaoPendente() {
		return inSolicitarProtocoloDocumentacaoPendente;
	}

	public void setInSolicitarProtocoloDocumentacaoPendente(Boolean inSolicitarProtocoloDocumentacaoPendente) {
		this.inSolicitarProtocoloDocumentacaoPendente = inSolicitarProtocoloDocumentacaoPendente;
	}

	public Boolean getInValidadeDefinitiva() {
		return inValidadeDefinitiva;
	}

	public void setInValidadeDefinitiva(Boolean inValidadeDefinitiva) {
		this.inValidadeDefinitiva = inValidadeDefinitiva;
	}

	public Boolean getInProcessoProtocolado() {
		return inProcessoProtocolado;
	}

	public void setInProcessoProtocolado(Boolean inProcessoProtocolado) {
		this.inProcessoProtocolado = inProcessoProtocolado;
	}

	public void setInConcluirHabilitado(Boolean inConcluirHabilitado) {
		this.inConcluirHabilitado = inConcluirHabilitado;
	}

	public Boolean getInConcluirHabilitado() {
		return inConcluirHabilitado;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public List<Processo> getListaProcessos() {
		return listaProcessos;
	}

	public void setListaProcessos(List<Processo> listaProcessos) {
		this.listaProcessos = listaProcessos;
	}

	public int getTentativa() {
		return tentativa;
	}

	public void setTentativa(int tentativa) {
		this.tentativa = tentativa;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
	public ContratoClienteFacade getFacade() {
        return processoEJB;
    }

	public Processo prepareCreate() {
		processo = new Processo();
		return processo;
	}

//	public void obtemProcesso(){
//		if(tarefa != null){
//			try{
//				Long processInstanceId = tarefa.getProcessInstanceId();
//				Long qtd = getFacade().exist(processInstanceId);
//				if(qtd > 0){
//					processo = getFacade().find(processInstanceId.intValue());
//				} else {
//					processo = new Processo(processInstanceId.intValue());
//				}
//			} catch(EJBException ex){
//				Logger.getLogger(this.getClass().getName()).log(Level.INFO, "EJB do Processo indisponível", ex);
//			} catch(Exception ex){
//				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
//			}
//		}
//	}

	public void releaseTask() {
		TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
		taskService.release(tarefa.getId(), JsfUtil.getLogin().getCdLogin());
		JsfUtil.addSuccessMessage("Tarefa " + tarefa.getName() + " liberada.");
	}

	public void completeTask() {
		TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
		Map<String, Object> map = new HashMap<>();
		map.put("contratoId_", processo.getIdContrato() + "i");
		System.out.println("contratoId: "+processo.getIdContrato());
		map.put("aprovado_", processo.isAprovado());
		taskService.complete(tarefa.getId(), JsfUtil.getLogin().getCdLogin(), map);
		JsfUtil.addSuccessMessage("Tarefa " + tarefa.getName() + " concluída.");
	}

}
