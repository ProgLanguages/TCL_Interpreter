package business;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import classes.tclLexer;
import classes.tclParser;
import visitors.VisitorTCL;

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