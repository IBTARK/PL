import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import asint.SintaxisAbstracta.Prog;
import errors.GestionErroresTiny.ErrorSintactico;
import procesamiento.Impresion;
import procesamiento.ProcRecursivo;

public class DomJudge {
	public static void main(String[] args) throws Exception {
		try {
			Reader input = new InputStreamReader(System.in);
			int op = input.read();
			Prog prog = null;
			if(op == 'a') {
				System.out.println("CONSTRUCCION AST ASCENDENTE");
				AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
				c_ast_ascendente.ConstructorASTs asint = new c_ast_ascendente.ConstructorASTsDJ(alex);
				prog = (Prog)asint.debug_parse().value;
			}
			else if (op == 'd') {
				System.out.println("CONSTRUCCION AST DESCENDENTE");
				c_ast_descendente.ConstructorASTs asint = new c_ast_descendente.ConstructorASTsDJ(input);
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
		catch (ErrorSintactico e) {
			System.out.println("ERROR_SINTACTICO");
		}
	}

}
