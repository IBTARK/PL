import java.io.IOException;
import java.io.Reader;

import AFD.Estado;
import componentesLexicos.ClaseLexica;
import componentesLexicos.UnidadLexica;
import componentesLexicos.UnidadLexicaMultivaluada;

public class AnalizadorLexicoTiny {
	private Reader input; // Flujo de entrada
	private StringBuffer lex; // Lexema del componente que se está reconociendo
	private int sigCar; // Siguiente carácter a procesar
	private int filaInicio; // Fila de inicio del componente léxico
	private int columnaInicio; // Columna de inicio del componente léxico
	private int filaActual; // Fila en el punto de lectura actual
	private int columnaActual; // Columna en el punto de lectura actual
	private Estado estado; // Estado del autómata
	
	// Secuencia de caracteres que representan el fin de línea en la plataforma (LF en Unix,
		// CR+LF en MS Windows ...)
	private static String NL = System.getProperty("line.separator");
	
	public AnalizadorLexicoTiny(Reader input) throws IOException {
		this.input = input;
		lex = new StringBuffer();
		sigCar = input.read();
		filaActual=1;
		columnaActual=1;
	}
	
	private void saltaFinDeLinea() throws IOException {
		for (int i=1; i < NL.length(); i++) {
			sigCar = input.read();
			if (sigCar != NL.charAt(i)) error();
		}
		sigCar = '\n';
	}
	
	private void sigCar() throws IOException {
		sigCar = input.read();
		// Si comienzo fin de línea, reconocerlo. Como resultado sigCar se fija a ‘\n’
		if (sigCar == NL.charAt(0)) saltaFinDeLinea();
		if (sigCar == '\n') {
			filaActual++;
			columnaActual=0;
		}
		else {
			columnaActual++;
		}
	}
	
	public UnidadLexica sigToken() throws IOException {
		estado = Estado.INICIO;
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		lex.delete(0,lex.length());
		
		while(true) {
			switch(estado) {
			
			}
		}
	}
	
	private void transita(Estado sig) throws IOException {
		lex.append((char)sigCar);
		sigCar();
		estado = sig;
	}
	
	private void transitaIgnorando(Estado sig) throws IOException {
		sigCar();
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		estado = sig;
	}
	
	
	/**
	 * Constructor del componente léxico INT
	 * 
	 * @return componente léxico INT
	 */
	private UnidadLexica unidadInt() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.INT, lex.toString());
	}
	
	/**
	 * Determinación del componente léxico asociado con el reconocimiento de un identificador
	 * @return
	 */
	private UnidadLexica unidadId() {
		switch(lex.toString()) {
		
		}
		
		return null;
	}
	
	/**
	 * Tratamiento de error léxico (tratamiento de errores simple)
	 */
	private void error() {
		System.err.println("("+filaActual+','+columnaActual+"):Caracter inexperado");
		System.exit(1);
	}
}


