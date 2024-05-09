package procesamiento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import errors.GestionErroresTiny;

public class Vinculacion implements Procesamiento {
	
	public static class TablaSimbolos {
		private List<Map<String, Nodo>> ts = new ArrayList<>();
		
		public Nodo vinculoDe(String id) {
			for (int i = ts.size()-1; i >= 0; i--)
				if (ts.get(i).containsKey(id))
					return ts.get(i).get(id);
			
			return null;
		}
		
		public boolean contiene(String id) {
			return ts.get(ts.size()-1).containsKey(id);
		}
		
		public void inserta(String id, Nodo vinculo) {
			ts.get(ts.size()-1).put(id, vinculo);
		}
		
		public void abreAmbito() {
			ts.add(new HashMap<>());
		}
		
		public void cierraAmbito() {
			ts.remove(ts.size()-1);
		}
	}
	
	private GestionErroresTiny error;
	private TablaSimbolos ts;
	
	public Vinculacion(GestionErroresTiny error) {
		this.error = error;
	}

	@Override
	public void procesa(Prog a) {
		ts = new TablaSimbolos();
		a.bloq().procesa(this);
	}

	@Override
	public void procesa(Bloque a) {
		ts.abreAmbito();
		a.decs().procesa(this);
		a.instrs().procesa(this);
		ts.cierraAmbito();
	}

	@Override
	public void procesa(SiDecs a) {
		a.ldecs().procesa(this);
		a.ldecs().procesa(new Vinculacion2());
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
		if (ts.contiene(a.iden()))
			error.errorSemantico(a.leeFila(), a.leeCol(), "Vinculacion: decProc");
		else
			ts.inserta(a.iden(), a);
		ts.abreAmbito();
		a.params().procesa(this);
		a.params().procesa(new Vinculacion2());
		a.bloq().procesa(this);
		ts.cierraAmbito();
	}

	@Override
	public void procesa(DecType a) {
		a.tipo().procesa(this);
		if (ts.contiene(a.iden()))
			error.errorSemantico(a.leeFila(), a.leeCol(), "Vinculacion: decType");
		else
			ts.inserta(a.iden(), a);
	}

	@Override
	public void procesa(DecVar a) {
		a.tipo().procesa(this);
		if (ts.contiene(a.iden()))
			error.errorSemantico(a.leeFila(), a.leeCol(), "Vinculacion: decVar");
		else
			ts.inserta(a.iden(), a);
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
		if(ts.contiene(a.id())) {
			error.errorSemantico(a.leeFila(), a.leeCol(), "Vinculacion: paramFormRef");
		}
		else {
			ts.inserta(a.id(), a);
		}
	}

	@Override
	public void procesa(ParamFormal a) {
		a.tipo().procesa(this);
		if(ts.contiene(a.id())) {
			error.errorSemantico(a.leeFila(), a.leeCol(), "Vinculacion: paramFormal");
		}
		else {
			ts.inserta(a.id(), a);
		}
	}

	@Override
	public void procesa(TArray a) {
		if(a.tipo().getClass() != TIden.class)
			a.tipo().procesa(this);
	}

	@Override
	public void procesa(TPunt a) {
		if(a.tipo().getClass() != TIden.class)
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
		a.setVinculo(ts.vinculoDe(a.iden()));
		if (a.getVinculo() == null)
			error.errorSemantico(a.leeFila(), a.leeCol(), "Tipo usado en declaración de tipo no declarado");
	}

	@Override
	public void procesa(TStruct a) {
		a.lcampos().procesa(this);
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
	}

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
	}

	@Override
	public void procesa(ProcInstr a) {
		a.setVinculo(ts.vinculoDe(a.iden()));
		if (a.getVinculo() == null)
			error.errorSemantico(a.leeFila(), a.leeCol(), "Procedimiento no declarado");
		a.paramReales().procesa(this);
	}

	@Override
	public void procesa(NlInstr a) {}

	@Override
	public void procesa(NewInstr a) {
		a.exp().procesa(this);
	}

	@Override
	public void procesa(ReadInstr a) {
		a.exp().procesa(this);
	}

	@Override
	public void procesa(WriteInstr a) {
		a.exp().procesa(this);
	}

	@Override
	public void procesa(DeleteInstr a) {
		a.exp().procesa(this);
	}

	@Override
	public void procesa(WhileInstr a) {
		a.exp().procesa(this);
		a.bloq().procesa(this);
	}

	@Override
	public void procesa(IfElseInstr a) {
		a.exp().procesa(this);
		a.bloq1().procesa(this);
		a.bloq2().procesa(this);
	}

	@Override
	public void procesa(IfInstr a) {
		a.exp().procesa(this);
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
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(Suma a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(And a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(Or a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(Resta a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(Menor a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(Mayor a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(MenorIgual a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(MayorIgual a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(Igual a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(Desigual a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(Mul a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(Div a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
	}

	@Override
	public void procesa(Mod a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);	
	}

	@Override
	public void procesa(Neg a) {
		a.opnd().procesa(this);
	}

	@Override
	public void procesa(Array a) {
		a.opnd().procesa(this);
		a.idx().procesa(this);
	}

	@Override
	public void procesa(ExpCampo a) {
		a.opnd().procesa(this);
	}

	@Override
	public void procesa(Punt a) {
		a.opnd().procesa(this);
	}

	@Override
	public void procesa(Not a) {
		a.opnd().procesa(this);
	}

	@Override
	public void procesa(LitEnt a) {}

	@Override
	public void procesa(LitReal a) {}

	@Override
	public void procesa(Iden a) {
		a.setVinculo(ts.vinculoDe(a.id()));
		if (a.getVinculo() == null)
			error.errorSemantico(a.leeFila(), a.leeCol(), "Identificador no declarado");
	}

	@Override
	public void procesa(True a) {}

	@Override
	public void procesa(False a) {}

	@Override
	public void procesa(LitCad a) {}

	@Override
	public void procesa(Null a) {}
	
	
	public class Vinculacion2 extends ProcesamientoAbstracto {

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
			if(a.tipo().getClass() == TIden.class) {
				a.setVinculo(a.tipo().getVinculo());
				if (a.getVinculo() == null)
					error.errorSemantico(a.leeFila(), a.leeCol(), "Tipo de array no declarado");
			}
		}

		@Override
		public void procesa(TPunt a) {
			a.tipo().procesa(this);
			if(a.tipo().getClass() == TIden.class) {
				a.setVinculo(ts.vinculoDe(((TIden) a.tipo()).iden()));
				if (a.getVinculo() == null)
					error.errorSemantico(a.leeFila(), a.leeCol(), "Tipo de puntero no declarado");
			}
		}

		@Override
		public void procesa(TIden a) {
			a.setVinculo(ts.vinculoDe(a.iden()));
			if (a.getVinculo() == null)
				error.errorSemantico(a.leeFila(), a.leeCol(), "Tipo usado en declaración de tipo no declarado");
		}

		@Override
		public void procesa(TStruct a) {
			a.lcampos().procesa(this);
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
		}
	}
}
