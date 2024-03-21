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
        public String toString() {
        	String s = decs.toString();
        	s += "\n";
        	s += "&&";
        	s += "\n";
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
        	this.dec = dec;
        	this.decs = decs;
        }
        public String toString() {
     	   	String s = decs.toString();
     	   	s += ";";
     	   	s += "\n";
     	   	s += dec.toString();
     	   	return s;
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
         public String toString() {
          	 String s = "(";
          	 s += params.toString();
          	 s += ")";
             return s;
         } 
     }

     public static class NoParam extends ParamForm {
     	 public NoParam() {
         }   
         public String toString() {
          	 String s = "()";
             return s;
         } 
     }

     public static class UnParam extends LParam {
     	 Param param;
     	 public UnParam(Param param) {
     		 this.param = param;
         }   
         public String toString() {
          	 String s = param.toString();
             return s;
         } 
     }

     public static class MuchosParams extends LParam {
     	 LParam params;
     	 Param param;
     	 public MuchosParams(LParam params, Param param) {
     		 this.params = params;
     		 this.param = param;
         }   
         public String toString() {
          	 String s = params.toString();
          	 s += ", ";
          	 s += param.toString();
             return s;
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
         public String toString() {
        	 String s = t.toString();
        	 s += "[";
        	 s += litEnt;
        	 s += "]";
        	 return s;
         } 
     }

     public static class TInt extends Tipo {
     	 public TInt() {
     	 } 
         public String toString() {
          	 String s = "<int>";
             return s;
         }
     }

     public static class TReal extends Tipo {
     	 public TReal() {
     	 } 
         public String toString() {
          	 String s = "<real>";
             return s;
         }
     }

     public static class TBool extends Tipo {
     	 public TBool() {
     	 } 
         public String toString() {
          	 String s = "<bool>";
             return s;
         }
     }

     public static class TString extends Tipo {
     	 public TString() {
     	 } 
         public String toString() {
          	 String s = "<string>";
             return s;
         }
     }

     public static class TIden extends Tipo {
     	 String iden;
     	 public TIden(String iden) {
     		 this.iden = iden;
     	 } 
         public String toString() {
          	 String s = iden;
             return s;
         }
     }

     public static class TStruct extends Tipo {
    	 LCampos campos;
    	 public TStruct(LCampos campos) {
    		 this.campos = campos;
    	 } 
         public String toString() {
        	 String s = "<struct> {\n";
        	 s += campos.toString();
        	 s += "\n}";
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
           public String imprimeOpnd(Exp opnd, int minPrior) {
           	String s = "";
           	if(opnd.prioridad() < minPrior){
           		s += "(";
           	}
           	s += opnd.toString();
           	if(opnd.prioridad() < minPrior) {
           		s += ")";
           	}
           	return s;
           }
           public abstract int prioridad();
        }

       	public static class SiExp extends ParamReales {
       		private LExp exps;
    	   	public SiExp(LExp exps) {
    	   		this.exps = exps;
    	   	}
    	   	public String toString() {
    	   		String s = "(" + exps.toString() + ")";
    	   		return s;
           	} 
       	}

       	public static class NoExp extends ParamReales {
    	   	public NoExp() {
    	   	}
    	   	public String toString() {
    	   		String s = "()";
    	   		return s;
           	} 
       	}

       	public static class UnaExp extends LExp {
       		Exp exp;
    	   	public UnaExp(Exp exp) {
    	   		this.exp = exp;
    	   	}
    	   	public String toString() {
    	   		String s = exp.toString();
    	   		return s;
           	} 
       	}

       	public static class MuchasExp extends LExp {
       		LExp exps;
       		Exp exp;
    	   	public MuchasExp(LExp exps, Exp exp) {
    	   		this.exp = exp;
    	   	}
    	   	public String toString() {
    	   		String s = exps.toString();
    	   		s += ", ";
    	   		s += exp.toString();
    	   		return s;
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
            	return s;
            }
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
            public String toString() {
                return imprimeExpBin(opnd0,"+",opnd1,2,3);
            }
            @Override
            public int prioridad(){
            	return 2;
            }
        }
        
        public static class Resta extends ExpBin {
            public Resta(Exp opnd0, Exp opnd1) {
                super(opnd0,opnd1);
            }
            public String toString() {
                return imprimeExpBin(opnd0,"-",opnd1,3,3);
            }
            @Override
            public int prioridad(){
            	return 2;
            }
        }
        
        public static class Array extends Exp {
        	private Exp opnd, idx;
            public Array(Exp opnd, Exp idx) {
                this.opnd = opnd;
                this.idx = idx;
            }
            public String toString() {
            	String s = imprimeOpnd(opnd, 6);
            	s += "[" + imprimeOpnd(idx, 0) + "]";
                return s;
            }
            @Override
            public int prioridad(){
            	return 6;
            }
        }
        
        public static class ExpCampo extends Exp {
        	private Exp opnd;
        	private String campo;
            public ExpCampo(Exp opnd, String campo) {
                this.opnd = opnd;
                this.campo = campo;
            }
            public String toString() {
            	String s = imprimeOpnd(opnd, 6);
            	s += "." + campo;
                return s;
            }
            @Override
            public int prioridad(){
            	return 6;
            }
        }
        
        public static class Punt extends Exp {
        	private Exp opnd;
            public Punt(Exp opnd) {
                this.opnd = opnd;
            }
            public String toString() {
            	String s = imprimeOpnd(opnd, 6);
            	s += "^";
                return s;
            }
            @Override
            public int prioridad(){
            	return 6;
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
