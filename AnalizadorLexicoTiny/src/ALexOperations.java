import componentesLexicos.ClaseLexica;
import componentesLexicos.UnidadLexica;
import componentesLexicos.UnidadLexicaMultivaluada;
import componentesLexicos.UnidadLexicaUnivaluada;

public class ALexOperations {
	
	private AnalizadorLexicoTiny alex;
	
	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;
	}

	private UnidadLexica unidadSUMA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.SUMA);
	}
	
	private UnidadLexica unidadRESTA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.RESTA);
	}
	
	private UnidadLexica unidadMULTIPLICACION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MULTIPLICACION);
	}
	
	private UnidadLexica unidadDIVISION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DIVISION);
	}
	
	private UnidadLexica unidadMODULO() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MODULO);
	}
	
	private UnidadLexica unidadMENOR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOR);
	}
	
	private UnidadLexica unidadMAYOR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYOR);
	}
	
	private UnidadLexica unidadMENOR_IGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOR_IGUAL);
	}
	
	private UnidadLexica unidadMAYOR_IGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYOR_IGUAL);
	}
	
	private UnidadLexica unidadDESIGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DESIGUAL);
	}
	
	private UnidadLexica unidadASIGNACION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ASIGNACION);
	}
	
	private UnidadLexica unidadIGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IGUAL);
	}
	
	private UnidadLexica unidadPARENTESIS_APERTURA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PARENTESIS_APERTURA);
	}
	
	private UnidadLexica unidadPARENTESIS_CIERRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PARENTESIS_CIERRE);
	}
	
	private UnidadLexica unidadLLAVE_APERTURA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_APERTURA);
	}
	
	private UnidadLexica unidadLLAVE_CIERRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_CIERRE);
	}
	
	private UnidadLexica unidadCORCHETE_APERTURA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_APERTURA);
	}
	
	private UnidadLexica unidadCORCHETE_CIERRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_CIERRE);
	}
	
	private UnidadLexica unidadPUNTO() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO);
	}
	
	private UnidadLexica unidadPUNTO_Y_COMA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO_Y_COMA);
	}
	
	private UnidadLexica unidadREFERENCIA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.REFERENCIA);
	}
	
	private UnidadLexica unidadTERMINACION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TERMINACION);
	}
	
	private UnidadLexica unidadINI_NOMBRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INI_NOMBRE);
	}
	
	private UnidadLexica unidadINT() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.INT, alex.lexema());
	}
	
	private UnidadLexica unidadREAL() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LITERAL_REAL, alex.lexema());
	}
	
	private UnidadLexica unidadEOF() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.EOF);
	}
	
	private UnidadLexica unidadID_REAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_REAL);
	}
	
	private UnidadLexica unidadID_STRING() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_STRING);
	}
	
	private UnidadLexica unidadID_OR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_OR);
	}
	
	private UnidadLexica unidadID_NOT() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_NOT);
	}
	
	private UnidadLexica unidadID_IF() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_IF);
	}
	
	private UnidadLexica unidadID_ELSE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_ELSE);
	}
	
	private UnidadLexica unidadID_NEW() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_NEW);
	}
	
	private UnidadLexica unidadID_DELETE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_DELETE);
	}
	
	private UnidadLexica unidadID_TYPE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_TYPE);
	}
	
	private UnidadLexica unidadFALSE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FALSE);
	}
	
	private UnidadLexica unidadID_BOOL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_BOOL);
	}
	
	private UnidadLexica unidadID_NULL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_NULL);
	}
	
	private UnidadLexica unidadID_WHILE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_WHILE);
	}
	
	private UnidadLexica unidadID_READ() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_READ);
	}
	
	private UnidadLexica unidadID_CALL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_CALL);
	}
	
	private UnidadLexica unidadID_NL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_NL);
	}
	
	private UnidadLexica unidadID() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.IDENTIFICADOR, alex.lexema());
	}
	
	private void error() {
		System.err.println("("+alex.fila()+','+alex.columna()+"):Caracter inesperado");
		System.exit(1);
	}
}
