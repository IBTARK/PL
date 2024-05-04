package maquinap;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;




public class MaquinaP {
   public static class EAccesoIlegitimo extends RuntimeException {
	private static final long serialVersionUID = 1L;} 
   public static class EAccesoAMemoriaNoInicializada extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public EAccesoAMemoriaNoInicializada(int pc,int dir) {
         super("pinst:"+pc+" dir:"+dir); 
      } 
   } 
   public static class EAccesoFueraDeRango extends RuntimeException {
	private static final long serialVersionUID = 1L;} 
   
   private GestorMemoriaDinamica gestorMemoriaDinamica;
   private GestorPilaActivaciones gestorPilaActivaciones;
    
   private class Valor {
      public int valorInt() {throw new EAccesoIlegitimo();}  
      public boolean valorBool() {throw new EAccesoIlegitimo();} 
      public String valorString() {throw new EAccesoIlegitimo();}
      public double valorReal() {throw new EAccesoIlegitimo();}
   } 
   private class ValorInt extends Valor {
      private int valor;
      public ValorInt(int valor) {
         this.valor = valor; 
      }
      public int valorInt() {return valor;}
      public double valorReal() {return valor;}
      public String toString() {
        return String.valueOf(valor);
      }
   }
   private class ValorReal extends Valor {
	      private double valor;
	      public ValorReal(double valor) {
	         this.valor = valor; 
	      }
	      public double valorReal() {return valor;}
	      public String toString() {
	        return String.valueOf(valor);
	      }
	   }
   private class ValorBool extends Valor {
      private boolean valor;
      public ValorBool(boolean valor) {
         this.valor = valor; 
      }
      public boolean valorBool() {return valor;}
      public String toString() {
        return String.valueOf(valor);
      }
   }
   private class ValorString extends Valor {
      private String valor;
      public ValorString(String valor) {
         this.valor = valor; 
      }
      public String valorString() {return valor;}
      public String toString() {
        return String.valueOf(valor);
      }
   }

   private List<Instruccion> codigoP;
   private Stack<Valor> pilaEvaluacion;
   private Valor[] datos; 
   private int pc;

   public interface Instruccion {
      void ejecuta();  
   }
   private ISuma ISUMA;
   private class ISuma implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.getClass() == ValorReal.class || opnd2.getClass() == ValorReal.class)
        	 pilaEvaluacion.push(new ValorReal(opnd1.valorReal() + opnd2.valorReal()));
         else
        	 pilaEvaluacion.push(new ValorInt(opnd1.valorInt() + opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "suma";};
   }
   private IMul IMUL;
   private class IMul implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.getClass() == ValorReal.class || opnd2.getClass() == ValorReal.class)
        	 pilaEvaluacion.push(new ValorReal(opnd1.valorReal() * opnd2.valorReal()));
         else
        	 pilaEvaluacion.push(new ValorInt(opnd1.valorInt() * opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "mul";};
   }
   private IAnd IAND;
   private class IAnd implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorBool() && opnd2.valorBool()));
         pc++;
      } 
      public String toString() {return "and";};
   }
   private class IApilaInt implements Instruccion {
      private int valor;
      public IApilaInt(int valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorInt(valor)); 
         pc++;
      } 
      public String toString() {return "apila-int("+valor+")";};
   }

   private class IApilaBool implements Instruccion {
      private boolean valor;
      public IApilaBool(boolean valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorBool(valor)); 
         pc++;
      } 
      public String toString() {return "apila-bool("+valor+")";};
   }

   private class IIrA implements Instruccion {
      private int dir;
      public IIrA(int dir) {
        this.dir = dir;  
      }
      public void ejecuta() {
            pc=dir;
      } 
      public String toString() {return "ir-a("+dir+")";};
   }

   private class IIrF implements Instruccion {
      private int dir;
      public IIrF(int dir) {
        this.dir = dir;  
      }
      public void ejecuta() {
         if(! pilaEvaluacion.pop().valorBool()) { 
            pc=dir;
         }   
         else {
            pc++; 
         }
      } 
      public String toString() {return "ir-f("+dir+")";};
   }
   
   private class ICopia implements Instruccion {
      private int tam;
      public ICopia(int tam) {
        this.tam = tam;  
      }
      public void ejecuta() {
            int dirOrigen = pilaEvaluacion.pop().valorInt();
            int dirDestino = pilaEvaluacion.pop().valorInt();
            if ((dirOrigen + (tam-1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            if ((dirDestino + (tam-1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            for (int i=0; i < tam; i++) 
                datos[dirDestino+i] = datos[dirOrigen+i]; 
            pc++;
      } 
      public String toString() {return "copia("+tam+")";};
   }
   
   private IFetch FETCH;
   private class IFetch implements Instruccion {
      public void ejecuta() {
        int dir = pilaEvaluacion.pop().valorInt();
        if (dir >= datos.length) throw new EAccesoFueraDeRango();
        if (datos[dir] == null)  throw new EAccesoAMemoriaNoInicializada(pc,dir);
        pilaEvaluacion.push(datos[dir]);
        pc++;
      } 
      public String toString() {return "apila-ind";};
   }

   private IStore STORE;
   private class IStore implements Instruccion {
      public void ejecuta() {
        Valor valor = pilaEvaluacion.pop();
        int dir = pilaEvaluacion.pop().valorInt();
        if (dir >= datos.length) throw new EAccesoFueraDeRango();
        datos[dir] = valor;
        pc++;
      } 
      public String toString() {return "desapila-ind";};
   }

   private class IAlloc implements Instruccion {
      private int tam;
      public IAlloc(int tam) {
        this.tam = tam;  
      }
      public void ejecuta() {
        int inicio = gestorMemoriaDinamica.alloc(tam);
        pilaEvaluacion.push(new ValorInt(inicio));
        pc++;
      } 
      public String toString() {return "alloc("+tam+")";};
   }

   private class IDealloc implements Instruccion {
      private int tam;
      public IDealloc(int tam) {
        this.tam = tam;  
      }
      public void ejecuta() {
        int inicio = pilaEvaluacion.pop().valorInt();
        gestorMemoriaDinamica.free(inicio,tam);
        pc++;
      } 
      public String toString() {return "dealloc("+tam+")";};
   }
   
   private class IActiva implements Instruccion {
       private int nivel;
       private int tamdatos;
       private int dirretorno;
       public IActiva(int nivel, int tamdatos, int dirretorno) {
           this.nivel = nivel;
           this.tamdatos = tamdatos;
           this.dirretorno = dirretorno;
       }
       public void ejecuta() {
          int base = gestorPilaActivaciones.creaRegistroActivacion(tamdatos);
          datos[base] = new ValorInt(dirretorno);
          datos[base+1] = new ValorInt(gestorPilaActivaciones.display(nivel));
          pilaEvaluacion.push(new ValorInt(base+2));
          pc++;
       }
       public String toString() {
          return "activa("+nivel+","+tamdatos+","+dirretorno+")";                 
       }
   }
   
   private class IDesactiva implements Instruccion {
       private int nivel;
       private int tamdatos;
       public IDesactiva(int nivel, int tamdatos) {
           this.nivel = nivel;
           this.tamdatos = tamdatos;
       }
       public void ejecuta() {
          int base = gestorPilaActivaciones.liberaRegistroActivacion(tamdatos);
          gestorPilaActivaciones.fijaDisplay(nivel,datos[base+1].valorInt());
          pilaEvaluacion.push(datos[base]); 
          pc++;
       }
       public String toString() {
          return "desactiva("+nivel+","+tamdatos+")";                 
       }

   }
   
   private class IDesapilad implements Instruccion {
       private int nivel;
       public IDesapilad(int nivel) {
         this.nivel = nivel;  
       }
       public void ejecuta() {
         gestorPilaActivaciones.fijaDisplay(nivel,pilaEvaluacion.pop().valorInt());  
         pc++;
       }
       public String toString() {
          return "desapilad("+nivel+")";                 
       }
   }
   private IDup IDUP;
   private class IDup implements Instruccion {
       public void ejecuta() {
           pilaEvaluacion.push(pilaEvaluacion.peek());
           pc++;
       }
       public String toString() {
          return "dup";                 
       }
   }
   private Instruccion ISTOP;
   private class IStop implements Instruccion {
       public void ejecuta() {
           pc = codigoP.size();
       }
       public String toString() {
          return "stop";                 
       }
   }
   
   
   private class IApilaDisp implements Instruccion {
       private int nivel;
       public IApilaDisp(int nivel) {
         this.nivel = nivel;  
       }
       public void ejecuta() {
         pilaEvaluacion.push(new ValorInt(gestorPilaActivaciones.display(nivel)));
         pc++;
       }
       public String toString() {
          return "apilad("+nivel+")";
       }

   }
   
   private Instruccion IIRD;
   private class IIrind implements Instruccion {
       public void ejecuta() {
          pc = pilaEvaluacion.pop().valorInt();  
       }
       public String toString() {
          return "ir-ind";                 
       }
   }
   
   private Instruccion DESAPILA;
   private class IDesapila implements Instruccion {
	   public void ejecuta() {
          pilaEvaluacion.pop();
          pc++;
       }
       public String toString() {
          return "desapila";                 
       }
   }
   
   private class IApilaString implements Instruccion {
       private String str;
       public IApilaString(String str) {
         this.str = str;  
       }
       public void ejecuta() {
         pilaEvaluacion.push(new ValorString(str));
         pc++;
       }
       public String toString() {
          return "apilaString("+str+")";
       }
   }
   
   private Instruccion ICASTREAL;
   private class ICastReal implements Instruccion {
       public void ejecuta() {
    	   int val = pilaEvaluacion.pop().valorInt();
    	   pilaEvaluacion.push(new ValorReal((double) val));
    	   pc++;
       }
       public String toString() {
          return "castReal";
       }
   }

   private IResta IRESTA;
   private class IResta implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.getClass() == ValorReal.class || opnd2.getClass() == ValorReal.class)
        	 pilaEvaluacion.push(new ValorReal(opnd1.valorReal() - opnd2.valorReal()));
         else
        	 pilaEvaluacion.push(new ValorInt(opnd1.valorInt() - opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "resta";};
   }
   private IDiv IDIV;
   private class IDiv implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.getClass() == ValorReal.class || opnd2.getClass() == ValorReal.class)
        	 pilaEvaluacion.push(new ValorReal(opnd1.valorReal() / opnd2.valorReal()));
         else
        	 pilaEvaluacion.push(new ValorInt(opnd1.valorInt() / opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "div";};
   }
   private IMod IMOD;
   private class IMod implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt() % opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "mod";};
   }

   private IIgual IIGUAL;
   private class IIgual implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.getClass() == ValorBool.class)
        	 pilaEvaluacion.push(new ValorBool(opnd1.valorBool() == opnd2.valorBool()));
         else if (opnd1.getClass() == ValorString.class)
        	 pilaEvaluacion.push(new ValorBool(opnd1.valorString() == opnd2.valorString()));
         else
        	 pilaEvaluacion.push(new ValorBool(opnd1.valorReal() == opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "igual";};
   }

   private IDesigual IDESIGUAL;
   private class IDesigual implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.getClass() == ValorBool.class)
        	 pilaEvaluacion.push(new ValorBool(opnd1.valorBool() != opnd2.valorBool()));
         else if (opnd1.getClass() == ValorString.class)
        	 pilaEvaluacion.push(new ValorBool(opnd1.valorString() != opnd2.valorString()));
         else
        	 pilaEvaluacion.push(new ValorBool(opnd1.valorReal() != opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "resta";};
   }

   private IOr IOR;
   private class IOr implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt() % opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "or";};
   }

   private IMayor IMAYOR;
   private class IMayor implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal() > opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "mayor";};
   }

   private IMenor IMENOR;
   private class IMenor implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal() < opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "menor";};
   }

   private IMayorIgual IMAYORIGUAL;
   private class IMayorIgual implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal() >= opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "mayorIgual";};
   }

   private IMenorIgual IMENORIGUAL;
   private class IMenorIgual implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal() <= opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "menorIgual";};
   }

   private INot INOT;
   private class INot implements Instruccion {
      public void ejecuta() {
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(!opnd1.valorBool()));
         pc++;
      } 
      public String toString() {return "not";};
   }

   private INeg IMENOS;
   private class INeg implements Instruccion {
      public void ejecuta() {
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.getClass() == ValorReal.class)
        	 pilaEvaluacion.push(new ValorReal(-opnd1.valorReal()));
         else
        	 pilaEvaluacion.push(new ValorInt(-opnd1.valorInt()));
         pc++;
      } 
      public String toString() {return "menosUnario";};
   }

   private IRead IREAD;
   private class IRead implements Instruccion {
      public void ejecuta() {
         int dir = pilaEvaluacion.pop().valorInt();
         if (datos[dir].getClass() == ValorString.class)
        	 pilaEvaluacion.push(new ValorString(in.nextLine()));
         else if (datos[dir].getClass() == ValorInt.class) {
        	 pilaEvaluacion.push(new ValorInt(in.nextInt()));
        	 in.nextLine();
         }
         else if (datos[dir].getClass() == ValorReal.class) {
        	 pilaEvaluacion.push(new ValorReal(in.nextDouble()));
        	 in.nextLine();
         }
         pc++;
      } 
      public String toString() {return "read";};
   }

   private IWrite IWRITE;
   private class IWrite implements Instruccion {
      public void ejecuta() {
         Valor val = pilaEvaluacion.pop();
         System.out.println(val.toString());
         pc++;
      } 
      public String toString() {return "write";};
   }

   private class IIdx implements Instruccion {
      private int tam;
      public IIdx(int tam) {
    	  this.tam = tam;  
      }
      public void ejecuta() {
    	  int idx = pilaEvaluacion.pop().valorInt();
    	  int dir = pilaEvaluacion.pop().valorInt();
    	  pilaEvaluacion.add(new ValorInt(dir + tam*idx));
      } 
      public String toString() {return "idx("+tam+")";};
   }
   

   public Instruccion desapila() {return DESAPILA;}
   public Instruccion apilaInt(int val) {return new IApilaInt(val);}
   public Instruccion apilaBool(boolean val) {return new IApilaBool(val);}
   public Instruccion apilaString(String val) {return new IApilaString(val);}
   public Instruccion castReal() {return ICASTREAL;}
   public Instruccion fetch() {return FETCH;}
   public Instruccion store() {return STORE;}
   public Instruccion copia(int tam) {return new ICopia(tam);}
   public Instruccion suma() {return ISUMA;}
   public Instruccion resta() {return IRESTA;}
   public Instruccion mul() {return IMUL;}
   public Instruccion div() {return IDIV;}
   public Instruccion mod() {return IMOD;}
   public Instruccion and() {return IAND;}
   public Instruccion or() {return IOR;}
   public Instruccion mayor() {return IMAYOR;}
   public Instruccion menor() {return IMENOR;}
   public Instruccion mayorIgual() {return IMAYORIGUAL;}
   public Instruccion menorIgual() {return IMENORIGUAL;}
   public Instruccion igual() {return IIGUAL;}
   public Instruccion desigual() {return IDESIGUAL;}
   public Instruccion not() {return INOT;}
   public Instruccion menosUnario() {return IMENOS;}
   public Instruccion alloc(int tam) {return new IAlloc(tam);} 
   public Instruccion dealloc(int tam) {return new IDealloc(tam);} 
   public Instruccion irA(int dir) {return new IIrA(dir);}
   public Instruccion irF(int dir) {return new IIrF(dir);}
   public Instruccion irD() {return IIRD;}
   public Instruccion activa(int nivel,int tam, int dirretorno) {return new IActiva(nivel,tam,dirretorno);}
   public Instruccion desactiva(int nivel, int tam) {return new IDesactiva(nivel,tam);}
   public Instruccion apilaDisp(int nivel) {return new IApilaDisp(nivel);}
   public Instruccion desapilaDisp(int nivel) {return new IDesapilad(nivel);}
   public Instruccion dup() {return IDUP;}
   public Instruccion stop() {return ISTOP;}
   public Instruccion read() {return IREAD;}
   public Instruccion write() {return IWRITE;}
   public Instruccion idx(int tam) {return new IIdx(tam);}
   public void emit(Instruccion i) {
      codigoP.add(i); 
   }

   private int tamdatos;
   private int tamheap;
   private int ndisplays;
   private Scanner in;
   public MaquinaP(Reader in, int tamdatos, int tampila, int tamheap, int ndisplays) {
      this.tamdatos = tamdatos;
      this.tamheap = tamheap;
      this.ndisplays = ndisplays;
      this.codigoP = new ArrayList<>();  
      pilaEvaluacion = new Stack<>();
      datos = new Valor[tamdatos+tampila+tamheap];
      this.pc = 0;
      ISUMA = new ISuma();
      IAND = new IAnd();
      IMUL = new IMul();
      FETCH = new IFetch();
      STORE = new IStore();
      IIRD = new IIrind();
      IDUP = new IDup();
      ISTOP = new IStop();
      DESAPILA = new IDesapila();
      ICASTREAL = new ICastReal();
      IRESTA = new IResta();
      IDIV = new IDiv();
      IMOD = new IMod();
      IOR = new IOr();
      IMAYOR = new IMayor();
      IMENOR = new IMenor();
      IMENORIGUAL = new IMenorIgual();
      IMAYORIGUAL = new IMayorIgual();
      IIGUAL = new IIgual();
      IDESIGUAL = new IDesigual();
      INOT = new INot();
      IMENOS = new INeg();
      gestorPilaActivaciones = new GestorPilaActivaciones(tamdatos,(tamdatos+tampila)-1,ndisplays); 
      gestorMemoriaDinamica = new GestorMemoriaDinamica(tamdatos+tampila,(tamdatos+tampila+tamheap)-1);
      this.in = new Scanner(in);
   }
   public void ejecuta() {
      while(pc != codigoP.size()) {
          codigoP.get(pc).ejecuta();
      } 
   }
   public void muestraCodigo() {
     System.out.println("CodigoP");
     for(int i=0; i < codigoP.size(); i++) {
        System.out.println(" "+i+":"+codigoP.get(i));
     }
   }
   public void muestraEstado() {
     System.out.println("Tam datos:"+tamdatos);  
     System.out.println("Tam heap:"+tamheap); 
     System.out.println("PP:"+gestorPilaActivaciones.pp());      
     System.out.print("Displays:");
     for (int i=1; i <= ndisplays; i++)
         System.out.print(i+":"+gestorPilaActivaciones.display(i)+" ");
     System.out.println();
     System.out.println("Pila de evaluacion");
     for(int i=0; i < pilaEvaluacion.size(); i++) {
        System.out.println(" "+i+":"+pilaEvaluacion.get(i));
     }
     System.out.println("Datos");
     for(int i=0; i < datos.length; i++) {
        System.out.println(" "+i+":"+datos[i]);
     }
     System.out.println("PC:"+pc);
   }
   
   public static void main(String[] args) {
       MaquinaP m = new MaquinaP(new InputStreamReader(System.in), 5,10,10,2);
        
          /*
            int x;
            proc store(int v) {
             x = v
            }
           &&
            call store(5)
          */
            
       
       m.emit(m.activa(1,1,8));
       m.emit(m.dup());
       m.emit(m.apilaInt(0));
       m.emit(m.suma());
       m.emit(m.apilaInt(5));
       m.emit(m.store());
       m.emit(m.desapilaDisp(1));
       m.emit(m.irA(9));
       m.emit(m.stop());
       m.emit(m.apilaInt(0));
       m.emit(m.apilaDisp(1));
       m.emit(m.apilaInt(0));
       m.emit(m.suma());
       m.emit(m.copia(1));
       m.emit(m.desactiva(1,1));
       m.emit(m.irD());       
       m.muestraCodigo();
       m.ejecuta();
       m.muestraEstado();
   }
}
