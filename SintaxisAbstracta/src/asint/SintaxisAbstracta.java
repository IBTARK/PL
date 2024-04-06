package asint;

import c_ast_descendente.Token;

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
    
    public static class Prog extends Nodo {
    	private Bloque bloq;
    	
    	public Prog(Bloque bloq) {
    		this.bloq = bloq;
    	}
    	public Bloque bloq() { return bloq; }
    	@Override
		public void imprime() {
			bloq.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Bloque extends Nodo {
    	private Decs decs;
    	private Instrs instrs;
    	
    	public Bloque(Decs decs, Instrs instrs) {
    		this.decs = decs;
    		this.instrs = instrs;
    	}
    	public Decs decs() { return decs; }
    	public Instrs instrs() { return instrs; }
    	@Override
		public void imprime() {
    		System.out.println("{");
			decs.imprime();
			instrs.imprime();
			System.out.println("}");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
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
    	private LDecs ldecs; 
       	public SiDecs(LDecs ldecs) {
       		this.ldecs = ldecs;
       	}
       	public LDecs ldecs() {return ldecs;}
		@Override
		public void imprime() {
			ldecs.imprime();
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
    	private LDecs ldecs;
        private Dec dec;
        public MuchasDecs(LDecs ldecs, Dec dec) {
        	this.dec = dec;
        	this.ldecs = ldecs;
        }
        public LDecs ldecs() {return ldecs;}
        public Dec dec() {return dec;}
		@Override
		public void imprime() {
			ldecs.imprime();
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
    
    public static abstract class ParamForms extends Nodo {
    	public ParamForms() {
    	}
    }

    public static abstract class LParams extends Nodo {
    	public LParams() {
    	}
    }
    
    public static abstract class ParamForm extends Nodo {
    	public ParamForm() {
    	}
    }
    
    public static class DecProc extends Dec {
    	private Token iden;
    	private ParamForms params;
    	private Bloque bloq;
        public DecProc(Token id, ParamForms params, Bloque bloq) {
           this.iden = id;
           this.params = params;
           this.bloq = bloq;
        }
		@Override
		public void imprime() {
			System.out.println("<proc>");
			System.out.println(iden);
			params.imprime();
			System.out.print(" ");
			bloq.imprime();
		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		public Token iden() {return iden;}
		public ParamForms params() {return params;} 
		public Bloque bloq() {return bloq;}
    }

    
    public static class DecType extends Dec {
    	private Tipo tipo;
    	private Token iden;
        public DecType(Tipo tipo, Token id) {
           this.tipo = tipo;
           this.iden = id;
        }
		@Override
		public void imprime() {
			System.out.println("<type>");
			tipo.imprime();
			System.out.print(" "); 
			System.out.println(iden);
		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Token iden() {return iden;} 
		public Tipo tipo() {return tipo;}
    }

    public static class DecVar extends Dec {
    	private Tipo tipo;
    	private Token iden;
        public DecVar(Tipo tipo, Token id) {
           this.tipo = tipo;
           this.iden = id;
        }
		@Override
		public void imprime() {
			tipo.imprime();
			System.out.print(" "); 
			System.out.println(iden);
		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Token iden() {return iden;} 
		public Tipo tipo() {return tipo;}
    }
    
    public static class ParamFormal extends ParamForm {
    	private Tipo t;
    	private Token id;
    	
    	public ParamFormal(Tipo t, Token id2) {
    		this.t = t;
    		this.id = id2;
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
		public Token id() {return id;} 
		public Tipo tipo() {return t;}
    }
    
    public static class ParamFormRef extends ParamForm {
    	private Tipo t;
    	private Token id;
    	
    	public ParamFormRef(Tipo t, Token id2) {
    		this.t = t;
    		this.id = id2;
    	}
    	
    	@Override
		public void imprime() {
			t.imprime();
			System.out.print("&");
			System.out.println(id);
		}
    	
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Token id() {return id;} 
		public Tipo tipo() {return t;}
    }

    public static class SiParam extends ParamForms {
     	LParams lparams;
     	public SiParam(LParams lparams) {
     		this.lparams = lparams;
        } 
        public LParams lparams() { return lparams; }
		@Override
		public void imprime() {
			System.out.print("(");
			lparams.imprime();
			System.out.println(")");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class NoParam extends ParamForms {
     	public NoParam() {
        }
		@Override
		public void imprime() {
			System.out.println("()");
		} 
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class UnParam extends LParams {
     	ParamForm param;
     	public UnParam(ParamForm param) {
     		this.param = param;
        }
        public ParamForm param() {return param;}
		@Override
		public void imprime() {
			param.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class MuchosParams extends LParams {
     	LParams lparams;
     	ParamForm param;
     	public MuchosParams(LParams lparams, ParamForm param) {
     		this.lparams = lparams;
     		this.param = param;
        }
        public LParams lparam() {return lparams;}
        public ParamForm param() {return param;}
		@Override
		public void imprime() {
			lparams.imprime();
			System.out.print(",");
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
        private Token litEnt;
        private Tipo t;
        public TArray(Tipo t, Token litEnt2) {
        	super();
        	this.t = t;
        	this.litEnt = litEnt2;
        }
        public Token litEnt() {return litEnt;}
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
    	Token iden;
     	public TIden(Token id) {
     		this.iden = id;
     	}
 		@Override
 		public void imprime() {
 			System.out.print(iden);
 		} 
        public Token iden() {return iden;}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class TStruct extends Tipo {
    	LCampos lcampos;
    	public TStruct(LCampos lcampos) {
    		this.lcampos = lcampos;
    	}
 		@Override
 		public void imprime() {
 			System.out.println("<struct> {");
 			lcampos.imprime();
 			System.out.print("\n}");
 		} 
        public LCampos lcampos() {return lcampos;}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static abstract class LCampos extends Nodo {
    	public LCampos() {
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
		public LCampos lcampos() {return lcampos;}
		public Campo campo() {return c;}
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
		public Campo campo() {return c;}
    }
    
    public static class Campo extends Nodo {
        private Tipo t;
        private Token id;
        
        public Campo(Tipo t, Token id2){
            this.t = t;
            this.id = id2;
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
		public Tipo tipo() {return t;}
		public Token iden() {return id;}
    }
     
    public static abstract class Instrs extends Nodo {
        public Instrs() {
        }
    }
      
    public static abstract class LInstrs extends Nodo {
        public LInstrs() {
   		   	super();
        }
    }
    
    public static abstract class Instr extends Nodo {
        public Instr() {
        }
     }
      
    public static class SiInstrs extends Instrs {
        private LInstrs linstrs; 
        public SiInstrs(LInstrs linstrs) {
            super();
            this.linstrs = linstrs;
        }
        public LInstrs linstrs() { return linstrs; };
		@Override
		public void imprime() {
			linstrs.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class NoInstrs extends Instrs {
        public NoInstrs() {
            super();
        }
		@Override
		public void imprime() {
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
       	
    public static class MuchasInstrs extends LInstrs {
    	private LInstrs linstrs;
        private Instr instr;
        public MuchasInstrs(LInstrs linstrs, Instr instr) {
            super();
            this.linstrs = linstrs;
            this.instr = instr;
        }
        public LInstrs linstrs() { return linstrs; }
        public Instr instr() { return instr; }
		@Override
		public void imprime() {
			linstrs.imprime();
 			System.out.println("; ");
 			instr.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }

    public static class UnaInstr extends LInstrs {
       	private Instr instr;
    	public UnaInstr(Instr instr) {
    	   	super();
    	   	this.instr = instr;
    	}
    	public Instr instr() { return instr; }
		@Override
		public void imprime() {
			instr.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
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
    
    public static class ProcInstr extends Instr {
    	private ParamReales params;
    	private Token iden;
    	
        public ProcInstr(ParamReales params, Token iden) {
    		this.params = params;
    		this.iden = iden;
        }
        public void imprime() {
			System.out.print("<call>");
			System.out.print(iden);
			params.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		public Token iden() {return iden;} 
		public ParamReales paramReales() {return params;}
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
    
    public static class NewInstr extends Instr {
    	private Exp exp;
        public NewInstr(Exp exp) {
    		this.exp = exp;
        }
        public void imprime() {
			System.out.print("<new>");
			exp.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Exp exp() {return exp;} 
    }
    
    public static class ReadInstr extends Instr {
    	private Exp exp;
        public ReadInstr(Exp exp) {
    		this.exp = exp;
        }
        public void imprime() {
			System.out.print("<read>");
			exp.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Exp exp() {return exp;} 
    }
    
    public static class WriteInstr extends Instr {
    	private Exp exp;
        public WriteInstr(Exp exp) {
    		this.exp = exp;
        }
        public void imprime() {
			System.out.print("<write>");
			exp.imprime();
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
		public Exp exp() {return exp;} 
    }
    
    public static class DeleteInstr extends Instr {
    	private Exp exp;
        public DeleteInstr(Exp exp) {
    		this.exp = exp;
        }
        public void imprime() {
			System.out.print("<delete>");
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
			System.out.print("<while>");
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
			System.out.print("<if>");
			exp.imprime();
			System.out.print(" ");
			bloq1.imprime();
			System.out.println();
			System.out.print("<else>");
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
			System.out.print("<if>");
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
    
    public static abstract class ParamReales extends Nodo {
       	public ParamReales() {
       	}
    }

    public static abstract class LExps extends Nodo {
       	public LExps() {
       	}
    }

    public static abstract class Exp extends Nodo {
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
   		private LExps lexps;
	   	public SiExp(LExps lexps) {
	   		this.lexps = lexps;
	   	}
	   	public LExps lexps() {return lexps;}
		@Override
		public void imprime() {
			System.out.print("(");
			lexps.imprime();
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

   	public static class UnaExp extends LExps {
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

   	public static class MuchasExp extends LExps {
   		LExps lexps;
   		Exp exp;
	   	public MuchasExp(LExps lexps, Exp exp) {
	   		this.exp = exp;
	   	} 
	   	public Exp exp() {return exp;}
	   	public LExps lexp() {return lexps;}
		@Override
		public void imprime() {
			lexps.imprime();
			System.out.print(",");
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
        public void imprimeExpUn(String op, Exp opnd, int np) {
        	System.out.print(op + " ");
        	imprimeOpnd(opnd,np);
        }
    }
    
    public static class Asignacion extends ExpBin {
        public Asignacion(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
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
        @Override
        public int prioridad(){
        	return 1;
        }
        @Override
		public void imprime() {
			imprimeExpBin(opnd0,"<",opnd1,1,2);
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
        @Override
        public int prioridad(){
        	return 1;
        }
        @Override
		public void imprime() {
			imprimeExpBin(opnd0,">",opnd1,1,2);
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
    
    public static class Mul extends ExpBin {
        public Mul(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
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
    
    public static class Neg extends ExpUn {

		public Neg(Exp opnd) {
			super(opnd);
		}
		@Override
		public int prioridad() {
			return 5;
		}
		@Override
		public void imprime() {
			imprimeExpUn("-",opnd,5);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class Not extends ExpUn {

		public Not(Exp opnd) {
			super(opnd);
		}
		@Override
		public int prioridad() {
			return 5;
		}
		@Override
		public void imprime() {
			imprimeExpUn("not",opnd,5);
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
			imprimeExpUn("[",idx,0);
        	System.out.print("]");
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class ExpCampo extends ExpUn {
    	private Token campo;
        public ExpCampo(Exp opnd, Token campo) {
        	super(opnd);
            this.opnd = opnd;
            this.campo = campo;
        }
        @Override
        public int prioridad(){
        	return 6;
        }
        public Token campo() {return campo;}
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
    
    public static class LitEnt extends Exp {
    	Token lit;
    	public LitEnt(Token lit) {
            this.lit = lit;
        }
        @Override
        public int prioridad(){
        	return 7;
        }
		@Override
		public void imprime() {
			System.out.println(lit);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class LitReal extends Exp {
    	Token lit;
    	public LitReal(Token lit) {
            this.lit = lit;
        }
        @Override
        public int prioridad(){
        	return 7;
        }
		@Override
		public void imprime() {
			System.out.println(lit);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class LitCad extends Exp {
    	Token lit;
    	public LitCad(Token lit) {
            this.lit = lit;
        }
        @Override
        public int prioridad(){
        	return 7;
        }
		@Override
		public void imprime() {
			System.out.println(lit);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class Iden extends Exp {
    	Token lit;
    	public Iden(Token id) {
            this.lit = id;
        }
        @Override
        public int prioridad(){
        	return 7;
        }
		@Override
		public void imprime() {
			System.out.println(lit);
		}
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		} 
    }
    
    public static class True extends Exp {
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
    
    public static class False extends Exp {
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
    
    public static class Null extends Exp {
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
    public Prog prog(Bloque bloq) {
    	return new Prog(bloq);
    }
    
    public Bloque bloq(Decs decs, Instrs instrs) {
    	return new Bloque(decs, instrs);
    }
    
    public Decs siDecs(LDecs ldecs) {
        return new SiDecs(ldecs);
    }
    
    public Decs noDecs() {
        return new NoDecs();
    }
    
    public LDecs muchasDecs(LDecs ldecs, Dec dec) {
        return new MuchasDecs(ldecs,dec);
    }
    
    public LDecs unaDec(Dec dec) {
        return new UnaDec(dec);
    }
    
    public Dec decProc(Token id, ParamForms paramForms, Bloque bloq) {
    	return new DecProc(id, paramForms, bloq);
    }
    
    public Dec decType(Tipo t, Token id) {
    	return new DecType(t, id);
    }
    
    public Dec decVar(Tipo t, Token id) {
    	return new DecVar(t, id);
    }
    
    public ParamForms siParam(LParams lparams) {
    	return new SiParam(lparams);
    }
    
    public ParamForms noParam() {
    	return new NoParam();
    }
    
    public LParams unParam(ParamForm param) {
    	return new UnParam(param);
    }
    
    public LParams muchosParams(LParams lparams, ParamForm param) {
    	return new MuchosParams(lparams, param);
    }
    
    public ParamForm paramForm(Tipo t, Token id) {
    	return new ParamFormal(t, id);
    }
    
    public ParamForm paramFormRef(Tipo t, Token id) {
    	return new ParamFormRef(t, id);
    }
    
    public Tipo tArray(Tipo t, Token litEnt) {
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
    
    public Tipo tIden(Token id) {
        return new TIden(id);
    }
    
    public Tipo tStruct(LCampos lcampos) {
        return new TStruct(lcampos);
    }
    
    public LCampos muchosCamps(LCampos lcampos, Campo c) {
    	return new MuchosCamps(lcampos, c);
    }
    
    public LCampos unCamp(Campo c) {
    	return new UnCamp(c);
    }
    
    public Campo campo(Tipo t, Token id) {
    	return new Campo(t, id);
    }
    
    public Instrs siInstrs(LInstrs linstrs) {
        return new SiInstrs(linstrs);
    }
    
    public Instrs noInstrs() {
        return new NoInstrs();
    }
    
    public LInstrs muchasInstrs(LInstrs linstrs, Instr instr) {
        return new MuchasInstrs(linstrs, instr);
    }
    
    public LInstrs unaInstr(Instr instr) {
        return new UnaInstr(instr);
    }    
    
    public Instr arrobaInstr(Exp exp) {
        return new ArrobaInstr(exp);
    }
    
    public Instr procInstr(Token iden, ParamReales params) {
        return new ProcInstr(params, iden);
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
    
    public ParamReales siExp(LExps lexps) {
        return new SiExp(lexps);
    }
    
    public ParamReales noExp() {
        return new NoExp();
    }
    
    public LExps muchasExp(LExps lexps, Exp exp) {
        return new MuchasExp(lexps, exp);
    }
    
    public LExps unaExp(Exp exp) {
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
    
    public Exp neg(Exp opnd) {
        return new Neg(opnd);
    }
    
    public Exp array(Exp opnd0, Exp opnd1) {
        return new Array(opnd0,opnd1);
    }
    
    public Exp expCampo(Exp opnd0, Token campo) {
        return new ExpCampo(opnd0,campo);
    }
    
    public Exp punt(Exp opnd) {
        return new Punt(opnd);
    }
    
    public Exp not(Exp opnd) {
        return new Not(opnd);
    }
    
    public LitEnt litEnt(Token lit){
    	return new LitEnt(lit);
    }
    
    public LitReal litReal(Token lit){
    	return new LitReal(lit);
    }
    
    public Iden iden(Token id){
    	return new Iden(id);
    }
    
    public True _true(){
    	return new True();
    }
    
    public False _false(){
    	return new False();
    }
    
    public LitCad litCad(Token lit){
    	return new LitCad(lit);
    }
    
    public Null _null(){
    	return new Null();
    }
}
