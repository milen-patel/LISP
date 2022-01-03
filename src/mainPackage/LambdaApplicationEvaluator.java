package mainPackage;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.function.Lambda;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;

public class LambdaApplicationEvaluator {

	public static SExpression helper(final Lambda lambda, final Environment environment, final SExpression expression) {
		final IdentifierAtom[] lambdaArgs = lambda.getArgumentNames();
		final Environment childEnvironment = environment.newChild();

		SExpression formalArgValues = expression.getTail();
		for (int i = 0; i < lambdaArgs.length; i++) {
			childEnvironment.assign(lambdaArgs[i], formalArgValues.getHead().eval(environment));
			formalArgValues = formalArgValues.getTail();
		}

		return lambda.eval(childEnvironment);
	}
}
