import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import alex.UnidadLexica;

public class Main {

	private static void imprime(UnidadLexica unidad) {
		try {
			System.out.println(unidad.lexema());
		} catch (UnsupportedOperationException e) {
			System.out.println(unidad.clase().getImage());
		}
	}	

	public static void main(String[] args) throws FileNotFoundException, IOException {
	    Reader input  = new InputStreamReader(new FileInputStream(new File("./src/entrada.txt")));
	    AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
	    UnidadLexica unidad = null;
	    do {
	    	try {  
		        unidad = al.sigToken();
		        imprime(unidad);
	    	}
	    	catch(AnalizadorLexicoTiny.ECaracterInesperado e) {
	            System.out.println("ERROR: "+ e.getMessage());
	    	}
	    }
	    while (unidad == null || unidad.clase() != ClaseLexica.EOF);
	}        
}
