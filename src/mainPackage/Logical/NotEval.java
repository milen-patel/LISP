package mainPackage.Logical;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class NotEval implements Evaluator {

	@Override
	public SExpression eval(final SExpression input, final Environment environment) {
		SExpression expression = input.getTail();
		if (expression instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'not'");
		}
		if (!(expression.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'not'");
		}
		expression = expression.getHead().eval(environment);
		if (expression instanceof NilAtom) {
			return main.lisp.parser.terms.TAtomicExpressionFactory.newInstance();
		} 
		return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
	}

}
