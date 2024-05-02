package procesamiento;

import asint.ProcesamientoAbstracto;
import asint.SintaxisAbstracta.Nodo;

public abstract class ProcesamientoAuxiliar extends ProcesamientoAbstracto {
	
	public Nodo resuelve(Nodo a) {
		a.procesa(this);
		return sol();
	}
    abstract Nodo sol();
}
