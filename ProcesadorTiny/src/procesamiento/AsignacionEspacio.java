package procesamiento;

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

public class AsignacionEspacio implements Procesamiento {
	
	private int dir, nivel, maxDir;
	
	private void incrDir(int n) {
		dir += n;
		if (dir > maxDir)
			maxDir = dir;
	}

	@Override
	public void procesa(Prog a) {
		nivel = 0;
		a.bloq().procesa(this);
		a.bloq().procesa(new AsignacionEspacio2());
	}

	@Override
	public void procesa(Bloque a) {
		a.decs().procesa(this);
		a.instrs().procesa(this);
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
		nivel++;
		a.setNivel(nivel);
		a.params().procesa(this);
		a.bloq().procesa(this);
		nivel--;
	}

	@Override
	public void procesa(DecType a) {
		a.setTam(a.tipo().getTam());
		a.setNivel(nivel);
	}

	@Override
	public void procesa(DecVar a) {
		a.setTam(a.tipo().getTam());
		a.setNivel(nivel);
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
		a.setTam(a.tipo().getTam());
		a.setNivel(nivel);
	}

	@Override
	public void procesa(ParamFormal a) {
		a.setTam(a.tipo().getTam());
		a.setNivel(nivel);
	}

	@Override
	public void procesa(TArray a) {
		if(a.tipo().getClass() != TIden.class) {
			a.tipo().procesa(this);
			a.setTam(a.tipo().getTam()*Integer.valueOf(a.litEnt()));
		}
	}

	@Override
	public void procesa(TPunt a) {
		if(a.tipo().getClass() != TIden.class) {
			a.setTam(a.tipo().getTam());
		}
		a.setTam(1);
	}

	@Override
	public void procesa(TInt a) {
		a.setTam(1);
	}

	@Override
	public void procesa(TReal a) {
		a.setTam(1);
	}

	@Override
	public void procesa(TBool a) {
		a.setTam(1);
	}

	@Override
	public void procesa(TString a) {
		a.setTam(1);
	}

	@Override
	public void procesa(TIden a) {
		Nodo t = ((DecType) a.getVinculo()).tipo();
		t.procesa(this);
		a.setTam(t.getTam());
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
		if(a.tipo().getClass() != TIden.class) {
			a.tipo().procesa(this);
			a.setTam(a.tipo().getTam());
		}
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
	}

	@Override
	public void procesa(ProcInstr a) {
	}

	@Override
	public void procesa(NlInstr a) {
	}

	@Override
	public void procesa(NewInstr a) {
	}

	@Override
	public void procesa(ReadInstr a) {
	}

	@Override
	public void procesa(WriteInstr a) {
	}

	@Override
	public void procesa(DeleteInstr a) {
	}

	@Override
	public void procesa(WhileInstr a) {
		a.bloq().procesa(this);
	}

	@Override
	public void procesa(IfElseInstr a) {
		a.bloq1().procesa(this);
		a.bloq2().procesa(this);
	}

	@Override
	public void procesa(IfInstr a) {
		a.bloq().procesa(this);
	}

	@Override
	public void procesa(BloqueInstr a) {
		a.bloq().procesa(this);
	}

	@Override
	public void procesa(SiExp a) {}

	@Override
	public void procesa(NoExp a) {}

	@Override
	public void procesa(MuchasExp a) {}

	@Override
	public void procesa(UnaExp a) {}

	@Override
	public void procesa(Asignacion a) {}

	@Override
	public void procesa(Suma a) {}

	@Override
	public void procesa(And a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Or a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Resta a) {}

	@Override
	public void procesa(Menor a) {
	}

	@Override
	public void procesa(Mayor a) {
	}

	@Override
	public void procesa(MenorIgual a) {
	}

	@Override
	public void procesa(MayorIgual a) {
	}

	@Override
	public void procesa(Igual a) {
	}

	@Override
	public void procesa(Desigual a) {
	}

	@Override
	public void procesa(Mul a) {}

	@Override
	public void procesa(Div a) {}

	@Override
	public void procesa(Mod a) {}

	@Override
	public void procesa(Neg a) {
	}

	@Override
	public void procesa(Array a) {}

	@Override
	public void procesa(ExpCampo a) {}

	@Override
	public void procesa(Punt a) {}

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

	
	
	public class AsignacionEspacio2 extends ProcesamientoAbstracto {

		@Override
		public void procesa(Bloque a) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void procesa(SiDecs a) {
			a.ldecs().procesa(this);
		}

		@Override
		public void procesa(NoDecs a) {}

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
			int dirAnt = dir;
			dir = 0;
			a.setDir(dir);
			a.params().procesa(this);
			a.bloq().procesa(this);
			a.setTam(dir);
			dir = dirAnt;
		}

		@Override
		public void procesa(DecType a) {
			a.tipo().procesa(this);
		}

		@Override
		public void procesa(DecVar a) {
			a.setDir(dir);
			a.tipo().procesa(this);
			incrDir(a.tipo().getTam());
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
			if(a.tipo().getClass() != TIden.class) {
				a.tipo().procesa(this);
				a.setTam(a.tipo().getTam()*Integer.valueOf(a.litEnt()));
			}
		}

		@Override
		public void procesa(TPunt a) {
			if(a.tipo().getClass() == TIden.class)
				a.tipo().procesa(this);
		}

		@Override
		public void procesa(TStruct a) {
			int dirAnt = dir;
			dir = 0;
			a.lcampos().procesa(this);
			a.setTam(dir);
			dir = dirAnt;
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
			a.bloq().procesa(this);
		}

		@Override
		public void procesa(IfElseInstr a) {
			a.bloq1().procesa(this);
			a.bloq2().procesa(this);
		}

		@Override
		public void procesa(IfInstr a) {
			a.bloq().procesa(this);
		}

		@Override
		public void procesa(BloqueInstr a) {
			a.bloq().procesa(this);
		}
	}
}
