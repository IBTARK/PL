package procesamiento;

import asint.Procesamiento;
import asint.SintaxisAbstracta.And;
import asint.SintaxisAbstracta.Array;
import asint.SintaxisAbstracta.ArrobaInstr;
import asint.SintaxisAbstracta.Asignacion;
import asint.SintaxisAbstracta.Bloque;
import asint.SintaxisAbstracta.BloqueInstr;
import asint.SintaxisAbstracta.Campo;
import asint.SintaxisAbstracta.DecProc;
import asint.SintaxisAbstracta.DecType;
import asint.SintaxisAbstracta.DecVar;
import asint.SintaxisAbstracta.DeleteInstr;
import asint.SintaxisAbstracta.Desigual;
import asint.SintaxisAbstracta.Div;
import asint.SintaxisAbstracta.ExpCampo;
import asint.SintaxisAbstracta.False;
import asint.SintaxisAbstracta.Iden;
import asint.SintaxisAbstracta.IfElseInstr;
import asint.SintaxisAbstracta.IfInstr;
import asint.SintaxisAbstracta.Igual;
import asint.SintaxisAbstracta.LitCad;
import asint.SintaxisAbstracta.LitEnt;
import asint.SintaxisAbstracta.LitReal;
import asint.SintaxisAbstracta.Mayor;
import asint.SintaxisAbstracta.MayorIgual;
import asint.SintaxisAbstracta.Menor;
import asint.SintaxisAbstracta.MenorIgual;
import asint.SintaxisAbstracta.Mod;
import asint.SintaxisAbstracta.MuchasDecs;
import asint.SintaxisAbstracta.MuchasExp;
import asint.SintaxisAbstracta.MuchasInstrs;
import asint.SintaxisAbstracta.MuchosCamps;
import asint.SintaxisAbstracta.MuchosParams;
import asint.SintaxisAbstracta.Mul;
import asint.SintaxisAbstracta.Neg;
import asint.SintaxisAbstracta.NewInstr;
import asint.SintaxisAbstracta.NlInstr;
import asint.SintaxisAbstracta.NoDecs;
import asint.SintaxisAbstracta.NoExp;
import asint.SintaxisAbstracta.NoInstrs;
import asint.SintaxisAbstracta.NoParam;
import asint.SintaxisAbstracta.Nodo;
import asint.SintaxisAbstracta.Not;
import asint.SintaxisAbstracta.Null;
import asint.SintaxisAbstracta.Or;
import asint.SintaxisAbstracta.ParamFormRef;
import asint.SintaxisAbstracta.ParamFormal;
import asint.SintaxisAbstracta.ProcInstr;
import asint.SintaxisAbstracta.Prog;
import asint.SintaxisAbstracta.Punt;
import asint.SintaxisAbstracta.ReadInstr;
import asint.SintaxisAbstracta.Resta;
import asint.SintaxisAbstracta.SiDecs;
import asint.SintaxisAbstracta.SiExp;
import asint.SintaxisAbstracta.SiInstrs;
import asint.SintaxisAbstracta.SiParam;
import asint.SintaxisAbstracta.Suma;
import asint.SintaxisAbstracta.TArray;
import asint.SintaxisAbstracta.TBool;
import asint.SintaxisAbstracta.TIden;
import asint.SintaxisAbstracta.TInt;
import asint.SintaxisAbstracta.TPunt;
import asint.SintaxisAbstracta.TReal;
import asint.SintaxisAbstracta.TString;
import asint.SintaxisAbstracta.TStruct;
import asint.SintaxisAbstracta.True;
import asint.SintaxisAbstracta.UnCamp;
import asint.SintaxisAbstracta.UnParam;
import asint.SintaxisAbstracta.UnaDec;
import asint.SintaxisAbstracta.UnaExp;
import asint.SintaxisAbstracta.UnaInstr;
import asint.SintaxisAbstracta.WhileInstr;
import asint.SintaxisAbstracta.WriteInstr;

public class Tipado implements Procesamiento {
	
	public static class ErrorTipado extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
	
	public static class Ok extends Nodo {
		@Override public void imprime() {}
		@Override public void procesa(Procesamiento p) {}
	}
	
	public static class Error extends Nodo {
		@Override public void imprime() {}
		@Override public void procesa(Procesamiento p) {}
	}
	
	private static final Ok OK = new Ok();
	private static final Error ERROR = new Error();
	
	private Nodo procesaTipo(ProcesamientoAuxiliar p, Nodo a) {
		a.procesa(p);
		return p.sol();
	}
	
	private Nodo ref(Nodo t) {
		if (t.getClass() == TIden.class)
			return ref(((DecType) t.getVinculo()).tipo());
		return t;
	}
	
	private Nodo ambosOk(Nodo t1, Nodo t2) {
		if (t1 == OK && t2 == OK)
			return OK;
		return ERROR;
	}
	
	private Nodo tipadoBinArit(Nodo t0, Nodo t1) {
		Nodo tt0 = ref(t0), tt1 = ref(t1);
		if (tt0.equals(tt1) && tt0.equals(new TInt()))
			return new TInt();
		else if ((tt0.getClass() == TReal.class || tt0.getClass() == TInt.class)
			  && (tt1.getClass() == TReal.class || tt1.getClass() == TInt.class))
			return new TReal();
		return ERROR;
	}

	@Override
	public void procesa(Prog a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Bloque a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(SiDecs a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(NoDecs a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(MuchasDecs a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(UnaDec a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(DecProc a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(DecType a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(DecVar a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(SiParam a) {}

	@Override
	public void procesa(NoParam a) {}

	@Override
	public void procesa(MuchosParams a) {}

	@Override
	public void procesa(UnParam a) {}

	@Override
	public void procesa(ParamFormRef a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ParamFormal a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(TArray a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(TPunt a) {
		// TODO Auto-generated method stub
		
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
	public void procesa(TIden a) {}

	@Override
	public void procesa(TStruct a) {}

	@Override
	public void procesa(MuchosCamps a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(UnCamp a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Campo a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(SiInstrs a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(NoInstrs a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(MuchasInstrs a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(UnaInstr a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ArrobaInstr a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ProcInstr a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(NlInstr a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(NewInstr a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ReadInstr a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(WriteInstr a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(DeleteInstr a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(WhileInstr a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(IfElseInstr a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(IfInstr a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(BloqueInstr a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(SiExp a) {
		a.lexps().procesa(this);
		a.setTipo(a.lexps().getTipo());
	}

	@Override
	public void procesa(NoExp a) {
		a.setTipo(OK);
	}

	@Override
	public void procesa(MuchasExp a) {
		a.lexp().procesa(this);
		a.exp().procesa(this);
		if (a.exp().getTipo() != ERROR)
			a.setTipo(a.lexp().getTipo());
		else
			a.setTipo(ERROR);
	}

	@Override
	public void procesa(UnaExp a) {
		a.exp().procesa(this);
		if (a.exp().getTipo() != ERROR)
			a.setTipo(OK);
		else
			a.setTipo(ERROR);
	}

	@Override
	public void procesa(Asignacion a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Suma a) {
		a.setTipo(tipadoBinArit(a.opnd0(), a.opnd1()));
	}

	@Override
	public void procesa(And a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Or a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Resta a) {
		a.setTipo(tipadoBinArit(a.opnd0(), a.opnd1()));
	}

	@Override
	public void procesa(Menor a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Mayor a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(MenorIgual a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(MayorIgual a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Igual a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Desigual a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Mul a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Div a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Mod a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Neg a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Array a) {
		a.opnd().procesa(this);
		a.idx().procesa(this);
		Nodo t = ref(a.opnd().getTipo());
		if (t.getClass() == TArray.class && ref(a.idx().getTipo()).getClass() == TInt.class)
			a.setTipo(((TArray) t).tipo());
		else
			a.setTipo(ERROR);
	}

	@Override
	public void procesa(ExpCampo a) {
		a.opnd().procesa(this);
		Nodo t = ref(a.opnd().getTipo());
		if (t.getClass() == TStruct.class)
			a.setTipo(new TieneCampo(a.campo()).resuelve(((TStruct) t).lcampos()));
	}

	@Override
	public void procesa(Punt a) {
		a.opnd().procesa(this);
		Nodo t = ref(a.opnd().getTipo());
		if (t.getClass() == TPunt.class)
			a.setTipo(((TPunt) t).tipo());
		else 
			a.setTipo(ERROR);
	}

	@Override
	public void procesa(Not a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LitEnt a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LitReal a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Iden a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(True a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(False a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LitCad a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Null a) {
		// TODO Auto-generated method stub
		
	}
	
	
	private static class TieneCampo extends ProcesamientoAuxiliar {
		String id;
		Nodo sol;
		public TieneCampo(String id) {
			this.id = id;
		}
		@Override
		public void procesa(MuchosCamps a) {
			if (a.campo().iden().equals(id))
				sol = OK;
			else
				a.lcampos().procesa(this);
		}
		@Override
		public void procesa(UnCamp a) {
			if (a.campo().iden().equals(id))
				sol = OK;
			else
				sol = ERROR;
		}
		@Override
		public Nodo sol() {
			return sol;
		}
	}
}
