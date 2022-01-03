package mainPackage.Expression;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class CondEvaluator implements Evaluator {

	@Override
	public SExpression eval(final SExpression input, final Environment environment) {

		// Case: 0 Argument Call
		if (input.getTail() instanceof NilAtom) {
			return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
		}
		
		SExpression expression = input.getTail();
		
		while (!expression.isAtom()) {
			if (expression.getHead().isAtom()) {
				return new NilAtom();
			}
			final SExpression condition = expression.getHead().getHead();
			
			final SExpression conditionEvaluated = condition.eval(environment);
			
			if (expression.getHead().getTail() instanceof NilAtom && !(conditionEvaluated instanceof NilAtom)) {
				return conditionEvaluated;
			}
			
			if (!(conditionEvaluated instanceof NilAtom)) {
				final SExpression result = expression.getHead().getTail().getHead();
				return result.eval(environment);
				
			}
			expression = expression.getTail();
		}
		
		return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
	}

}
