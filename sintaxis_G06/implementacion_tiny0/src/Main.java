
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import asint.AnalizadorSintacticoTiny;
import errors.GestionErroresTiny.ErrorLexico;
import errors.GestionErroresTiny.ErrorSintactico;

public class Main {
   public static void main(String[] args) throws Exception {
	 Reader input = new InputStreamReader(new FileInputStream(args[0]));
     AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(input);
     try {    
    	 asint.analiza();
         System.out.println("OK");
      }
      catch(ErrorLexico e) {
         System.out.println(e.getMessage()); 
      }
      catch(ErrorSintactico e) {
         System.out.println(e.getMessage()); 
      }
   }
}   
   
