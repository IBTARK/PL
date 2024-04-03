import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import asint.SintaxisAbstracta.Bloque;
import procesamiento.ProcRecursivo;

public class Main {
	public static void main(String[] args) throws Exception {
		Bloque prog;
		if(args[1].equals("asc")) {
			Reader input = new InputStreamReader(new FileInputStream(args[0]));
			AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
			c_ast_ascendente.ConstructorASTs asint = new c_ast_ascendente.ConstructorASTs(alex);
			prog = (Bloque)asint.parse().value;
		}
		else {
			c_ast_descendente.ConstructorASTs asint = new c_ast_descendente.ConstructorASTs(new FileReader(args[1]));
			asint.disable_tracing();
			prog = asint.analiza();
		}
		
		if (args[2].equals("rec")) {
			new ProcRecursivo().imprime(prog);
		}
		else if (args[2].equals("int")) {
			// TODO
		}
		else if (args[2].equals("vis")) {
			// TODO
		}
	}
}   
