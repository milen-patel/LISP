package mainPackage.Expression;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class QuoteEvaluator implements Evaluator {

	@Override
	public SExpression eval(final SExpression input, final Environment environment) {
		//System.out.println(input.getHead().getClass());
		//System.out.println(input.getTail().getHead().getTail().toString());
		final SExpression expression = input.getTail();
		if (expression instanceof NilAtom) {
			throw new IllegalStateException("Missing argument for operator 'QUOTE'");
		}
		if (!(expression.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'QUOTE'");
		}
		return expression.getHead();
	}

}
