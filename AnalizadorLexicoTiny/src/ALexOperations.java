import componentesLexicos.ClaseLexica;
import componentesLexicos.UnidadLexica;
import componentesLexicos.UnidadLexicaMultivaluada;
import componentesLexicos.UnidadLexicaUnivaluada;

public class ALexOperations {
	
	private AnalizadorLexicoTiny alex;
	
	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;
	}

	
//*******************************************************************
//Identificación de las clases léxicas
	
	
	/**
	 * Constructor del componente léxico SUMA
	 * 
	 * @return componente léxico SUMA
	 */
	private UnidadLexica unidadSUMA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.SUMA);
	}
	
	/**
	 * Constructor del componente léxico RESTA
	 * 
	 * @return componente léxico RESTA
	 */
	private UnidadLexica unidadRESTA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.RESTA);
	}
	
	/**
	 * Constructor del componente léxico MULTIPLICACION
	 * 
	 * @return componente léxico MULTIPLICACION
	 */
	private UnidadLexica unidadMULTIPLICACION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MULTIPLICACION);
	}
	
	/**
	 * Constructor del componente léxico DIVISION
	 * 
	 * @return componente léxico DIVISION
	 */
	private UnidadLexica unidadDIVISION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DIVISION);
	}
	
	/**
	 * Constructor del componente léxico MODULO
	 * 
	 * @return componente léxico MODULO
	 */
	private UnidadLexica unidadMODULO() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MODULO);
	}
	
	/**
	 * Constructor del componente léxico MENOR
	 * 
	 * @return componente léxico MENOR
	 */
	private UnidadLexica unidadMENOR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOR);
	}
	
	/**
	 * Constructor del componente léxico MAYOR
	 * 
	 * @return componente léxico MAYOR
	 */
	private UnidadLexica unidadMAYOR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYOR);
	}
	
	/**
	 * Constructor del componente léxico MENOR_IGUAL
	 * 
	 * @return componente léxico MENOR_IGUAL
	 */
	private UnidadLexica unidadMENOR_IGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOR_IGUAL);
	}
	
	/**
	 * Constructor del componente léxico MAYOR_IGUAL
	 * 
	 * @return componente léxico MAYOR_IGUAL
	 */
	private UnidadLexica unidadMAYOR_IGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYOR_IGUAL);
	}
	
	/**
	 * Constructor del componente léxico DESIGUAL
	 * 
	 * @return componente léxico DESIGUAL
	 */
	private UnidadLexica unidadDESIGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DESIGUAL);
	}
	
	/**
	 * Constructor del componente léxico IGUAL
	 * 
	 * @return componente léxico IGUAL
	 */
	private UnidadLexica unidadASIGNACION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ASIGNACION);
	}
	
	/**
	 * Constructor del componente léxico IGUAL
	 * 
	 * @return componente léxico IGUAL
	 */
	private UnidadLexica unidadIGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IGUAL);
	}
	
	/**
	 * Constructor del componente léxico PARENTESIS_APERTURA
	 * 
	 * @return componente léxico PARENTESIS_APERTURA
	 */
	private UnidadLexica unidadPARENTESIS_APERTURA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PARENTESIS_APERTURA);
	}
	
	/**
	 * Constructor del componente léxico PARENTESIS_CIERRE
	 * 
	 * @return componente léxico PARENTESIS_CIERRE
	 */
	private UnidadLexica unidadPARENTESIS_CIERRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PARENTESIS_CIERRE);
	}
	
	/**
	 * Constructor del componente léxico LLAVE_APERTURA
	 * 
	 * @return componente léxico LLAVE_APERTURA
	 */
	private UnidadLexica unidadLLAVE_APERTURA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_APERTURA);
	}
	
	/**
	 * Constructor del componente léxico LLAVE_CIERRE
	 * 
	 * @return componente léxico LLAVE_CIERRE
	 */
	private UnidadLexica unidadLLAVE_CIERRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_CIERRE);
	}
	
	/**
	 * Constructor del componente léxico LLAVE_APERTURA
	 * 
	 * @return componente léxico LLAVE_APERTURA
	 */
	private UnidadLexica unidadCORCHETE_APERTURA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_APERTURA);
	}
	
	/**
	 * Constructor del componente léxico LLAVE_CIERRE
	 * 
	 * @return componente léxico LLAVE_CIERRE
	 */
	private UnidadLexica unidadCORCHETE_CIERRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_CIERRE);
	}
	
	/**
	 * Constructor del componente léxico PUNTO
	 * 
	 * @return componente léxico PUNTO
	 */
	private UnidadLexica unidadPUNTO() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO);
	}
	
	/**
	 * Constructor del componente léxico PUNTO_Y_COMA
	 * 
	 * @return componente léxico PUNTO_Y_COMA
	 */
	private UnidadLexica unidadPUNTO_Y_COMA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO_Y_COMA);
	}
	
	/**
	 * Constructor del componente léxico REFERENCIA
	 * 
	 * @return componente léxico REFERENCIA
	 */
	private UnidadLexica unidadREFERENCIA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.REFERENCIA);
	}
	
	
	/**
	 * Constructor del componente léxico TERMINACION
	 * 
	 * @return componente léxico TERMINACION
	 */
	private UnidadLexica unidadTERMINACION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TERMINACION);
	}
	
	/**
	 * Constructor del componente léxico INI_NOMBRE
	 * 
	 * @return componente léxico INI_NOMBRE
	 */
	private UnidadLexica unidadINI_NOMBRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INI_NOMBRE);
	}
	
	/**
	 * Constructor del componente léxico INT
	 * 
	 * @return componente léxico INT
	 */
	private UnidadLexica unidadINT() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.INT, alex.lexema());
	}
	
	/**
	 * Constructor del componente léxico REAL
	 * 
	 * @return componente léxico REAL
	 */
	private UnidadLexica unidadREAL() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LITERAL_REAL, alex.lexema());
	}
	
	/**
	 * Constructor del componente léxico EOF
	 * 
	 * @return componente léxico EOF
	 */
	private UnidadLexica unidadEOF() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.EOF);
	}
	
	/**
	 * Constructor del componente léxico ID_REAL
	 * 
	 * @return componente léxico ID_REAL
	 */
	private UnidadLexica unidadID_REAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_REAL);
	}
	
	/**
	 * Constructor del componente léxico ID_STRING
	 * 
	 * @return componente léxico ID_STRING
	 */
	private UnidadLexica unidadID_STRING() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_STRING);
	}
	
	/**
	 * Constructor del componente léxico NOT
	 * 
	 * @return componente léxico NOT
	 */
	private UnidadLexica unidadID_NOT() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_NOT);
	}
	
	/**
	 * Constructor del componente léxico ID_ELSE
	 * 
	 * @return componente léxico ID_ELSE
	 */
	private UnidadLexica unidadID_ELSE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_ELSE);
	}
	
	/**
	 * Constructor del componente léxico ID_DELETE
	 * 
	 * @return componente léxico ID_DELETE
	 */
	private UnidadLexica unidadID_DELETE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_DELETE);
	}
	
	/**
	 * Constructor del componente léxico ID_TYPE
	 * 
	 * @return componente léxico ID_TYPE
	 */
	private UnidadLexica unidadID_TYPE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_TYPE);
	}
	
	/**
	 * Constructor del componente léxico BOOL
	 * 
	 * @return componente léxico BOOL
	 */
	private UnidadLexica unidadID_BOOL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_BOOL);
	}
	
	/**
	 * Constructor del componente léxico NULL
	 * 
	 * @return componente léxico NULL
	 */
	private UnidadLexica unidadID_NULL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_NULL);
	}
	
	/**
	 * Constructor del componente léxico WHILE
	 * 
	 * @return componente léxico WHILE
	 */
	private UnidadLexica unidadID_WHILE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_WHILE);
	}
	
	/**
	 * Constructor del componente léxico READ
	 * 
	 * @return componente léxico READ
	 */
	private UnidadLexica unidadID_READ() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_READ);
	}
	
	/**
	 * Constructor del componente léxico CALL
	 * 
	 * @return componente léxico CALL
	 */
	private UnidadLexica unidadID_CALL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_CALL);
	}
	
	/**
	 * Determinación del componente léxico asociado con el reconocimiento de un identificador
	 * 
	 * @return componente léxico asociado a una palabra reservada o un identificador
	 */
	private UnidadLexica unidadID() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.IDENTIFICADOR, alex.lexema());
	}
	
//*******************************************************************
//Tratamiento de errores
	
	
	/**
	 * Tratamiento de error léxico (tratamiento de errores simple)
	 */
	private void error() {
		System.err.println("("+alex.fila()+','+alex.columna()+"):Caracter inesperado");
		System.exit(1);
	}
}
