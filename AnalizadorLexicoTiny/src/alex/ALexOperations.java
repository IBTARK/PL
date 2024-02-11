package alex;
public class ALexOperations {
    
   @SuppressWarnings("serial")
   public static class ECaracterInesperado extends RuntimeException {
       public ECaracterInesperado(String msg) {
           super(msg);
       }
   }; 
	
	private AnalizadorLexicoTiny alex;
	
	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;
	}

	public UnidadLexica unidadSUMA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.SUMA);
	}
	
	public UnidadLexica unidadRESTA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.RESTA);
	}
	
	public UnidadLexica unidadMULTIPLICACION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MULTIPLICACION);
	}
	
	public UnidadLexica unidadDIVISION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DIVISION);
	}
	
	public UnidadLexica unidadMODULO() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MODULO);
	}
	
	public UnidadLexica unidadMENOR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOR);
	}
	
	public UnidadLexica unidadMAYOR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYOR);
	}
	
	public UnidadLexica unidadMENOR_IGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOR_IGUAL);
	}
	
	public UnidadLexica unidadMAYOR_IGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYOR_IGUAL);
	}
	
	public UnidadLexica unidadDESIGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DESIGUAL);
	}
	
	public UnidadLexica unidadASIGNACION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ASIGNACION);
	}
	
	public UnidadLexica unidadIGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IGUAL);
	}
	
	public UnidadLexica unidadPARENTESIS_APERTURA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PARENTESIS_APERTURA);
	}
	
	public UnidadLexica unidadPARENTESIS_CIERRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PARENTESIS_CIERRE);
	}
	
	public UnidadLexica unidadLLAVE_APERTURA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_APERTURA);
	}
	
	public UnidadLexica unidadLLAVE_CIERRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_CIERRE);
	}
	
	public UnidadLexica unidadCORCHETE_APERTURA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_APERTURA);
	}
	
	public UnidadLexica unidadCORCHETE_CIERRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_CIERRE);
	}
	
	public UnidadLexica unidadPUNTO() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO);
	}
	
	public UnidadLexica unidadPUNTO_Y_COMA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO_Y_COMA);
	}
	
	public UnidadLexica unidadCOMA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.COMA);
	}
	
	public UnidadLexica unidadREFERENCIA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.REFERENCIA);
	}
	
	public UnidadLexica unidadTERMINACION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TERMINACION);
	}
	
	public UnidadLexica unidadINI_NOMBRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INI_NOMBRE);
	}
	
	public UnidadLexica unidadINT() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LITERAL_ENTERO, alex.lexema());
	}
	
	public UnidadLexica unidadREAL() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LITERAL_REAL, alex.lexema());
	}
	
	public UnidadLexica unidadEOF() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.EOF);
	}
	
	public UnidadLexica unidadID_INT() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_INT, alex.lexema());
	}
	
	public UnidadLexica unidadID_REAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_REAL);
	}
	
	public UnidadLexica unidadID_STRING() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_STRING);
	}
	
	public UnidadLexica unidadID_AND() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_AND);
	}
	
	public UnidadLexica unidadID_OR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_OR);
	}
	
	public UnidadLexica unidadID_NOT() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_NOT);
	}
	
	public UnidadLexica unidadID_IF() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_IF);
	}
	
	public UnidadLexica unidadID_ELSE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_ELSE);
	}
	
	public UnidadLexica unidadID_NEW() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_NEW);
	}
	
	public UnidadLexica unidadID_DELETE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_DELETE);
	}
	
	public UnidadLexica unidadID_TYPE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_TYPE);
	}
	
	public UnidadLexica unidadFALSE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FALSE);
	}
	
	public UnidadLexica unidadTRUE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TRUE);
	}
	
	public UnidadLexica unidadID_BOOL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_BOOL);
	}
	
	public UnidadLexica unidadID_NULL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_NULL);
	}
	
	public UnidadLexica unidadID_WHILE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_WHILE);
	}
	
	public UnidadLexica unidadID_READ() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_READ);
	}
	
	public UnidadLexica unidadID_PROC() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_PROC);
	}
	
	public UnidadLexica unidadID_STRUCT() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_STRUCT);
	}
	
	public UnidadLexica unidadID_WRITE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_WRITE);
	}
	
	public UnidadLexica unidadID_CALL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_CALL);
	}
	
	public UnidadLexica unidadID_NL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ID_NL);
	}
	
	public UnidadLexica unidadID() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.IDENTIFICADOR, alex.lexema());
	}
	
	public UnidadLexica unidadINDIRECCION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INDIRECCION);
	}
	
	public UnidadLexica unidaININOMBRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INI_NOMBRE);
	}
	
	public UnidadLexica unidadCADENA() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LITERAL_CADENA, alex.lexema());
	}
	
	public void error() {
		throw new ECaracterInesperado("("+alex.fila()+','+alex.columna()+"):Caracter inesperado");
	}
}
