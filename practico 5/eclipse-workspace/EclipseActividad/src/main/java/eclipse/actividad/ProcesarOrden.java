package eclipse.actividad;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class ProcesarOrden implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) {
		System.out.println("***Procesando la orden desde Java***");
	}

}