package eclipse.actividad;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

public class EmpezarListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) {
		System.out.println("***Comienzo de orden: "+execution.getEventName()+"***");
	}

}
