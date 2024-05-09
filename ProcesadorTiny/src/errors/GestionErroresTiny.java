package errors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
	
	private class ErrorSemantico {
		int fila, col;
		String msg;
		public ErrorSemantico(int f, int c, String s) {
			fila = f;
			col = c;
			msg = s;
		}
	}
	
	List<ErrorSemantico> errores = new ArrayList<>();
	boolean domJudge = false;
	public void errorVinculacion(int fila, int columna, String msg) {
		errores.add(new ErrorSemantico(fila, columna, "Errores_vinculacion fila:"+fila+" col:"+columna+ (domJudge ? "" : " : " + msg)));
	}

	public void errorPretipado(int fila, int columna, String msg) {
		errores.add(new ErrorSemantico(fila, columna, "Errores_pretipado fila:"+fila+" col:"+columna+ (domJudge ? "" : " : " + msg)));
	}

	public void errorTipado(int fila, int columna, String msg) {
		errores.add(new ErrorSemantico(fila, columna, "Errores_tipado fila:"+fila+" col:"+columna+ (domJudge ? "" : " : " + msg)));
	}
	
	public boolean hayError() {
		return !errores.isEmpty();
	}
	
	public void mostrarErrores() {
		errores.sort(new Comparator<ErrorSemantico>() {
			@Override
			public int compare(ErrorSemantico o1, ErrorSemantico o2) {
				int aux = Integer.compare(o1.fila, o2.fila);
				return aux != 0 ? aux : Integer.compare(o1.col, o2.col);
			}
		});
		for (ErrorSemantico str : errores)
			System.out.println(str.msg);
	}
	
	public void cancelarUltimo() {
		errores.remove(errores.size()-1);
	}
	
	public void modoDomJudge(boolean b) {
		domJudge = b;
	}
}
