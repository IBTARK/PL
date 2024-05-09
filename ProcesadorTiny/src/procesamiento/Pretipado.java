package procesamiento;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import asint.ProcesamientoAbstracto;
import asint.SintaxisAbstracta.Bloque;
import asint.SintaxisAbstracta.Campo;
import asint.SintaxisAbstracta.DecProc;
import asint.SintaxisAbstracta.DecType;
import asint.SintaxisAbstracta.DecVar;
import asint.SintaxisAbstracta.MuchasDecs;
import asint.SintaxisAbstracta.MuchosCamps;
import asint.SintaxisAbstracta.MuchosParams;
import asint.SintaxisAbstracta.NoDecs;
import asint.SintaxisAbstracta.NoParam;
import asint.SintaxisAbstracta.ParamFormRef;
import asint.SintaxisAbstracta.ParamFormal;
import asint.SintaxisAbstracta.Prog;
import asint.SintaxisAbstracta.SiDecs;
import asint.SintaxisAbstracta.SiParam;
import asint.SintaxisAbstracta.TArray;
import asint.SintaxisAbstracta.TBool;
import asint.SintaxisAbstracta.TIden;
import asint.SintaxisAbstracta.TInt;
import asint.SintaxisAbstracta.TPunt;
import asint.SintaxisAbstracta.TReal;
import asint.SintaxisAbstracta.TString;
import asint.SintaxisAbstracta.TStruct;
import asint.SintaxisAbstracta.UnCamp;
import asint.SintaxisAbstracta.UnParam;
import asint.SintaxisAbstracta.UnaDec;
import errors.GestionErroresTiny;

public class Pretipado extends ProcesamientoAbstracto {

	private GestionErroresTiny error;
	private Stack<Set<String>> pilaConjCampos = new Stack<>();
	
	public Pretipado(GestionErroresTiny error) {
		this.error = error;
	}

	@Override
	public void procesa(Prog a) {
		a.bloq().procesa(this);
	}

	@Override
	public void procesa(Bloque a) {
		a.decs().procesa(this);
	}

	@Override
	public void procesa(SiDecs a) {
		a.ldecs().procesa(this);
	}

	@Override
	public void procesa(NoDecs a) {}

	@Override
	public void procesa(MuchasDecs a) {
		a.ldecs().procesa(this);
		a.dec().procesa(this);
	}

	@Override
	public void procesa(UnaDec a) {
		a.dec().procesa(this);
	}

	@Override
	public void procesa(DecProc a) {
		a.params().procesa(this);
		a.bloq().procesa(this);
	}

	@Override
	public void procesa(DecType a) {
		a.tipo().procesa(this);
	}

	@Override
	public void procesa(DecVar a) {
		a.tipo().procesa(this);
	}

	@Override
	public void procesa(SiParam a) {
		a.lparams().procesa(this);
	}

	@Override
	public void procesa(NoParam a) {}

	@Override
	public void procesa(MuchosParams a) {
		a.lparam().procesa(this);
		a.param().procesa(this);
	}

	@Override
	public void procesa(UnParam a) {
		a.param().procesa(this);
	}

	@Override
	public void procesa(ParamFormRef a) {
		a.tipo().procesa(this);
	}

	@Override
	public void procesa(ParamFormal a) {
		a.tipo().procesa(this);
	}

	@Override
	public void procesa(TArray a) {
		a.tipo().procesa(this);
		if (a.litEnt().charAt(0) == '-')
			error.errorSemantico(a.leeFila(), a.leeCol(), "Pretipado: entero negativo en array");
	}

	@Override
	public void procesa(TPunt a) {
		a.tipo().procesa(this);
	}

	@Override
	public void procesa(TInt a) {}

	@Override
	public void procesa(TReal a) {}

	@Override
	public void procesa(TBool a) {}

	@Override
	public void procesa(TString a) {}

	@Override
	public void procesa(TIden a) {
		if (a.getVinculo() == null)
			System.out.print("");
		if (a.getVinculo().getClass() != DecType.class)
			error.errorSemantico(a.leeFila(), a.leeCol(), "Pretipado: tIden no vinculado a decType");
	}

	@Override
	public void procesa(TStruct a) {
		pilaConjCampos.add(new HashSet<>());
		a.lcampos().procesa(this);
		pilaConjCampos.pop();
	}

	@Override
	public void procesa(MuchosCamps a) {
		a.lcampos().procesa(this);
		a.campo().procesa(this);
	}

	@Override
	public void procesa(UnCamp a) {
		a.campo().procesa(this);
	}

	@Override
	public void procesa(Campo a) {
		a.tipo().procesa(this);
		Set<String> top = pilaConjCampos.pop();
		pilaConjCampos.add(top);
		if (top.contains(a.iden()))
			error.errorSemantico(a.leeFila(), a.leeCol(), "Pretipado: Campo "+ a.iden() +" duplicado");
		else
			top.add(a.iden());
	}
}
