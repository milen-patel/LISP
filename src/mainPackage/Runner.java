package mainPackage;
import main.Main;
import main.lisp.evaluator.ExpressionEvaluatorFactory;
public class Runner {
	public static void main(final String[] args) {
		main.lisp.parser.terms.ExpressionFactory.setClass(BetterSExpression.class);
		(new OperatorRegistry()).registerAll();;
		main.lisp.evaluator.environment.EnvironmentFactory.setClass(EnvironmentImplementation.class);
		main.lisp.parser.terms.IdentifierAtomFactory.setClass(IdentifierAtomImplementation.class);
		ExpressionEvaluatorFactory.setClass(GeneralExpressionEvaluator.class);
		Main.main(args);
	}
}
