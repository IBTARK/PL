import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import asint.SintaxisAbstracta.Prog;
import procesamiento.Impresion;
import procesamiento.ProcRecursivo;

public class DomJudge {
	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(System.in);
		int op = input.read();
		Prog prog;
		if(op == 'a') {
			System.out.println("CONSTRUCCION AST ASCENDENTE");
			AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
			c_ast_ascendente.ConstructorASTs asint = new c_ast_ascendente.ConstructorASTs(alex);
			prog = (Prog)asint.parse().value;
		}
		else if (op == 'd') {
			System.out.println("CONSTRUCCION AST DESCENDENTE");
			c_ast_descendente.ConstructorASTs asint = new c_ast_descendente.ConstructorASTs(input);
			asint.disable_tracing();
			prog = asint.analiza();
		}

		System.out.println("IMPRESION RECURSIVA");
		new ProcRecursivo().imprime(prog);
		System.out.println("IMPRESION INTERPRETE");
		prog.imprime();
		System.out.println("IMPRESION VISITANTE");
		prog.procesa(new Impresion());
	}

}
