package mainPackage.Expression;

import java.util.Stack;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.SExpression;

public class ListEvaluator implements Evaluator {

	@Override
	public SExpression eval(final SExpression input, final Environment environment) {
		SExpression expression = input.getTail();
		int count = 0;
		
		final Stack<SExpression> stack = new Stack<SExpression>();
		while(!expression.isAtom()) {
			final SExpression next = expression.getHead().eval(environment);
			expression = expression.getTail();
			stack.push(next);
			count++;
		}
		
		final SExpression result = main.lisp.parser.terms.NilAtomicExpressionFactory.newInstance();
		
		return process(count, stack, result);
	}
	
	public static SExpression process(final int countParameter, final Stack<SExpression> stack, final SExpression start) {
		SExpression result = start;
		int count = countParameter;
		while(count-- > 0) {
			result =  ExpressionFactory.newInstance(stack.pop(), result);
		}
		return result;
	}

}
