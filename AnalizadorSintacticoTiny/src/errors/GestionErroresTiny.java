package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {
	public static class ErrorLexico extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public ErrorLexico(String msg) {
			super(msg);
       	}
    } 
    public static class ErrorSintactico extends RuntimeException {
		private static final long serialVersionUID = 1L;
        public ErrorSintactico(String msg) {
        	super(msg);
        }
    } 
    public void errorLexico(int fila, int col, char car) {
    	throw new ErrorLexico("ERROR fila "+fila+","+col+": Caracter inesperado: "+car); 
    }  
    public void errorSintactico(UnidadLexica unidadLexica) {
    	throw new ErrorSintactico("ERROR fila "+unidadLexica.fila()+", columna "+unidadLexica.columna()+" : Elemento inesperado "+unidadLexica.lexema());
    }
    public void errorFatal(Exception e) {
    	System.out.println(e);
    	e.printStackTrace();
    	System.exit(1);
   }
}
