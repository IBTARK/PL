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
    
    public static class Bloque extends Nodo {
    	private Decs decs;
    	private Instr instr;
    	
    	public Bloque(Decs decs, Instr instr) {
    		this.decs = decs;
    		this.instr = instr;
    	}
    	
    	@Override
		public void imprime() {
    		System.out.println("{");
			decs.imprime();
			instr.imprime();
			System.out.println();
			System.out.println("{");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
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
        public LDecs ldecs() {return decs;}
        public Dec dec() {return dec;}
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
        public Dec dec() {return dec;}
		@Override
		public void imprime() {
			dec.imprime();
		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static abstract class Dec extends Nodo {
    	public Dec() {
    	}
    }
    
    public static class DecProg extends Dec {
    	private String iden;
    	private ParamForm param;
    	private Bloque bloc;
        public DecProg(String iden, ParamForm param, Bloque bloc) {
           this.iden = iden;
           this.param = param;
           this.bloc = bloc;
        }
		@Override
		public void imprime() {
			System.out.println("<prog> ");
			System.out.println(iden);
			System.out.println("(");
			param.imprime();
			System.out.println(")"); 
			bloc.imprime();
		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		public String iden() {return iden;}
		public ParamForm param() {return param;} 
		public Bloque bloc() {return bloc;}
    }

    
    public static class DecType extends Dec {
    	private Tipo tipo;
    	private String iden;
        public DecType(Tipo tipo, String iden) {
           this.tipo = tipo;
           this.iden = iden;
        }
		@Override
		public void imprime() {
			System.out.println("<type> ");
			tipo.imprime();
			System.out.println(" "); 
			System.out.println(iden);
		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public String iden() {return iden;} 
		public Tipo tipo() {return tipo;}
    }

    public static class DecVar extends Dec {
    	private Tipo tipo;
    	private String iden;
        public DecVar(Tipo tipo, String iden) {
           this.tipo = tipo;
           this.iden = iden;
        }
		@Override
		public void imprime() {
			tipo.imprime();
			System.out.println(" "); 
			System.out.println(iden);
		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public String iden() {return iden;} 
		public Tipo tipo() {return tipo;}
    }
    
    public static abstract class ParamForm extends Nodo {
    	public ParamForm() {
    	}
    }
    
    public static class ParamFormal extends ParamForm {
    	private Tipo t;
    	private String id;
    	
    	public ParamFormal(Tipo t, String id) {
    		this.t = t;
    		this.id = id;
    	}
    	@Override
		public void imprime() {
			t.imprime();
			System.out.println(" " + id);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class ParamFormRef extends ParamForm {
    	private Tipo t;
    	private String id;
    	
    	public ParamFormRef(Tipo t, String id) {
    		this.t = t;
    		this.id = id;
    	}
    	
    	@Override
		public void imprime() {
			t.imprime();
			System.out.print(" & ");
			System.out.println(" " + id);
		}
    	
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
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
    
    public static class TPunt extends Tipo {
        private Tipo t;
        public TPunt(Tipo t) {
        	super();
        	this.t = t;
        }
        public Tipo tipo() {return t;}
		@Override
		public void imprime() {
			System.out.print("^ ");
			t.imprime();
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
    
    public static class LCampos extends Nodo{ //TODO

		@Override
		public void imprime() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void procesa(Procesamiento p) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    public static class MuchosCamps extends LCampos{
    	private LCampos lcampos;
    	private Campo c;
    	
    	public MuchosCamps(LCampos lcampos, Campo c) {
    		this.lcampos = lcampos;
    		this.c = c;
    	}
    	
    	@Override
 		public void imprime() {
 			lcampos.imprime();
 			System.out.println(", ");
 			c.imprime();
 		} 
    	
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class UnCamp extends LCampos{
    	private Campo c;
    	
    	public UnCamp(Campo c) {
    		this.c = c;
    	}
    	
    	@Override
 		public void imprime() {
 			c.imprime();
 		} 
    	
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Campo extends Nodo{
        private Tipo t;
        private String id;
        
        public Campo(Tipo t, String id){
            this.t = t;
            this.id = id;
        }
        
        @Override
 		public void imprime() {
 			t.imprime();
 			System.out.println(" " + id);
 		} 
 		
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
    
    public static class ArrobaInstr  extends  Instr {
    	private Exp exp;
        public ArrobaInstr(Exp exp) {
    		this.exp = exp;
        }
        public void imprime() {
			System.out.print("@");
			exp.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		public Exp exp() {return exp;} 
    }
    
    public static class ProcInstr  extends  Instr {
    	private ParamReales param;
    	private String iden;
    	
        public ProcInstr(ParamReales param, String iden) {
    		this.param = param;
    		this.iden = iden;
        }
        public void imprime() {
			System.out.print("<call> ");
			System.out.print(iden);
			param.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		public String iden() {return iden;} 
		public ParamReales paramReales() {return param;}
    }
    
    public static class NlInstr  extends  Instr {

        public NlInstr() {
        }
        public void imprime() {
			System.out.print("<nl>");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class NewInstr  extends  Instr {
    	private Exp exp;
        public NewInstr(Exp exp) {
    		this.exp = exp;
        }
        public void imprime() {
			System.out.print("<new> ");
			exp.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Exp exp() {return exp;} 
    }
    
    public static class ReadInstr  extends  Instr {
    	private Exp exp;
        public ReadInstr(Exp exp) {
    		this.exp = exp;
        }
        public void imprime() {
			System.out.print("<read> ");
			exp.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Exp exp() {return exp;} 
    }
    
    public static class WriteInstr  extends  Instr {
    	private Exp exp;
        public WriteInstr(Exp exp) {
    		this.exp = exp;
        }
        public void imprime() {
			System.out.print("<write> ");
			exp.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Exp exp() {return exp;} 
    }
    
    public static class DeleteInstr  extends  Instr {
    	private Exp exp;
        public DeleteInstr(Exp exp) {
    		this.exp = exp;
        }
        public void imprime() {
			System.out.print("<delete> ");
			exp.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Exp exp() {return exp;} 
    }
    
    public static class WhileInstr  extends  Instr {
    	private Exp exp;
    	private Bloque bloq;
        public WhileInstr(Exp exp, Bloque bloq) {
    		this.exp = exp;
    		this.bloq = bloq;
        }
        public void imprime() {
			System.out.print("<while> ");
			exp.imprime();
			System.out.print(" ");
			bloq.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Exp exp() {return exp;} 
		public Bloque bloq() {return bloq;} 
    }
    
    public static class IfElseInstr  extends  Instr {
    	private Exp exp;
    	private Bloque bloq1;
    	private Bloque bloq2;
        public IfElseInstr(Exp exp, Bloque bloq1, Bloque bloq2) {
    		this.exp = exp;
    		this.bloq1 = bloq1;
    		this.bloq2 = bloq2;
        }
        public void imprime() {
			System.out.print("<if> ");
			exp.imprime();
			System.out.print(" ");
			bloq1.imprime();
			System.out.println();
			System.out.print("<else> ");
			bloq2.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Exp exp() {return exp;} 
		public Bloque bloq1() {return bloq1;} 
		public Bloque bloq2() {return bloq2;}
    }
    
    public static class IfInstr extends Instr {
    	private Exp exp;
    	private Bloque bloq;
        public IfInstr(Exp exp, Bloque bloq) {
    		this.exp = exp;
    		this.bloq = bloq;
        }
        public void imprime() {
			System.out.print("<if> ");
			exp.imprime();
			System.out.print(" ");
			bloq.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Exp exp() {return exp;} 
		public Bloque bloq() {return bloq;} 
    }
    
    public static class BloqueInstr  extends  Instr {
    	private Bloque bloq;
        public BloqueInstr(Bloque bloq) {
    		this.bloq = bloq;
        }
        public void imprime() {
        	bloq.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Bloque bloq() {return bloq;} 
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
            //return imprimeExpBin(opnd0,"=",opnd1,1,0);
        	return "Aignacion(" + opnd0 + ", " + opnd1 + ")";
        }
        @Override
        public int prioridad(){
        	return 0;
        }
        @Override
		public void imprime() {
			imprimeExpBin(opnd0,"=",opnd1,1,0);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class And extends ExpBin{
    	public And(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            //return imprimeExpBin(opnd0,"=",opnd1,1,0);
        	return "And(" + opnd0 + ", " + opnd1 + ")";
        }
        @Override
        public int prioridad(){
        	return 3;
        }
        @Override
		public void imprime() {
			imprimeExpBin(opnd0,"and",opnd1,4,3);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Or extends ExpBin{
    	public Or(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            //return imprimeExpBin(opnd0,"=",opnd1,1,0);
        	return "Or(" + opnd0 + ", " + opnd1 + ")";
        }
        @Override
        public int prioridad(){
        	return 3;
        }
        @Override
		public void imprime() {
			imprimeExpBin(opnd0,"or",opnd1,4,4);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Menor extends ExpBin {
        public Menor(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            //return imprimeExpBin(opnd0,"<",opnd1,1,2);
        	return "Menor(" + opnd0 + ", " + opnd1 + ")";
        }
        @Override
        public int prioridad(){
        	return 1;
        }
        @Override
		public void imprime() {
			imprimeExpBin(opnd0,"or",opnd1,1,2);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Mayor extends ExpBin {
        public Mayor(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
      
        public String toString() {
            //return imprimeExpBin(opnd0,"<",opnd1,1,2);
        	return "Mayor(" + opnd0 + ", " + opnd1 + ")";
        }
        @Override
        public int prioridad(){
        	return 1;
        }
        @Override
		public void imprime() {
			imprimeExpBin(opnd0,"or",opnd1,1,2);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class MenorIgual extends ExpBin {
        public MenorIgual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "MenorIgual(" + opnd0 + ", " + opnd1 + ")";
        }
        @Override
        public int prioridad(){
        	return 1;
        }
        @Override
		public void imprime() {
			imprimeExpBin(opnd0,"<=",opnd1,1,2);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class MayorIgual extends ExpBin {
        public MayorIgual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        
        public String toString() {
            //return imprimeExpBin(opnd0,"<",opnd1,1,2);
        	return "MayorIgual(" + opnd0 + ", " + opnd1 + ")";
        }
        @Override
        public int prioridad(){
        	return 1;
        }
        @Override
		public void imprime() {
			imprimeExpBin(opnd0,">=",opnd1,1,2);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Igual extends ExpBin {
        public Igual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "Igual(" + opnd0 + ", " + opnd1 + ")";
        }
        @Override
        public int prioridad(){
        	return 1;
        }
        @Override
		public void imprime() {
			imprimeExpBin(opnd0,"=",opnd1,1,2);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Desigual extends ExpBin {
        public Desigual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        
        public String toString() {
            //return imprimeExpBin(opnd0,"<",opnd1,1,2);
        	return "Desigual(" + opnd0 + ", " + opnd1 + ")";
        }
        @Override
        public int prioridad(){
        	return 1;
        }
        @Override
		public void imprime() {
			imprimeExpBin(opnd0,"!=",opnd1,1,2);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Suma extends ExpBin {
        public Suma(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            //return imprimeExpBin(opnd0,"<",opnd1,1,2);
        	return "Suma(" + opnd0 + ", " + opnd1 + ")";
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
        
        public String toString() {
            //return imprimeExpBin(opnd0,"<",opnd1,1,2);
        	return "Resta(" + opnd0 + ", " + opnd1 + ")";
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
    
    public static class Mul extends ExpBin {
        public Mul(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        
        public String toString() {
            //return imprimeExpBin(opnd0,"<",opnd1,1,2);
        	return "Mul(" + opnd0 + ", " + opnd1 + ")";
        }
        @Override
        public int prioridad(){
        	return 4;
        }
		@Override
		public void imprime() {
			imprimeExpBin(opnd0,"*",opnd1,4,5);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Div extends ExpBin {
        public Div(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        
        public String toString() {
            //return imprimeExpBin(opnd0,"<",opnd1,1,2);
        	return "Div(" + opnd0 + ", " + opnd1 + ")";
        }
        
        @Override
        public int prioridad(){
        	return 4;
        }
		@Override
		public void imprime() {
			imprimeExpBin(opnd0,"/",opnd1,4,5);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Mod extends ExpBin {
        public Mod(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        
        public String toString() {
            //return imprimeExpBin(opnd0,"<",opnd1,1,2);
        	return "Mod(" + opnd0 + ", " + opnd1 + ")";
        }
        @Override
        public int prioridad(){
        	return 4;
        }
		@Override
		public void imprime() {
			imprimeExpBin(opnd0,"%",opnd1,4,5);
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
    
    public static class LitEnt extends ExpUn{
    	public LitEnt(Exp opnd) {
        	super(opnd);
            this.opnd = opnd;
        }
        @Override
        public int prioridad(){
        	return 7;
        }
		@Override
		public void imprime() {
			System.out.println(opnd);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class LitReal extends ExpUn{
    	public LitReal(Exp opnd) {
        	super(opnd);
            this.opnd = opnd;
        }
        @Override
        public int prioridad(){
        	return 7;
        }
		@Override
		public void imprime() {
			System.out.println(opnd);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Iden extends ExpUn{
    	public Iden(Exp opnd) {
        	super(opnd);
            this.opnd = opnd;
        }
        @Override
        public int prioridad(){
        	return 7;
        }
		@Override
		public void imprime() {
			System.out.println(opnd);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class True extends Exp{
    	public True() {

        }
        @Override
        public int prioridad(){
        	return 7;
        }
		@Override
		public void imprime() {
			System.out.println("<true>");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class False extends Exp{
    	public False() {

        }
        @Override
        public int prioridad(){
        	return 7;
        }
		@Override
		public void imprime() {
			System.out.println("<false>");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class LitCad extends ExpUn{
    	public LitCad(Exp opnd) {
        	super(opnd);
            this.opnd = opnd;
        }
        @Override
        public int prioridad(){
        	return 7;
        }
		@Override
		public void imprime() {
			System.out.println(opnd);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Null extends Exp{
    	public Null() {

        }
        @Override
        public int prioridad(){
        	return 7;
        }
		@Override
		public void imprime() {
			System.out.println("<null>");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    //constructoras
    public Bloque bloq(Decs decs, Instr instr) {
    	return new Bloque(decs, instr);
    }
    
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
    
    public ParamForm paramForm(Tipo t, String id) {
    	return new ParamFormal(t, id);
    }
    
    public ParamForm paramFormRef(Tipo t, String id) {
    	return new ParamFormRef(t, id);
    }
    
    public Tipo tArray(Tipo t, String litEnt) {
        return new TArray(t, litEnt);
    }
    
    public Tipo tPunt(Tipo t) {
        return new TPunt(t);
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
    
    public LCampos muchosCamps(LCampos lcampos, Campo c) {
    	return new MuchosCamps(lcampos, c);
    }
    
    public LCampos unCamp(Campo c) {
    	return new UnCamp(c);
    }
    
    public Campo campo(Tipo t, String id) {
    	return new Campo(t, id);
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
    
    public Instr arrobaInstr(Exp exp) {
        return new ArrobaInstr(exp);
    }
    
    public Instr procInstr(ParamReales param, String iden) {
        return new ProcInstr(param, iden);
    }
    
    public Instr nlInstr() {
        return new NlInstr();
    }
    
    public Instr newInstr(Exp exp) {
        return new NewInstr(exp);
    }
    
    public Instr readInstr(Exp exp) {
        return new ReadInstr(exp);
    }
    
    public Instr writeInstr(Exp exp) {
        return new WriteInstr(exp);
    }
    
    public Instr deleteInstr(Exp exp) {
        return new DeleteInstr(exp);
    }
    
    public Instr whileInstr(Exp exp, Bloque bloq) {
        return new WhileInstr(exp, bloq);
    }
    
    public Instr ifElseInstr(Exp exp, Bloque bloq1, Bloque bloq2) {
        return new IfElseInstr(exp, bloq1, bloq2);
    }
    
    public Instr ifInstr(Exp exp, Bloque bloq) {
        return new IfInstr(exp, bloq);
    }
    
    public Instr bloqueInstr(Bloque bloq) {
        return new BloqueInstr(bloq);
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
    
    public And and(Exp opnd0, Exp opnd1) {
        return new And(opnd0,opnd1);
    }
    
    public Or or(Exp opnd0, Exp opnd1) {
        return new Or(opnd0,opnd1);
    }
    
    public Exp resta(Exp opnd0, Exp opnd1) {
        return new Resta(opnd0,opnd1);
    }
    
    public Exp mul(Exp opnd0, Exp opnd1) {
        return new Mul(opnd0,opnd1);
    }
    
    public Exp div(Exp opnd0, Exp opnd1) {
        return new Div(opnd0,opnd1);
    }
    
    public Exp mod(Exp opnd0, Exp opnd1) {
        return new Mod(opnd0,opnd1);
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
    
    public LitEnt litEnt(Exp opnd){
    	return new LitEnt(opnd);
    }
    
    public LitReal litReal(Exp opnd){
    	return new LitReal(opnd);
    }
    
    public Iden iden(Exp opnd){
    	return new Iden(opnd);
    }
    
    public True _true(){
    	return new True();
    }
    
    public False _false(){
    	return new False();
    }
    
    public LitCad litCad(Exp opnd){
    	return new LitCad(opnd);
    }
    
    public Null _null(){
    	return new Null();
    }
}
