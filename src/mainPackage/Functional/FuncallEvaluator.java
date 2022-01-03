package mainPackage.Functional;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.BasicFunction;
import main.lisp.evaluator.function.Function;
import main.lisp.evaluator.function.Lambda;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;
import mainPackage.FunctionApplicationEvaluator;
import mainPackage.LambdaApplicationEvaluator;

public class FuncallEvaluator implements Evaluator {

	@Override
	public SExpression eval(final SExpression expressionOriginal, final Environment environment) {
		if (expressionOriginal.isAtom() || expressionOriginal.getTail().isAtom()) {
			ExpressionFactory.newInstance(new TAtom(), new TAtom());
			throw new RuntimeException("Bad Args");
		}


		final SExpression expression = expressionOriginal.getTail();
		final SExpression evaluatedExpression = expression.getHead().eval(environment);
		
		if (evaluatedExpression instanceof BasicFunction) {
			return FunctionApplicationEvaluator.helper((Function) evaluatedExpression, environment, expression);
		}
		final Lambda lambda = (Lambda) evaluatedExpression;
		return LambdaApplicationEvaluator.helper(lambda, environment, expression); // remove unneeded param
	}

}

/*
 * 
 * package mainPackage;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.Lambda;
import main.lisp.parser.terms.SExpression;

public class FuncallEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expression, Environment environment) {
		if (expression.isAtom()) {
			throw new RuntimeException("Bad Args");
		}
		if (expression.getTail().isAtom()) {
			throw new RuntimeException("Bad Args");
		}

		expression = expression.getTail();
		System.out.println("MILEN: " + expression.getHead().toString());
		Lambda lambda = (Lambda) expression.getHead().eval(environment);

		return (new LambdaApplicationEvaluator()).helper(lambda, environment, expression);
	}

}
*/
