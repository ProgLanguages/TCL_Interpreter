package classes;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Interpreter {

	public static void main(String[] args) throws Exception {
		tclLexer lexer = new tclLexer(CharStreams.fromFileName("input.txt"));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tclParser parser = new tclParser(tokens);
		ParseTree tree = parser.inicio();

		VisitorTCL<Object> loader = new VisitorTCL<Object>();
		loader.visit(tree);
		
	}
}