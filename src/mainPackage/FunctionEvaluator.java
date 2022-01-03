package mainPackage;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.BasicLambda;
import main.lisp.evaluator.function.FunctionFactory;
import main.lisp.evaluator.function.Lambda;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class FunctionEvaluator implements Evaluator {

	@Override
	public SExpression eval(final SExpression expression, final Environment environment) {
		FunctionFactory.newInstance(new BasicLambda(new IdentifierAtom[] {}, new NilAtom()), environment);
		return FunctionFactory.newInstance((Lambda) expression.getTail().getHead().eval(environment), environment);
	}

}
