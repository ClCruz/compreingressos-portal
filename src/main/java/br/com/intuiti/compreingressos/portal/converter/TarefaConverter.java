package br.com.intuiti.compreingressos.portal.converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.kie.api.task.model.Task;

import br.com.intuiti.compreingressos.portal.bpm.TaskBPM;
import br.com.intuiti.compreingressos.portal.model.Tarefa;

@FacesConverter("tarefaConverter")
public class TarefaConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	System.out.println("TAREFACONVERTER");
        if (value == null || value.length() == 0) {
            return null;
        }        
        Task task = TaskBPM.getRuntimeEngine().getTaskService().getTaskById(Long.valueOf(value));
        Tarefa t = new Tarefa();
        t.setId(task.getId());
        t.setProcessInstanceId(task.getTaskData().getProcessInstanceId());
        t.setName(task.getName());
        return t;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Tarefa) {
            Tarefa o = (Tarefa) value;
            return Long.toString(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{value, value.getClass().getName(), Tarefa.class.getName()});
            return null;
        }
    }
    
    
}
