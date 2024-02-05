import java.io.IOException;
import java.io.Reader;

import AFD.Estado;
import componentesLexicos.ClaseLexica;
import componentesLexicos.UnidadLexica;
import componentesLexicos.UnidadLexicaMultivaluada;
import componentesLexicos.UnidadLexicaUnivaluada;

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
				case REC_ID:
					if(hayLetra() || hayDigito()) transita(Estado.REC_ID);
					else return unidadID();
					break;
				case REC_CERO:
					if (hayDecimal()) transita(Estado.DECIMAL);
					else if (hayExponente()) transita(Estado.EXPONENCIAL);
					else return unidadINT();
					break;
				case REC_ENTERO:
					if (hayDigito()) transita(Estado.REC_ENTERO);
					else if (hayDecimal()) transita(Estado.DECIMAL);
					else if (hayExponente()) transita(Estado.EXPONENCIAL);
					else return unidadINT();
					break;
				case EXP_SIGNO:
					if (hayCero()) transita(Estado.REC_EXP_0);
					else if (hayDigitoPositivo()) transita(Estado.REC_EXP);
					else error();
					break;
				case REC_EXP:
					if(hayDigito()) transita(Estado.REC_EXP);
					else return unidadREAL();
					break;
				case DECIMAL:
					if(hayDigitoPositivo()) transita(Estado.REC_DECIMAL);
					else if(hayCero()) transita(Estado.REC_0_DECIMAL);
					break;
				case EXPONENCIAL:
					if(hayDigitoPositivo()) transita(Estado.REC_EXP);
					else if(hayMas() || hayMenos()) transita(Estado.EXP_SIGNO);
					else if(hayCero()) transita(Estado.REC_EXP_0);
					else error();
					break;
				case _0_DECIMAL:
					if(hayCero()) transita(Estado._0_DECIMAL);
					else if(hayDigitoPositivo()) transita(Estado.REC_DECIMAL);
					else error();
					break;
				case REC_MAS:
					if(hayCero()) transita(Estado.REC_CERO);
					else if(hayDigitoPositivo()) transita(Estado.REC_ENTERO);
					else return unidadSUMA();
					break;
				case REC_MENOS:
					if(hayCero()) transita(Estado.REC_CERO);
					else if(hayDigitoPositivo()) transita(Estado.REC_ENTERO);
					else return unidadRESTA();
					break;
				case INI_TERMINACION:
					if(hayAmpersand()) transita(Estado.REC_TERMINACION);
					else error();
					break;
				case REC_L_APERTURA:
					return unidadLLAVE_APERTURA();
				case REC_L_CIERRE:
					return unidadLLAVE_CIERRE();
				case REC_DISTINTO:
					return unidadDESIGUAL();
				case REC_MENOR:
					if(hayIgual()) transita(Estado.REC_MENOR_IGUAL);
					else return unidadMENOR();
					break;
				case REC_MENOR_IGUAL:
					return unidadMENOR_IGUAL();
				case EXCLAMACION:
					if(hayIgual()) transita(Estado.REC_DISTINTO);
					else error();
					break;
				case REC_MUL:
					return unidadMULTIPLICACION();
				case REC_DIV:
					return unidadDIVISION();
				case REC_PUNTO_COMA:
					return unidadPUNTO_Y_COMA();
				case REC_IGUAL:
					if (hayIgual()) transita(Estado.REC_IGUAL_IGUAL);
					else error();
					break;
				case REC_MAYOR:
					if (hayIgual()) transita(Estado.REC_MAYOR_IGUAL);
					else return unidadMAYOR();
					break;
				case REC_P_APERTURA:
					return unidadPARENTESIS_APERTURA();
				case REC_NOMBRE:
					return unidadINI_NOMBRE();
				case REC_COMENTARIO:
					if (haySaltoLinea()) transitaIgnorando(Estado.INICIO);
					else transitaIgnorando(Estado.REC_COMENTARIO);
					break;
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
	
//*******************************************************************
//Métodos auxiliares
	
	/**
	 * Indica si el siguiente carácter es una letra ([a-z, A-Z] | _)
	 * 
	 * @return True si el siguiente carácter es una letra
	 */
	private boolean hayLetra() {
		return sigCar >= 'a' && sigCar <= 'z' || sigCar >= 'A' && sigCar <= 'z' || sigCar == '_';
	}
	
	/**
	 * Indica si el siguiente carácter es un dígito positivo [1-9]
	 * 
	 * @return True si el siguiente carácter es un dígitio positivo
	 */
	private boolean hayDigitoPositivo() {
		//49: representación del 1 en ASCCI 
		//57: representación del 9 en ASCCI
		return sigCar >= 49 && sigCar <= 57;
	}
	
	/**
	 * Indica si el siguiente carácter es un dígito [0-9]
	 * 
	 * @return True si el siguiente carácter es un dígitio
	 */
	private boolean hayDigito() {
		//48: representación del 0 en ASCCI 
		//57: representación del 9 en ASCCI
		return sigCar >= 48 && sigCar <= 57;
	}
	
	/**
	 * Indica si el siguiente carácter es el 0
	 * 
	 * @return True si el siguiente carácter el 0
	 */
	private boolean hayCero() {
		//48: representación del 0 en ASCCI 
		return sigCar == 48;
	}
	
	/**
	 * Indica si el siguiente carácter es el +
	 * 
	 * @return True si el siguiente carácter es el +
	 */
	private boolean hayMas() {
		//43: representación del + en ASCCI
		return sigCar == 43;
	}
	
	/**
	 * Indica si el siguiente carácter es el -
	 * 
	 * @return True si el siguiente carácter es el -
	 */
	private boolean hayMenos() {
		//45: representación del - en ASCCI
		return sigCar == 45;
	}
	
	/**
	 * Indica si el siguiente carácter es el *
	 * 
	 * @return True si el siguiente carácter es el *
	 */
	private boolean hayMultiplicacion() {
		//42: representación del * en ASCCI
		return sigCar == 42;
	}
	
	/**
	 * Indica si el siguiente carácter es el /
	 * 
	 * @return True si el siguiente carácter es el /
	 */
	private boolean hayDivision() {
		//47: representación del / en ASCCI
		return sigCar == 47;
	}
	
	/**
	 * Indica si el siguiente carácter es el &
	 * 
	 * @return True si el siguiente carácter es el &
	 */
	private boolean hayAmpersand() {
		//38: representación del & en ASCCI
		return sigCar == 38;
	}
	
	/**
	 * Indica si el siguiente carácter es el =
	 * 
	 * @return True si el siguiente carácter es el =
	 */
	private boolean hayIgual() {
		//61: representación del = en ASCCI
		return sigCar == 61;
	}
	
	/**
	 * Indica si el siguiente carácter es un salto de línea
	 * 
	 * @return True si el siguiente carácter es un salto de línea
	 */
	private boolean haySaltoLinea() {
		return sigCar == '\n';
	}
	
	/**
	 * Indica si el siguiente carácter es un salto de línea
	 * 
	 * @return True si el siguiente carácter es un salto de línea
	 */
	private boolean hayDecimal() {
		return sigCar == '.';
	}
	
	/**
	 * Indica si el siguiente carácter es un salto de línea
	 * 
	 * @return True si el siguiente carácter es un salto de línea
	 */
	private boolean hayExponente() {
		return sigCar == 'e' || sigCar == 'E';
	}
	
//*******************************************************************
//Identificación de las clases léxicas
	
	
	/**
	 * Constructor del componente léxico SUMA
	 * 
	 * @return componente léxico SUMA
	 */
	private UnidadLexica unidadSUMA() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.SUMA);
	}
	
	/**
	 * Constructor del componente léxico RESTA
	 * 
	 * @return componente léxico RESTA
	 */
	private UnidadLexica unidadRESTA() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.RESTA);
	}
	
	/**
	 * Constructor del componente léxico MULTIPLICACION
	 * 
	 * @return componente léxico MULTIPLICACION
	 */
	private UnidadLexica unidadMULTIPLICACION() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MULTIPLICACION);
	}
	
	/**
	 * Constructor del componente léxico DIVISION
	 * 
	 * @return componente léxico DIVISION
	 */
	private UnidadLexica unidadDIVISION() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DIVISION);
	}
	
	/**
	 * Constructor del componente léxico ASIGNACION
	 * 
	 * @return componente léxico ASIGNACION
	 */
	private UnidadLexica unidadASIGNACION() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.ASIGNACION);
	}
	
	/**
	 * Constructor del componente léxico MENOR
	 * 
	 * @return componente léxico MENOR
	 */
	private UnidadLexica unidadMENOR() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOR);
	}
	
	/**
	 * Constructor del componente léxico MAYOR
	 * 
	 * @return componente léxico MAYOR
	 */
	private UnidadLexica unidadMAYOR() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYOR);
	}
	
	/**
	 * Constructor del componente léxico MENOR_IGUAL
	 * 
	 * @return componente léxico MENOR_IGUAL
	 */
	private UnidadLexica unidadMENOR_IGUAL() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOR_IGUAL);
	}
	
	/**
	 * Constructor del componente léxico MAYOR_IGUAL
	 * 
	 * @return componente léxico MAYOR_IGUAL
	 */
	private UnidadLexica unidadMAYOR_IGUAL() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYOR_IGUAL);
	}
	
	/**
	 * Constructor del componente léxico DESIGUAL
	 * 
	 * @return componente léxico DESIGUAL
	 */
	private UnidadLexica unidadDESIGUAL() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DESIGUAL);
	}
	
	/**
	 * Constructor del componente léxico IGUAL
	 * 
	 * @return componente léxico IGUAL
	 */
	private UnidadLexica unidadIGUAL() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.IGUAL);
	}
	
	/**
	 * Constructor del componente léxico PARENTESIS_APERTURA
	 * 
	 * @return componente léxico PARENTESIS_APERTURA
	 */
	private UnidadLexica unidadPARENTESIS_APERTURA() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PARENTESIS_APERTURA);
	}
	
	/**
	 * Constructor del componente léxico PARENTESIS_CIERRE
	 * 
	 * @return componente léxico PARENTESIS_CIERRE
	 */
	private UnidadLexica unidadPARENTESIS_CIERRE() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PARENTESIS_CIERRE);
	}
	
	/**
	 * Constructor del componente léxico LLAVE_APERTURA
	 * 
	 * @return componente léxico LLAVE_APERTURA
	 */
	private UnidadLexica unidadLLAVE_APERTURA() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.LLAVE_APERTURA);
	}
	
	/**
	 * Constructor del componente léxico LLAVE_CIERRE
	 * 
	 * @return componente léxico LLAVE_CIERRE
	 */
	private UnidadLexica unidadLLAVE_CIERRE() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.LLAVE_CIERRE);
	}
	
	/**
	 * Constructor del componente léxico PUNTO_Y_COMA
	 * 
	 * @return componente léxico PUNTO_Y_COMA
	 */
	private UnidadLexica unidadPUNTO_Y_COMA() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PUNTO_Y_COMA);
	}
	
	
	/**
	 * Constructor del componente léxico TERMINACION
	 * 
	 * @return componente léxico TERMINACION
	 */
	private UnidadLexica unidadTERMINACION() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.TERMINACION);
	}
	
	/**
	 * Constructor del componente léxico INI_NOMBRE
	 * 
	 * @return componente léxico INI_NOMBRE
	 */
	private UnidadLexica unidadINI_NOMBRE() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.INI_NOMBRE);
	}
	
	
	/**
	 * Constructor del componente léxico INT
	 * 
	 * @return componente léxico INT
	 */
	private UnidadLexica unidadINT() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.INT, lex.toString());
	}
	
	/**
	 * Constructor del componente léxico REAL
	 * 
	 * @return componente léxico REAL
	 */
	private UnidadLexica unidadREAL() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.REAL, lex.toString());
	}
	
	/**
	 * Determinación del componente léxico asociado con el reconocimiento de un identificador
	 * 
	 * @return componente léxico asociado a una palabra reservada o un identificador
	 */
	private UnidadLexica unidadID() {
		//El leguaje no distingue entre minúsculas y mayúsculas en las palabras reservadas
		switch(lex.toString().toUpperCase()) {
			case "INT":
				return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.INT);
			case "REAL":
				return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.REAL);
			case "BOOL":
				return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.BOOL);
			case "TRUE":
				return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.TRUE);
			case "FALSE":
				return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FALSE);
			case "AND":
				return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.AND);
			case "OR":
				return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OR);
			case "NOT":
				return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.NOT);
			default://En los identificadores si que se distingue entre mayúsculas y minúsculas
				return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.IDENTIFICADOR, lex.toString());
		}
	}
	
//*******************************************************************
//Tratamiento de errores
	
	
	/**
	 * Tratamiento de error léxico (tratamiento de errores simple)
	 */
	private void error() {
		System.err.println("("+filaActual+','+columnaActual+"):Caracter inexperado");
		System.exit(1);
	}
}


