package constructoresAsts.asint;

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
    }

    public static abstract class Exp  extends  Nodo {
       public Exp() {
		   super();
       }
    }
    
    private static abstract class ExpBin extends Exp {
        protected Exp opnd0;
        protected Exp opnd1;
        public ExpBin(Exp opnd0, Exp opnd1) {
            super();
            this.opnd0 = opnd0;
            this.opnd1 = opnd1;
        }
        public String imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1) {
        	String s = imprimeOpnd(opnd0,np0);
        	s += " " + op + " ";
        	s += imprimeOpnd(opnd1,np1);
        	System.out.println(s);
        	return s;
        }
        public String imprimeOpnd(Exp opnd, int minPrior) {
        	String s = "";
        	if(((ExpBin)opnd).prioridad() < minPrior){
        		s += "(";
        	}
        	s += opnd;
        	if(((ExpBin)opnd).prioridad() < minPrior) {
        		s += ")";
        	}
        	return s;
        }
        public abstract int prioridad();
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
    
    public static class LiteralEntero extends Exp {
        private String num;
        public LiteralEntero(String num) {
            super();
            this.num = num;
        }
        public String toString() {
        	System.out.println(num);
            return num;
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
       private LDecs decs; 
       public SiDecs(LDecs decs) {
          super();
          this.decs = decs;
       }   
        public String toString() {
        	String s = decs.toString();
        	s += "\n";
        	s += "&&";
        	s += "\n";
        	System.out.println(s);
            return s;
        } 
    }
    public static class NoDecs extends Decs {
       public NoDecs() {
          super();
       }   
       public String toString() {
            return "";
        } 
    }
     	
    public static class MuchasDecs extends LDecs {
    	private LDecs decs;
        private Dec dec;
        public MuchasDecs(LDecs decs, Dec dec) {
           super();
           this.dec = dec;
           this.decs = decs;
        }
        public String toString() {
     	   String s = decs.toString();
     	   s += ";";
     	   s += "\n";
     	   s += dec.toString();
     	   System.out.println(s);
     	   return s;
         } 
     }

     public static class UnaDec extends LDecs {
        private Dec dec;
        public UnaDec(Dec dec) {
           super();
           this.dec = dec;
        }
        public String toString() {
        	System.out.println(dec.toString());
            return dec.toString();
         } 
     }
     
     public static abstract class Tipo extends Nodo {
         public Tipo() {
  		   super();
         }
      }
     
     public static class TArray extends Tipo {
         private LiteralEntero litEnt;
         private Tipo t;
         public TArray(Tipo t, LiteralEntero litEnt) {
            super();
            this.t = t;
            this.litEnt = litEnt;
         }
         public String toString() {
        	 String s = t.toString();
        	 s += "[";
        	 s += litEnt.toString();
        	 s += "]";
        	 System.out.println(s);
             return s;
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
          	System.out.println(instrs.toString());
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
       	   System.out.println(s);
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
          	System.out.println(instr.toString());
              return instr.toString();
           } 
       }
    
    //constructoras
    public Exp Asignacion(Exp opnd0, Exp opnd1) {
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
    
    public Exp literalEntero(String num) {
        return new LiteralEntero(num);
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
    
    public Tipo tArray(Tipo t, LiteralEntero litEnt) {
        return new TArray(t, litEnt);
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
   
}
