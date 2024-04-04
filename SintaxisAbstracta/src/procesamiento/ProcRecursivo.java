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
    
    private void imprimeExpUn(String op, Exp opnd, int np) {
    	System.out.print(op + " ");
    	imprimeOpnd(opnd,np);
    }
    
    public void imprime(Decs decs) {
    	if (claseDe(decs, SiDecs.class)) {
    		imprime(((SiDecs) decs).ldecs());
    		System.out.println();
			System.out.println("&&");
    	}
    }
    
    public void imprime(LDecs ldecs) {
    	if (claseDe(ldecs, UnaDec.class))
    		imprime(((UnaDec) ldecs).dec());
    	else {
    		imprime(((MuchasDecs) ldecs).ldecs());
    		System.out.print(";");
    		imprime(((MuchasDecs) ldecs).dec());
    	}
    }
    
    public void imprime(Dec dec) { 	
    	if (claseDe(dec, DecProc.class)) {
    		System.out.print("<prog> ");
    		System.out.print(((DecProc) dec).iden());
    		System.out.print(" ");
    		imprime(((DecProc) dec).params());
    		System.out.print(" ");
    		imprime(((DecProc) dec).bloq());
    	}
    	else if (claseDe(dec, DecType.class)) {
    		System.out.print("<type> ");
    		imprime(((DecType) dec).tipo());
    		System.out.print(" ");
    		System.out.print(((DecType) dec).iden());
    	}
    	else if (claseDe(dec, DecVar.class)) {
    		imprime(((DecVar) dec).tipo());
    		System.out.print(" ");
    		System.out.print(((DecVar) dec).iden());
    	}
    }
    
    public void imprime(ParamForms params) {
    	System.out.print("(");
    	if (claseDe(params, SiParam.class))
    		imprime(((SiParam) params).lparams());
    	System.out.print(")");
    }
    
    public void imprime(LParams lparam) {
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
    	else if (claseDe(tipo, TArray.class)) {
    		imprime(((TArray) tipo).tipo());
    		System.out.print("[" + ((TArray) tipo).litEnt() + "]");
    	}
    }
    
    public void imprime(Instrs instrs) {
    	if (claseDe(instrs, SiInstrs.class)) {
    		imprime(((SiInstrs) instrs).linstrs());
    	}
    }
    
    public void imprime(LInstrs linstrs) {
    	if (claseDe(linstrs, UnaInstr.class))
    		imprime(((UnaInstr) linstrs).instr());
    	else {
    		imprime(((MuchasInstrs) linstrs).linstrs());
    		System.out.print(";");
    		imprime(((MuchasInstrs) linstrs).instr());
    	}
    }
    
    public void imprime(Instr instr) { 	
    	if (claseDe(instr, ArrobaInstr.class)) {
    		System.out.print("@");
    		imprime(((ArrobaInstr) instr).exp());
    	}
    	else if (claseDe(instr, ProcInstr.class)) {
    		System.out.print("<call>");
    		System.out.print(((ProcInstr) instr).iden());
    		imprime(((ProcInstr) instr).paramReales());
    	}
    	else if (claseDe(instr, NlInstr.class)) {
    		System.out.print("<nl>");
    	}
    	else if (claseDe(instr, NewInstr.class)) {
    		System.out.print("<new>");
    		imprime(((NewInstr) instr).exp());
    	}
    	else if (claseDe(instr, ReadInstr.class)) {
    		System.out.print("<read>");
    		imprime(((ReadInstr) instr).exp());
    	}
    	else if (claseDe(instr, WriteInstr.class)) {
    		System.out.print("<write>");
    		imprime(((WriteInstr) instr).exp());
    	}
    	else if (claseDe(instr, DeleteInstr.class)) {
    		System.out.print("<delete>");
    		imprime(((DeleteInstr) instr).exp());
    	}
    	else if (claseDe(instr, WhileInstr.class)) {
    		System.out.print("<while> ");
    		imprime(((WhileInstr) instr).exp());
    		System.out.print(" ");
    		imprime(((WhileInstr) instr).bloq());
    	}
    	else if (claseDe(instr, IfElseInstr.class)) {
    		System.out.print("<if> ");
    		imprime(((IfElseInstr) instr).exp());
    		System.out.print(" ");
    		imprime(((IfElseInstr) instr).bloq1());
    		System.out.print("\n}");
    		System.out.print("<else> ");
    		imprime(((IfElseInstr) instr).bloq2());
    	}
    	else if (claseDe(instr, IfInstr.class)) {
    		System.out.print("<if> ");
    		imprime(((IfInstr) instr).exp());
    		System.out.print(" ");
    		imprime(((IfInstr) instr).bloq());
    	}
    	else if (claseDe(instr, BloqueInstr.class)) {
    		imprime(((BloqueInstr) instr).bloq());
    	}
    }
    
    public void imprime(ParamReales params) {
		System.out.print("(");
    	if (claseDe(params, SiExp.class))
    		imprime(((SiExp) params).lexps());
		System.out.print(")");
    }
    
    public void imprime(LExps params) {
    	if (claseDe(params, UnaExp.class))
    		imprime(((UnaExp) params).exp());
    	else {
    		imprime(((MuchasExp) params).lexp());
    		System.out.print(", ");
    		imprime(((MuchasExp) params).exp());
    	}
    }
    
    public void imprime(Exp exp) {
    	if (claseDe(exp, Asignacion.class)) {
    		imprimeExpBin(((Asignacion) exp).opnd0(), "=", ((Menor) exp).opnd1(), 1, 0);
    	}
    	else if (claseDe(exp, Menor.class)) {
    		imprimeExpBin(((Menor) exp).opnd0(), "<", ((Menor) exp).opnd1(), 1, 2);
    	}
    	else if (claseDe(exp, Mayor.class)) {
    		imprimeExpBin(((Mayor) exp).opnd0(), ">", ((Mayor) exp).opnd1(), 1, 2);
    	}
    	else if (claseDe(exp, MenorIgual.class)) {
    		imprimeExpBin(((MenorIgual) exp).opnd0(), "<=", ((MenorIgual) exp).opnd1(), 1, 2);
    	}
    	else if (claseDe(exp, MayorIgual.class)) {
    		imprimeExpBin(((MayorIgual) exp).opnd0(), ">=", ((MayorIgual) exp).opnd1(), 1, 2);
    	}
    	else if (claseDe(exp, Igual.class)) {
    		imprimeExpBin(((Igual) exp).opnd0(), "==", ((Igual) exp).opnd1(), 1, 2);
    	}
    	else if (claseDe(exp, Desigual.class)) {
    		imprimeExpBin(((Desigual) exp).opnd0(), "!=", ((Desigual) exp).opnd1(), 1, 2);
    	}
    	else if (claseDe(exp, Suma.class)) {
    		imprimeExpBin(((Suma) exp).opnd0(), "+", ((Suma) exp).opnd1(), 2, 3);
    	}
    	else if (claseDe(exp, Resta.class)) {
    		imprimeExpBin(((Resta) exp).opnd0(), "-", ((Resta) exp).opnd1(), 3, 3);
    	}
    	else if (claseDe(exp, Mul.class)) {
    		imprimeExpBin(((Resta) exp).opnd0(), "*", ((Mul) exp).opnd1(), 4, 5);
    	}
    	else if (claseDe(exp, Div.class)) {
    		imprimeExpBin(((Resta) exp).opnd0(), "/", ((Div) exp).opnd1(), 4, 5);
    	}
    	else if (claseDe(exp, Mod.class)) {
    		imprimeExpBin(((Resta) exp).opnd0(), "%", ((Mod) exp).opnd1(), 4, 5);
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
