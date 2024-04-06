/* ConstructorASTs.java */
/* Generated By:JavaCC: Do not edit this line. ConstructorASTs.java */
package c_ast_descendente;
import asint.ClaseSemantica;
import asint.SintaxisAbstracta.*;

public class ConstructorASTs implements ConstructorASTsConstants {
   private ClaseSemantica sem = new ClaseSemantica();

  final public Prog analiza() throws ParseException {
    trace_call("analiza");
    try {
Prog prog;
      prog = programa();
      jj_consume_token(0);
{if ("" != null) return prog;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("analiza");
    }
}

  final public Prog programa() throws ParseException {
    trace_call("programa");
    try {
Bloque bloq;
      bloq = bloque();
{if ("" != null) return sem.prog(bloq);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("programa");
    }
}

  final public Bloque bloque() throws ParseException {
    trace_call("bloque");
    try {
Instrs instrs; Decs decs;
      jj_consume_token(34);
      decs = declaraciones();
      instrs = instrucciones();
      jj_consume_token(35);
{if ("" != null) return sem.bloq(decs, instrs);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("bloque");
    }
}

  final public Decs declaraciones() throws ParseException {
    trace_call("declaraciones");
    try {
LDecs ldecsa;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case int_id:
      case real_id:
      case bool_id:
      case string_id:
      case struct_id:
      case proc_id:
      case type_id:
      case iden:
      case 44:{
        ldecsa = lista_declaraciones();
        jj_consume_token(36);
{if ("" != null) return sem.siDecs(ldecsa);}
        break;
        }
      default:
        jj_la1[0] = jj_gen;
{if ("" != null) return sem.noDecs();}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("declaraciones");
    }
}

  final public LDecs lista_declaraciones() throws ParseException {
    trace_call("lista_declaraciones");
    try {
LDecs ldecsa; Dec dec;
      dec = declaracion();
      ldecsa = r_lista_declaraciones(sem.unaDec(dec));
{if ("" != null) return ldecsa;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_declaraciones");
    }
}

  final public LDecs r_lista_declaraciones(LDecs ldecsah) throws ParseException {
    trace_call("r_lista_declaraciones");
    try {
LDecs ldecsa; Dec dec;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 37:{
        jj_consume_token(37);
        dec = declaracion();
        ldecsa = r_lista_declaraciones(sem.muchasDecs(ldecsah,dec));
{if ("" != null) return ldecsa;}
        break;
        }
      default:
        jj_la1[1] = jj_gen;
{if ("" != null) return ldecsah;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("r_lista_declaraciones");
    }
}

  final public Dec declaracion() throws ParseException {
    trace_call("declaracion");
    try {
Token id; ParamForms params; Bloque bloque; Tipo tipo;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case proc_id:{
        jj_consume_token(proc_id);
        id = jj_consume_token(iden);
        params = parametros_formales();
        bloque = bloque();
{if ("" != null) return (Dec) sem.decProc(id.image, params, bloque).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
        }
      case type_id:{
        jj_consume_token(type_id);
        tipo = tipo();
        id = jj_consume_token(iden);
{if ("" != null) return (Dec) sem.decType(tipo, id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
        }
      case int_id:
      case real_id:
      case bool_id:
      case string_id:
      case struct_id:
      case iden:
      case 44:{
        tipo = tipo();
        id = jj_consume_token(iden);
{if ("" != null) return (Dec) sem.decVar(tipo, id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("declaracion");
    }
}

  final public ParamForms parametros_formales() throws ParseException {
    trace_call("parametros_formales");
    try {
ParamForms a;
      jj_consume_token(38);
      a = lista_parametros_formales_e();
      jj_consume_token(39);
{if ("" != null) return a;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("parametros_formales");
    }
}

  final public ParamForms lista_parametros_formales_e() throws ParseException {
    trace_call("lista_parametros_formales_e");
    try {
LParams lparams;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case int_id:
      case real_id:
      case bool_id:
      case string_id:
      case struct_id:
      case iden:
      case 44:{
        lparams = lista_parametros_formales();
{if ("" != null) return sem.siParam(lparams);}
        break;
        }
      default:
        jj_la1[3] = jj_gen;
{if ("" != null) return sem.noParam();}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_parametros_formales_e");
    }
}

  final public LParams lista_parametros_formales() throws ParseException {
    trace_call("lista_parametros_formales");
    try {
LParams lparams; ParamForm param;
      param = parametro_formal();
      lparams = r_lista_parametros_formales(sem.unParam(param));
{if ("" != null) return lparams;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_parametros_formales");
    }
}

  final public LParams r_lista_parametros_formales(LParams ah) throws ParseException {
    trace_call("r_lista_parametros_formales");
    try {
LParams lparams; ParamForm param;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 40:{
        jj_consume_token(40);
        param = parametro_formal();
        lparams = r_lista_parametros_formales(sem.muchosParams(ah, param));
{if ("" != null) return lparams;}
        break;
        }
      default:
        jj_la1[4] = jj_gen;
{if ("" != null) return ah;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("r_lista_parametros_formales");
    }
}

  final public ParamForm parametro_formal() throws ParseException {
    trace_call("parametro_formal");
    try {
Tipo tipo; ParamForm param;
      tipo = tipo();
      param = r_parametro_formal(tipo);
{if ("" != null) return param;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("parametro_formal");
    }
}

  final public ParamForm r_parametro_formal(Tipo tipoah) throws ParseException {
    trace_call("r_parametro_formal");
    try {
Token id;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 41:{
        jj_consume_token(41);
        id = jj_consume_token(iden);
{if ("" != null) return (ParamForm) sem.paramFormRef(tipoah ,id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
        }
      case iden:{
        id = jj_consume_token(iden);
{if ("" != null) return (ParamForm) sem.paramForm(tipoah ,id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("r_parametro_formal");
    }
}

  final public Tipo tipo() throws ParseException {
    trace_call("tipo");
    try {
Tipo tipoa;
      tipoa = T0();
{if ("" != null) return tipoa;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("tipo");
    }
}

  final public Tipo T0() throws ParseException {
    trace_call("T0");
    try {
Tipo tipo1a; Tipo tipoa;
      tipo1a = T1();
      tipoa = RT0(tipo1a);
{if ("" != null) return tipoa;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("T0");
    }
}

  final public Tipo RT0(Tipo tipoah) throws ParseException {
    trace_call("RT0");
    try {
Tipo tipoa; Token id;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 42:{
        jj_consume_token(42);
        id = jj_consume_token(literalEntero);
        jj_consume_token(43);
        tipoa = RT0((Tipo) sem.tArray(tipoah, id.image).ponFila(id.beginLine).ponCol(id.beginColumn));
{if ("" != null) return tipoa;}
        break;
        }
      default:
        jj_la1[6] = jj_gen;
{if ("" != null) return tipoah;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("RT0");
    }
}

  final public Tipo T1() throws ParseException {
    trace_call("T1");
    try {
Tipo tipoa;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 44:{
        jj_consume_token(44);
        tipoa = T1();
{if ("" != null) return sem.tPunt(tipoa);}
        break;
        }
      case int_id:
      case real_id:
      case bool_id:
      case string_id:
      case struct_id:
      case iden:{
        tipoa = T2();
{if ("" != null) return tipoa;}
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("T1");
    }
}

  final public Tipo T2() throws ParseException {
    trace_call("T2");
    try {
Token id; LCampos campos;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case int_id:{
        jj_consume_token(int_id);
{if ("" != null) return sem.tInt();}
        break;
        }
      case real_id:{
        jj_consume_token(real_id);
{if ("" != null) return sem.tReal();}
        break;
        }
      case bool_id:{
        jj_consume_token(bool_id);
{if ("" != null) return sem.tBool();}
        break;
        }
      case string_id:{
        jj_consume_token(string_id);
{if ("" != null) return sem.tString();}
        break;
        }
      case iden:{
        id = jj_consume_token(iden);
{if ("" != null) return (Tipo) sem.tIden(id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
        }
      case struct_id:{
        jj_consume_token(struct_id);
        jj_consume_token(34);
        campos = lista_campos();
        jj_consume_token(35);
{if ("" != null) return sem.tStruct(campos);}
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("T2");
    }
}

  final public LCampos lista_campos() throws ParseException {
    trace_call("lista_campos");
    try {
Campo camp; LCampos lcampos;
      camp = campo();
      lcampos = r_lista_campos(sem.unCamp(camp));
{if ("" != null) return lcampos;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_campos");
    }
}

  final public LCampos r_lista_campos(LCampos lcamposah) throws ParseException {
    trace_call("r_lista_campos");
    try {
LCampos lcampos; Campo camp;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 40:{
        jj_consume_token(40);
        camp = campo();
        lcampos = r_lista_campos(sem.muchosCamps(lcamposah, camp));
{if ("" != null) return lcampos;}
        break;
        }
      default:
        jj_la1[9] = jj_gen;
{if ("" != null) return lcamposah;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("r_lista_campos");
    }
}

  final public Campo campo() throws ParseException {
    trace_call("campo");
    try {
Tipo tipo; Token id;
      tipo = tipo();
      id = jj_consume_token(iden);
{if ("" != null) return (Campo) sem.campo(tipo, id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("campo");
    }
}

  final public Instrs instrucciones() throws ParseException {
    trace_call("instrucciones");
    try {
LInstrs lInstrsa;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case if_id:
      case call_id:
      case nl_id:
      case new_id:
      case read_id:
      case write_id:
      case delete_id:
      case while_id:
      case 34:
      case 45:{
        lInstrsa = lista_instrucciones();
{if ("" != null) return sem.siInstrs(lInstrsa);}
        break;
        }
      default:
        jj_la1[10] = jj_gen;
{if ("" != null) return sem.noInstrs();}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("instrucciones");
    }
}

  final public LInstrs lista_instrucciones() throws ParseException {
    trace_call("lista_instrucciones");
    try {
LInstrs lInstrsa; Instr instra;
      instra = instruccion();
      lInstrsa = r_lista_instrucciones(sem.unaInstr(instra));
{if ("" != null) return lInstrsa;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_instrucciones");
    }
}

  final public LInstrs r_lista_instrucciones(LInstrs instrah) throws ParseException {
    trace_call("r_lista_instrucciones");
    try {
LInstrs lInstrsa; Instr instra;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 37:{
        jj_consume_token(37);
        instra = instruccion();
        lInstrsa = r_lista_instrucciones(sem.muchasInstrs(instrah,instra));
{if ("" != null) return lInstrsa;}
        break;
        }
      default:
        jj_la1[11] = jj_gen;
{if ("" != null) return instrah;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("r_lista_instrucciones");
    }
}

  final public Instr instruccion() throws ParseException {
    trace_call("instruccion");
    try {
Exp exp; ParamReales param; Bloque bloq; Instr instr; Token id;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 45:{
        jj_consume_token(45);
        exp = expresion();
{if ("" != null) return sem.arrobaInstr(exp);}
        break;
        }
      case call_id:{
        jj_consume_token(call_id);
        id = jj_consume_token(iden);
        param = parametros_reales();
{if ("" != null) return (Instr) sem.procInstr(id.image, param).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
        }
      case nl_id:{
        jj_consume_token(nl_id);
{if ("" != null) return sem.nlInstr();}
        break;
        }
      case new_id:{
        jj_consume_token(new_id);
        exp = expresion();
{if ("" != null) return sem.newInstr(exp);}
        break;
        }
      case read_id:{
        jj_consume_token(read_id);
        exp = expresion();
{if ("" != null) return sem.readInstr(exp);}
        break;
        }
      case write_id:{
        jj_consume_token(write_id);
        exp = expresion();
{if ("" != null) return sem.writeInstr(exp);}
        break;
        }
      case delete_id:{
        jj_consume_token(delete_id);
        exp = expresion();
{if ("" != null) return sem.deleteInstr(exp);}
        break;
        }
      case while_id:{
        jj_consume_token(while_id);
        exp = expresion();
        bloq = bloque();
{if ("" != null) return sem.whileInstr(exp, bloq);}
        break;
        }
      case if_id:{
        jj_consume_token(if_id);
        exp = expresion();
        bloq = bloque();
        instr = r_instruccion_if(exp, bloq);
{if ("" != null) return instr;}
        break;
        }
      case 34:{
        bloq = bloque();
{if ("" != null) return sem.bloqueInstr(bloq);}
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("instruccion");
    }
}

  final public Instr r_instruccion_if(Exp exp, Bloque bloq) throws ParseException {
    trace_call("r_instruccion_if");
    try {
Bloque bloque;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case else_id:{
        jj_consume_token(else_id);
        bloque = bloque();
{if ("" != null) return sem.ifElseInstr(exp, bloq, bloque);}
        break;
        }
      default:
        jj_la1[13] = jj_gen;
{if ("" != null) return sem.ifInstr(exp, bloq);}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("r_instruccion_if");
    }
}

  final public ParamReales parametros_reales() throws ParseException {
    trace_call("parametros_reales");
    try {
ParamReales lista;
      jj_consume_token(38);
      lista = lista_expresiones_e();
      jj_consume_token(39);
{if ("" != null) return lista;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("parametros_reales");
    }
}

  final public ParamReales lista_expresiones_e() throws ParseException {
    trace_call("lista_expresiones_e");
    try {
LExp lista;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case literalEntero:
      case literalReal:
      case literalCadena:
      case not_id:
      case true_id:
      case false_id:
      case null_id:
      case iden:
      case 38:
      case 47:{
        lista = lista_expresiones();
{if ("" != null) return sem.siExp(lista);}
        break;
        }
      default:
        jj_la1[14] = jj_gen;
{if ("" != null) return sem.noExp();}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_expresiones_e");
    }
}

  final public LExp lista_expresiones() throws ParseException {
    trace_call("lista_expresiones");
    try {
LExp exps; Exp exp;
      exp = expresion();
      exps = r_lista_expresiones(sem.unaExp(exp));
{if ("" != null) return exps;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_expresiones");
    }
}

  final public LExp r_lista_expresiones(LExp ah) throws ParseException {
    trace_call("r_lista_expresiones");
    try {
LExp exps; Exp exp;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 40:{
        jj_consume_token(40);
        exp = expresion();
        exps = r_lista_expresiones(sem.muchasExp(ah, exp));
{if ("" != null) return exps;}
        break;
        }
      default:
        jj_la1[15] = jj_gen;
{if ("" != null) return ah;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("r_lista_expresiones");
    }
}

  final public Exp expresion() throws ParseException {
    trace_call("expresion");
    try {
Exp e;
      e = E0();
{if ("" != null) return e;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("expresion");
    }
}

  final public Exp E0() throws ParseException {
    trace_call("E0");
    try {
Exp e1,e2;
      e1 = E1();
      e2 = RE0(e1);
{if ("" != null) return e2;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("E0");
    }
}

  final public Exp RE0(Exp eh) throws ParseException {
    trace_call("RE0");
    try {
Exp e;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 46:{
        jj_consume_token(46);
        e = E0();
{if ("" != null) return sem.asignacion(eh, e);}
        break;
        }
      default:
        jj_la1[16] = jj_gen;
{if ("" != null) return eh;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("RE0");
    }
}

  final public Exp E1() throws ParseException {
    trace_call("E1");
    try {
Exp e1,e2;
      e2 = E2();
      e1 = RE1(e2);
{if ("" != null) return e1;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("E1");
    }
}

  final public Exp RE1(Exp eh) throws ParseException {
    trace_call("RE1");
    try {
String op; Exp e2,e1;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:{
        op = OP1();
        e2 = E2();
        e1 = RE1(sem.mkop1(op,eh,e2));
{if ("" != null) return e1;}
        break;
        }
      default:
        jj_la1[17] = jj_gen;
{if ("" != null) return eh;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("RE1");
    }
}

  final public Exp E2() throws ParseException {
    trace_call("E2");
    try {
Exp e3,re2,rec2;
      e3 = E3();
      re2 = RE2(e3);
      rec2 = REC2(re2);
{if ("" != null) return rec2;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("E2");
    }
}

  final public Exp RE2(Exp ah) throws ParseException {
    trace_call("RE2");
    try {
Exp e3;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 47:{
        jj_consume_token(47);
        e3 = E3();
{if ("" != null) return sem.resta(ah, e3);}
        break;
        }
      default:
        jj_la1[18] = jj_gen;
{if ("" != null) return ah;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("RE2");
    }
}

  final public Exp REC2(Exp ah) throws ParseException {
    trace_call("REC2");
    try {
Exp e3,rec2;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 48:{
        jj_consume_token(48);
        e3 = E3();
        rec2 = REC2(sem.suma(ah, e3));
{if ("" != null) return rec2;}
        break;
        }
      default:
        jj_la1[19] = jj_gen;
{if ("" != null) return ah;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("REC2");
    }
}

  final public Exp E3() throws ParseException {
    trace_call("E3");
    try {
Exp e4,re3;
      e4 = E4();
      re3 = RE3(e4);
{if ("" != null) return re3;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("E3");
    }
}

  final public Exp RE3(Exp ah) throws ParseException {
    trace_call("RE3");
    try {
Exp e3,e4,re3;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case and_id:{
        jj_consume_token(and_id);
        e3 = E3();
{if ("" != null) return sem.and(ah, e3);}
        break;
        }
      case or_id:{
        jj_consume_token(or_id);
        e4 = E4();
{if ("" != null) return sem.or(ah, e4);}
        break;
        }
      default:
        jj_la1[20] = jj_gen;
{if ("" != null) return ah;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("RE3");
    }
}

  final public Exp E4() throws ParseException {
    trace_call("E4");
    try {
Exp e5,e4;
      e5 = E5();
      e4 = RE4(e5);
{if ("" != null) return e4;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("E4");
    }
}

  final public Exp RE4(Exp eh) throws ParseException {
    trace_call("RE4");
    try {
String op; Exp e5,e4;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 56:
      case 57:
      case 58:{
        op = OP4();
        e5 = E5();
        e4 = RE4(sem.mkop4(op,eh,e5));
{if ("" != null) return e4;}
        break;
        }
      default:
        jj_la1[21] = jj_gen;
{if ("" != null) return eh;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("RE4");
    }
}

  final public Exp E5() throws ParseException {
    trace_call("E5");
    try {
String op; Exp e5,e6;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case not_id:
      case 47:{
        op = OP5();
        e5 = E5();
{if ("" != null) return sem.mkop5(op,e5);}
        break;
        }
      case literalEntero:
      case literalReal:
      case literalCadena:
      case true_id:
      case false_id:
      case null_id:
      case iden:
      case 38:{
        e6 = E6();
{if ("" != null) return e6;}
        break;
        }
      default:
        jj_la1[22] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("E5");
    }
}

  final public Exp E6() throws ParseException {
    trace_call("E6");
    try {
Exp e7,re6;
      e7 = E7();
      re6 = RE6(e7);
{if ("" != null) return re6;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("E6");
    }
}

  final public Exp RE6(Exp ah) throws ParseException {
    trace_call("RE6");
    try {
Exp e0,re6; Token id;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 42:{
        jj_consume_token(42);
        e0 = E0();
        jj_consume_token(43);
        re6 = RE6(sem.array(ah,e0));
{if ("" != null) return re6;}
        break;
        }
      case 49:{
        jj_consume_token(49);
        id = jj_consume_token(iden);
        re6 = RE6((Exp) sem.expCampo(ah,id.image).ponFila(id.beginLine).ponCol(id.beginColumn));
{if ("" != null) return re6;}
        break;
        }
      case 44:{
        jj_consume_token(44);
        re6 = RE6(sem.punt(ah));
{if ("" != null) return re6;}
        break;
        }
      default:
        jj_la1[23] = jj_gen;
{if ("" != null) return ah;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("RE6");
    }
}

  final public Exp E7() throws ParseException {
    trace_call("E7");
    try {
Token lit; Exp e0;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case literalEntero:{
        lit = jj_consume_token(literalEntero);
{if ("" != null) return (Exp) sem.litEnt(lit.image).ponFila(lit.beginLine).ponCol(lit.beginColumn);}
        break;
        }
      case literalReal:{
        lit = jj_consume_token(literalReal);
{if ("" != null) return (Exp) sem.litReal(lit.image).ponFila(lit.beginLine).ponCol(lit.beginColumn);}
        break;
        }
      case iden:{
        lit = jj_consume_token(iden);
{if ("" != null) return (Exp) sem.iden(lit.image).ponFila(lit.beginLine).ponCol(lit.beginColumn);}
        break;
        }
      case true_id:{
        lit = jj_consume_token(true_id);
{if ("" != null) return (Exp) sem._true().ponFila(lit.beginLine).ponCol(lit.beginColumn);}
        break;
        }
      case false_id:{
        lit = jj_consume_token(false_id);
{if ("" != null) return (Exp) sem._false().ponFila(lit.beginLine).ponCol(lit.beginColumn);}
        break;
        }
      case literalCadena:{
        lit = jj_consume_token(literalCadena);
{if ("" != null) return (Exp) sem.litCad(lit.image).ponFila(lit.beginLine).ponCol(lit.beginColumn);}
        break;
        }
      case null_id:{
        lit = jj_consume_token(null_id);
{if ("" != null) return (Exp) sem._null().ponFila(lit.beginLine).ponCol(lit.beginColumn);}
        break;
        }
      case 38:{
        jj_consume_token(38);
        e0 = E0();
        jj_consume_token(39);
{if ("" != null) return e0;}
        break;
        }
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("E7");
    }
}

  final public String OP1() throws ParseException {
    trace_call("OP1");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 50:{
        jj_consume_token(50);
{if ("" != null) return "<";}
        break;
        }
      case 51:{
        jj_consume_token(51);
{if ("" != null) return ">";}
        break;
        }
      case 52:{
        jj_consume_token(52);
{if ("" != null) return "<=";}
        break;
        }
      case 53:{
        jj_consume_token(53);
{if ("" != null) return ">=";}
        break;
        }
      case 54:{
        jj_consume_token(54);
{if ("" != null) return "==";}
        break;
        }
      case 55:{
        jj_consume_token(55);
{if ("" != null) return "!=";}
        break;
        }
      default:
        jj_la1[25] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("OP1");
    }
}

  final public String OP4() throws ParseException {
    trace_call("OP4");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 56:{
        jj_consume_token(56);
{if ("" != null) return "*";}
        break;
        }
      case 57:{
        jj_consume_token(57);
{if ("" != null) return "/";}
        break;
        }
      case 58:{
        jj_consume_token(58);
{if ("" != null) return "%";}
        break;
        }
      default:
        jj_la1[26] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("OP4");
    }
}

  final public String OP5() throws ParseException {
    trace_call("OP5");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 47:{
        jj_consume_token(47);
{if ("" != null) return "-";}
        break;
        }
      case not_id:{
        jj_consume_token(not_id);
{if ("" != null) return "not";}
        break;
        }
      default:
        jj_la1[27] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("OP5");
    }
}

  /** Generated Token Manager. */
  public ConstructorASTsTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[28];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x6f800,0x0,0x6f800,0xf800,0x0,0x0,0x0,0xf800,0xf800,0x0,0x7f80000,0x0,0x7f80000,0x8000000,0xc0010700,0x0,0x0,0x0,0x0,0x0,0x30000000,0x0,0xc0010700,0x0,0xc0000700,0x0,0x0,0x10000,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x1002,0x20,0x1002,0x1002,0x100,0x202,0x400,0x1002,0x2,0x100,0x2004,0x20,0x2004,0x0,0x8043,0x100,0x4000,0xfc0000,0x8000,0x10000,0x0,0x7000000,0x8043,0x21400,0x43,0xfc0000,0x7000000,0x8000,};
	}

  {
      enable_tracing();
  }
  /** Constructor with InputStream. */
  public ConstructorASTs(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public ConstructorASTs(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new ConstructorASTsTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 28; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 28; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public ConstructorASTs(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new ConstructorASTsTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 28; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new ConstructorASTsTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 28; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public ConstructorASTs(ConstructorASTsTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 28; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ConstructorASTsTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 28; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   trace_token(token, "");
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	   trace_token(token, " (in getNextToken)");
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[59];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 28; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 59; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  private int trace_indent = 0;
/** Enable tracing. */
  final public void enable_tracing() {
	 trace_enabled = true;
  }

/** Disable tracing. */
  final public void disable_tracing() {
	 trace_enabled = false;
  }

  protected void trace_call(String s) {
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.println("Call:	" + s);
	 }
	 trace_indent = trace_indent + 2;
  }

  protected void trace_return(String s) {
	 trace_indent = trace_indent - 2;
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.println("Return: " + s);
	 }
  }

  protected void trace_token(Token t, String where) {
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.print("Consumed token: <" + tokenImage[t.kind]);
	   if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
		 System.out.print(": \"" + TokenMgrError.addEscapes(t.image) + "\"");
	   }
	   System.out.println(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where);
	 }
  }

  protected void trace_scan(Token t1, int t2) {
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.print("Visited token: <" + tokenImage[t1.kind]);
	   if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
		 System.out.print(": \"" + TokenMgrError.addEscapes(t1.image) + "\"");
	   }
	   System.out.println(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">");
	 }
  }

}
