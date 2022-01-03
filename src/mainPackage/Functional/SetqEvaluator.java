package mainPackage.Functional;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class SetqEvaluator implements Evaluator {

	@Override
	public SExpression eval(final SExpression expression, final Environment environment) {
		if (expression.getTail() instanceof NilAtom) {
			throw new IllegalStateException("Cannot pass 0 arguments to setq");
		}
		
		final SExpression expressionCopy = expression.getTail();
		
		if (expressionCopy.getTail() instanceof NilAtom) {
			throw new IllegalStateException("Cannot pass 1 argument to setq");
		}
		
		final SExpression left = expressionCopy.getHead();
		
		if (!(left instanceof IdentifierAtom)) {
			throw new IllegalStateException("Setq first parameter must be an identifier");
		}
		
		
		final SExpression right = expressionCopy.getTail().getHead().eval(environment);
		environment.assign((IdentifierAtom) left, right);

		return right;
	}

}
