
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import asint_desc.ParseException;
import asint_desc.TokenMgrError;
import errors.GestionErroresTiny.ErrorLexico;
import errors.GestionErroresTiny.ErrorSintactico;

public class Main {
   public static void main(String[] args) throws Exception {
     if (args[1].equals("-asc"))
        ejecuta_ascendente(args[0]);
     else if(args[1].equals("-desc"))
        ejecuta_descendente(args[0]);  
     else 
        System.out.println("Error en los argumentos");;
   }
   
   private static void ejecuta_ascendente(String in) throws Exception {       
     Reader input = new InputStreamReader(new FileInputStream(in));
     AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
     asint_asc.AnalizadorSintacticoTiny asint = new asint_asc.AnalizadorSintacticoTiny(alex);
     try {    
    	 asint.parse();
         System.out.println("OK");
      }
      catch(ErrorLexico e) {
         System.out.println(e.getMessage()); 
      }
      catch(ErrorSintactico e) {
         System.out.println(e.getMessage()); 
      }
  }
   private static void ejecuta_descendente(String in) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(in));
     asint_desc.AnalizadorSintacticoTiny asint = new asint_desc.AnalizadorSintacticoTiny(input);
     asint.disable_tracing();
     try { 
    	 asint.analiza();
         System.out.println("OK");
     }
     catch(ParseException e) {
        System.out.println(e.getMessage()); 
     }
     catch(TokenMgrError e) {
        System.out.println(e.getMessage()); 
     }
   }
}   
   
