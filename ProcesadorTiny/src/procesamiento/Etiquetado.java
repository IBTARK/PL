package procesamiento;

import java.util.LinkedList;
import java.util.List;

import asint.Procesamiento;
import asint.ProcesamientoAbstracto;
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
import asint.SintaxisAbstracta.Exp;
import asint.SintaxisAbstracta.ExpCampo;
import asint.SintaxisAbstracta.False;
import asint.SintaxisAbstracta.Iden;
import asint.SintaxisAbstracta.IfElseInstr;
import asint.SintaxisAbstracta.IfInstr;
import asint.SintaxisAbstracta.Igual;
import asint.SintaxisAbstracta.LExp;
import asint.SintaxisAbstracta.LParams;
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
import asint.SintaxisAbstracta.ParamForm;
import asint.SintaxisAbstracta.ParamFormRef;
import asint.SintaxisAbstracta.ParamFormal;
import asint.SintaxisAbstracta.ParamForms;
import asint.SintaxisAbstracta.ParamReales;
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

public class Etiquetado implements Procesamiento {
	
	private int etq = 0;
	private List<DecProc> procPendientes = new LinkedList<>();
	
	private void apila(DecProc dp) {
		procPendientes.add(dp);
	}
	
	private boolean esDesignador(Exp e) {
		Class<?> c = e.getClass();
		return c == Iden.class
			|| c == Array.class
			|| c == ExpCampo.class
			|| c == Punt.class;
	}
	
	private Nodo ref(Nodo t) {
		if (t.getClass() == TIden.class)
			return ref(((DecType) t.getVinculo()).tipo());
		return t;
	}
	
	private void accVal(Exp e) {
		if (esDesignador(e))
			etq++;
	}
	
	private class EtiquetadoAccId extends ProcesamientoAbstracto {
		@Override
		public void procesa(DecVar a) {
			if (a.getNivel() == 0)
				etq++;
			else
				etq += 3;
		}
		@Override
		public void procesa(ParamFormal a) {
			etq += 3;
		}
		@Override
		public void procesa(ParamFormRef a) {
			etq += 4;
		}
	}
	
	private void etiquetadoBin(Exp e1, Exp e2) {
		e1.procesa(this);
		accVal(e1);
		e2.procesa(this);
		accVal(e2);
	}
	
	private void castAritm(Exp exp, Exp e) {
		if (ref(e.getTipo()).getClass() == TReal.class && ref(exp.getTipo()).getClass() == TInt.class)
			etq++;
	}
	
	private void etiquetadoBinAritm(Exp e1, Exp e2, Exp e) {
		e1.procesa(this);
		accVal(e1);
		castAritm(e1, e);
		e2.procesa(this);
		accVal(e2);
		castAritm(e2, e);
	}
	
	private void etiquetadoPasoParams(ParamForms params, ParamReales exps) {
		if (params.getClass() == SiParam.class && exps.getClass() == SiExp.class)
			etiquetadoPasoParams(((SiParam) params).lparams(), ((SiExp) exps).lexps());
	}
	
	private void etiquetadoPasoParams(LParams params, LExp exps) {
		if (params.getClass() == UnParam.class && exps.getClass() == UnaExp.class)
			etiquetadoPasoParam(((UnParam) params).param(), ((UnaExp) exps).exp());
		else if (params.getClass() == MuchosParams.class && exps.getClass() == MuchasExp.class) {
			etiquetadoPasoParams(((MuchosParams) params).lparam(), ((MuchasExp) exps).lexp());
			etiquetadoPasoParam(((MuchosParams) params).param(), ((MuchasExp) exps).exp());
		}
	}
	
	private void etiquetadoPasoParam(ParamForm param, Exp exp) {
		etq += 7;
		exp.procesa(this);
		
		Class<?> t1, t2 = exp.getTipo().getClass();
		if (param.getClass() == ParamFormal.class)
			t1 = ((ParamFormal) param).tipo().getClass();
		else t1 = ((ParamFormRef) param).tipo().getClass();
		if (param.getClass() == ParamFormal.class && t1 == TReal.class && t2 == TInt.class) {
			accVal(exp);
			etq++;
		}
		etq++;
	}
	
	private class EtiquetadoLiberaParam extends ProcesamientoAbstracto {
		@Override
		public void procesa(SiParam a) {
			a.lparams().procesa(this);
		}
		@Override
		public void procesa(UnParam a) {
			a.param().procesa(this);
		}
		@Override
		public void procesa(MuchosParams a) {
			a.lparam().procesa(this);
			a.param().procesa(this);
		}
		@Override
		public void procesa(ParamFormal a) {
			etq += 5;
		}
		@Override
		public void procesa(ParamFormRef a) {
			etq += 5;
		}
	}
	
	

	@Override
	public void procesa(Prog a) {
		a.setPrim(etq);
		a.bloq().procesa(this);
		etq++;
		while(!procPendientes.isEmpty()) {
			DecProc p = procPendientes.remove(0);
			p.setPrim(etq);
			etq++;
			p.bloq().procesa(this);
			p.params().procesa(new EtiquetadoLiberaParam());
			etq += 2;
			p.setSig(etq);
		}
		a.setSig(etq);
	}

	@Override
	public void procesa(Bloque a) {
		a.setPrim(etq);
		a.decs().procesa(this);
		a.instrs().procesa(this);
		a.setSig(etq);
	}

	@Override
	public void procesa(SiDecs a) {
		a.ldecs().procesa(this);
	}

	@Override
	public void procesa(NoDecs a) {}

	@Override
	public void procesa(UnaDec a) {
		a.dec().procesa(this);
	}
	
	@Override
	public void procesa(MuchasDecs a) {
		a.ldecs().procesa(this);
		a.dec().procesa(this);
	}

	@Override
	public void procesa(DecProc a) {
		apila(a);
	}

	@Override
	public void procesa(DecType a) {}

	@Override
	public void procesa(DecVar a) {}

	@Override
	public void procesa(SiParam a) {}

	@Override
	public void procesa(NoParam a) {}

	@Override
	public void procesa(MuchosParams a) {}

	@Override
	public void procesa(UnParam a) {}

	@Override
	public void procesa(ParamFormRef a) {	}

	@Override
	public void procesa(ParamFormal a) {}

	@Override
	public void procesa(TArray a) {}

	@Override
	public void procesa(TPunt a) {}

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
	public void procesa(MuchosCamps a) {}

	@Override
	public void procesa(UnCamp a) {}

	@Override
	public void procesa(Campo a) {}

	@Override
	public void procesa(SiInstrs a) {
		a.setPrim(etq);
		a.linstrs().procesa(this);
		a.setSig(etq);
	}

	@Override
	public void procesa(NoInstrs a) {
		a.setPrim(etq);
		a.setSig(etq);
	}

	@Override
	public void procesa(MuchasInstrs a) {
		a.setPrim(etq);
		a.linstrs().procesa(this);
		a.instr().procesa(this);
		a.setSig(etq);
	}

	@Override
	public void procesa(UnaInstr a) {
		a.setPrim(etq);
		a.instr().procesa(this);
		a.setSig(etq);
	}

	@Override
	public void procesa(ArrobaInstr a) {
		a.setPrim(etq);
		a.exp().procesa(this);
		etq++;
		a.setSig(etq);
		
	}

	@Override
	public void procesa(ProcInstr a) {
		a.setPrim(etq);
		etq++;
		etiquetadoPasoParams(((DecProc) a.getVinculo()).params(), a.paramReales());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(NlInstr a) {
		a.setPrim(etq);
		etq += 2;
		a.setSig(etq);
	}

	@Override
	public void procesa(NewInstr a) {
		a.setPrim(etq);
		a.exp().procesa(this);
		etq += 2;
		a.setSig(etq);
	}

	@Override
	public void procesa(ReadInstr a) {
		a.setPrim(etq);
		a.exp().procesa(this);
		etq += 2;
		a.setSig(etq);
	}

	@Override
	public void procesa(WriteInstr a) {
		a.setPrim(etq);
		a.exp().procesa(this);
		accVal(a.exp());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(DeleteInstr a) {
		a.setPrim(etq);
		a.exp().procesa(this);
		etq += 2;
		a.setSig(etq);
	}

	@Override
	public void procesa(WhileInstr a) {
		a.setPrim(etq);
		a.exp().procesa(this);
		accVal(a.exp());
		etq++;
		a.bloq().procesa(this);
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(IfElseInstr a) {
		a.setPrim(etq);
		a.exp().procesa(this);
		accVal(a.exp());
		etq++;
		a.bloq1().procesa(this);
		etq++;
		a.bloq2().procesa(this);
		a.setSig(etq);
	}

	@Override
	public void procesa(IfInstr a) {
		a.setPrim(etq);
		a.exp().procesa(this);
		accVal(a.exp());
		etq++;
		a.bloq().procesa(this);
		a.setSig(etq);
	}

	@Override
	public void procesa(BloqueInstr a) {
		a.setPrim(etq);
		a.bloq().procesa(this);
		a.setSig(etq);
	}

	@Override
	public void procesa(SiExp a) {
		a.setPrim(etq);
		a.lexps().procesa(this);
		a.setSig(etq);
	}

	@Override
	public void procesa(NoExp a) {
		a.setPrim(etq);
		a.setSig(etq);
	}

	@Override
	public void procesa(MuchasExp a) {
		a.setPrim(etq);
		a.lexp().procesa(this);
		a.exp().procesa(this);
		a.setSig(etq);
	}

	@Override
	public void procesa(UnaExp a) {
		a.setPrim(etq);
		a.exp().procesa(this);
		a.setSig(etq);
	}

	@Override
	public void procesa(Asignacion a) {
		a.setPrim(etq);
		a.opnd0().procesa(this);
		etq++;
		a.opnd1().procesa(this);
		
		if(ref(a.opnd0().getTipo()).getClass() == TReal.class && ref(a.opnd1().getTipo()).getClass() == TInt.class) {
			accVal(a.opnd1());
			etq++;
		}
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Suma a) {
		a.setPrim(etq);
		etiquetadoBinAritm(a.opnd0(), a.opnd1(), a);
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(And a) {
		a.setPrim(etq);
		etiquetadoBin(a.opnd0(), a.opnd1());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Or a) {
		a.setPrim(etq);
		etiquetadoBin(a.opnd0(), a.opnd1());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Resta a) {
		a.setPrim(etq);
		etiquetadoBinAritm(a.opnd0(), a.opnd1(), a);
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Menor a) {
		a.setPrim(etq);
		etiquetadoBin(a.opnd0(), a.opnd1());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Mayor a) {
		a.setPrim(etq);
		etiquetadoBin(a.opnd0(), a.opnd1());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(MenorIgual a) {
		a.setPrim(etq);
		etiquetadoBin(a.opnd0(), a.opnd1());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(MayorIgual a) {
		a.setPrim(etq);
		etiquetadoBin(a.opnd0(), a.opnd1());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Igual a) {
		a.setPrim(etq);
		etiquetadoBin(a.opnd0(), a.opnd1());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Desigual a) {
		a.setPrim(etq);
		etiquetadoBin(a.opnd0(), a.opnd1());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Mul a) {
		a.setPrim(etq);
		etiquetadoBinAritm(a.opnd0(), a.opnd1(), a);
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Div a) {
		a.setPrim(etq);
		etiquetadoBinAritm(a.opnd0(), a.opnd1(), a);
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Mod a) {
		a.setPrim(etq);
		etiquetadoBin(a.opnd0(), a.opnd1());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Neg a) {
		a.setPrim(etq);
		a.opnd().procesa(this);
		accVal(a.opnd());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Array a) {
		a.setPrim(etq);
		a.opnd().procesa(this);
		a.idx().procesa(this);
		accVal(a.idx());
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(ExpCampo a) {
		a.setPrim(etq);
		a.opnd().procesa(this);
		etq += 2;
		a.setSig(etq);
	}

	@Override
	public void procesa(Punt a) {
		a.setPrim(etq);
		a.opnd().procesa(this);
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Not a) {
		a.setPrim(etq);
		a.opnd().procesa(this);
		accVal(a.opnd());
		etq++;
		a.setSig(etq);	
	}

	@Override
	public void procesa(LitEnt a) {
		a.setPrim(etq);
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(LitReal a) {
		a.setPrim(etq);
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Iden a) {
		a.setPrim(etq);
		a.getVinculo().procesa(new EtiquetadoAccId());
		a.setSig(etq);
	}

	@Override
	public void procesa(True a) {
		a.setPrim(etq);
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(False a) {
		a.setPrim(etq);
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(LitCad a) {
		a.setPrim(etq);
		etq++;
		a.setSig(etq);
	}

	@Override
	public void procesa(Null a) {
		a.setPrim(etq);
		etq++;
		a.setSig(etq);
	}

}
