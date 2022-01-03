package mainPackage.Expression;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class EvalEvaluator implements Evaluator {

	@Override
	public SExpression eval(final SExpression input, final Environment environment) {
		final SExpression expression = input.getTail();
		if (expression instanceof NilAtom) {
			throw new IllegalStateException("Not enough arguments for operator 'EVAL'");
		}
		if (!(expression.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'EVAL'");
		}
		return expression.getHead().eval(environment).eval(environment);
	}

}
