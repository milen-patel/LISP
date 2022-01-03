package mainPackage.Relational;

import mainPackage.AbstractComparisonEvaluator;

public class LessThanEvaluator extends AbstractComparisonEvaluator{

	@Override
	public String getOperatorString() {
		return "<";
	}

	@Override
	public boolean comparator(final double first, final double second) {
		return first < second;
	}

}
