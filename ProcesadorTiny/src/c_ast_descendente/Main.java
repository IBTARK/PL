package c_ast_descendente;
import java.io.FileReader;
public class Main{
   public static void main(String[] args) throws Exception {
      ConstructorASTsDJ asint = new ConstructorASTsDJ(new FileReader(args[0]));
      asint.disable_tracing();
      System.out.println(asint.analiza());
   }
}