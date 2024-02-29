package alex;

public class UnidadLexicaUnivaluada extends UnidadLexica {
	
	public UnidadLexicaUnivaluada(int fila, int columna, ClaseLexica clase) {
		super(fila,columna,clase,clase.getImage());
	}
	
	public String lexema(){
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return "[clase:"+ clase() + ",fila:" + fila() + ",col:" + columna() + "]";
	}
}
