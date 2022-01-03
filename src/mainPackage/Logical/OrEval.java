package mainPackage.Logical;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class OrEval implements Evaluator{

	@Override
	public SExpression eval(final SExpression input, final Environment environment) {
		// Case: 0 Arguments
		if (input.getTail() instanceof NilAtom) {
			return main.lisp.parser.terms.TAtomicExpressionFactory.newInstance();
		}
		SExpression expression = input.getTail();
		
		
		while (!expression.isAtom()) {
			final SExpression currentResult = expression.getHead().eval(environment);
			if (!(currentResult instanceof NilAtom)) {
				return currentResult;
			}
			expression = expression.getTail();
		}
	
		return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
	}

}
