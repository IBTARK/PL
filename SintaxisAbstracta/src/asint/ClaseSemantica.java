package asint;

public class ClaseSemantica extends SintaxisAbstracta {
	
	public static class Op6 extends Nodo {
		private String op, lex;
		private Exp a;
		public Op6(String op, Exp a, String lex) {
			this.op = op;
			this.lex = lex;
			this.a = a;
		}
	}
    
	public ClaseSemantica() {
        super();
    }
    
    public Exp mkop(String op, Exp opnd1, Exp opnd2) {
        switch(op) {
            case "<": return menor(opnd1,opnd2);
            case ">": return mayor(opnd1,opnd2);
            case "<=": return menorIgual(opnd1,opnd2);
            case ">=": return mayorIgual(opnd1,opnd2);
            case "==": return igual(opnd1,opnd2);
            case "!=": return desigual(opnd1,opnd2);
            default: throw new UnsupportedOperationException("Bad op");
        }
    }
    
    public Exp mkop(Op6 op, Exp opnd) {
        switch(op.op) {
            case "[": return array(opnd,op.a);
            case ".": return expCampo(opnd,op.lex);
            case "^": return punt(opnd);
            default: throw new UnsupportedOperationException("Bad op");
        }
    }
}
