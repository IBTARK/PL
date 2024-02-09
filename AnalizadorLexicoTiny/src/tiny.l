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
  return ops.unidadEOF();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z]|_)
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

division = /
menor_igual = <=
modulo = %
corchete_apertura = \[
punto = .
terminacion = &&
bool = (b|B)(o|O)(o|O)(l|L)
null = (n|N)(u|U)(l|L)(l|L)
while = (w|W)(h|H)(i|I)(l|L)(e|E)
read = (r|R)(e|E)(a|A)(d|D)
call = (c|C)(a|A)(l|L)(l|L)
entero = [\+,\-]?{parteEntera}
separador = [ \t\r\b\n]

resta = \-
menor = <
desigual = !=
parentesis_apertura = \(
llave_apertura = \{
punto_coma = \;
int = (i|I)(n|N)(t|T)
false = (f|F)(a|A)(l|L)(s|S)(e|E)
if = (i|I)(f|F)
new = (n|N)(e|E)(w|W) 
or = (o|O)(r|R)
nl = (n|N)(l|L)
literal_cadena = " ([^\"])* "

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



{multiplicacion}			{return ops.unidadMULTIPLICACION();}
{mayor}						{return ops.unidadMAYOR();}
{igual_igual}				{return ops.unidadIGUAL();}
{parentesis_cierre}			{return ops.unidadPARENTESIS_CIERRE();}
{llave_cierre}				{return ops.unidadLLAVE_CIERRE();}
{referencia}				{return ops.unidadREFERENCIA();}
{real}						{return ops.unidadID_REAL();}
{string}					{return ops.unidadID_STRING();}
{else}						{return ops.unidadID_ELSE();}
{delete}					{return ops.unidadID_DELETE();}
{type}						{return ops.unidadID_TYPE();}
{not}						{return ops.unidadID_NOT();}
{identificador}				{return ops.unidadID();}

{division}        			{return ops.unidadDIVISION();}
{menor_igual}        		{return ops.unidadMENOR_IGUAL();}
{modulo}					{return ops.unidadMODULO();}
{corchete_apertura}			{return ops.unidadCORCHETE_APERTURA();}
{punto}						{return ops.unidadPUNTO();}
{terminacion}				{return ops.unidadTERMINACION();}
{bool}						{return ops.unidadID_BOOL();}
{null}						{return ops.unidadID_NULL();}
{while}						{return ops.unidadID_WHILE();}
{read}						{return ops.unidadID_READ();}
{call}						{return ops.unidadID_CALL();}
{entero}					{return ops.unidadINT();}
{separador}					{}

{resta}                     {return ops.unidadRESTA();}
{menor}						{return ops.unidadMENOR();}
{desigual}					{return ops.unidadDESIGUAL();}
{parentesis_apertura} 		{return ops.unidadPARENTESIS_APERTURA();}
{llave_apertura} 			{return ops.unidadLLAVE_APERTURA();}
{punto_coma} 				{return ops.unidadPUNTO_Y_COMA();}
{int}						{return ops.unidadINT();}
{false} 					{return ops.unidadFALSE();}
{if}						{return ops.unidadID_IF();}
{new} 						{return ops.unidadID_NEW();}
{or}						{return ops.unidadID_OR();}
{nl}						{return ops.unidadID_NL();}
{literal_cadena}			{} //esto no se si debde devolver algo