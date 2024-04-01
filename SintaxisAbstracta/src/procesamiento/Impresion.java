package procesamiento;

import asint.Procesamiento;
import asint.SintaxisAbstracta.Array;
import asint.SintaxisAbstracta.ArrobaInstr;
import asint.SintaxisAbstracta.Asignacion;
import asint.SintaxisAbstracta.BloqueInstr;
import asint.SintaxisAbstracta.DecType;
import asint.SintaxisAbstracta.DecVar;
import asint.SintaxisAbstracta.DeleteInstr;
import asint.SintaxisAbstracta.Div;
import asint.SintaxisAbstracta.Exp;
import asint.SintaxisAbstracta.ExpCampo;
import asint.SintaxisAbstracta.IfElseInstr;
import asint.SintaxisAbstracta.IfInstr;
import asint.SintaxisAbstracta.Mod;
import asint.SintaxisAbstracta.MuchasDecs;
import asint.SintaxisAbstracta.MuchasExp;
import asint.SintaxisAbstracta.MuchosParams;
import asint.SintaxisAbstracta.Mul;
import asint.SintaxisAbstracta.NewInstr;
import asint.SintaxisAbstracta.NlInstr;
import asint.SintaxisAbstracta.NoDecs;
import asint.SintaxisAbstracta.NoExp;
import asint.SintaxisAbstracta.NoParam;
import asint.SintaxisAbstracta.ProcInstr;
import asint.SintaxisAbstracta.Punt;
import asint.SintaxisAbstracta.ReadInstr;
import asint.SintaxisAbstracta.Resta;
import asint.SintaxisAbstracta.SiDecs;
import asint.SintaxisAbstracta.SiExp;
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
import asint.SintaxisAbstracta.UnParam;
import asint.SintaxisAbstracta.UnaDec;
import asint.SintaxisAbstracta.UnaExp;
import asint.SintaxisAbstracta.WhileInstr;
import asint.SintaxisAbstracta.WriteInstr;

public class Impresion implements Procesamiento {
    
    private void imprimeOpnd(Exp opnd, int minPrior) {
       	if(opnd.prioridad() < minPrior){
       		System.out.print("(");
       	}
       	opnd.procesa(this);
       	if(opnd.prioridad() < minPrior) {
       		System.out.print(")");
       	}
    }

    private void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1) {
    	imprimeOpnd(opnd0,np0);
    	System.out.print(" " + op + " ");
    	imprimeOpnd(opnd1,np1);
    }

	@Override
	public void procesa(SiDecs a) {
		a.ldecs().procesa(this);
		System.out.print("\n&&");
		System.out.println();
	}

	@Override
	public void procesa(NoDecs a) {
		
	}

	@Override
	public void procesa(MuchasDecs a) {
		// TODO Auto-generated method stub
		a.ldecs().procesa(this);
		System.out.print(";\n");
		a.dec().procesa(this);
	}

	@Override
	public void procesa(UnaDec a) {
		a.dec().procesa(this);
	}
	
	@Override
	public void procesa(DecProc a) {
		System.out.print("<proc> ");
		System.out.print(a.iden());
		a.paramForm().procesa(this); 
		System.out.print(" ");
		a.prog().procesa(this);
	}
	
	
	@Override
	public void procesa(DecType a) {
		System.out.print("<type> ");
		a.tipo().procesa(this); 
		System.out.print(" ");
		System.out.print(a.iden());
	}
	
	@Override
	public void procesa(DecVar a) {
		a.tipo().procesa(this); 
		System.out.print(" ");
		System.out.print(a.iden());
	}

	@Override
	public void procesa(SiParam a) {
		System.out.print("(");
		a.lparam().procesa(this);
		System.out.print(")");
	}

	@Override
	public void procesa(NoParam a) {
		System.out.print("()");
	}

	@Override
	public void procesa(UnParam a) {
		a.param().procesa(this);
	}

	@Override
	public void procesa(MuchosParams a) {
		a.lparam().procesa(this);
		System.out.print(", ");
		a.param().procesa(this);
	}

	@Override
	public void procesa(TArray a) {
		a.tipo().procesa(this);
		System.out.print("["+a.num()+"]");
	}
	
	@Override
	public void procesa(TPunt a) {
		System.out.print("^");
		a.tipo().procesa(this);
	}

	@Override
	public void procesa(TInt a) {
		System.out.print("<int>");
	}

	@Override
	public void procesa(TReal a) {
		System.out.print("<real>");
	}

	@Override
	public void procesa(TBool a) {
		System.out.print("<bool>");
	}

	@Override
	public void procesa(TString a) {
		System.out.print("<string>");
	}

	@Override
	public void procesa(TIden a) {
		System.out.print(a.iden());
	}

	@Override
	public void procesa(TStruct a) {
		System.out.println("<struct> {");
		a.lcampos().procesa(this);
		System.out.print("\n}");
	}
	
	@Override
	public void procesa(ArrobaInstr a) {
		System.out.print("@");
		a.exp().procesa(this);
	}
	
	@Override
	public void procesa(ProcInstr a) {
		System.out.print("<call> ");
		System.out.print(a.iden());
		a.paramReal().procesa(this);
	}
	
	@Override
	public void procesa(NlInstr a) {
		System.out.print("<nl>");
	}
	
	@Override
	public void procesa(NewInstr a) {
		System.out.print("<new> ");
		a.exp().procesa(this);
	}
	
	@Override
	public void procesa(ReadInstr a) {
		System.out.print("<read> ");
		a.exp().procesa(this);
	}
	
	@Override
	public void procesa(WriteInstr a) {
		System.out.print("<write> ");
		a.exp().procesa(this);
	}
	
	@Override
	public void procesa(DeleteInstr a) {
		System.out.print("<delete> ");
		a.exp().procesa(this);
	}
	
	@Override
	public void procesa(WhileInstr a) {
		System.out.print("<while> ");
		a.exp().procesa(this);
		System.out.print(" ");
		a.prog().procesa(this);
	}
	
	@Override
	public void procesa(IfElseInstr a) {
		System.out.print("<if> ");
		a.exp().procesa(this);
		System.out.print(" ");
		a.prog().procesa(this);
		System.out.print("\n}");
		System.out.print("<else> ");
		a.prog().procesa(this);
	}
	
	@Override
	public void procesa(IfInstr a) {
		System.out.print("<if> ");
		a.exp().procesa(this);
		System.out.print(" ");
		a.prog().procesa(this);
	}
	
	@Override
	public void procesa(BloqueInstr a) {
		a.prog().procesa(this);
	}

	@Override
	public void procesa(SiExp a) {
		System.out.print("(");
		a.lexp().procesa(this);
		System.out.print(")");
	}

	@Override
	public void procesa(NoExp a) {
		System.out.print("()");
	}

	@Override
	public void procesa(UnaExp a) {
		a.exp().procesa(this);
	}

	@Override
	public void procesa(MuchasExp a) {
		a.lexp().procesa(this);
		System.out.print(", ");
		a.exp().procesa(this);
	}
	
	@Override
	public void procesa(Asignacion a) {
		imprimeExpBin(a.opnd0(),"=",a.opnd1(),1,0);
	}

	@Override
	public void procesa(Suma a) {
		imprimeExpBin(a.opnd0(),"+",a.opnd1(),2,3);
	}

	@Override
	public void procesa(Resta a) {
		imprimeExpBin(a.opnd0(),"-",a.opnd1(),3,3);
	}
	
	@Override
	public void procesa(Mul a) {
		imprimeExpBin(a.opnd0()," * ",a.opnd1(),4,5);
	}
	
	@Override
	public void procesa(Div a) {
		imprimeExpBin(a.opnd0(),"/",a.opnd1(),4,5);
	}
	
	@Override
	public void procesa(Mod a) {
		imprimeExpBin(a.opnd0(),"%",a.opnd1(),4,5);
	}

	@Override
	public void procesa(Array a) {
		imprimeOpnd(a.opnd(), 6);
    	System.out.print("[");
    	imprimeOpnd(a.idx(), 0);
    	System.out.print("]");
	}

	@Override
	public void procesa(ExpCampo a) {
		imprimeOpnd(a.opnd(), 6);
		System.out.print("."+a.campo());
	}

	@Override
	public void procesa(Punt a) {
		imprimeOpnd(a.opnd(), 6);
		System.out.print("^");
	}
}