package mainPackage;

import main.lisp.parser.terms.BasicExpression;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class BetterSExpression extends BasicExpression implements SExpression {
	public static final String OPEN_PARENTHESIS = "(";
	public static final String CLOSE_PARENTHESIS = ")";
	public static final String PERIOD = " . ";
	public static final String SPACE = " ";
	
	public BetterSExpression(final SExpression head, final SExpression tail) {
		super(head, tail);
	}
	
	@Override
	public boolean isList() {
		if (this.isAtom()) {
			return false;
		}
		return isListHelper(this);
	} 
	
	private static boolean isListHelper(final SExpression expression) {
		if (expression.isAtom()) {
			return expression instanceof NilAtom;
		}
		return isListHelper(expression.getTail());
	}
	
	
	@Override
	public String toStringAsList() {
		String parsedString = toStringAsListHelper();
		if (parsedString.length() > 0 && parsedString.charAt(0) == ' ') {
			parsedString = parsedString.substring(1);
		}

		return OPEN_PARENTHESIS + parsedString + CLOSE_PARENTHESIS;
	}
	
	@Override
	public String toStringAsListHelper() {
		if (this.getTail().isAtom()) {
			return SPACE + this.getHead().toString();
		}
		if (OPEN_PARENTHESIS.contains(CLOSE_PARENTHESIS)) {
			return toString();
		}
		return SPACE + this.getHead().toString() + ((BetterSExpression) this.getTail()).toStringAsListHelper();
	}
	
	@Override
	public String toStringAsSExpression() {
		if (OPEN_PARENTHESIS.contains(CLOSE_PARENTHESIS)) {
			return this.toString();
		}
		return OPEN_PARENTHESIS + this.getHead().toStringAsSExpression() + PERIOD +
				this.getTail().toStringAsSExpression() + CLOSE_PARENTHESIS;
	}

	@Override
	public String toStringAsSExpressionDeep() {
		return OPEN_PARENTHESIS + this.getHead().toStringAsSExpressionDeep() + PERIOD +
				this.getTail().toStringAsSExpressionDeep() + CLOSE_PARENTHESIS;
	}
	
	@Override
	public String toString() {
		return this.isList() ? toStringAsList() : toStringAsSExpression();
	}

}
