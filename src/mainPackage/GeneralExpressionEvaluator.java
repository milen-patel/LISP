package mainPackage;

import main.lisp.evaluator.BasicExpressionEvaluator;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.Lambda;
import main.lisp.parser.terms.SExpression;

public class GeneralExpressionEvaluator extends BasicExpressionEvaluator implements Evaluator {
	
	@Override
	public SExpression eval(final SExpression expression, final Environment environment) {
		if (expression.isAtom() || expression.getHead().isAtom()) {
			return super.eval(expression, environment);
		}

		// Evaluate the lambda
		final SExpression label = expression.getHead().getHead();

		if (!(label.toString().equalsIgnoreCase("lambda"))) {
			return super.eval(expression, environment);
		}

		final Lambda lambda = (Lambda) expression.getHead().eval(environment);
		return LambdaApplicationEvaluator.helper(lambda, environment, expression); // do i even need this function
	}
}
