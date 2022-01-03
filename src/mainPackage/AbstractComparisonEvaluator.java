package mainPackage;

import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.DecimalAtom;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public abstract class AbstractComparisonEvaluator implements AbstractComparisonEvaluatorInterface {
	@Override
	public abstract String getOperatorString();
	
	@Override
	public abstract boolean comparator(double first, double second);
	
	public final static String TICK = "'";
	
	@Override
	public void checkInput(final SExpression tail) {
		if (tail instanceof NilAtom || tail.getHead() instanceof NilAtom || tail.getTail() instanceof NilAtom || !(tail.getTail().getTail() instanceof NilAtom)) {
			String reason = "Bad arguments for operator '";
			reason+=getOperatorString();
			reason += TICK;
			throw new IllegalStateException(reason);
		}
	}

	@Override
	public SExpression eval(final SExpression expression, final Environment environment) {
		final SExpression tail = expression.getTail();
		checkInput(tail);
		
		final SExpression arg1 = tail.getHead();
		final SExpression arg2 = tail.getTail().getHead();
		
		final SExpression result1 =  arg1.eval(environment);
		final SExpression result2 =  arg2.eval(environment);
		
		double firstResult = 0.0;
		double secondResult = 0.0;
		
		int correctArgs = 0;
		if (result1 instanceof IntegerAtom) {
			correctArgs++;
			firstResult = ((IntegerAtom) result1).getValue();
		} 
		if (result1 instanceof DecimalAtom) {
			correctArgs++;
			firstResult = ((DecimalAtom) result1).getValue();	
		}
		
		if (result2 instanceof IntegerAtom) {
			correctArgs++;
			secondResult = ((IntegerAtom) result2).getValue();
		}
		if (result2 instanceof DecimalAtom) {
			correctArgs++;
			secondResult = ((DecimalAtom) result2).getValue();	
		}
		
		if (correctArgs == 2 && comparator(firstResult, secondResult)) {
			return main.lisp.parser.terms.TAtomicExpressionFactory.newInstance();
		}

		return main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
	}

}
