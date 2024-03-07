package asint_asc;


import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import errors.GestionErroresTiny.ErrorLexico;
import errors.GestionErroresTiny.ErrorSintactico;
import java_cup.runtime.Scanner;

public class DomJudge {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(System.in);
     Scanner alex = new AnalizadorLexicoTiny(input);
     AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTinyDJ(alex);
	 //asint.setScanner(alex);
     try {    
        asint.debug_parse();
     }
     catch(ErrorLexico e) {
        System.out.println("ERROR_LEXICO"); 
     }
     catch(ErrorSintactico e) {
        System.out.println("ERROR_SINTACTICO"); 
     }
 }

}
