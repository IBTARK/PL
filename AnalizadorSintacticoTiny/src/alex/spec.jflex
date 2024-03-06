package alex;

import errors.GestionErroresTiny; 

%%
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode
%public
%cup

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEOF();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z]|_)
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = {digitoPositivo}{digito}*|0
parteDecimal = {digito}*{digitoPositivo}|0

multiplicacion = \*
mayor = >
igual_igual = ==
parentesis_cierre = \)
llave_cierre = \}
referencia = &
real = (r|R)(e|E)(a|A)(l|L)
string = (s|S)(t|T)(r|R)(i|I)(n|N)(g|G)
else = (e|E)(l|L)(s|S)(e|E)
delete = (d|D)(e|E)(l|L)(e|E)(t|T)(e|E)
type = (t|T)(y|Y)(p|P)(e|E)
not = (n|N)(o|O)(t|T)
identificador = {letra}({letra}|{digito})*

division = \/
menor_igual = <=
modulo = %
corchete_apertura = \[
punto = \.
terminacion = &&
bool = (b|B)(o|O)(o|O)(l|L)
null = (n|N)(u|U)(l|L)(l|L)
while = (w|W)(h|H)(i|I)(l|L)(e|E)
read = (r|R)(e|E)(a|A)(d|D)
call = (c|C)(a|A)(l|L)(l|L)
literal_entero = [\+\-]?{parteEntera}
separador = [ \t\r\b\n]

resta = \-
menor = <
desigual = \!=
parentesis_apertura = \(
llave_apertura = \{
punto_coma = \;
int = (i|I)(n|N)(t|T)
false = (f|F)(a|A)(l|L)(s|S)(e|E)
if = (i|I)(f|F)
new = (n|N)(e|E)(w|W) 
or = (o|O)(r|R)
nl = (n|N)(l|L)
literal_cadena = \"([^\"])*\"

suma = \+
asignacion = \=
mayor_igual = \>=
indireccion = \^
corchete_cierre = \]
coma = \,
ini_nombre = \@
true = (t|T)(r|R)(u|U)(e|E)
proc = (p|P)(r|R)(o|O)(c|C)
struct = (s|S)(t|T)(r|R)(u|U)(c|C)(t|T)
write = (w|W)(r|R)(i|I)(t|T)(e|E)
and = (a|A)(n|N)(d|D)
literal_real = {literal_entero}(\.{parteDecimal}|((\.{parteDecimal})?(e|E){literal_entero}))
comentario = ##([^\n])*

%%
{int}						{return ops.unidadID_INT();}
{real}						{return ops.unidadID_REAL();}
{string}					{return ops.unidadID_STRING();}
{bool}						{return ops.unidadID_BOOL();}
{true}						{return ops.unidadTRUE();}
{false} 					{return ops.unidadFALSE();}
{null}						{return ops.unidadID_NULL();}
{while}						{return ops.unidadID_WHILE();}
{if}						{return ops.unidadID_IF();}
{else}						{return ops.unidadID_ELSE();}
{read}						{return ops.unidadID_READ();}
{write}						{return ops.unidadID_WRITE();}
{call}						{return ops.unidadID_CALL();}
{delete}					{return ops.unidadID_DELETE();}
{type}						{return ops.unidadID_TYPE();}
{new} 						{return ops.unidadID_NEW();}
{nl}						{return ops.unidadID_NL();}
{proc}						{return ops.unidadID_PROC();}
{struct}					{return ops.unidadID_STRUCT();}
{not}						{return ops.unidadID_NOT();}
{and}						{return ops.unidadID_AND();}
{or}						{return ops.unidadID_OR();}

{literal_real}				{return ops.unidadREAL();}
{literal_entero}			{return ops.unidadINT();}
{literal_cadena}			{return ops.unidadCADENA();}
{identificador}				{return ops.unidadID();}

{suma} 						{return ops.unidadSUMA();}
{resta}                     {return ops.unidadRESTA();}
{multiplicacion}			{return ops.unidadMULTIPLICACION();}
{division}        			{return ops.unidadDIVISION();}
{modulo}					{return ops.unidadMODULO();}
{indireccion} 				{return ops.unidadINDIRECCION();}
{ini_nombre}				{return ops.unidaININOMBRE();}
{referencia}				{return ops.unidadREFERENCIA();}
{coma}						{return ops.unidadCOMA();}
{punto_coma} 				{return ops.unidadPUNTO_Y_COMA();}
{punto}						{return ops.unidadPUNTO();}

{asignacion} 				{return ops.unidadASIGNACION();}
{mayor}						{return ops.unidadMAYOR();}
{menor}						{return ops.unidadMENOR();}
{mayor_igual} 				{return ops.unidadMAYOR_IGUAL();}
{menor_igual}        		{return ops.unidadMENOR_IGUAL();}
{igual_igual}				{return ops.unidadIGUAL();}
{desigual}					{return ops.unidadDESIGUAL();}

{parentesis_apertura} 		{return ops.unidadPARENTESIS_APERTURA();}
{parentesis_cierre}			{return ops.unidadPARENTESIS_CIERRE();}
{corchete_apertura}			{return ops.unidadCORCHETE_APERTURA();}
{corchete_cierre}			{return ops.unidadCORCHETE_CIERRE();}
{llave_apertura} 			{return ops.unidadLLAVE_APERTURA();}
{llave_cierre}				{return ops.unidadLLAVE_CIERRE();}

{terminacion}				{return ops.unidadTERMINACION();}
{comentario}				{}
{separador}					{}

[^]                         {errores.errorLexico(fila(), columna(), lexema());} 
