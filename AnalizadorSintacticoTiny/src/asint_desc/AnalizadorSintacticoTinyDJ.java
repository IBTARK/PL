package asint_desc;

import java.io.Reader;


public class AnalizadorSintacticoTinyDJ extends AnalizadorSintacticoTiny {
    private void imprime(Token t) {
        switch(t.kind) {
            case and_id:
            case bool_id:
            case call_id:
            case else_id:
            case if_id:
            case delete_id:
            case false_id:
            case true_id:
            case int_id:
            case real_id:
            case string_id:
            case struct_id:
            case new_id:
            case write_id:
            case while_id:
            case type_id:
            case read_id:
            case nl_id:
            case not_id:
            case null_id:
            case or_id:
            case proc_id: System.out.println("<"+t.image+">"); break;
            case EOF: System.out.println("<EOF>"); break;
            default: System.out.println(t.image);
        }
    }
    
    public AnalizadorSintacticoTinyDJ(Reader r) {
        super(r);
        disable_tracing();
    }
    final protected void trace_token(Token t, String where) {
        imprime(t);
    }
}
