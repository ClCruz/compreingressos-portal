package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.manager.audit.VariableInstanceLog;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;

import br.com.intuiti.compreingressos.portal.bean.ContratoClienteFacade;
import br.com.intuiti.compreingressos.portal.bpm.TaskBPM;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.model.ContratoCliente;

public class TaskController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ContratoClienteFacade contratoEJB;

    private Task task;
    private List<Task> listaTask;
    private String content;

//    private List<SelectItem> filterFaseContrato;
    @ManagedProperty(name = "loginController", value = "#{loginController}")
    private LoginController loginController = new LoginController();

    public TaskController() {
    }

    @PostConstruct
    public void initialize() {
        retrieveTasks();
    }

    public ContratoClienteFacade getFacade() {
        return contratoEJB;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Task> getListaTask() {
        return listaTask;
    }

    public void setListaTask(List<Task> listaTask) {
        this.listaTask = listaTask;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    @SuppressWarnings("unchecked")
    public String processInstance(Task task, String variable) {
        AuditService auditService = TaskBPM.getRuntimeEngine().getAuditService();
        List<VariableInstanceLog> variablesLog = (List<VariableInstanceLog>) auditService.findVariableInstances(task.getTaskData().getProcessInstanceId(), variable);
        String value = null;
        for (VariableInstanceLog varLog : variablesLog) {
            value = varLog.getValue();
        }
        return value;
    }

    public String redirect(Task task) {
        TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
        if (task.getTaskData().getStatus() == Status.Reserved) {
            taskService.start(task.getId(), loginController.getUsuario().getDsNome());
        }
        Map<String, Object> c = taskService.getTaskContent(task.getId());
        return "/pages/contratoCliente/" + c.get("TaskName").toString() + "?faces-redirect=true&amp;includeViewParams=true";
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void retrieveTasks() {
        TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
        listaTask = new ArrayList();
        for (TaskSummary t : taskService.getTasksAssignedAsPotentialOwner(loginController.getUsuario().getCdLogin(), "en-UK")) {
            listaTask.add(taskService.getTaskById(t.getId()));
        }
    }

    public void startTask(Task task) {
        TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
        taskService.start(task.getId(), loginController.getUsuario().getDsNome());
        br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.addSuccessMessage("Contrato " + task.getName() + " obtido.");
        retrieveTasks();
    }

    public void releaseTask(Task task) {
        TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
        taskService.release(task.getId(), loginController.getUsuario().getDsNome());
        br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.addSuccessMessage("Contrato " + task.getName() + " liberado.");
        retrieveTasks();
    }

    public void completeTask(Task task) {
        TaskService taskService = TaskBPM.getRuntimeEngine().getTaskService();
        taskService.complete(task.getId(), loginController.getUsuario().getDsNome(), new HashMap<String, Object>());
        br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.addSuccessMessage("Contrato " + task.getName() + " finalizado.");
        retrieveTasks();
    }

    public void deleteTask() {
        KieSession taskService = TaskBPM.getRuntimeEngine().getKieSession();
        taskService.abortProcessInstance(task.getTaskData().getProcessInstanceId());

        Long qtd = getFacade().exist(task.getTaskData().getProcessInstanceId());
        if (qtd > 0) {

            Long processInstance = task.getTaskData().getProcessInstanceId();
            ContratoCliente contrato = getFacade().find(processInstance.intValue());
//	            contrato.setUsuaIdUltimaAlteracao(loginController.getUsuario());
//	            contrato.setProcNmFase(task.getName());
            contrato.setInStatusContrato("F");
            contrato.setDtInativacao(new Date());
//	            processo.setProcDsHistorico("Processo encerrado manualmente pelo usuário: "+ loginController.getUsuario().getDsNome() +". Justificativa: "+ content);
            getFacade().edit(contrato);
        }

        JsfUtil.addSuccessMessage("Contrato " + task.getName() + " excluído.");
        retrieveTasks();
    }

   public String status(String status){
        String resultado;        
        switch (status){
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

}
