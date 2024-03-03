package asint_desc;

import java.io.FileReader;
import asint_desc.AnalizadorSintacticoTiny;

public class Main {
   public static void main(String[] args) throws Exception {
      AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(new FileReader(args[0]));
      asint.disable_tracing();
      asint.analiza();
   }
}