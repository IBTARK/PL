package procesamiento;

import asint.ProcesamientoAbstracto;
import asint.SintaxisAbstracta.Nodo;

public abstract class ProcesamientoAuxiliar<T> extends ProcesamientoAbstracto {
	
	public T resuelve(Nodo a) {
		a.procesa(this);
		return sol();
	}
    abstract T sol();
}
