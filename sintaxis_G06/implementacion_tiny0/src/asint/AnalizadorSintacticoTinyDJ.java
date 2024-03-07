package asint;

import alex.UnidadLexica;
import java.io.IOException;
import java.io.Reader;

public class AnalizadorSintacticoTinyDJ extends AnalizadorSintacticoTiny {
	
    public AnalizadorSintacticoTinyDJ(Reader input) throws IOException {
    	super(input); 
    }
       
    protected final void traza_emparejamiento(UnidadLexica unidad) {
    	try {
 			System.out.println(unidad.lexema());
 		} catch (UnsupportedOperationException e) {
 			System.out.println(unidad.clase().getImage());
 		}
    } 
}
