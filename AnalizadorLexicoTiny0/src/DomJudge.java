import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import alex.UnidadLexica;

public class DomJudge {
	private static void imprime(UnidadLexica unidad) {
		try {
			System.out.println(unidad.lexema());
		} catch (UnsupportedOperationException e) {
			System.out.println(unidad.clase().getImage());
		}
	}	

	public static void main(String[] args) throws FileNotFoundException, IOException {
	    Reader input  = new InputStreamReader(System.in);
	    AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
	    UnidadLexica unidad = null;
	    do {
	    	try {  
		        unidad = al.sigToken();
		        imprime(unidad);
	    	}
	    	catch(AnalizadorLexicoTiny.ECaracterInesperado e) {
	            System.out.println("ERROR");
	    	}
	    }
	    while (unidad.clase() != ClaseLexica.EOF);
	}        
} 
