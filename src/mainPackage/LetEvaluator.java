package mainPackage;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;

public class LetEvaluator implements Evaluator {

	@Override
	public SExpression eval(final SExpression input, final Environment environment) {
		final Environment childEnvironment = environment.newChild();
		final SExpression expression = input.getTail();
		
		handleDeclarations(expression, childEnvironment, environment);
		
		SExpression body = expression.getTail();
		SExpression retVal = null;
		while (!body.isAtom()) {
			retVal = body.getHead().eval(childEnvironment);
			body = body.getTail();
		}
		
		return retVal;
	}

	
	public static void handleDeclarations(final SExpression expression, final Environment childEnvironment, final Environment environment) {
		SExpression declarations = expression.getHead();
		while (!declarations.isAtom()) {
			childEnvironment.assign((IdentifierAtom) declarations.getHead().getHead(), declarations.getHead().getTail().getHead().eval(environment));
			declarations = declarations.getTail();
		}
	}
}
