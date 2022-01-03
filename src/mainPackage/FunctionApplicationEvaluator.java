package mainPackage;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.Function;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;

public class FunctionApplicationEvaluator implements Evaluator {

	@Override
	public SExpression eval(final SExpression expression, final Environment environment) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static SExpression helper(final Function expression, final Environment callerEnvironment, final SExpression formalArgs) {
		final Environment definerEnvironment = expression.getEnvironment();
		final Environment childEnvironment = definerEnvironment.newChild();
		final IdentifierAtom[] funArgs = expression.getLambda().getArgumentNames();
		
		SExpression current = formalArgs.getTail();
		for (int i = 0; i < funArgs.length; i++) {
			childEnvironment.assign(funArgs[i], current.getHead().eval(callerEnvironment));
			current = current.getTail();
		}
		
		return expression.getLambda().eval(childEnvironment);
	}

}
