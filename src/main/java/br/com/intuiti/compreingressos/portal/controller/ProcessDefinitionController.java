package br.com.intuiti.compreingressos.portal.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.services.client.api.RemoteRuntimeEngineFactory;

import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;


@ManagedBean(name="processDefinitionController")
@ViewScoped
public class ProcessDefinitionController {
    
    public class ProcessDefinition{
        public String name;
        public String version;
        public String packageName;

        public ProcessDefinition(String name, String version, String packageName) {
            this.name = name;
            this.version = version;
            this.packageName = packageName;
        }        

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }
        
        
    }
    
    private List<ProcessDefinition> processDefinition;

    public ProcessDefinitionController() {
        processDefinition = new ArrayList<>();
        processDefinition.add(new ProcessDefinition("Solicitação de Contrato","1.0.2", "compreingressos:gestao-contrato:1.0.0-SNAPSHOT"));
    }  
    
    public List<ProcessDefinition> getProcessDefinition() {
        return processDefinition;
    }

    public void setProcessDefinition(List<ProcessDefinition> processDefinition) {
        this.processDefinition = processDefinition;
    }
    
    public void startProcess() throws MalformedURLException{
        String deploymentId = "compreingressos:gestao-contrato:1.0.0-SNAPSHOT";
        URL appUrl = new URL("http://200.155.9.201:8080/jbpm-console/");
        String user = JsfUtil.getLogin().getDsNome();
        String password = JsfUtil.getLogin().getCdPww();


        RuntimeEngine engine;
        engine = RemoteRuntimeEngineFactory.newRestBuilder()
        		.addUrl(appUrl)
        		.addUserName(user)
        		.addPassword(password)
        		.addDeploymentId(deploymentId)
        		.build();        
        
        KieSession ksession = engine.getKieSession();
        TaskService taskService = engine.getTaskService();
        AuditService auditService = engine.getAuditService();

        ProcessInstance processInstance = ksession.startProcess("gestao-contrato.Comercial");
        JsfUtil.addSuccessMessage("Processo Comercial foi iniciado.");
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, null, "Processo Comercial foi iniciado.");
    }
        
}
