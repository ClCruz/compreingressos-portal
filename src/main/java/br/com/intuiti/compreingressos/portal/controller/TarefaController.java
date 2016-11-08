package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.kie.api.runtime.KieSession;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;

import br.com.intuiti.compreingressos.portal.bean.ContratoClienteFacade;
import br.com.intuiti.compreingressos.portal.bpm.TaskBPM;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.model.Tarefa;

/**
 *
 * @author edicarlos.barbosa
 */
@ManagedBean(name = "tarefaController")
@ViewScoped
public class TarefaController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ContratoClienteFacade contratoEJB;

    private Tarefa tarefa;
    private List<Tarefa> tarefas;
    private List<Tarefa> tarefasFiltradas;

    private List<SelectItem> filterFaseProcesso;

    public TarefaController() {
    }

    @PostConstruct
    public void initialize() {
        retrieveTasks();
    }

    public ContratoClienteFacade getFacade() {
        return contratoEJB;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public List<Tarefa> getTarefasFiltradas() {
        return tarefasFiltradas;
    }

    public void setTarefasFiltradas(List<Tarefa> tarefasFiltradas) {
        this.tarefasFiltradas = tarefasFiltradas;
    }

    public String redirect(Tarefa task) {
        TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
        if (task.getStatus().equals("Reserved")) {
            taskService.start(task.getId(), JsfUtil.getLogin().getCdLogin());
        }
        Map<String, Object> c = taskService.getTaskContent(task.getId());
        return "/pages/" + c.get("TaskName").toString()+"?faces-redirect=true&amp;includeViewParams=true";
    }

    public void retrieveTasks() {
        tarefas = new ArrayList<>();
        try {
            TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
            for (TaskSummary t : taskService.getTasksAssignedAsPotentialOwner(JsfUtil.getLogin().getCdLogin(), "en-UK")) {
                Tarefa tar = new Tarefa();
                tar.setId(t.getId());
                tar.setName(t.getName());
                tar.setProcessInstanceId(t.getProcessInstanceId());
                tar.setDataCriacao(t.getCreatedOn());
                tar.setStatus(t.getStatusId());
                tar.setActualOwner(t.getActualOwnerId());
                tar.setDeploymentId(t.getDeploymentId());
                tarefas.add(tar);
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    public void startTask(Tarefa task) {
        TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
        taskService.start(task.getId(), JsfUtil.getLogin().getCdLogin());
        JsfUtil.addSuccessMessage("Tarefa " + task.getName() + " obtida.");
        retrieveTasks();
    }

    public void releaseTask(Tarefa task) {
        TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
        taskService.release(task.getId(), JsfUtil.getLogin().getCdLogin());
        JsfUtil.addSuccessMessage("Tarefa " + task.getName() + " liberada.");
        retrieveTasks();
    }

    public void completeTask(Tarefa task) {
        TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
        taskService.complete(task.getId(), JsfUtil.getLogin().getCdLogin(), new HashMap<String, Object>());
        JsfUtil.addSuccessMessage("Tarefa " + task.getName() + " concluída.");
        retrieveTasks();
    }

    public void deleteTask() {
        KieSession taskService = TaskBPM.getRuntimeEngine().getKieSession();
        taskService.abortProcessInstance(tarefa.getProcessInstanceId());

        Long qtd = getFacade().exist(tarefa.getProcessInstanceId());
        if (qtd > 0) {
//            Long processInstance = tarefa.getProcessInstanceId();
        }

        JsfUtil.addSuccessMessage("Processo " + tarefa.getName() + " excluído.");
        retrieveTasks();
    }
    
    

    public String status(String status) {
        String resultado;
        switch (status) {
            case "InProgress":
                resultado = "Em Andamento";
                break;
            case "Ready":
                resultado = "Não Iniciada";
                break;
            case "Reserved":
                resultado = "Reservada";
                break;
            default:
                resultado = "Não Iniciada";
        }
        return resultado;
    }

    public String getSituacao(Task task) {
        String situacao = getFacade().findSituacao(task.getTaskData().getProcessInstanceId());
        if (situacao != null) {
            return situacao;
        } else {
            return "";
        }
    }

    public List<SelectItem> getFilterFaseProcesso() {
        if (filterFaseProcesso == null) {
            filterFaseProcesso = new ArrayList<>(Arrays.asList(
                    new SelectItem("Receber solicitação do cliente", "Receber solicitação do cliente"),
                    new SelectItem("Analisar documentos", "Analisar documentos"),
                    new SelectItem("Solicitar doc. cliente", "Solicitar doc. cliente"),
                    new SelectItem("Solicitar protocolo com pendencia", "Solicitar protocolo com pendencia"),
                    new SelectItem("Solicitar protocolo", "Solicitar protocolo"),
                    new SelectItem("Aguardar deferimento", "Aguardar deferimento"),
                    new SelectItem("Enviar documentos para o cliente", "Enviar documentos para o cliente"),
                    new SelectItem("Faturar Serviço", "Faturar Serviço"),
                    new SelectItem("Emitir NF", "Emitir Nota Fiscal")
            ));
        }
        return filterFaseProcesso;
    }

}
