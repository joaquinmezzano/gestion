package eclipse.actividad.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.test.Deployment;
import org.flowable.engine.test.FlowableTest;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;

@FlowableTest
public class ProcessTest {
	
	private static final String PROCESS_KEY = "from_property";
	private static final String BPMN = "processes/From_Property_-_Unit_Testing.bpmn20.xml";
	
    @Test
    @Deployment(resources = BPMN)
    public void testFormulario(RuntimeService runtimeService, TaskService taskService) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(PROCESS_KEY);
        assertNotNull(pi);

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        assertNotNull(task);
        assertEquals("Rellenar formulario", task.getName());
        assertEquals("aceptar_rechazar", task.getFormKey());
    }

    @Test
    @Deployment(resources = BPMN)
    public void testAceptar(RuntimeService runtimeService, TaskService taskService) {
    	ProcessInstance pi = runtimeService.startProcessInstanceByKey(PROCESS_KEY);
        assertNotNull(pi);
        
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        assertNotNull(task);
        assertEquals("Rellenar formulario", task.getName());
        
        taskService.complete(task.getId(), Map.of("boton_aceptar_rechazar", "Aceptar"));
        
        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        assertNotNull(task);
        assertEquals("Aceptar", task.getName());
        
        taskService.complete(task.getId());
        
        assertEquals(0, runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).count());
    }
    
    @Test
    @Deployment(resources = BPMN)
    public void testRechazar(RuntimeService runtimeService, TaskService taskService) {
    	ProcessInstance pi = runtimeService.startProcessInstanceByKey(PROCESS_KEY);
        assertNotNull(pi);
        
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        assertNotNull(task);
        assertEquals("Rellenar formulario", task.getName());
        
        taskService.complete(task.getId(), Map.of("boton_aceptar_rechazar", "Rechazar"));
        
        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        assertNotNull(task);
        assertEquals("Rechazar", task.getName());
        
        taskService.complete(task.getId());
        
        assertEquals(0, runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).count());
    }
    
}