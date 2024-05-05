package procesamiento;

import java.util.ArrayList;
import java.util.List;

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
	
	private void accVal(Exp e) {
		if (esDesignador(e))
			m.emit(m.fetch());
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
			m.emit(m.alloc(1));
			m.emit(m.store());
			m.emit(m.fetch());
			exp.procesa(this);
			Class<?> t1 = ref(param.getTipo()).getClass(), t2 = exp.getTipo().getClass();
			if (t1 == TReal.class && t2 == TInt.class) {
				accVal(exp);
				m.emit(m.castReal());
				m.emit(m.store());
			}
			else if (esDesignador(exp))
				m.emit(m.copia(param.getTipo().getTam()));
			else
				m.emit(m.store());
		}
	}
	
	private class GenLiberaParam extends ProcesamientoAuxiliar<Object> {
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
			m.emit(m.dup());
			m.emit(m.apilaInt(a.getDir()));
			m.emit(m.suma());
			m.emit(m.fetch());
			m.emit(m.dealloc(1));
		}
		@Override
		public void procesa(ParamFormRef a) {
			m.emit(m.dup());
			m.emit(m.apilaInt(a.getDir()));
			m.emit(m.suma());
			m.emit(m.fetch());
			m.emit(m.dealloc(a.getTipo().getTam()));
		}
		@Override
		Object sol() {
			return null;
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
		apila(a);
	}

	@Override
	public void procesa(DecType a) {
	}

	@Override
	public void procesa(DecVar a) {
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
		a.exp().procesa(this);
		m.emit(m.desapila());
	}

	@Override
	public void procesa(ProcInstr a) {
		
	}

	@Override
	public void procesa(NlInstr a) {
	}

	@Override
	public void procesa(NewInstr a) {
		a.exp().procesa(this);
		ref(a.exp.tipo()) = TPunt.getClass();
		m.emit(m.alloc(a.exp.tipo().getTam()));
		m.emit(m.store());
	}

	@Override
	public void procesa(ReadInstr a) {
		a.exp().procesa(this);
		m.emit(m.read());
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
		m.emit(m.irF(a.sig()));
		a.bloq().procesa(this);
		m.emit(m.irA(a.prim()));
	}

	@Override
	public void procesa(IfElseInstr a) {
		a.exp().procesa(this);
		accVal(a.exp());
		m.emit(m.irF(a.bloq2().prim()));
		a.bloq1().procesa(this);
		m.emit(m.irA(a.sig()));
		a.bloq2().procesa(this);
	}

	@Override
	public void procesa(IfInstr a) {
		a.exp().procesa(this);
		accVal(a.exp());
		m.emit(m.irF(a.sig()));
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Suma a) {
		genCodBinAritm(a.opnd0(), a.opnd1(), a);
		m.emit(m.suma());
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
		genCodBinAritm(a.opnd0(), a.opnd1(), a);
		m.emit(m.resta());
	}

	@Override
	public void procesa(Menor a) {
		genCodBin(a.opnd0(), a.opnd1(), a);
		m.emit(m.menor());
	}

	@Override
	public void procesa(Mayor a) {
		genCodBin(a.opnd0(), a.opnd1(), a);
		m.emit(m.mayor());
	}

	@Override
	public void procesa(MenorIgual a) {
		genCodBin(a.opnd0(), a.opnd1(), a);
		m.emit(m.menorIgual());
	}

	@Override
	public void procesa(MayorIgual a) {
		genCodBin(a.opnd0(), a.opnd1(), a);
		m.emit(m.mayorIgual());
	}

	@Override
	public void procesa(Igual a) {
		genCodBin(a.opnd0(), a.opnd1(), a);
		m.emit(m.igual());
	}

	@Override
	public void procesa(Desigual a) {
		genCodBin(a.opnd0(), a.opnd1(), a);
		m.emit(m.desigual());
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
		accVal(a.idx());
		m.emit(m.idx(ref(a.opnd()).getTipo().getTam()));
	}

	@Override
	public void procesa(ExpCampo a) {
		a.opnd().procesa(this);
		TStruct t = (TStruct) ref(a.opnd().getTipo());
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
}
