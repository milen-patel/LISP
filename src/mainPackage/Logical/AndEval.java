package mainPackage.Logical;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class AndEval implements Evaluator {

	@Override
	public SExpression eval(final SExpression expression, final Environment environment) {
		// Case: 0 Arguments
		if (expression.getTail() instanceof NilAtom) {
			return main.lisp.parser.terms.TAtomicExpressionFactory.newInstance();
		}
		
		SExpression expressionTail = expression.getTail();
		SExpression retVal = null;
		
		while (!expressionTail.isAtom()) {
			final SExpression currentResult = expressionTail.getHead().eval(environment);
			if (currentResult instanceof NilAtom) {
				return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
			}
			retVal = currentResult;
			expressionTail = expressionTail.getTail();
		}
	
		return retVal;
	}

}
