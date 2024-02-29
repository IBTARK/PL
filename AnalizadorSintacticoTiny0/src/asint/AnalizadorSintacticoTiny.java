package asint;

import java.io.IOException;
import java.io.Reader;
import java.util.EnumSet;
import java.util.Set;

import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import alex.UnidadLexica;
import errors.GestionErroresTiny;

public class AnalizadorSintacticoTiny {
   private UnidadLexica anticipo;       // token adelantado
   private AnalizadorLexicoTiny alex;   // analizador léxico 
   private GestionErroresTiny errores;  // gestor de errores
   private Set<ClaseLexica> esperados;  // clases léxicas esperadas
   
   public AnalizadorSintacticoTiny(Reader input) throws IOException {
	   
	   errores = new GestionErroresTiny();
	   alex = new AnalizadorLexicoTiny(input, errores);
	   esperados = EnumSet.noneOf(ClaseLexica.class);
       
	   sigToken();                      
   }
   
   public void analiza() {
      programa();
      empareja(ClaseLexica.EOF);
   }
   
   private void programa() {
	   bloque();
   }
   
   private void bloque() {
	   switch(anticipo.clase()) {
       case LLAVE_APERTURA: 
    	   empareja(ClaseLexica.LLAVE_APERTURA);
           declaraciones();
           instrucciones();
           empareja(ClaseLexica.LLAVE_CIERRE);
    	   break;
       default:
           esperados(ClaseLexica.LLAVE_APERTURA);
           error();
	   }      
   }
   
   private void declaraciones() {
	   switch(anticipo.clase()) {
	   case INT:
	   case REAL:
	   case BOOL:
		   lista_declaraciones();
	       empareja(ClaseLexica.TERMINACION);
           break;
       default:
    	   esperados(ClaseLexica.INT, 
        		     ClaseLexica.REAL, 
        		     ClaseLexica.BOOL);
    	   break;
	   }
   }
   
   private void lista_declaraciones() {
	   declaración();
	   r_lista_declaraciones();
   }
   
   private void r_lista_declaraciones() {
	   switch(anticipo.clase()) {
       case PUNTO_Y_COMA: 
           empareja(ClaseLexica.PUNTO_Y_COMA);
           declaración();
           r_lista_declaraciones();
           break;
       default:
          esperados(ClaseLexica.PUNTO_Y_COMA);
          break;
	   }
   }
   
   private void declaración() {
	   tipo();
	   empareja(ClaseLexica.IDENTIFICADOR);
   }
   
   private void tipo() {
      switch(anticipo.clase()) {
          case INT: empareja(ClaseLexica.INT); break;
          case REAL: empareja(ClaseLexica.REAL); break; 
          case BOOL: empareja(ClaseLexica.BOOL); break;
          default:
              esperados(ClaseLexica.INT,
            		    ClaseLexica.REAL,
            		    ClaseLexica.BOOL);
              error();
      }
   }
   
   private void instrucciones() {
	   switch(anticipo.clase()) {
	   case INI_NOMBRE:
		   lista_instrucciones();
		   break;
	   default:
		   esperados(ClaseLexica.INI_NOMBRE);
	       break;
	   }
   }
   
   private void lista_instrucciones() {
	   instrucción();
	   r_lista_instrucciones();
	   
   }
   
   private void r_lista_instrucciones() {
	   switch(anticipo.clase()) {
       case PUNTO_Y_COMA: 
           empareja(ClaseLexica.PUNTO_Y_COMA);
           instrucción();
           r_lista_instrucciones();
           break;
       default:
          esperados(ClaseLexica.PUNTO_Y_COMA);
          break;
	   }
   }
   
   private void instrucción() {
	   empareja(ClaseLexica.INI_NOMBRE);
       expresión();
   }
   
   private void expresión_básica() {
      switch(anticipo.clase()) {
          case LITERAL_ENTERO: empareja(ClaseLexica.LITERAL_ENTERO); break;
          case LITERAL_REAL: empareja(ClaseLexica.LITERAL_REAL); break; 
          case IDENTIFICADOR: empareja(ClaseLexica.IDENTIFICADOR); break;
          case TRUE: empareja(ClaseLexica.TRUE); break;
          case FALSE: empareja(ClaseLexica.FALSE); break;
          default:
              esperados(ClaseLexica.LITERAL_ENTERO,
            		    ClaseLexica.LITERAL_REAL,
            		    ClaseLexica.IDENTIFICADOR,
            		    ClaseLexica.TRUE,
            		    ClaseLexica.FALSE);
              error();
      }   
   }
   
   private void expresión() {
	   E0();
   }
   
   private void E0() {
	   E1();
	   RE0();
   }
   
   private void RE0() {
	   switch(anticipo.clase()) {
       case ASIGNACION: 
		   empareja(ClaseLexica.ASIGNACION);
		   E0();
		   break;
       default:
           esperados(ClaseLexica.ASIGNACION);
           break;
 	   }
    }
   
   	private void E1() {
      E2();
      RE1();
   }
   
   private void RE1() {
  	  switch (anticipo.clase()) {
         case MENOR:
         case MAYOR:
         case MENOR_IGUAL:
         case MAYOR_IGUAL:
         case IGUAL:
         case DESIGUAL:
         	OP1();
         	E2();
         	RE1();
         	break;
         default:
         	esperados(ClaseLexica.MENOR, 
         			  ClaseLexica.MAYOR,
         			  ClaseLexica.MENOR_IGUAL, 
         			  ClaseLexica.MAYOR_IGUAL,
         			  ClaseLexica.IGUAL, 
         			  ClaseLexica.DESIGUAL);
         	break;
         }
   }
   
   private void E2() {
	   E3();
	   RE2();
	   REC2();
   }
   
   private void RE2() {
	   switch(anticipo.clase()) {
       case RESTA:
		   empareja(ClaseLexica.RESTA);
		   E3();
		   break;
       default:
           esperados(ClaseLexica.RESTA);
           break;
 	   }
	   
   }
   
   private void REC2() {
	   switch(anticipo.clase()) {
       case SUMA:
		   empareja(ClaseLexica.SUMA);
		   E3();
		   REC2();
		   break;
       default:
           esperados(ClaseLexica.SUMA);
           break;
 	   }
	   
   }
   
   private void E3() {
      E4();
      RE3();
   }
   
   private void RE3() {
      switch (anticipo.clase()) {
	  	 case AND:
	       	empareja(ClaseLexica.AND);
	       	E3();
	       	break;
	     case OR:
	     	empareja(ClaseLexica.OR);
	     	E4();
	     	break;
	     default:
	     	esperados(ClaseLexica.AND, ClaseLexica.OR);
	     	break;
      }
   }
   
   private void E4() {
	   E5();
	   RE4();
   }
   
   private void RE4() {
	   switch(anticipo.clase()) {
       case MULTIPLICACION:
       case DIVISION:
    	   OP4();
    	   E5();
    	   RE4();
    	   break; 
       default:
           esperados(ClaseLexica.MULTIPLICACION,ClaseLexica.DIVISION);
           break;
	   }   
   }
   
   public void E5() {
      switch (anticipo.clase()) {
         case RESTA:
         case NOT:
         	OP5();
         	E5();
         	break;
         case LITERAL_ENTERO:
         case LITERAL_REAL:
         case IDENTIFICADOR:
         case TRUE:
         case FALSE:
         case PARENTESIS_APERTURA:
         	E6();
         	break;
         default:
         	esperados(ClaseLexica.RESTA,
         			  ClaseLexica.NOT,
         			  ClaseLexica.LITERAL_ENTERO,
         			  ClaseLexica.LITERAL_REAL,
         			  ClaseLexica.IDENTIFICADOR,
         			  ClaseLexica.TRUE,
         			  ClaseLexica.FALSE,
         			  ClaseLexica.PARENTESIS_APERTURA);
         	break;
      }
   }
   
   private void E6() {
	   switch (anticipo.clase()) {
       case PARENTESIS_APERTURA:
    	   empareja(ClaseLexica.PARENTESIS_APERTURA);
    	   E0();
    	   empareja(ClaseLexica.PARENTESIS_CIERRE);
    	   break;
       case LITERAL_ENTERO: 
       case LITERAL_REAL: 
       case IDENTIFICADOR: 
       case TRUE: 
       case FALSE:
    	   expresión_básica();
    	   break;
       default:
       	esperados(ClaseLexica.PARENTESIS_APERTURA, 
       			ClaseLexica.LITERAL_ENTERO,
       			ClaseLexica.LITERAL_REAL, 
       			ClaseLexica.IDENTIFICADOR,
       			ClaseLexica.TRUE, 
       			ClaseLexica.FALSE);
       	error();
	   } 
   }
   
   private void OP1() {
      switch (anticipo.clase()) {
         case MENOR: empareja(ClaseLexica.MENOR); break;
         case MAYOR: empareja(ClaseLexica.MAYOR); break;
         case MENOR_IGUAL: empareja(ClaseLexica.MENOR_IGUAL); break;
         case MAYOR_IGUAL: empareja(ClaseLexica.MAYOR_IGUAL); break;
         case IGUAL: empareja(ClaseLexica.IGUAL); break;
         case DESIGUAL: empareja(ClaseLexica.DESIGUAL); break;
         default:
         	esperados(ClaseLexica.MENOR, ClaseLexica.MAYOR,
         		ClaseLexica.MENOR_IGUAL, ClaseLexica.MAYOR_IGUAL,
         		ClaseLexica.IGUAL, ClaseLexica.DESIGUAL);
         	error();
      }
   }
   
   private void OP4() {
	   switch (anticipo.clase()) {
       case MULTIPLICACION: empareja(ClaseLexica.MULTIPLICACION); break;
       case DIVISION: empareja(ClaseLexica.DIVISION); break;
       default:
       	esperados(ClaseLexica.MULTIPLICACION, ClaseLexica.DIVISION);
       	error();
	   } 
   }
   
   public void OP5() {
	      switch (anticipo.clase()) {
	         case RESTA: empareja(ClaseLexica.RESTA); break;
	         case NOT: empareja(ClaseLexica.NOT); break;
	         default:
	         	esperados(ClaseLexica.RESTA,
	         			  ClaseLexica.NOT);
	         	error();
	      }
	   }

   private void esperados(ClaseLexica ... esperadas) {
       for(ClaseLexica c: esperadas) {
           esperados.add(c);
       }
   }
   
   private void empareja(ClaseLexica claseEsperada) {
      if (anticipo.clase() == claseEsperada) {
          traza_emparejamiento(anticipo);
          sigToken();
      }
      else {
          esperados(claseEsperada);
          error();
      }
   }
   
   private void sigToken() {
      try {
        anticipo = alex.sigToken();
        esperados.clear();
      }
      catch(IOException e) {
        errores.errorFatal(e);
      }
   }
   
    private void error() {
        errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), esperados);
    }
  
    protected void traza_emparejamiento(UnidadLexica anticipo) {} 
}
