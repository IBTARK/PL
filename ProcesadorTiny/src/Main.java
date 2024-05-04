import java.io.FileInputStream;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import asint.SintaxisAbstracta.Prog;
import c_ast_ascendente.ConstructorASTs;
import c_ast_descendente.ParseException;
import c_ast_descendente.TokenMgrError;
import errors.GestionErroresTiny;
import errors.GestionErroresTiny.ErrorLexico;
import errors.GestionErroresTiny.ErrorSintactico;
import maquinap.MaquinaP;
import procesamiento.AsignacionEspacio;
import procesamiento.Etiquetado;
import procesamiento.GeneracionCodigo;
import procesamiento.Pretipado;
import procesamiento.Tipado;
import procesamiento.Vinculacion;

public class Main {
	
	private static Prog construye_ast(Reader input, char constructor) throws Exception {
		if(constructor == 'a') {
			try {
				AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
				ConstructorASTs asint = new ConstructorASTs(alex);
				Prog p = (Prog) asint.parse().value;
				return p;
			}
			catch(ErrorLexico e) {
				System.out.println("ERROR_LEXICO");
			}
			catch(ErrorSintactico e) {
				System.out.println("ERROR_SINTACTICO");
				System.exit(0);
			}
		}
		else if(constructor == 'd') {
			try {
				c_ast_descendente.ConstructorASTs asint = new c_ast_descendente.ConstructorASTs(input);
				asint.disable_tracing();
				return asint.analiza();
			}
			catch(TokenMgrError e) {
				System.out.println("ERROR_LEXICO");
			}
			catch(ParseException e) {
				System.out.println("ERROR_SINTACTICO");
				System.exit(0);
			}
		}
		else {
			System.err.println("Metodo de construccion no soportado:"+constructor);
		}
		return null;
	}
	
	public static void procesa(Prog p, Reader datos) throws Exception {
 		GestionErroresTiny errores = new GestionErroresTiny();
 		new Vinculacion(errores).procesa(p);
 		if(!errores.hayError()) {
 			new Pretipado(errores).procesa(p);
 		}
 		if(!errores.hayError()) {
 			new Tipado(errores).procesa(p);
 		}
 		if(!errores.hayError()) {
 			new AsignacionEspacio().procesa(p);
 			new Etiquetado().procesa(p);
 			MaquinaP m = new MaquinaP(datos,500,5000,5000,10);
 			new GeneracionCodigo(m).procesa(p);
 			m.ejecuta();
 		}
 	}
	
 	public static void main(String[] args) throws Exception {
 		Reader r = new BISReader(new FileInputStream(args[0]));
 		char constructor = args[1].charAt(0);
 		Prog prog = construye_ast(r,constructor);
 		if(prog != null) {
 			procesa(prog, r);
 		}
 	}
}   
