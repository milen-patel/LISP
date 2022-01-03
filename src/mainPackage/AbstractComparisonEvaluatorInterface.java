package mainPackage;

import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.SExpression;

public interface AbstractComparisonEvaluatorInterface extends Evaluator {
	public String getOperatorString();
	public boolean comparator(double first, double second);
	public void checkInput(final SExpression tail);
}
