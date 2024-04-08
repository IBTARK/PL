package procesamiento;

import asint.SintaxisAbstracta;

public class ProcRecursivo extends SintaxisAbstracta {
	
    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    }
    
    private void imprime(String token, int fila, int col) {
    	System.out.println(String.format("%s$f:%d,c:%d$", token, fila, col));
    }
    
    private void imprimeOpnd(Exp opnd, int minPrior) {
       	if(opnd.prioridad() < minPrior){
       		System.out.println("(");
       	}
       	imprime(opnd);
       	if(opnd.prioridad() < minPrior) {
       		System.out.println(")");
       	}
    }

    private void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1, Exp exp) {
    	imprimeOpnd(opnd0,np0);
    	imprime(op, exp.leeFila(), exp.leeCol());
    	imprimeOpnd(opnd1,np1);
    }
    
    private void imprimeExpUn(String op, Exp opnd, int np, Exp exp) {
    	imprime(op, exp.leeFila(), exp.leeCol());
    	imprimeOpnd(opnd,np);
    }
    
    public void imprime(Prog prog) {
    	imprime(prog.bloq());
    }
    
    public void imprime(Bloque bloq) {
    	System.out.println("{");
    	imprime(bloq.decs());
    	imprime(bloq.instrs());
    	System.out.println("}");
    }
    
    public void imprime(Decs decs) {
    	if (claseDe(decs, SiDecs.class)) {
    		imprime(((SiDecs) decs).ldecs());
			System.out.println("&&");
    	}
    }
    
    public void imprime(LDecs ldecs) {
    	if (claseDe(ldecs, UnaDec.class))
    		imprime(((UnaDec) ldecs).dec());
    	else {
    		imprime(((MuchasDecs) ldecs).ldecs());
    		System.out.println(";");
    		imprime(((MuchasDecs) ldecs).dec());
    	}
    }
    
    public void imprime(Dec dec) { 	
    	if (claseDe(dec, DecProc.class)) {
    		System.out.println("<proc>");
    		imprime(((DecProc) dec).iden(), dec.leeFila(), dec.leeCol());
    		imprime(((DecProc) dec).params());
    		imprime(((DecProc) dec).bloq());
    	}
    	else if (claseDe(dec, DecType.class)) {
    		System.out.println("<type>");
    		imprime(((DecType) dec).tipo());
    		imprime(((DecType) dec).iden(), dec.leeFila(), dec.leeCol());
    	}
    	else if (claseDe(dec, DecVar.class)) {
    		imprime(((DecVar) dec).tipo());
    		imprime(((DecVar) dec).iden(), dec.leeFila(), dec.leeCol());
    	}
    }
    
    public void imprime(ParamForms params) {
    	System.out.println("(");
    	if (claseDe(params, SiParam.class))
    		imprime(((SiParam) params).lparams());
    	System.out.println(")");
    }
    
    public void imprime(LParams lparam) {
    	if (claseDe(lparam, UnParam.class))
    		imprime(((UnParam) lparam).param());
    	else {
    		imprime(((MuchosParams) lparam).lparam());
    		System.out.println(",");
    		imprime(((MuchosParams) lparam).param());
    	}
    }
    
    public void imprime(ParamForm param) {
    	if (claseDe(param, ParamFormal.class)) {
    		imprime(((ParamFormal) param).tipo());
    		imprime(((ParamFormal) param).id(), param.leeFila(), param.leeCol());
    	}
    	else {
    		imprime(((ParamFormRef) param).tipo());
    		System.out.println("&");
    		imprime(((ParamFormRef) param).id(), param.leeFila(), param.leeCol());
    	}
    }
    
    public void imprime(Tipo tipo) {
    	if (claseDe(tipo, TInt.class)) {
    		System.out.println("<int>");
    	}
    	else if (claseDe(tipo, TReal.class)) {
    		System.out.println("<real>");
    	}
    	else if (claseDe(tipo, TBool.class)) {
    		System.out.println("<bool>");
    	}
    	else if (claseDe(tipo, TString.class)) {
    		System.out.println("<string>");
    	}
    	else if (claseDe(tipo, TIden.class)) {
    		imprime(((TIden) tipo).iden(), tipo.leeFila(), tipo.leeCol());
    	}
    	else if (claseDe(tipo, TStruct.class)) {
    		System.out.println("<struct>");
    		System.out.println("{");
    		imprime(((TStruct) tipo).lcampos());
    		System.out.println("}");
    	}
    	else if (claseDe(tipo, TPunt.class)) {
    		System.out.println("^");
    		imprime(((TPunt) tipo).tipo());
    	}
    	else if (claseDe(tipo, TArray.class)) {
    		imprime(((TArray) tipo).tipo());
    		System.out.println("[");
    		System.out.println(((TArray) tipo).litEnt());
    		imprime("]", tipo.leeFila(), tipo.leeCol());
    	}
    }
    
    public void imprime(LCampos campos) {
    	if (claseDe(campos, UnCamp.class)) {
    		imprime(((UnCamp) campos).campo());
    	}
    	else {
    		imprime(((MuchosCamps) campos).lcampos());
    		System.out.println(",");
    		imprime(((MuchosCamps) campos).campo());
    	}
    }
    
    public void imprime(Campo campo) {
    	imprime(campo.tipo());
    	imprime(campo.iden(), campo.leeFila(), campo.leeCol());
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
    		System.out.println(";");
    		imprime(((MuchasInstrs) linstrs).instr());
    	}
    }
    
    public void imprime(Instr instr) { 	
    	if (claseDe(instr, ArrobaInstr.class)) {
    		System.out.println("@");
    		imprime(((ArrobaInstr) instr).exp());
    	}
    	else if (claseDe(instr, ProcInstr.class)) {
    		System.out.println("<call>");
    		imprime(((ProcInstr) instr).iden(), instr.leeFila(), instr.leeCol());
    		imprime(((ProcInstr) instr).paramReales());
    	}
    	else if (claseDe(instr, NlInstr.class)) {
    		System.out.println("<nl>");
    	}
    	else if (claseDe(instr, NewInstr.class)) {
    		System.out.println("<new>");
    		imprime(((NewInstr) instr).exp());
    	}
    	else if (claseDe(instr, ReadInstr.class)) {
    		System.out.println("<read>");
    		imprime(((ReadInstr) instr).exp());
    	}
    	else if (claseDe(instr, WriteInstr.class)) {
    		System.out.println("<write>");
    		imprime(((WriteInstr) instr).exp());
    	}
    	else if (claseDe(instr, DeleteInstr.class)) {
    		System.out.println("<delete>");
    		imprime(((DeleteInstr) instr).exp());
    	}
    	else if (claseDe(instr, WhileInstr.class)) {
    		System.out.println("<while>");
    		imprime(((WhileInstr) instr).exp());
    		imprime(((WhileInstr) instr).bloq());
    	}
    	else if (claseDe(instr, IfElseInstr.class)) {
    		System.out.println("<if>");
    		imprime(((IfElseInstr) instr).exp());
    		imprime(((IfElseInstr) instr).bloq1());
    		System.out.println("<else>");
    		imprime(((IfElseInstr) instr).bloq2());
    	}
    	else if (claseDe(instr, IfInstr.class)) {
    		System.out.println("<if>");
    		imprime(((IfInstr) instr).exp());
    		imprime(((IfInstr) instr).bloq());
    	}
    	else if (claseDe(instr, BloqueInstr.class)) {
    		imprime(((BloqueInstr) instr).bloq());
    	}
    }
    
    public void imprime(ParamReales params) {
		System.out.println("(");
    	if (claseDe(params, SiExp.class))
    		imprime(((SiExp) params).lexps());
		System.out.println(")");
    }
    
    public void imprime(LExp params) {
    	if (claseDe(params, UnaExp.class))
    		imprime(((UnaExp) params).exp());
    	else {
    		imprime(((MuchasExp) params).lexp());
    		System.out.println(",");
    		imprime(((MuchasExp) params).exp());
    	}
    }
    
    public void imprime(Exp exp) {
    	if (claseDe(exp, Asignacion.class)) {
    		imprimeExpBin(((Asignacion) exp).opnd0(), "=", ((Asignacion) exp).opnd1(), 1, 0, exp);
    	}
    	else if (claseDe(exp, Menor.class)) {
    		imprimeExpBin(((Menor) exp).opnd0(), "<", ((Menor) exp).opnd1(), 1, 2, exp);
    	}
    	else if (claseDe(exp, Mayor.class)) {
    		imprimeExpBin(((Mayor) exp).opnd0(), ">", ((Mayor) exp).opnd1(), 1, 2, exp);
    	}
    	else if (claseDe(exp, MenorIgual.class)) {
    		imprimeExpBin(((MenorIgual) exp).opnd0(), "<=", ((MenorIgual) exp).opnd1(), 1, 2, exp);
    	}
    	else if (claseDe(exp, MayorIgual.class)) {
    		imprimeExpBin(((MayorIgual) exp).opnd0(), ">=", ((MayorIgual) exp).opnd1(), 1, 2, exp);
    	}
    	else if (claseDe(exp, Igual.class)) {
    		imprimeExpBin(((Igual) exp).opnd0(), "==", ((Igual) exp).opnd1(), 1, 2, exp);
    	}
    	else if (claseDe(exp, Desigual.class)) {
    		imprimeExpBin(((Desigual) exp).opnd0(), "!=", ((Desigual) exp).opnd1(), 1, 2, exp);
    	}
    	else if (claseDe(exp, Suma.class)) {
    		imprimeExpBin(((Suma) exp).opnd0(), "+", ((Suma) exp).opnd1(), 2, 3, exp);
    	}
    	else if (claseDe(exp, Resta.class)) {
    		imprimeExpBin(((Resta) exp).opnd0(), "-", ((Resta) exp).opnd1(), 3, 3, exp);
    	}
    	else if (claseDe(exp, Mul.class)) {
    		imprimeExpBin(((Mul) exp).opnd0(), "*", ((Mul) exp).opnd1(), 4, 5, exp);
    	}
    	else if (claseDe(exp, Div.class)) {
    		imprimeExpBin(((Div) exp).opnd0(), "/", ((Div) exp).opnd1(), 4, 5, exp);
    	}
    	else if (claseDe(exp, Mod.class)) {
    		imprimeExpBin(((Mod) exp).opnd0(), "%", ((Mod) exp).opnd1(), 4, 5, exp);
    	}
    	else if (claseDe(exp, Neg.class)) {
    		imprimeExpUn("-", ((Neg) exp).opnd(), 5, exp);
    	}
    	else if (claseDe(exp, Not.class)) {
    		imprimeExpUn("<not>", ((Not) exp).opnd(), 5, exp);
    	}
    	else if (claseDe(exp, Array.class)) {
    		imprimeOpnd(((Array) exp).opnd(), 6);
        	imprime("[", exp.leeFila(), exp.leeCol());
        	imprime(((Array) exp).idx());
        	System.out.println("]");
    	}
    	else if (claseDe(exp, ExpCampo.class)) {
    		imprimeOpnd(((ExpCampo) exp).opnd(), 6);
        	System.out.println(".");
        	imprime(((ExpCampo) exp).campo(), exp.leeFila(), exp.leeCol());
    	}
    	else if (claseDe(exp, Punt.class)) {
    		imprimeOpnd(((Punt) exp).opnd(), 6);
        	imprime("^", exp.leeFila(), exp.leeCol());
    	}
    	else if (claseDe(exp, LitEnt.class)) {
        	imprime(((LitEnt) exp).lit(), exp.leeFila(), exp.leeCol());
    	}
    	else if (claseDe(exp, LitReal.class)) {
        	imprime(((LitReal) exp).lit(), exp.leeFila(), exp.leeCol());
    	}
    	else if (claseDe(exp, LitCad.class)) {
        	imprime(((LitCad) exp).lit(), exp.leeFila(), exp.leeCol());
    	}
    	else if (claseDe(exp, Iden.class)) {
        	imprime(((Iden) exp).id(), exp.leeFila(), exp.leeCol());
    	}
    	else if (claseDe(exp, True.class)) {
        	imprime("<true>", exp.leeFila(), exp.leeCol());
    	}
    	else if (claseDe(exp, False.class)) {
        	imprime("<false>", exp.leeFila(), exp.leeCol());
    	}
    	else if (claseDe(exp, Null.class)) {
        	imprime("<null>", exp.leeFila(), exp.leeCol());
    	}
    }
}
