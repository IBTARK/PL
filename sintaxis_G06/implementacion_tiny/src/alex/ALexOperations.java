package alex;

import asint_asc.ClaseLexica;

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
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SUMA, "+");
	}
	
	public UnidadLexica unidadRESTA() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RESTA, "-");
	}
	
	public UnidadLexica unidadMULTIPLICACION() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MULTIPLICACION, "*");
	}
	
	public UnidadLexica unidadDIVISION() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIVISION, "/");
	}
	
	public UnidadLexica unidadMODULO() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MODULO, "%");
	}
	
	public UnidadLexica unidadMENOR() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR, "<");
	}
	
	public UnidadLexica unidadMAYOR() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR, ">");
	}
	
	public UnidadLexica unidadMENOR_IGUAL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR_IGUAL, "<=");
	}
	
	public UnidadLexica unidadMAYOR_IGUAL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR_IGUAL, ">=");
	}
	
	public UnidadLexica unidadDESIGUAL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DESIGUAL, "!=");
	}
	
	public UnidadLexica unidadASIGNACION() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ASIGNACION, "=");
	}
	
	public UnidadLexica unidadIGUAL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL, "==");
	}
	
	public UnidadLexica unidadPARENTESIS_APERTURA() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PARENTESIS_APERTURA, "(");
	}
	
	public UnidadLexica unidadPARENTESIS_CIERRE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PARENTESIS_CIERRE, ")");
	}
	
	public UnidadLexica unidadLLAVE_APERTURA() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVE_APERTURA, "{");
	}
	
	public UnidadLexica unidadLLAVE_CIERRE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVE_CIERRE, "}");
	}
	
	public UnidadLexica unidadCORCHETE_APERTURA() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_APERTURA, "[");
	}
	
	public UnidadLexica unidadCORCHETE_CIERRE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_CIERRE, "]");
	}
	
	public UnidadLexica unidadPUNTO() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO, ".");
	}
	
	public UnidadLexica unidadPUNTO_Y_COMA() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO_Y_COMA, ";");
	}
	
	public UnidadLexica unidadCOMA() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA, ",");
	}
	
	public UnidadLexica unidadREFERENCIA() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REFERENCIA, "&");
	}
	
	public UnidadLexica unidadTERMINACION() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TERMINACION, "&&");
	}
	
	public UnidadLexica unidadINI_NOMBRE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INI_NOMBRE, "@");
	}
	
	public UnidadLexica unidadINT() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LITERAL_ENTERO, alex.lexema());
	}
	
	public UnidadLexica unidadREAL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LITERAL_REAL, alex.lexema());
	}
	
	public UnidadLexica unidadEOF() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF, "<EOF>");
	}
	
	public UnidadLexica unidadID_INT() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_INT, "<int>");
	}
	
	public UnidadLexica unidadID_REAL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_REAL, "<real>");
	}
	
	public UnidadLexica unidadID_STRING() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_STRING, "<string>");
	}
	
	public UnidadLexica unidadID_AND() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_AND, "<and>");
	}
	
	public UnidadLexica unidadID_OR() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_OR, "<or>");
	}
	
	public UnidadLexica unidadID_NOT() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_NOT, "<not>");
	}
	
	public UnidadLexica unidadID_IF() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_IF, "<if>");
	}
	
	public UnidadLexica unidadID_ELSE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_ELSE, "<else>");
	}
	
	public UnidadLexica unidadID_NEW() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_NEW, "<new>");
	}
	
	public UnidadLexica unidadID_DELETE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_DELETE, "<delete>");
	}
	
	public UnidadLexica unidadID_TYPE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_TYPE, "<type>");
	}
	
	public UnidadLexica unidadFALSE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSE, "<false>");
	}
	
	public UnidadLexica unidadTRUE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRUE, "<true>");
	}
	
	public UnidadLexica unidadID_BOOL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_BOOL, "<bool>");
	}
	
	public UnidadLexica unidadID_NULL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_NULL, "<null>");
	}
	
	public UnidadLexica unidadID_WHILE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_WHILE, "<while>");
	}
	
	public UnidadLexica unidadID_READ() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_READ, "<read>");
	}
	
	public UnidadLexica unidadID_PROC() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_PROC, "<proc>");
	}
	
	public UnidadLexica unidadID_STRUCT() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_STRUCT, "<struct>");
	}
	
	public UnidadLexica unidadID_WRITE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_WRITE, "<write>");
	}
	
	public UnidadLexica unidadID_CALL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_CALL, "<call>");
	}
	
	public UnidadLexica unidadID_NL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID_NL, "<nl>");
	}
	
	public UnidadLexica unidadID() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDENTIFICADOR, alex.lexema());
	}
	
	public UnidadLexica unidadINDIRECCION() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INDIRECCION, "^");
	}
	
	public UnidadLexica unidaININOMBRE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INI_NOMBRE, "@");
	}
	
	public UnidadLexica unidadCADENA() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LITERAL_CADENA, alex.lexema());
	}
	
	public void error() {
		throw new ECaracterInesperado("("+alex.fila()+','+alex.columna()+"):Caracter inesperado");
	}
}
