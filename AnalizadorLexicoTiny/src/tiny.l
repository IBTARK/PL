%%
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode

%{
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = {digitoPositivo}{digito}*
parteDecimal = {digito}* {digitoPositivo}
comentario = #[^\n]* 
evalua = evalua
donde = donde
identificador = {letra}({letra}|{digito})*
numeroReal = [\+,\-]?{parteEntera}\.{parteDecimal}
operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
parentesisApertura = \(
parentesisCierre = \)
igual = \=
coma  = \,




DIVISION = /
MENOR_IGUAL = <=
MODULO = %
CORCHETE_APERTURA = [
PUNTO = .
TERMINACION = &&
BOOL = (b|B)(o|O)(o|O)(l|L)
NULL = (n|N)(u|U)(l|L)(l|L)
WHILE = (w|W)(h|H)(i|I)(l|L)(e|E)
READ = (r|R)(e|E)(a|A)(d|D)
CALL = (c|C)(a|A)(l|L)(l|L)
ENTERO = [\+,\-]?{parteEntera}
SEPARADOR = [ \t\r\b\n]
%%
{comentario}              {}
{evalua}                  {return ops.unidadEvalua();}
{donde}                   {return ops.unidadDonde();}
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
{numeroReal}              {return ops.unidadReal();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{igual}                   {return ops.unidadIgual();} 
{coma}                    {return ops.unidadComa();}
[^]                       {ops.error();} 




{DIVISION}        			{return ops.unidadDIVISION();}
{MENOR_IGUAL}        		{return ops.unidadMENOR_IGUAL();}
{MODULO}					{return ops.unidadMODULO();}
{CORCHETE_APERTURA}			{return ops.unidadCORCHETE_APERTURA();}
{PUNTO}						{return ops.unidadPUNTO();}
{TERMINACION}				{return ops.unidadTERMINACION();}
{BOOL}						{return ops.unidadBOOL();}
{NULL}						{return ops.unidadNULL();}
{WHILE}						{return ops.unidadWHILE();}
{READ}						{return ops.unidadREAD();}
{CALL}						{return ops.unidadCALL();}
{ENTERO}					{return ops.unidadENTERO();}
{SEPARADOR}					{}