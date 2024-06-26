package procesamiento;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import asint.SintaxisAbstracta.LCampos;
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
import errors.GestionErroresTiny;

public class Tipado implements Procesamiento {
	
	public static class Ok extends Nodo {
		@Override public void imprime() {}
		@Override public void procesa(Procesamiento p) {}
	}
	
	public static class Error extends Nodo {
		@Override public void imprime() {}
		@Override public void procesa(Procesamiento p) {}
	}
	
	private static class Unificaciones {
		private Map<Nodo,Set<Nodo>> unif = new HashMap<>();
		public boolean contiene(Nodo n1, Nodo n2) {
			return unif.containsKey(n1) && unif.get(n1).contains(n2);
		}
		public void inserta(Nodo n1, Nodo n2) {
			if (!unif.containsKey(n1))
				unif.put(n1, new HashSet<>());
			if (!unif.containsKey(n2))
				unif.put(n2, new HashSet<>());
			
			unif.get(n1).add(n2);
			unif.get(n2).add(n1);
		}
	}
	
	private static final Ok OK = new Ok();
	private static final Error ERROR = new Error();
	private GestionErroresTiny error;
	private Unificaciones unif = new Unificaciones();
	
	public Tipado(GestionErroresTiny error) {
		this.error = error;
	}
	
	private Nodo avisoError(Nodo t1, Nodo t2, int fila, int col, String msg) {
		if (t1 != ERROR && t2 != ERROR)
			error.errorTipado(fila, col, msg);
		return ERROR;
	}
	
	private Nodo avisoError(Nodo t, int fila, int col, String msg) {
		if (t != ERROR)
			error.errorTipado(fila, col, msg);
		return ERROR;
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
	
	private boolean esDesignador(Exp e) {
		Class<?> c = e.getClass();
		return c == Iden.class
			|| c == Array.class
			|| c == ExpCampo.class
			|| c == Punt.class;
	}
	
	private Nodo ambosOk(Nodo t1, Nodo t2) {
		if (t1 == OK && t2 == OK)
			return OK;
		return ERROR;
	}
	
	private Nodo tipadoBinArit(Exp e0, Exp e1, Exp e) {
		e0.procesa(this);
		e1.procesa(this);
		Class<?> tt0 = accTipo(e0).getClass(), tt1 = accTipo(e1).getClass();
		if (tt0 == tt1 && tt0 == TInt.class)
			return new TInt();
		else if ((tt0 == TReal.class || tt0 == TInt.class)
			  && (tt1 == TReal.class || tt1 == TInt.class))
			return new TReal();
		return avisoError(e0.getTipo(), e1.getTipo(), e.leeFila(), e.leeCol(), "Tipos incompatibles en operacion aritmética");
	}
	
	private Nodo tipadoBinLog(Exp e0, Exp e1, Exp e) {
		e0.procesa(this);
		e1.procesa(this);
		Class<?> tt0 = accTipo(e0).getClass(), tt1 = accTipo(e1).getClass();
		if (tt0 == tt1 && tt0 == TBool.class)
			return new TBool();
		else
			return avisoError(e0.getTipo(), e1.getTipo(), e.leeFila(), e.leeCol(), "Tipos incompatibles en operacion lógica");
	}
	
	private Nodo tipadoBinRel(Exp e0, Exp e1, Exp e) {
		e0.procesa(this);
		e1.procesa(this);
		Class<?> tt0 = accTipo(e0).getClass(), tt1 = accTipo(e1).getClass();
		if ((tt0 == TInt.class || tt0 == TReal.class) && (tt1 == TInt.class || tt1 == TReal.class))
			return new TBool();
		else if (tt0 == tt1 && tt0 == TBool.class)
			return new TBool();
		else if (tt0 == tt1 && tt0 == TString.class)
			return new TBool();
		else if ((tt0 == TPunt.class || tt0 == Null.class) && (tt1 == TPunt.class || tt1 == Null.class))
			return new TBool();
		else
			return avisoError(e0.getTipo(), e1.getTipo(), e.leeFila(), e.leeCol(), "Tipos incompatibles en operacion relacional");
	}
	
	private Nodo tipadoBinComp(Exp e0, Exp e1, Exp e) {
		if (tipadoBinRel(e0, e1, e) != ERROR)
			return new TBool();
		error.cancelarUltimo();
		
		Class<?> tt0 = accTipo(e0).getClass(), tt1 = accTipo(e1).getClass();
		if ((tt0 == TPunt.class || tt0 == Null.class) && (tt1 == TPunt.class || tt1 == Null.class))
			return new TBool();
		else
			return avisoError(e0.getTipo(), e1.getTipo(), e.leeFila(), e.leeCol(), "Tipos incompatibles en operacion de comparación");
	}
	
	private boolean compatibles(Nodo t0, Nodo t1) {
		unif = new Unificaciones();
		unif.inserta(t0, t1);
		return unificables(t0, t1);
	}
	
	private boolean sonUnificables(Nodo t0, Nodo t1){
    	if (!unif.contiene(t0, t1)) {
    		unif.inserta(t0, t1);
        	return unificables(t0, t1);
     	}
    	else
        	return true;
    }
    
    private boolean unificables(Nodo t0, Nodo t1){
    	Nodo tipo0 = ref(t0), tipo1 = ref(t1);
		Class<?> tt0 = tipo0.getClass(), tt1 = tipo1.getClass();
        if ((tt0 == tt1) && (tt0 == TBool.class || tt0 == TInt.class || tt0 == TString.class))
            return true;
        else if (tt0 == TReal.class && (tt1 == TInt.class || tt1 == TReal.class))
            return true;
        else if (tt0 == TArray.class && tt1 == TArray.class) {
        	TArray a0 = (TArray) tipo0, a1 = (TArray) tipo1;
            if (a0.litEnt().equals(a1.litEnt()))
                return sonUnificables(a0.tipo(), a1.tipo());
            else
                return false;
        }
        else if (tt0 == TStruct.class && tt1 == TStruct.class)
            return compatibles(((TStruct) tipo0).lcampos(), ((TStruct) tipo1).lcampos());
        else if (tt0 == TPunt.class && tt1 == Null.class)
            return true;
        else if (tt0 == TPunt.class && tt1 == TPunt.class)
            return sonUnificables(((TPunt) tipo0).tipo(), ((TPunt) tipo1).tipo());
        else
            return false;
	}
    
    private boolean compatibles(LCampos lc1, LCampos lc2) {
    	if (lc1.getClass() == lc2.getClass() && lc1.getClass() == UnCamp.class)
    		return unificables(((UnCamp) lc1).campo().tipo(), ((UnCamp) lc2).campo().tipo());
    	else if (lc1.getClass() == lc2.getClass())
    		return unificables(((MuchosCamps) lc1).campo().tipo(), ((MuchosCamps) lc2).campo().tipo())
    			&& compatibles(((MuchosCamps) lc1).lcampos(), ((MuchosCamps) lc2).lcampos());
    	else
    		return false;
    }
    
    private Nodo tipoParams(ParamForms pform, ParamReales preales, Nodo a) {
    	if (pform.getClass() == SiParam.class && preales.getClass() == SiExp.class)
    		return tipoSiParams(((SiParam) pform).lparams(), ((SiExp) preales).lexps(), a);
    	else if (pform.getClass() == NoParam.class && preales.getClass() == NoExp.class)
    		return OK;
    	else
    		return avisoError(pform.getTipo(), preales.getTipo(), a.leeFila(), a.leeCol(), "Distinto número de parámetros");
    }
    
    private Nodo tipoSiParams(LParams params, LExp exps, Nodo a) {
    	if (params.getClass() == UnParam.class && exps.getClass() == UnaExp.class)
    		return tipoParam(((UnParam) params).param(), ((UnaExp) exps).exp(), a);
    	
    	else if (params.getClass() == MuchosParams.class && exps.getClass() == MuchasExp.class) {
    		Nodo t1 = tipoSiParams(((MuchosParams) params).lparam(), ((MuchasExp) exps).lexp(), a);
    		Nodo t2 = tipoParam(((MuchosParams) params).param(), ((MuchasExp) exps).exp(), a);
    		return ambosOk(t1, t2);
    	}
    	else
    		return avisoError(params.getTipo(), exps.getTipo(), a.leeFila(), a.leeCol(), "Distinto número de parámetros");
    }
    
    private Nodo tipoParam(ParamForm param, Exp exp, Nodo a) {
    	if (param.getClass() == ParamFormal.class && compatibles(((ParamFormal) param).tipo(), exp.getTipo()))
    		return OK;
    	else if (param.getClass() == ParamFormRef.class && esDesignador(exp) && compatibles(((ParamFormRef) param).tipo(), exp.getTipo())) {
    		Class<?> t1 = ref(((ParamFormRef) param).tipo()).getClass(), t2 = ref(exp.getTipo()).getClass();
    		if (!(t1 == TReal.class && t2 == TReal.class) && (t1 == TReal.class || t2 == TReal.class))
        		return avisoError(param.getTipo(), exp.getTipo(), exp.leeFila(), exp.leeCol(), "Tipos incompatibles en parámetro");
    		else
    			return OK;
    	}
    	else
    		return avisoError(param.getTipo(), exp.getTipo(), exp.leeFila(), exp.leeCol(), "Tipos incompatibles en parámetro");
    }

	private static class TieneCampo extends ProcesamientoAuxiliar<Nodo> {
		String id;
		Nodo sol;
		public TieneCampo(String id) {
			this.id = id;
		}
		@Override
		public void procesa(MuchosCamps a) {
			if (a.campo().iden().equals(id))
				sol = a.campo().tipo();
			else
				a.lcampos().procesa(this);
		}
		@Override
		public void procesa(UnCamp a) {
			if (a.campo().iden().equals(id))
				sol = a.campo().tipo();
			else
				sol = ERROR;
		}
		@Override
		public Nodo sol() {
			return sol;
		}
	}
    

	@Override
	public void procesa(Prog a) {
		a.bloq().procesa(this);
		a.setTipo(a.bloq().getTipo());
	}

	@Override
	public void procesa(Bloque a) {
		a.decs().procesa(this);
		a.instrs().procesa(this);
		a.setTipo(ambosOk(a.decs().getTipo(), a.instrs().getTipo()));
	}

	@Override
	public void procesa(SiDecs a) {
		a.ldecs().procesa(this);
		a.setTipo(a.ldecs().getTipo());
	}

	@Override
	public void procesa(NoDecs a) {
		a.setTipo(OK);
	}

	@Override
	public void procesa(MuchasDecs a) {
		a.ldecs().procesa(this);
		a.dec().procesa(this);
		a.setTipo(ambosOk(a.ldecs().getTipo(), a.dec().getTipo()));
	}

	@Override
	public void procesa(UnaDec a) {
		a.dec().procesa(this);
		a.setTipo(a.dec().getTipo());	
	}

	@Override
	public void procesa(DecProc a) {
		a.bloq().procesa(this);
		a.setTipo(a.bloq().getTipo());
	}

	@Override
	public void procesa(DecType a) {
		a.setTipo(OK);
	}

	@Override
	public void procesa(DecVar a) {
		a.setTipo(OK);
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
		a.setTipo(a.linstrs().getTipo());
	}

	@Override
	public void procesa(NoInstrs a) {
		a.setTipo(OK);
	}

	@Override
	public void procesa(MuchasInstrs a) {
		a.linstrs().procesa(this);
		a.instr().procesa(this);
		a.setTipo(ambosOk(a.linstrs().getTipo(), a.instr().getTipo()));
	}

	@Override
	public void procesa(UnaInstr a) {
		a.instr().procesa(this);
		a.setTipo(a.instr().getTipo());
	}

	@Override
	public void procesa(ArrobaInstr a) {
		a.exp().procesa(this);
		if (a.exp().getTipo() == ERROR)
			a.setTipo(ERROR);
		else
			a.setTipo(OK);
	}

	@Override
	public void procesa(ProcInstr a) {
		a.paramReales().procesa(this);
		if (a.getVinculo().getClass() != DecProc.class)
			avisoError(OK, a.leeFila(), a.leeCol(), "Tipo incompatible en new");
		else if (a.paramReales().getTipo() != OK)
			a.setTipo(ERROR);
		else
			a.setTipo(tipoParams(((DecProc) a.getVinculo()).params(), a.paramReales(), a));
	}

	@Override
	public void procesa(NlInstr a) {
		a.setTipo(OK);
	}

	@Override
	public void procesa(NewInstr a) {
		a.exp().procesa(this);
		if(accTipo(a.exp()).getClass() == TPunt.class)
			a.setTipo(OK);
		else
			a.setTipo(avisoError(a.exp().getTipo(), a.exp().leeFila(), a.exp().leeCol(), "Tipo incompatible en new"));
	}

	@Override
	public void procesa(ReadInstr a) {
		a.exp().procesa(this);
		Nodo t = accTipo(a.exp());
		if(esDesignador(a.exp()) 
				&& (t.getClass() == TInt.class 
				|| t.getClass() == TReal.class 
				|| t.getClass() == TString.class))
			a.setTipo(OK);
		else
			a.setTipo(avisoError(a.exp().getTipo(), a.exp().leeFila(), a.exp().leeCol(), "Tipo incompatible en read"));
	}

	@Override
	public void procesa(WriteInstr a) {
		a.exp().procesa(this);
		Nodo t = accTipo(a.exp());
		if(t.getClass() == TInt.class 
				|| t.getClass() == TReal.class 
				|| t.getClass() == TBool.class
				|| t.getClass() == TString.class)
			a.setTipo(OK);
		else
			a.setTipo(avisoError(a.exp().getTipo(), a.exp().leeFila(), a.exp().leeCol(), "Tipo incompatible en write"));
	}

	@Override
	public void procesa(DeleteInstr a) {
		a.exp().procesa(this);
		if(accTipo(a.exp()).getClass() == TPunt.class)
			a.setTipo(OK);
		else
			a.setTipo(avisoError(a.exp().getTipo(), a.exp().leeFila(), a.exp().leeCol(), "Tipo incompatible en delete"));
	}

	@Override
	public void procesa(WhileInstr a) {
		a.exp().procesa(this);
		a.bloq().procesa(this);
		if(a.bloq().getTipo() == OK && accTipo(a.exp()).getClass() == TBool.class)
			a.setTipo(OK);
		else
			a.setTipo(avisoError(a.exp().getTipo(), a.bloq().getTipo(), a.exp().leeFila(), a.exp().leeCol(), "Tipo incompatible en while"));
	}

	@Override
	public void procesa(IfElseInstr a) {
		a.exp().procesa(this);
		a.bloq1().procesa(this);
		a.bloq2().procesa(this);
		if(accTipo(a.exp()).getClass() == TBool.class && a.bloq2().getTipo() == OK)
			a.setTipo(a.bloq1().getTipo() == a.bloq2().getTipo() ? a.bloq1().getTipo() : ERROR);
		else
			a.setTipo(avisoError(a.exp().getTipo(), a.exp().leeFila(), a.exp().leeCol(), "Tipo incompatible en if"));
	}

	@Override
	public void procesa(IfInstr a) {
		a.exp().procesa(this);
		a.bloq().procesa(this);
		if(accTipo(a.exp()).getClass() == TBool.class)
			a.setTipo(a.bloq().getTipo());
		else
			a.setTipo(avisoError(a.exp().getTipo(), a.exp().leeFila(), a.exp().leeCol(), "Tipo incompatible en if"));
	}

	@Override
	public void procesa(BloqueInstr a) {
		a.bloq().procesa(this);
		a.setTipo(a.bloq().getTipo());
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
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	    if (esDesignador(a.opnd0()) && compatibles(accTipo(a.opnd0()), accTipo(a.opnd1()))) {
			a.setTipo(a.opnd0().getTipo());
		}
	    else
	    	a.setTipo(avisoError(a.opnd0().getTipo(), a.opnd1().getTipo(), a.leeFila(), a.leeCol(), "Tipos incompatibles en asignacion"));
	}

	@Override
	public void procesa(Suma a) {
		a.setTipo(tipadoBinArit(a.opnd0(), a.opnd1(), a));
	}

	@Override
	public void procesa(And a) {
		a.setTipo(tipadoBinLog(a.opnd0(), a.opnd1(), a));
	}

	@Override
	public void procesa(Or a) {
		a.setTipo(tipadoBinLog(a.opnd0(), a.opnd1(), a));
	}

	@Override
	public void procesa(Resta a) {
		a.setTipo(tipadoBinArit(a.opnd0(), a.opnd1(), a));
	}

	@Override
	public void procesa(Menor a) {
		a.setTipo(tipadoBinRel(a.opnd0(), a.opnd1(), a));
	}

	@Override
	public void procesa(Mayor a) {
		a.setTipo(tipadoBinRel(a.opnd0(), a.opnd1(), a));
	}

	@Override
	public void procesa(MenorIgual a) {
		a.setTipo(tipadoBinRel(a.opnd0(), a.opnd1(), a));
	}

	@Override
	public void procesa(MayorIgual a) {
		a.setTipo(tipadoBinRel(a.opnd0(), a.opnd1(), a));
	}

	@Override
	public void procesa(Igual a) {
		a.setTipo(tipadoBinComp(a.opnd0(), a.opnd1(), a));
	}

	@Override
	public void procesa(Desigual a) {
		a.setTipo(tipadoBinComp(a.opnd0(), a.opnd1(), a));
	}

	@Override
	public void procesa(Mul a) {
		a.setTipo(tipadoBinArit(a.opnd0(), a.opnd1(), a));
	}

	@Override
	public void procesa(Div a) {
		a.setTipo(tipadoBinArit(a.opnd0(), a.opnd1(), a));
	}

	@Override
	public void procesa(Mod a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
		Nodo t0 = accTipo(a.opnd0());
		Nodo t1 = accTipo(a.opnd1());
        if (t0.getClass() == TInt.class && t1.getClass() == TInt.class)
             a.setTipo(t0);
        else
        	a.setTipo(avisoError(a.opnd0().getTipo(), a.opnd1().getTipo(), a.leeFila(), a.leeCol(), "Tipos incompatibles en mod"));
	}

	@Override
	public void procesa(Neg a) {
		a.opnd().procesa(this);
		Nodo t = accTipo(a.opnd());
		if(t.getClass() == TInt.class) {
			a.setTipo(t);
		}
		else if(t.getClass() == TReal.class) {
			a.setTipo(t);
		}
		else
			a.setTipo(avisoError(a.opnd().getTipo(), a.leeFila(), a.leeCol(), "Tipo incompatible en neg"));
	}

	@Override
	public void procesa(Array a) {
		a.opnd().procesa(this);
		a.idx().procesa(this);
		Nodo t = accTipo(a.opnd());
		if (t.getClass() == TArray.class && accTipo(a.idx()).getClass() == TInt.class)
			a.setTipo(((TArray) t).tipo());
		else
			a.setTipo(avisoError(a.opnd().getTipo(), a.leeFila(), a.leeCol(), "Tipo incompatible en idx"));
	}

	@Override
	public void procesa(ExpCampo a) {
		a.opnd().procesa(this);
		Nodo t = accTipo(a.opnd());
		if (t.getClass() == TStruct.class) {
			a.setTipo(new TieneCampo(a.campo()).resuelve(((TStruct) t).lcampos()));
			if (a.getTipo() == ERROR)
				avisoError(a.opnd().getTipo(), a.leeFila(), a.leeCol(), "Tipo incompatible en acceso a campo");
		}
		else
			a.setTipo(avisoError(a.opnd().getTipo(), a.leeFila(), a.leeCol(), "Tipo incompatible en acceso a campo"));
	}

	@Override
	public void procesa(Punt a) {
		a.opnd().procesa(this);
		Nodo t = accTipo(a.opnd());
		if (t.getClass() == TPunt.class)
			a.setTipo(((TPunt) t).tipo());
		else 
			a.setTipo(avisoError(a.opnd().getTipo(), a.leeFila(), a.leeCol(), "Tipo incompatible en acceso a puntero"));
	}

	@Override
	public void procesa(Not a) {
		a.opnd().procesa(this);
		Nodo t = accTipo(a.opnd());
		if(t.getClass() == TBool.class) {
			a.setTipo(t);
		}
		else
			a.setTipo(avisoError(a.opnd().getTipo(), a.leeFila(), a.leeCol(), "Tipo incompatible en not"));
	}

	@Override
	public void procesa(LitEnt a) {
		a.setTipo(new TInt());
	}

	@Override
	public void procesa(LitReal a) {
		a.setTipo(new TReal());
	}

	@Override
	public void procesa(Iden a) {
		Class<?> t = a.getVinculo().getClass();
		if (t == DecVar.class)
			a.setTipo(((DecVar) a.getVinculo()).tipo());
		else if (t == ParamFormal.class)
			a.setTipo(((ParamFormal) a.getVinculo()).tipo());
		else if (t == ParamFormRef.class)
			a.setTipo(new TInt());
		else
			a.setTipo(avisoError(OK, a.leeFila(), a.leeCol(), "Tipo incompatible en tIden"));
	}

	@Override
	public void procesa(True a) {
		a.setTipo(new TBool());
	}

	@Override
	public void procesa(False a) {
		a.setTipo(new TBool());
	}

	@Override
	public void procesa(LitCad a) {
		a.setTipo(new TString());
	}

	@Override
	public void procesa(Null a) {
		a.setTipo(new Null());
	}
}
