
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import alex.UnidadLexica;
import errors.GestionErroresTiny;

public class Main {
   public static void main(String[] args) throws Exception {
     if (args[0].equals("-lex")) {  
         ejecuta_lexico(args[1]);
     }
     else {
         if (args[0].equals("-asc"))
            ejecuta_ascendente(args[1]);
         else if(args[0].equals("-desc"))
            ejecuta_descendente(args[1]);  
         else 
            ;
     }
   }
   
   private static void ejecuta_lexico(String in) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(in));
     AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
     GestionErroresTiny errores = new GestionErroresTiny();
     UnidadLexica t = (UnidadLexica) alex.next_token();
     while (t.clase() != ClaseLexica.EOF) {
         System.out.println(t);
         t = (UnidadLexica) alex.next_token();   
     }
   }
   private static void ejecuta_ascendente(String in) throws Exception {       
     Reader input = new InputStreamReader(new FileInputStream(in));
     AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
  }
   private static void ejecuta_descendente(String in) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(in));
   }
}   
   
