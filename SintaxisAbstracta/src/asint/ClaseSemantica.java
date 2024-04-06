package asint;

public class ClaseSemantica extends SintaxisAbstracta {
    
	public ClaseSemantica() {
        super();
    }
    
    public Exp mkop1(String op, Exp opnd1, Exp opnd2) {
        switch(op) {
            case "<": return menor(opnd1,opnd2);
            case ">": return mayor(opnd1,opnd2);
            case "<=": return menorIgual(opnd1,opnd2);
            case ">=": return mayorIgual(opnd1,opnd2);
            case "==": return igual(opnd1,opnd2);
            case "!=": return desigual(opnd1,opnd2);
            default: throw new UnsupportedOperationException("Bad op1");
        }
    }
    
    public Exp mkop4(String op, Exp opnd1, Exp opnd2) {
        switch(op) {
            case "*": return mul(opnd1,opnd2);
            case "/": return div(opnd1,opnd2);
            case "%": return mod(opnd1,opnd2);
            default: throw new UnsupportedOperationException("Bad op4");
        }
    }
    
    public Exp mkop5(String op, Exp opnd) {
        switch(op) {
            case "-": return neg(opnd);
            case "not": return not(opnd);
            default: throw new UnsupportedOperationException("Bad op4");
        }
    }
}
