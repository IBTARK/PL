package procesamiento;

import asint.SintaxisAbstracta;
import asint.SintaxisAbstracta.Exp;

public class ProcRecursivo extends SintaxisAbstracta {
	
    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
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
    
    public void imprime(DecProg dec) { 	
    	if (claseDe(dec, DecProg.class)) {
    		System.out.print("<prog> ");
    		System.out.print(((DecProg) dec).iden());
    		System.out.print(" ");
    		imprime(((DecProg) dec).param());
    		System.out.print(" ");
    		imprime(((DecProg) dec).bloc());
    	}
    }
    
    public void imprime(DecType dec) { 	
    	if (claseDe(dec, DecType.class)) {
    		System.out.print("<type> ");
    		imprime(((DecType) dec).tipo());
    		System.out.print(" ");
    		System.out.print(((DecType) dec).iden());
    	}
    }
    
    public void imprime(DecVar dec) { 	
    	if (claseDe(dec, DecVar.class)) {
    		imprime(((DecVar) dec).tipo());
    		System.out.print(" ");
    		System.out.print(((DecVar) dec).iden());
    	}
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
    	else if (claseDe(tipo, TPunt.class)) {
    		System.out.print("^ ");
    		imprime(((TPunt) tipo).tipo());
    	}
    }
    
    public void imprime(ArrobaInstr instr) { 	
    	if (claseDe(instr, ArrobaInstr.class)) {
    		System.out.print("@ ");
    		imprime(((ArrobaInstr) instr).exp());
    	}
    }
    
    public void imprime(ProcInstr instr) { 	
    	if (claseDe(instr, ProcInstr.class)) {
    		System.out.print("<call> ");
    		System.out.print(((ProcInstr) instr).iden());
    		imprime(((ProcInstr) instr).paramReales());
    	}
    }
    
    public void imprime(NlInstr instr) { 	
    	if (claseDe(instr, NlInstr.class)) {
    		System.out.print("<nl>");
    	}
    }
    
    public void imprime(NewInstr instr) { 	
    	if (claseDe(instr, NewInstr.class)) {
    		System.out.print("<new>");
    		imprime(((NewInstr) instr).exp());
    	}
    }
    
    public void imprime(DeleteInstr instr) { 	
    	if (claseDe(instr, DeleteInstr.class)) {
    		System.out.print("<delete>");
    		imprime(((DeleteInstr) instr).exp());
    	}
    }
    
    public void imprime(WriteInstr instr) { 	
    	if (claseDe(instr, WriteInstr.class)) {
    		System.out.print("<write>");
    		imprime(((WriteInstr) instr).exp());
    	}
    }
    
    public void imprime(ReadInstr instr) { 	
    	if (claseDe(instr, ReadInstr.class)) {
    		System.out.print("<read>");
    		imprime(((ReadInstr) instr).exp());
    	}
    }
    
    public void imprime(WhileInstr instr) { 	
    	if (claseDe(instr, WhileInstr.class)) {
    		System.out.print("<while> ");
    		imprime(((WhileInstr) instr).exp());
    		System.out.print(" ");
    		imprime(((WhileInstr) instr).bloq());
    	}
    }
    
    public void imprime(IfElseInstr instr) { 	
    	if (claseDe(instr, IfElseInstr.class)) {
    		System.out.print("<if> ");
    		imprime(((IfElseInstr) instr).exp());
    		System.out.print(" ");
    		imprime(((IfElseInstr) instr).bloq1());
    		System.out.print("\n}");
    		System.out.print("<else> ");
    		imprime(((IfElseInstr) instr).bloq2());
    	}
    }
    
    public void imprime(IfInstr instr) { 	
    	if (claseDe(instr, IfInstr.class)) {
    		System.out.print("<if> ");
    		imprime(((IfInstr) instr).exp());
    		System.out.print(" ");
    		imprime(((IfInstr) instr).bloq());
    	}
    }
    
    public void imprime(BloqueInstr instr) { 	
    	if (claseDe(instr, BloqueInstr.class)) {
    		imprime(((BloqueInstr) instr).bloq());
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
    
    public void imprime(Exp exp) {
    	if (claseDe(exp, Suma.class)) {
    		imprimeExpBin(((Suma) exp).opnd0(), "+", ((Suma) exp).opnd1(), 2, 3);
    	}
    	else if (claseDe(exp, Resta.class)) {
    		imprimeExpBin(((Resta) exp).opnd0(), "-", ((Resta) exp).opnd1(), 3, 3);
    	}
    	else if (claseDe(exp, Mul.class)) {
    		imprimeExpBin(((Resta) exp).opnd0(), "*", ((Resta) exp).opnd1(), 4, 5);
    	}
    	else if (claseDe(exp, Div.class)) {
    		imprimeExpBin(((Resta) exp).opnd0(), "/", ((Resta) exp).opnd1(), 4, 5);
    	}
    	else if (claseDe(exp, Mod.class)) {
    		imprimeExpBin(((Resta) exp).opnd0(), "%", ((Resta) exp).opnd1(), 4, 5);
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
