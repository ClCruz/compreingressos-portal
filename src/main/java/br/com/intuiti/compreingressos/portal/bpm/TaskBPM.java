package br.com.intuiti.compreingressos.portal.bpm;

import java.net.MalformedURLException;
import java.net.URL;

import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.services.client.api.RemoteRuntimeEngineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;

/**
 *
 * @author edicarlosbarbosa
 */
public final class TaskBPM {

    private static final String URL = System.getenv("JBPM_URL");
    private static final Logger logger = LoggerFactory.getLogger(TaskBPM.class);

    private static final String deploymentId = "compreingressos:gestao-contrato:1.0.0-SNAPSHOT";
    private static RuntimeEngine engineFactory = null;    
    
    public static RuntimeEngine getRuntimeEngine() {
        if (engineFactory == null) {
            try {
                URL jbpmURL = new URL(URL);
                engineFactory = RemoteRuntimeEngineFactory.newRestBuilder()
                        .addUrl(jbpmURL)
                        .addUserName(JsfUtil.getLogin().getCdLogin())
                        .addPassword(JsfUtil.getLogin().getCdPww())
                        .addDeploymentId(deploymentId)
                        .build();
                	
            } catch (MalformedURLException ex) {
                logger.error("Error when build URL for jBPM: " + URL, ex);
            }
        }
        return engineFactory;
    }
    
    public static void clearFactory(){
    	engineFactory = null;
    }
}
