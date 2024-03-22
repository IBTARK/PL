package procesamiento;

import asint.SintaxisAbstracta;
import asint.SintaxisAbstracta.Exp;

public class ProcRecursivo extends SintaxisAbstracta {
	
    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    } 
    
    
    public void imprime(ParamForm params) {
    	System.out.print("(");
    	if (claseDe(params, SiParam.class))
    		imprime(((SiParam) params).lparam());
    	System.out.print(")");
    }
    
    public void imprime(LParam lparam) {
    	if (claseDe(lparam, UnParam.class))
    		imprime(((UnParam) lparam).param());
    	else {
    		imprime(((MuchosParams) lparam).lparam());
    		System.out.print(", ");
    		imprime(((MuchosParams) lparam).param());
    	}
    }
    
    public void imprime(Tipo tipo) {
    	if (claseDe(tipo, TInt.class)) {
    		System.out.print("<int>");
    	}
    	else if (claseDe(tipo, TReal.class)) {
    		System.out.print("<real>");
    	}
    	else if (claseDe(tipo, TBool.class)) {
    		System.out.print("<bool>");
    	}
    	else if (claseDe(tipo, TString.class)) {
    		System.out.print("<string>");
    	}
    	else if (claseDe(tipo, TIden.class)) {
    		System.out.print(((TIden) tipo).iden());
    	}
    	else if (claseDe(tipo, TStruct.class)) {
    		System.out.println("<struct> {");
    		imprime(((TStruct) tipo).lcampos());
    		System.out.println("}");
    	}
    }
    
    public void imprime(ParamReales params) {
		System.out.print("(");
    	if (claseDe(params, SiExp.class))
    		imprime(((SiExp) params).lexp());
		System.out.print(")");
    }
    
    public void imprime(LExp params) {
    	if (claseDe(params, UnaExp.class))
    		imprime(((UnaExp) params).exp());
    	else {
    		imprime(((MuchasExp) params).lexp());
    		System.out.print(", ");
    		imprime(((MuchasExp) params).exp());
    	}
    }
    
    private void imprimeOpnd(Exp opnd, int minPrior) {
       	if(opnd.prioridad() < minPrior){
       		System.out.print("(");
       	}
       	imprime(opnd);
       	if(opnd.prioridad() < minPrior) {
       		System.out.print(")");
       	}
    }

    private void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1) {
    	imprimeOpnd(opnd0,np0);
    	System.out.print(" " + op + " ");
    	imprimeOpnd(opnd1,np1);
    }
    
    public void imprime(Exp exp) {
    	if (claseDe(exp, Suma.class)) {
    		imprimeExpBin(((Suma) exp).opnd0(), ((Suma) exp).op(), ((Suma) exp).opnd1(), 2, 3);
    	}
    	else if (claseDe(exp, Resta.class)) {
    		imprimeExpBin(((Resta) exp).opnd0(), ((Resta) exp).op(), ((Resta) exp).opnd1(), 3, 3);
    	}
    	else if (claseDe(exp, Array.class)) {
    		imprimeOpnd(((Array) exp).opnd(), 6);
        	System.out.print("[");
        	imprime(((Array) exp).idx());
        	System.out.print("]");
    	}
    	else if (claseDe(exp, ExpCampo.class)) {
    		imprimeOpnd(((ExpCampo) exp).opnd(), 6);
        	System.out.print("." + ((ExpCampo) exp).campo());
    	}
    	else if (claseDe(exp, Punt.class)) {
    		imprimeOpnd(((Punt) exp).opnd(), 6);
        	System.out.print("^");
    	}
    }
}
