package procesamiento;

import asint.Procesamiento;
import asint.SintaxisAbstracta.Array;
import asint.SintaxisAbstracta.Exp;
import asint.SintaxisAbstracta.ExpCampo;
import asint.SintaxisAbstracta.MuchasDecs;
import asint.SintaxisAbstracta.MuchasExp;
import asint.SintaxisAbstracta.MuchosParams;
import asint.SintaxisAbstracta.NoDecs;
import asint.SintaxisAbstracta.NoExp;
import asint.SintaxisAbstracta.NoParam;
import asint.SintaxisAbstracta.Punt;
import asint.SintaxisAbstracta.Resta;
import asint.SintaxisAbstracta.SiDecs;
import asint.SintaxisAbstracta.SiExp;
import asint.SintaxisAbstracta.SiParam;
import asint.SintaxisAbstracta.Suma;
import asint.SintaxisAbstracta.TArray;
import asint.SintaxisAbstracta.TBool;
import asint.SintaxisAbstracta.TIden;
import asint.SintaxisAbstracta.TInt;
import asint.SintaxisAbstracta.TReal;
import asint.SintaxisAbstracta.TString;
import asint.SintaxisAbstracta.TStruct;
import asint.SintaxisAbstracta.UnParam;
import asint.SintaxisAbstracta.UnaDec;
import asint.SintaxisAbstracta.UnaExp;

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
	public void procesa(Suma a) {
		imprimeExpBin(a.opnd0(),"+",a.opnd1(),2,3);
	}

	@Override
	public void procesa(Resta a) {
		imprimeExpBin(a.opnd0(),"-",a.opnd1(),3,3);
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
