package constructoresAsts.descendente;
import java.io.FileReader;
public class Main{
   public static void main(String[] args) throws Exception {
      ConstructorASTs asint = new ConstructorASTs(new FileReader(args[0]));
      asint.disable_tracing();
      System.out.println(asint.analiza());
   }
}