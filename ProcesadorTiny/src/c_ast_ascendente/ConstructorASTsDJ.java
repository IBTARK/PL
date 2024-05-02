package c_ast_ascendente;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

public class ConstructorASTsDJ extends ConstructorASTs {

	public ConstructorASTsDJ(Scanner s) {
		super(s);
	}
	
	public void debug_message(String msg) {}
    public void debug_shift(Symbol token) {
       System.out.println(token.value);
    }
}
