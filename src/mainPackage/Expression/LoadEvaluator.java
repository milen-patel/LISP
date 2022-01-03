package mainPackage.Expression;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;

public class LoadEvaluator implements Evaluator {

	@Override
	public SExpression eval(final SExpression input, final Environment environment) {
		SExpression expression = input.getTail();
		if (expression instanceof NilAtom) {
			throw new RuntimeException("Missing Arguement");
		}
		if (!(expression.getTail() instanceof NilAtom)) {
			throw new RuntimeException("Too Many Arguements");
		}
		expression = expression.getHead();

		if (expression instanceof StringAtom) {
			String path = ((StringAtom) expression).getValue();
			path = path.replace("\"", "");
			
			String result = "";
			 final Path wiki_path = Paths.get(path);

			    try {
			      final List<String> lines = java.nio.file.Files.readAllLines(wiki_path);

			      for (String line : lines) {
			       result += line;
			      }
			    } catch (IOException e) {
			      System.out.println(e);
			    }
			
			try {
				main.lisp.interpreter.InterpreterModelSingleton.get().newInput(result);
				return main.lisp.parser.terms.TAtomicExpressionFactory.newInstance();
			} catch (Exception e) {
				return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
			}
		}
		
		return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
	}

}
