package procesamiento;

import asint.Procesamiento;
import asint.SintaxisAbstracta.*;

public class Impresion implements Procesamiento {

	private static final String FORMAT = "%s$f:%d,c:%d$\n";
    
    private void imprimeOpnd(Exp opnd, int minPrior) {
       	if(opnd.prioridad() < minPrior){
       		System.out.println("(");
       	}
       	opnd.procesa(this);
       	if(opnd.prioridad() < minPrior) {
       		System.out.println(")");
       	}
    }

    private void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1, Exp exp) {
    	imprimeOpnd(opnd0,np0);
		System.out.printf(FORMAT, op, exp.leeFila(), exp.leeCol());
    	imprimeOpnd(opnd1,np1);
    }
    
    private void imprimeExpUn(String op, Exp opnd, int np, Exp a) {
		System.out.printf(FORMAT, op, a.leeFila(), a.leeCol());
    	imprimeOpnd(opnd,np);
    }
    
    @Override
	public void procesa(Prog a) {
		a.bloq().procesa(this);
	}
    
    @Override
	public void procesa(Bloque a) {
    	System.out.println("{");
		a.decs().procesa(this);
		a.instrs().procesa(this);
		System.out.println("}");
	}

	@Override
	public void procesa(SiDecs a) {
		a.ldecs().procesa(this);
		System.out.println("&&");
	}

	@Override
	public void procesa(NoDecs a) {
	}

	@Override
	public void procesa(MuchasDecs a) {
		a.ldecs().procesa(this);
		System.out.println(";");
		a.dec().procesa(this);
	}

	@Override
	public void procesa(UnaDec a) {
		a.dec().procesa(this);
	}
	
	@Override
	public void procesa(DecProc a) {
		System.out.println("<proc>");
		System.out.printf(FORMAT, a.iden(), a.leeFila(), a.leeCol());
		a.params().procesa(this); 
		a.bloq().procesa(this);
	}
	
	
	@Override
	public void procesa(DecType a) {
		System.out.println("<type>");
		a.tipo().procesa(this); 
		System.out.printf(FORMAT, a.iden(), a.leeFila(), a.leeCol());
	}
	
	@Override
	public void procesa(DecVar a) {
		a.tipo().procesa(this); 
		System.out.printf(FORMAT, a.iden(), a.leeFila(), a.leeCol());
	}

	@Override
	public void procesa(SiParam a) {
		System.out.println("(");
		a.lparams().procesa(this);
		System.out.println(")");
	}

	@Override
	public void procesa(NoParam a) {
		System.out.println("(");
		System.out.println(")");
	}

	@Override
	public void procesa(UnParam a) {
		a.param().procesa(this);
	}

	@Override
	public void procesa(MuchosParams a) {
		a.lparam().procesa(this);
		System.out.println(",");
		a.param().procesa(this);
	}
	
	@Override
	public void procesa(ParamFormRef a) {
		a.tipo().procesa(this);
		System.out.println("&");
		System.out.printf(FORMAT, a.id(), a.leeFila(), a.leeCol());
	}
	
	@Override
	public void procesa(ParamFormal a) {
		a.tipo().procesa(this);
		System.out.printf(FORMAT, a.id(), a.leeFila(), a.leeCol());
	}

	@Override
	public void procesa(TArray a) {
		a.tipo().procesa(this);
		System.out.println("[");
		System.out.println(a.litEnt());
		System.out.printf(FORMAT, "]", a.leeFila(), a.leeCol());
	}
	
	@Override
	public void procesa(TPunt a) {
		System.out.println("^");
		a.tipo().procesa(this);
	}

	@Override
	public void procesa(TInt a) {
		System.out.println("<int>");
	}

	@Override
	public void procesa(TReal a) {
		System.out.println("<real>");
	}

	@Override
	public void procesa(TBool a) {
		System.out.println("<bool>");
	}

	@Override
	public void procesa(TString a) {
		System.out.println("<string>");
	}

	@Override
	public void procesa(TIden a) {
		System.out.printf(FORMAT, a.iden(), a.leeFila(), a.leeCol());
	}

	@Override
	public void procesa(TStruct a) {
		System.out.println("<struct>");
		System.out.println("{");
		a.lcampos().procesa(this);
		System.out.println("}");
	}
	
	@Override
	public void procesa(MuchosCamps a) {
		a.lcampos().procesa(this);
		System.out.println(",");
		a.campo().procesa(this);
	}
	
	@Override
	public void procesa(UnCamp a) {
		a.campo().procesa(this);
	}
	
	@Override
	public void procesa(Campo a) {
		a.tipo().procesa(this);
		System.out.printf(FORMAT, a.iden(), a.leeFila(), a.leeCol());
	}
	
	@Override
	public void procesa(SiInstrs a) {
		a.linstrs().procesa(this);
	}
	
	@Override
	public void procesa(NoInstrs a) {
	}
	
	@Override
	public void procesa(MuchasInstrs a) {
		a.linstrs().procesa(this);
		System.out.println(";");
		a.instr().procesa(this);
	}
	
	@Override
	public void procesa(UnaInstr a) {
		a.instr().procesa(this);
	}
	
	@Override
	public void procesa(ArrobaInstr a) {
		System.out.println("@");
		a.exp().procesa(this);
	}
	
	@Override
	public void procesa(ProcInstr a) {
		System.out.println("<call>");
		System.out.printf(FORMAT, a.iden(), a.leeFila(), a.leeCol());
		a.paramReales().procesa(this);
	}
	
	@Override
	public void procesa(NlInstr a) {
		System.out.println("<nl>");
	}
	
	@Override
	public void procesa(NewInstr a) {
		System.out.println("<new>");
		a.exp().procesa(this);
	}
	
	@Override
	public void procesa(ReadInstr a) {
		System.out.println("<read>");
		a.exp().procesa(this);
	}
	
	@Override
	public void procesa(WriteInstr a) {
		System.out.println("<write>");
		a.exp().procesa(this);
	}
	
	@Override
	public void procesa(DeleteInstr a) {
		System.out.println("<delete>");
		a.exp().procesa(this);
	}
	
	@Override
	public void procesa(WhileInstr a) {
		System.out.println("<while>");
		a.exp().procesa(this);
		a.bloq().procesa(this);
	}
	
	@Override
	public void procesa(IfElseInstr a) {
		System.out.println("<if>");
		a.exp().procesa(this);
		a.bloq1().procesa(this);
		System.out.println("<else>");
		a.bloq2().procesa(this);
	}
	
	@Override
	public void procesa(IfInstr a) {
		System.out.println("<if>");
		a.exp().procesa(this);
		a.bloq().procesa(this);
	}
	
	@Override
	public void procesa(BloqueInstr a) {
		a.bloq().procesa(this);
	}

	@Override
	public void procesa(SiExp a) {
		System.out.println("(");
		a.lexps().procesa(this);
		System.out.println(")");
	}

	@Override
	public void procesa(NoExp a) {
		System.out.println("(");
		System.out.println(")");
	}

	@Override
	public void procesa(UnaExp a) {
		a.exp().procesa(this);
	}

	@Override
	public void procesa(MuchasExp a) {
		a.lexp().procesa(this);
		System.out.println(",");
		a.exp().procesa(this);
	}
	
	@Override
	public void procesa(LitEnt a) {
		System.out.printf(FORMAT, a.lit(), a.leeFila(), a.leeCol());
	}
	
	@Override
	public void procesa(LitReal a) {
		System.out.printf(FORMAT, a.lit(), a.leeFila(), a.leeCol());
	}
	
	@Override
	public void procesa(Iden a) {
		System.out.printf(FORMAT, a.id(), a.leeFila(), a.leeCol());
	}
	
	@Override
	public void procesa(True a) {
		System.out.printf(FORMAT, "<true>", a.leeFila(), a.leeCol());
	}
	
	@Override
	public void procesa(False a) {
		System.out.printf(FORMAT, "<false>", a.leeFila(), a.leeCol());
	}
	
	@Override
	public void procesa(LitCad a) {
		System.out.printf(FORMAT, a.lit(), a.leeFila(), a.leeCol());
	}
	
	@Override
	public void procesa(Null a) {
		System.out.printf(FORMAT, "<null>", a.leeFila(), a.leeCol());
	}
	
	@Override
	public void procesa(Asignacion a) {
		imprimeExpBin(a.opnd0(),"=",a.opnd1(),1,0, a);
	}

	@Override
	public void procesa(Suma a) {
		imprimeExpBin(a.opnd0(),"+",a.opnd1(),2,3, a);
	}

	@Override
	public void procesa(Resta a) {
		imprimeExpBin(a.opnd0(),"-",a.opnd1(),3,3, a);
	}
	
	@Override
	public void procesa(And a) {
		imprimeExpBin(a.opnd0(),"<and>",a.opnd1(),4,5, a);
	}
	
	@Override
	public void procesa(Or a) {
		imprimeExpBin(a.opnd0(),"<or>",a.opnd1(),4,5, a);
	}
	
	@Override
	public void procesa(Mul a) {
		imprimeExpBin(a.opnd0(),"*",a.opnd1(),4,5, a);
	}
	
	@Override
	public void procesa(Div a) {
		imprimeExpBin(a.opnd0(),"/",a.opnd1(),4,5, a);
	}
	
	@Override
	public void procesa(Mod a) {
		imprimeExpBin(a.opnd0(),"%",a.opnd1(),4,5, a);
	}
	
	@Override
	public void procesa(Menor a) {
		imprimeExpBin(a.opnd0(),"<",a.opnd1(),1,2, a);
	}
	
	@Override
	public void procesa(Mayor a) {
		imprimeExpBin(a.opnd0(),">",a.opnd1(),1,2, a);
	}
	
	@Override
	public void procesa(MenorIgual a) {
		imprimeExpBin(a.opnd0(),"<=",a.opnd1(),1,2, a);
	}
	
	@Override
	public void procesa(MayorIgual a) {
		imprimeExpBin(a.opnd0(),">=",a.opnd1(),1,2, a);
	}
	
	@Override
	public void procesa(Igual a) {
		imprimeExpBin(a.opnd0(),"==",a.opnd1(),1,2, a);
	}
	
	@Override
	public void procesa(Desigual a) {
		imprimeExpBin(a.opnd0(),"!=",a.opnd1(),1,2, a);
	}

	@Override
	public void procesa(Array a) {
		imprimeOpnd(a.opnd(), 6);
		System.out.printf(FORMAT, "[", a.leeFila(), a.leeCol());
    	a.idx().procesa(this);
    	System.out.println("]");
	}

	@Override
	public void procesa(ExpCampo a) {
		imprimeOpnd(a.opnd(), 6);
		System.out.println(".");
		System.out.printf(FORMAT, a.campo(), a.leeFila(), a.leeCol());
	}

	@Override
	public void procesa(Punt a) {
		imprimeOpnd(a.opnd(), 6);
		System.out.printf(FORMAT, "^", a.leeFila(), a.leeCol());
	}

	@Override
	public void procesa(Neg a) {
		imprimeExpUn("-", a.opnd(), 5, a);
	}

	@Override
	public void procesa(Not a) {
		imprimeExpUn("<not>", a.opnd(), 5, a);
	}
}
