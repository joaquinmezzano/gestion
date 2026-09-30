package eclipse.actividad;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

public class CrearTareaListener implements TaskListener {
	
	@Override
	public void notify(DelegateTask task) {
		System.out.println("*** TASK LISTENER ***");
		System.out.println("Se creó la tarea: "+task.getName());
		System.out.println("Asigana a: "+task.getAssignee());
	}
	
}