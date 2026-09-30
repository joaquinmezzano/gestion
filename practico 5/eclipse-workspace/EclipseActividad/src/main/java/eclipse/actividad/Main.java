package eclipse.actividad;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

public class Main {
	public static void main(String[] args) {

        // Crear el motor Flowable
        ProcessEngine engine =
            ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration()
                .buildProcessEngine();

        System.out.println("Flowable iniciado");

        // Desplegar el BPMN
        engine.getRepositoryService()
            .createDeployment()
            .addClasspathResource(
                "processes/Miprimerproceso.bpmn20.xml")
            .deploy();

        System.out.println("Proceso desplegado");

        // Iniciar una instancia
        ProcessInstance proceso =
            engine.getRuntimeService()
                .startProcessInstanceByKey("miprimerproceso");

        System.out.println("Proceso iniciado");

        // Buscar la tarea que esta esperando
        Task tarea =
            engine.getTaskService()
                .createTaskQuery()
                .processInstanceId(proceso.getId())
                .singleResult();
        
        System.out.println("Tarea actual: " + tarea.getName());
        
        // Completar la primer tarea
        engine.getTaskService().complete(tarea.getId());
        
        // Hacemos lo mismo con la segunda tarea
        Task tarea2 =
                engine.getTaskService()
                    .createTaskQuery()
                    .processInstanceId(proceso.getId())
                    .singleResult();
            
        System.out.println("Tarea actual: " + tarea2.getName());
        
        engine.close();
    }
}
