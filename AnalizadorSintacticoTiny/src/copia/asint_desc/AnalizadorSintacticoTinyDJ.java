package asint_desc;

import java.io.Reader;


public class AnalizadorSintacticoTinyDJ extends AnalizadorSintacticoTiny {
    private void imprime(Token t) {
        switch(t.kind) {
            case and_id:
            	System.out.println("<and>"); break;
            case bool_id:
            	System.out.println("<bool>"); break;
            case call_id:
            	System.out.println("<call>"); break;
            case else_id:
            	System.out.println("<else>"); break;
            case if_id:
            	System.out.println("<if>"); break;
            case delete_id:
            	System.out.println("<delete>"); break;
            case false_id:
            	System.out.println("<false>"); break;
            case true_id:
            	System.out.println("<true>"); break;
            case int_id:
            	System.out.println("<int>"); break;
            case real_id:
            	System.out.println("<real>"); break;
            case string_id:
            	System.out.println("<string>"); break;
            case struct_id:
            	System.out.println("<struct>"); break;
            case new_id:
            	System.out.println("<new>"); break;
            case write_id:
            	System.out.println("<write>"); break;
            case while_id:
            	System.out.println("<while>"); break;
            case type_id:
            	System.out.println("<type>"); break;
            case read_id:
            	System.out.println("<read>"); break;
            case nl_id:
            	System.out.println("<nl>"); break;
            case not_id:
            	System.out.println("<not>"); break;
            case null_id:
            	System.out.println("<null>"); break;
            case or_id:
            	System.out.println("<or>"); break;
            case proc_id:
            	System.out.println("<proc>"); break;
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
