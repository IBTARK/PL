package procesamiento;

import java.util.HashSet;
import java.util.List;
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

public class Vinculacion implements Procesamiento {
	
	public static class TablaSimbolos {
		private List<Map<String, Nodo>> ts;
		
		public Nodo vinculoDe(String id) {
			for (int i = ts.size()-1; i >= 0; i--)
				if (ts.get(i).containsKey(id))
					return ts.get(i).get(id);
			
			//TODO errores
		}
		
		public boolean contiene(String id) {
			return ts.get(ts.size()-1).containsKey(id);
		}
		
		public void inserta(String id, Nodo vinculo) {
			ts.get(ts.size()-1).put(id, vinculo);
		}
	}
	
	private TablaSimbolos ts;
	private Set<String> conjCampos;

	@Override
	public void procesa(Prog a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Bloque a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(SiDecs a) { //He hecho este como ejemplo de dos pasadas
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
		//abreAmbito(ts);
		a.paramForms().procesa();
		a.bloq().procesa();
		//cierraAmbito(ts);
		if (ts.contiene(a.id()))
			error;
		else
			ts.inserta(a.id(), this);
	}

	@Override
	public void procesa(DecType a) {
		a.tipo().procesa();
		if (ts.contiene(a.id()))
			error;
		else
			ts.inserta(a.id(), this);
	}

	@Override
	public void procesa(DecVar a) {
		a.tipo().procesa();
		if (ts.contiene(a.id()))
			error;
		else
			ts.inserta(a.id(), this);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ParamFormal a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(TArray a) {
		if(a.tipo().getClass() != TIden.class) {
			a.tipo().procesa(this);
		}
		if(a.litEnt().charAt(0) == '-') {
			error;
		}
		else {
			error;
		}
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
	public void procesa(TIden a) {
		a.setVinculo(ts.vinculoDe(a.iden()));
		if (a.getVinculo().getClass() != DecType.class)
			error;
	}

	@Override
	public void procesa(TStruct a) {
		conjCampos = new HashSet<>();
		a.lcampos().procesa(this);
	}

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Or a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Resta a) {
		a.opnd0().procesa(this);
		a.opnd1().procesa(this);
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
		// TODO Auto-generated method stub
		
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

	
	
	public class Vinculacion2 extends ProcesamientoAuxiliar {

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
			a.paramForms().procesa(new Vinculacion2());
		}

		@Override
		public void procesa(DecType a) {
			a.tipo().procesa(new Vinculacion2());
		}

		@Override
		public void procesa(DecVar a) {
			a.tipo().procesa(new Vinculacion2());
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void procesa(ParamFormal a) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void procesa(TArray a) {
			if(a.tipo().getClass() == TIden.class) {
				a.setVinculo(ts.vinculoDe(a.litEnt()));
				if(a.getVinculo().getClass() != DecType.class) {
					error;
				}
			}
			else {
				a.tipo().procesa(this);
			}
		}

		@Override
		public void procesa(TPunt a) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void procesa(TStruct a) {
			a.lcampos().procesa(this);
		}

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
		Nodo sol() {
			return null;
		}
	}
}
