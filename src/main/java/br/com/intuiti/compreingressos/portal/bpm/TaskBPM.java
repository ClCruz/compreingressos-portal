package br.com.intuiti.compreingressos.portal.bpm;

import java.net.MalformedURLException;
import java.net.URL;

import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.services.client.api.RemoteRuntimeEngineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.intuiti.compreingressos.portal.model.Usuario;

/**
 *
 * @author edicarlosbarbosa
 */
public final class TaskBPM {

    private static final String URL = "http://200.155.9.201:8080/jbpm-console/";
    private static final Logger logger = LoggerFactory.getLogger(TaskBPM.class);

    private static final String deploymentId = "compreingressos:gestao-contrato:1.0.0-SNAPSHOT";
    private static RuntimeEngine engineFactory = null;    
    
    public static RuntimeEngine getRuntimeEngine(Usuario usuario) {
        if (engineFactory == null) {
            try {
                URL jbpmURL = new URL(URL);
                engineFactory = RemoteRuntimeEngineFactory.newRestBuilder()
                        .addUrl(jbpmURL)
                        .addUserName("admin")
                        .addPassword("admin")
                        .addDeploymentId(deploymentId)
                        .build();
            } catch (MalformedURLException ex) {
                logger.error("Error when build URL for jBPM: " + URL, ex);
            }
        }
        return engineFactory;
    }
    
}
