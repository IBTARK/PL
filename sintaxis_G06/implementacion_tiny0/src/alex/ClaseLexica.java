package alex;

public enum ClaseLexica {
	SUMA("+"), 
	RESTA("-"), 
	MULTIPLICACION("*"), 
	DIVISION("/"), 
	AND("<and>"), 
	OR("<or>"), 
	NOT("<not>"), 
	ASIGNACION("="), 
	MENOR("<"), 
	MAYOR(">"), 
	MENOR_IGUAL("<="), 
	MAYOR_IGUAL(">="), 
	DESIGUAL("!="), 
	IGUAL("=="), 
	INT("<int>"), 
	REAL("<real>"), 
	BOOL("<bool>"), 
	TRUE("<true>"), 
	FALSE("<false>"), 
	PARENTESIS_APERTURA("("), 
	PARENTESIS_CIERRE(")"), 
	LLAVE_APERTURA("{"), 
	LLAVE_CIERRE("}"), 
	PUNTO_Y_COMA(";"), 
	TERMINACION("&&"), 
	INI_NOMBRE("@"), 
	LITERAL_ENTERO, 
	LITERAL_REAL, 
	IDENTIFICADOR, 
	EOF("<EOF>"), 
	;
	
	private String image;
	
	public String getImage() {
	     return image;
	 }
	
	 private ClaseLexica() {
	     image = toString();
	 }
	 
	 private ClaseLexica(String image) {
	    this.image = image;  
	 }
}
