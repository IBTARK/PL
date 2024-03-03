package alex;

import java_cup.runtime.Symbol;

public abstract class UnidadLexica extends Symbol {
	protected ClaseLexica clase;
	private int fila;
	private int columna;
	
	public UnidadLexica(int fila, int columna, ClaseLexica clase, String lexema) {
		super(clase.ordinal(), lexema);
		this.fila = fila;
		this.columna = columna;
		this.clase = clase;
	}
	public ClaseLexica clase (){
		return clase;
	}
	
	public abstract String lexema();
	
	public int fila(){
		return fila;
	}
	
	public int columna(){
		return columna;
	}	
}
