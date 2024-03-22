package asint;

public class SintaxisAbstracta {

    public static abstract class Nodo  {
       public Nodo() {
		   fila=col=-1;
       }   
	   private int fila;
	   private int col;
	   public Nodo ponFila(int fila) {
		    this.fila = fila;
            return this;			
	   }
	   public Nodo ponCol(int col) {
		    this.col = col;
            return this;			
	   }
	   public int leeFila() {
		  return fila; 
	   }
	   public int leeCol() {
		  return col; 
	   }
	   public abstract void imprime();
	   public abstract void procesa(Procesamiento p);
    }

    public static abstract class Decs extends Nodo {
    	public Decs() {
    	}
    }
    
    public static abstract class LDecs extends Nodo {
        public LDecs() {
 		   super();
        }
     }
    
    public static class SiDecs extends Decs {
    	private LDecs decs; 
       	public SiDecs(LDecs decs) {
       		this.decs = decs;
       	}
       	public LDecs ldecs() {return decs;}
		@Override
		public void imprime() {
			decs.imprime();
			System.out.println();
			System.out.println("&&");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    public static class NoDecs extends Decs {
    	public NoDecs() {
    		super();
    	}
		@Override
		public void imprime() {} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
     	
    public static class MuchasDecs extends LDecs {
    	private LDecs decs;
        private Dec dec;
        public MuchasDecs(LDecs decs, Dec dec) {
        	this.dec = dec;
        	this.decs = decs;
        }
		@Override
		public void imprime() {
			decs.imprime();
			System.out.println(";");
			dec.imprime();
		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class UnaDec extends LDecs {
        private Dec dec;
        public UnaDec(Dec dec) {
           this.dec = dec;
        }
        public String toString() {
            return dec.toString();
        }
		@Override
		public void imprime() {
			dec.imprime();
		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static abstract class ParamForm extends Nodo {
    	public ParamForm() {
    	}
    }

    public static abstract class Param extends Nodo {
    	public Param() {
    	}
    }

    public static abstract class LParam extends Nodo {
    	public LParam() {
    	}
    }

    public static class SiParam extends ParamForm {
     	LParam params;
     	public SiParam(LParam params) {
     		this.params = params;
        } 
        public LParam lparam() { return params; }
		@Override
		public void imprime() {
			System.out.print("(");
			params.imprime();
			System.out.print(")");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class NoParam extends ParamForm {
     	public NoParam() {
        }
		@Override
		public void imprime() {
			System.out.print("()");
		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class UnParam extends LParam {
     	Param param;
     	public UnParam(Param param) {
     		this.param = param;
        }
        public Param param() {return param;}
		@Override
		public void imprime() {
			param.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class MuchosParams extends LParam {
     	LParam params;
     	Param param;
     	public MuchosParams(LParam params, Param param) {
     		this.params = params;
     		this.param = param;
        }
        public LParam lparam() {return params;}
        public Param param() {return param;}
		@Override
		public void imprime() {
			params.imprime();
			System.out.print(", ");
			param.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static abstract class Tipo extends Nodo {
     	public Tipo() {
     	}
    }
     
    public static class TArray extends Tipo {
        private String litEnt;
        private Tipo t;
        public TArray(Tipo t, String litEnt) {
        	super();
        	this.t = t;
        	this.litEnt = litEnt;
        }
        public String num() {return litEnt;}
        public Tipo tipo() {return t;}
		@Override
		public void imprime() {
			t.imprime();
			System.out.print("["+litEnt+"]");
		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class TInt extends Tipo {
     	public TInt() {
     	}
 		@Override
 		public void imprime() {
 			System.out.print("<int>");
 		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class TReal extends Tipo {
     	public TReal() {
     	}
 		@Override
 		public void imprime() {
 			System.out.print("<real>");
 		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class TBool extends Tipo {
     	public TBool() {
     	}
 		@Override
 		public void imprime() {
 			System.out.print("<bool>");
 		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class TString extends Tipo {
     	public TString() {
     	}
 		@Override
 		public void imprime() {
 			System.out.print("<string>");
 		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class TIden extends Tipo {
     	String iden;
     	public TIden(String iden) {
     		this.iden = iden;
     	}
 		@Override
 		public void imprime() {
 			System.out.print(iden);
 		} 
        public String iden() {return iden;}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class TStruct extends Tipo {
    	LCampos campos;
    	public TStruct(LCampos campos) {
    		this.campos = campos;
    	}
 		@Override
 		public void imprime() {
 			System.out.println("<struct> {");
 			campos.imprime();
 			System.out.print("\n}");
 		} 
        public LCampos lcampos() {return campos;}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
     
    public static abstract class Instr extends Nodo {
        public Instr() {
        }
     }
      
    public static abstract class LInstrs extends Nodo {
        public LInstrs() {
   		   	super();
        }
    }
      
    public static class SiInstrs extends Instr {
        private LInstrs instrs; 
        public SiInstrs(LInstrs instrs) {
            super();
            this.instrs = instrs;
        }   
        public String toString() {
            return instrs.toString();
        } 
    }
    public static class NoInstrs extends Instr {
        public NoInstrs() {
            super();
        }   
        public String toString() {
            return "";
        } 
    }
       	
    public static class MuchasInstrs extends LInstrs {
    	private LInstrs instrs;
        private Instr instr;
        public MuchasInstrs(LInstrs instrs, Instr instr) {
            super();
            this.instrs = instrs;
            this.instr = instr;
        }
        public String toString() {
       	    String s = instrs.toString();
       	    s += ";";
       	   s += "\n";
       	   s += instr.toString();
       	   return s;
        } 
    }

    public static class UnaInstr extends LInstrs {
       	private Instr instr;
    	public UnaInstr(Instr instr) {
    	   	super();
    	   	this.instr = instr;
    	}
    	public String toString() {
    	   	return instr.toString();
        } 
    }

    public static abstract class ParamReales extends Nodo {
       	public ParamReales() {
       	}
    }

    public static abstract class LExp extends Nodo {
       	public LExp() {
       	}
    }

    public static abstract class Exp  extends  Nodo {
        public Exp() {
    		super();
        }
        public void imprimeOpnd(Exp opnd, int minPrior) {
            if(opnd.prioridad() < minPrior){
            	System.out.print("(");
            }
            opnd.imprime();
            if(opnd.prioridad() < minPrior) {
              	System.out.print(")");
            }
        }
        public abstract int prioridad();
    }

   	public static class SiExp extends ParamReales {
   		private LExp exps;
	   	public SiExp(LExp exps) {
	   		this.exps = exps;
	   	}
	   	public LExp lexp() {return exps;}
		@Override
		public void imprime() {
			System.out.print("(");
			exps.imprime();
			System.out.print(")");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
   	}

   	public static class NoExp extends ParamReales {
	   	public NoExp() {
	   	}
		@Override
		public void imprime() {
			System.out.print("()");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
   	}

   	public static class UnaExp extends LExp {
   		Exp exp;
	   	public UnaExp(Exp exp) {
	   		this.exp = exp;
	   	} 
	   	public Exp exp() {return exp;}
		@Override
		public void imprime() {
			exp.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
   	}

   	public static class MuchasExp extends LExp {
   		LExp exps;
   		Exp exp;
	   	public MuchasExp(LExp exps, Exp exp) {
	   		this.exp = exp;
	   	} 
	   	public Exp exp() {return exp;}
	   	public LExp lexp() {return exps;}
		@Override
		public void imprime() {
			exps.imprime();
			System.out.print(", ");
			exp.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
   	}
    
    private static abstract class ExpBin extends Exp {
        Exp opnd0;
        Exp opnd1;
        public ExpBin(Exp opnd0, Exp opnd1) {
            super();
            this.opnd0 = opnd0;
            this.opnd1 = opnd1;
        }
        public void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1) {
        	imprimeOpnd(opnd0,np0);
        	System.out.print(" " + op + " ");
        	imprimeOpnd(opnd1,np1);
        }
        public Exp opnd0() {return opnd0;}
        public Exp opnd1() {return opnd1;}
    }
    
    private static abstract class ExpUn extends Exp {
        Exp opnd;
        public ExpUn(Exp opnd) {
            super();
            this.opnd = opnd;
        }
        public Exp opnd() {return opnd;}
    }
    
    public static class Asignacion extends ExpBin {
        public Asignacion(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return imprimeExpBin(opnd0,"=",opnd1,1,0);
        }
        @Override
        public int prioridad(){
        	return 0;
        }
    }
    
    public static class Menor extends ExpBin {
        public Menor(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return imprimeExpBin(opnd0,"<",opnd1,1,2);
        }
        @Override
        public int prioridad(){
        	return 1;
        }
    }
    
    public static class Mayor extends ExpBin {
        public Mayor(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return imprimeExpBin(opnd0,">",opnd1,1,2);
        }
        @Override
        public int prioridad(){
        	return 1;
        }
    }
    
    public static class MenorIgual extends ExpBin {
        public MenorIgual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return imprimeExpBin(opnd0,"<=",opnd1,1,2);
        }
        @Override
        public int prioridad(){
        	return 1;
        }
    }
    
    public static class MayorIgual extends ExpBin {
        public MayorIgual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return imprimeExpBin(opnd0,">=",opnd1,1,2);
        }
        @Override
        public int prioridad(){
        	return 1;
        }
    }
    
    public static class Igual extends ExpBin {
        public Igual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return imprimeExpBin(opnd0,"==",opnd1,1,2);
        }
        @Override
        public int prioridad(){
        	return 1;
        }
    }
    
    public static class Desigual extends ExpBin {
        public Desigual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return imprimeExpBin(opnd0,"!=",opnd1,1,2);
        }
        @Override
        public int prioridad(){
        	return 1;
        }
    }
    
    public static class Suma extends ExpBin {
        public Suma(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        @Override
        public int prioridad(){
        	return 2;
        }
		@Override
		public void imprime() {
			imprimeExpBin(opnd0,"+",opnd1,2,3);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Resta extends ExpBin {
        public Resta(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        @Override
        public int prioridad(){
        	return 2;
        }
		@Override
		public void imprime() {
			imprimeExpBin(opnd0,"-",opnd1,3,3);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Array extends ExpUn {
    	private Exp idx;
        public Array(Exp opnd, Exp idx) {
        	super(opnd);
            this.opnd = opnd;
            this.idx = idx;
        }
        @Override
        public int prioridad(){
        	return 6;
        }
        public Exp idx() {return idx;}
		@Override
		public void imprime() {
			imprimeOpnd(opnd, 6);
        	System.out.print("[");
        	imprimeOpnd(idx, 0);
        	System.out.print("]");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class ExpCampo extends ExpUn {
    	private String campo;
        public ExpCampo(Exp opnd, String campo) {
        	super(opnd);
            this.opnd = opnd;
            this.campo = campo;
        }
        @Override
        public int prioridad(){
        	return 6;
        }
        public String campo() {return campo;}
		@Override
		public void imprime() {
			imprimeOpnd(opnd, 6);
			System.out.print("."+campo);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Punt extends ExpUn {
        public Punt(Exp opnd) {
        	super(opnd);
            this.opnd = opnd;
        }
        @Override
        public int prioridad(){
        	return 6;
        }
		@Override
		public void imprime() {
			imprimeOpnd(opnd, 6);
			System.out.print("^");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    //constructoras
    public Decs siDecs(LDecs decs) {
        return new SiDecs(decs);
    }
    
    public Decs noDecs() {
        return new NoDecs();
    }
    
    public LDecs muchasDecs(LDecs decs, Dec dec) {
        return new MuchasDecs(decs,dec);
    }
    
    public LDecs unaDec(Dec dec) {
        return new UnaDec(dec);
    }
    
    public ParamForm siParam(LParam params) {
    	return new SiParam(params);
    }
    
    public ParamForm noParam() {
    	return new NoParam();
    }
    
    public LParam unParam(Param param) {
    	return new UnParam(param);
    }
    
    public LParam muchosParams(LParam params, Param param) {
    	return new MuchosParams(params, param);
    }
    
    public Tipo tArray(Tipo t, String litEnt) {
        return new TArray(t, litEnt);
    }
    
    public Tipo tInt() {
        return new TInt();
    }
    
    public Tipo tReal() {
        return new TReal();
    }
    
    public Tipo tBool() {
        return new TBool();
    }
    
    public Tipo tString() {
        return new TString();
    }
    
    public Tipo tIden(String iden) {
        return new TIden(iden);
    }
    
    public Tipo tStruct(LCampos campos) {
        return new TStruct(campos);
    }
    
    public Instr siInstrs(LInstrs instrs) {
        return new SiInstrs(instrs);
    }
    
    public Instr noInstrs() {
        return new NoInstrs();
    }
    
    public LInstrs muchasInstrs(LInstrs instrs, Instr instr) {
        return new MuchasInstrs(instrs, instr);
    }
    
    public LInstrs unaInstr(Instr instr) {
        return new UnaInstr(instr);
    }    
    
    public ParamReales siExp(LExp exp) {
        return new SiExp(exp);
    }
    
    public ParamReales noExp() {
        return new NoExp();
    }
    
    public LExp muchasExp(LExp exps, Exp exp) {
        return new MuchasExp(exps, exp);
    }
    
    public LExp unaExp(Exp exp) {
        return new UnaExp(exp);
    }    
    
    public Exp asignacion(Exp opnd0, Exp opnd1) {
    	return new Asignacion(opnd0,opnd1);
    }   
       
    public Exp menor(Exp opnd0, Exp opnd1) {
        return new Menor(opnd0,opnd1);
    }
    
    public Exp mayor(Exp opnd0, Exp opnd1) {
        return new Mayor(opnd0,opnd1);
    }
    
    public Exp menorIgual(Exp opnd0, Exp opnd1) {
        return new MenorIgual(opnd0,opnd1);
    }
    
    public Exp mayorIgual(Exp opnd0, Exp opnd1) {
        return new MayorIgual(opnd0,opnd1);
    }
    
    public Exp igual(Exp opnd0, Exp opnd1) {
        return new Igual(opnd0,opnd1);
    }
    
    public Exp desigual(Exp opnd0, Exp opnd1) {
        return new Desigual(opnd0,opnd1);
    }
    
    public Exp suma(Exp opnd0, Exp opnd1) {
        return new Suma(opnd0,opnd1);
    }
    
    public Exp resta(Exp opnd0, Exp opnd1) {
        return new Resta(opnd0,opnd1);
    }
    
    public Exp array(Exp opnd0, Exp opnd1) {
        return new Array(opnd0,opnd1);
    }
    
    public Exp expCampo(Exp opnd0, String campo) {
        return new ExpCampo(opnd0,campo);
    }
    
    public Exp punt(Exp opnd) {
        return new Punt(opnd);
    }
}
