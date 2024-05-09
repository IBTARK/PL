package procesamiento;

import java.util.ArrayList;
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
import maquinap.MaquinaP;

public class GeneracionCodigo implements Procesamiento {
	
	private List<DecProc> procPendientes = new ArrayList<>();
	private MaquinaP m;
	
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
	
	private Nodo accTipo(Nodo n) {
		if (n.getVinculo() != null && n.getVinculo().getClass() == ParamFormRef.class)
			return ref(((ParamFormRef) n.getVinculo()).tipo());
		else
			return ref(n.getTipo());
	}
	
	private void accVal(Exp e) {
		if (esDesignador(e))
			m.emit(m.fetch());
	}
	
	private void accVar(Nodo n) {
		m.emit(m.apilaDisp(n.getNivel()));
		m.emit(m.apilaInt(n.getDir()));
		m.emit(m.suma());
	}
	
	private void genCodBin(Exp e1, Exp e2) {
		e1.procesa(this);
		accVal(e1);
		e2.procesa(this);
		accVal(e2);
	}
	
	private void castAritm(Exp exp, Exp e) {
		if (ref(e.getTipo()).getClass() == TReal.class && ref(exp.getTipo()).getClass() == TInt.class)
			m.emit(m.castReal());
	}
	
	private void genCodBinAritm(Exp e1, Exp e2, Exp e) {
		e1.procesa(this);
		accVal(e1);
		castAritm(e1, e);
		e2.procesa(this);
		accVal(e2);
		castAritm(e2, e);
	}
	
	private void genCodBinRel(Exp e1, Exp e2) {
		e1.procesa(this);
		accVal(e1);
		castAritm(e1, e2);
		e2.procesa(this);
		accVal(e2);
		castAritm(e2, e1);
	}
	
	private void genPasoParams(ParamForms params, ParamReales exps) {
		if (params.getClass() == SiParam.class && exps.getClass() == SiExp.class)
			genPasoParams(((SiParam) params).lparams(), ((SiExp) exps).lexps());
	}
	
	private void genPasoParams(LParams params, LExp exps) {
		if (params.getClass() == UnParam.class && exps.getClass() == UnaExp.class)
			genPasoParam(((UnParam) params).param(), ((UnaExp) exps).exp());
		else {
			genPasoParams(((MuchosParams) params).lparam(), ((MuchasExp) exps).lexp());
			genPasoParam(((MuchosParams) params).param(), ((MuchasExp) exps).exp());
		}
	}
	
	private void genPasoParam(ParamForm param, Exp exp) {
		m.emit(m.dup());
		m.emit(m.apilaInt(param.getDir()));
		m.emit(m.suma());
		m.emit(m.dup());
		
		if (param.getClass() == ParamFormRef.class) {
			m.emit(m.alloc(1));
			m.emit(m.store());
			m.emit(m.fetch());
			exp.procesa(this);
			m.emit(m.store());
		}
		else {
			Nodo tipo1 = ((ParamFormal) param).tipo();
			m.emit(m.alloc(tipo1.getTam()));
			m.emit(m.store());
			m.emit(m.fetch());
			exp.procesa(this);
			Class<?> t1 = tipo1.getClass(), t2 = exp.getTipo().getClass();
			if (t1 == TReal.class && t2 == TInt.class) {
				accVal(exp);
				m.emit(m.castReal());
				m.emit(m.store());
			}
			else if (esDesignador(exp))
				m.emit(m.copia(tipo1.getTam()));
			else
				m.emit(m.store());
		}
	}
	
	private class GenAccId extends ProcesamientoAbstracto {
		@Override
		public void procesa(DecVar a) {
			if (a.getNivel() == 0)
				m.emit(m.apilaInt(a.getDir()));
			else
				accVar(a);
		}
		@Override
		public void procesa(ParamFormal a) {
			accVar(a);
			m.emit(m.fetch());
		}
		@Override
		public void procesa(ParamFormRef a) {
			accVar(a);
			m.emit(m.fetch());
			m.emit(m.fetch());
		}
	}
	
	private class GenLiberaParam extends ProcesamientoAbstracto {
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
			m.emit(m.apilaDisp(a.getNivel()));
			m.emit(m.apilaInt(a.getDir()));
			m.emit(m.suma());
			m.emit(m.fetch());
			m.emit(m.dealloc(1));
		}
		@Override
		public void procesa(ParamFormRef a) {
			m.emit(m.apilaDisp(a.getNivel()));
			m.emit(m.apilaInt(a.getDir()));
			m.emit(m.suma());
			m.emit(m.fetch());
			m.emit(m.dealloc(a.tipo().getTam()));
		}
	}

	private static class Desplazamiento extends ProcesamientoAuxiliar<Integer> {
		private Integer sol;
		private String id;
		public Desplazamiento(String id) {
			this.id = id;
		}
		@Override
		public void procesa(UnCamp a) {
			sol = a.campo().getDir();
		}
		@Override
		public void procesa(MuchosCamps a) {
			Campo c = (Campo) a.campo();
			if (c.iden().equals(id))
				sol = c.getDir();
			else
				a.lcampos().procesa(this);
		}
		@Override
		Integer sol() {
			return sol;
		}
	}
	
	
	
	public GeneracionCodigo(MaquinaP mp) {
		m = mp;
	}

	@Override
	public void procesa(Prog a) {
		a.bloq().procesa(this);
		m.emit(m.stop());
		while(!procPendientes.isEmpty()) {
			DecProc p = procPendientes.remove(0);
			m.emit(m.desapilaDisp(p.getNivel()));
			p.bloq().procesa(this);
			p.params().procesa(new GenLiberaParam());
			m.emit(m.desactiva(p.getNivel(), p.getTam()));
			m.emit(m.irD());
		}
	}

	@Override
	public void procesa(Bloque a) {
		a.decs().procesa(this);
		a.instrs().procesa(this);
	}

	public void procesa(SiDecs a) {
		a.ldecs().procesa(this);
	}

	@Override
	public void procesa(NoDecs a) {}

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
	public void procesa(ParamFormRef a) {}

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
		a.linstrs().procesa(this);
	}

	@Override
	public void procesa(NoInstrs a) {}

	@Override
	public void procesa(MuchasInstrs a) {
		a.linstrs().procesa(this);
		a.instr().procesa(this);
	}

	@Override
	public void procesa(UnaInstr a) {
		a.instr().procesa(this);
	}

	@Override
	public void procesa(ArrobaInstr a) {
		a.exp().procesa(this);
		m.emit(m.desapila());
	}

	@Override
	public void procesa(ProcInstr a) {
		m.emit(m.activa(a.getVinculo().getNivel(), a.getVinculo().getTam(), a.getSig()));
		genPasoParams(((DecProc) a.getVinculo()).params(), a.paramReales());
		m.emit(m.irA(((DecProc) a.getVinculo()).getPrim()));
	}

	@Override
	public void procesa(NlInstr a) {
		m.emit(m.apilaString("\n"));
		m.emit(m.write());
	}

	@Override
	public void procesa(NewInstr a) {
		a.exp().procesa(this);
		Nodo t = accTipo(a.exp());
		if (t.getClass() == TPunt.class);
			m.emit(m.alloc(((TPunt) t).getTam()));
		m.emit(m.store());
	}

	@Override
	public void procesa(ReadInstr a) {
		a.exp().procesa(this);
		m.emit(m.read(a.exp().getTipo().getClass()));
		m.emit(m.store());
	}

	@Override
	public void procesa(WriteInstr a) {
		a.exp().procesa(this);
		accVal(a.exp());
		m.emit(m.write());
	}

	@Override
	public void procesa(DeleteInstr a) {
		a.exp().procesa(this);
		m.emit(m.fetch());
		m.emit(m.dealloc(a.exp().getTam()));
	}

	@Override
	public void procesa(WhileInstr a) {
		a.exp().procesa(this);
		accVal(a.exp());
		m.emit(m.irF(a.getSig()));
		a.bloq().procesa(this);
		m.emit(m.irA(a.getPrim()));
	}

	@Override
	public void procesa(IfElseInstr a) {
		a.exp().procesa(this);
		accVal(a.exp());
		m.emit(m.irF(a.bloq2().getPrim()));
		a.bloq1().procesa(this);
		m.emit(m.irA(a.getSig()));
		a.bloq2().procesa(this);
	}

	@Override
	public void procesa(IfInstr a) {
		a.exp().procesa(this);
		accVal(a.exp());
		m.emit(m.irF(a.getSig()));
		a.bloq().procesa(this);
	}

	@Override
	public void procesa(BloqueInstr a) {
		a.bloq().procesa(this);
	}

	@Override
	public void procesa(SiExp a) {
		a.lexps().procesa(this);
	}

	@Override
	public void procesa(NoExp a) {}

	@Override
	public void procesa(MuchasExp a) {
		a.lexp().procesa(this);
		a.exp().procesa(this);
	}

	@Override
	public void procesa(UnaExp a) {
		a.exp().procesa(this);
	}

	@Override
	public void procesa(Asignacion a) {
		a.opnd0().procesa(this);
		m.emit(m.dup());
		a.opnd1().procesa(this);
		if (accTipo(a.opnd0()).getClass() == TReal.class && accTipo(a.opnd1()).getClass() == TInt.class) {
		        accVal(a.opnd1());
		        m.emit(m.castReal());
		        m.emit(m.store());
		}
	    else if (esDesignador(a.opnd1())) {
	        m.emit(m.copia(accTipo(a.opnd1()).getTam()));
	    }
	    else {
	    	m.emit(m.store());
	    }
	}

	@Override
	public void procesa(Suma a) {
		genCodBinAritm(a.opnd0(), a.opnd1(), a);
		m.emit(m.suma());
	}

	@Override
	public void procesa(And a) {
		genCodBin(a.opnd0(), a.opnd1());
		m.emit(m.and());
	}

	@Override
	public void procesa(Or a) {
		genCodBin(a.opnd0(), a.opnd1());
		m.emit(m.or());
	}

	@Override
	public void procesa(Resta a) {
		genCodBinAritm(a.opnd0(), a.opnd1(), a);
		m.emit(m.resta());
	}

	@Override
	public void procesa(Menor a) {
		genCodBinRel(a.opnd0(), a.opnd1());
		m.emit(m.menor());
	}

	@Override
	public void procesa(Mayor a) {
		genCodBinRel(a.opnd0(), a.opnd1());
		m.emit(m.mayor());
	}

	@Override
	public void procesa(MenorIgual a) {
		genCodBinRel(a.opnd0(), a.opnd1());
		m.emit(m.menorIgual());
	}

	@Override
	public void procesa(MayorIgual a) {
		genCodBinRel(a.opnd0(), a.opnd1());
		m.emit(m.mayorIgual());
	}

	@Override
	public void procesa(Igual a) {
		genCodBinRel(a.opnd0(), a.opnd1());
		m.emit(m.igual());
	}

	@Override
	public void procesa(Desigual a) {
		genCodBinRel(a.opnd0(), a.opnd1());
		m.emit(m.desigual());
	}

	@Override
	public void procesa(Mul a) {
		genCodBinAritm(a.opnd0(), a.opnd1(), a);
		m.emit(m.mul());
	}

	@Override
	public void procesa(Div a) {
		genCodBinAritm(a.opnd0(), a.opnd1(), a);
		m.emit(m.div());
	}

	@Override
	public void procesa(Mod a) {
		genCodBin(a.opnd0(), a.opnd1());
		m.emit(m.div());
	}

	@Override
	public void procesa(Neg a) {
		a.opnd().procesa(this);
		accVal(a.opnd());
		m.emit(m.menosUnario());
	}

	@Override
	public void procesa(Array a) {
		a.opnd().procesa(this);
		a.idx().procesa(this);
		accVal(a.idx());
		m.emit(m.idx(((TArray) accTipo(a.opnd())).tipo().getTam()));
	}

	@Override
	public void procesa(ExpCampo a) {
		a.opnd().procesa(this);
		TStruct t = (TStruct) accTipo(a.opnd());
		m.emit(m.apilaInt(new Desplazamiento(a.campo()).resuelve(t.lcampos())));
		m.emit(m.suma());
	}

	@Override
	public void procesa(Punt a) {
		a.opnd().procesa(this);
		m.emit(m.fetch());
	}

	@Override
	public void procesa(Not a) {
		a.opnd().procesa(this);
		accVal(a.opnd());
		m.emit(m.not());
	}

	@Override
	public void procesa(LitEnt a) {
		m.emit(m.apilaInt(Integer.valueOf(a.lit())));
	}

	@Override
	public void procesa(LitReal a) {
		m.emit(m.apilaReal(Double.valueOf(a.lit())));
	}

	@Override
	public void procesa(Iden a) {
		a.getVinculo().procesa(new GenAccId());
	}

	@Override
	public void procesa(True a) {
		m.emit(m.apilaBool(true));
	}

	@Override
	public void procesa(False a) {
		m.emit(m.apilaBool(false));
	}

	@Override
	public void procesa(LitCad a) {
		m.emit(m.apilaString(a.lit()));
	}

	@Override
	public void procesa(Null a) {
		m.emit(m.apilaInt(-1));
	}
}
