package asint_desc;

import java.io.FileReader;

public class Main {
   public static void main(String[] args) throws Exception {
      AnalizadorSintacticoTinyDJ asint = new AnalizadorSintacticoTinyDJ(new FileReader(args[0]));
      asint.disable_tracing();
      asint.analiza();
   }
}