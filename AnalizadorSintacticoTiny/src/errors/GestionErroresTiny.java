package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {
   public class ErrorLexico extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ErrorLexico(String msg) {
           super(msg);
       }
   } 
   public class ErrorSintactico extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ErrorSintactico(String msg) {
           super(msg);
       }
   } 
   public void errorLexico(int fila, int columna, String lexema) {
     throw new ErrorLexico("ERROR fila "+fila+": Caracter inesperado: "+lexema); 
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     throw new ErrorSintactico("ERROR fila "+unidadLexica.fila()+", columna "+unidadLexica.columna()+" : Elemento inesperado "+unidadLexica.lexema());
   }
}
