package alex;

public enum ClaseLexica {
	SUMA("+"), 
	RESTA("-"), 
	MULTIPLICACION("*"), 
	DIVISION("/"),  
	MODULO("%"),  
	ASIGNACION("="), 
	MENOR("<"), 
	MAYOR(">"), 
	MENOR_IGUAL("<="), 
	MAYOR_IGUAL(">="), 
	DESIGUAL("!="), 
	IGUAL("=="), 
	PUNTO("."), 
	COMA(","),
	PUNTO_Y_COMA(";"), 
	REFERENCIA("&"), 
	TERMINACION("&&"), 
	INI_NOMBRE("@"), 
	INDIRECCION("^"), 
	ID_AND("<and>"), 
	ID_OR("<or>"), 
	ID_NOT("<not>"),
	REAL("<real>"), 
	ID_BOOL("<bool>"), 
	TRUE("<true>"), 
	FALSE("<false>"), 
	PARENTESIS_APERTURA("("), 
	PARENTESIS_CIERRE(")"), 
	LLAVE_APERTURA("{"), 
	LLAVE_CIERRE("}"),
	CORCHETE_APERTURA("["), 
	CORCHETE_CIERRE("]"),
	LITERAL_ENTERO, 
	LITERAL_REAL, 
	IDENTIFICADOR,
	LITERAL_CADENA,
	EOF("EOF"),
	ID_REAL("<real>"),
	ID_IF("<if>"),
	ID_ELSE("<else>"),
	ID_NEW("<new>"),
	ID_DELETE("<delete>"),
	ID_TYPE("<type>"),
	ID_STRING("<string>"),
	ID_NULL("<null>"),
	ID_WHILE("<while>"),
	ID_READ("<read>"),
	ID_CALL("<call>"), 
	ID_NL("<nl>"), 
	ID_PROC("<proc>"), 
	ID_STRUCT("<struct>"), 
	ID_WRITE("<write>"), 	
	ID_INT("<int>"),
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
