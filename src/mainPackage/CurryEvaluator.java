package mainPackage;

import java.util.ArrayList;
import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.Lambda;
import main.lisp.evaluator.function.LambdaFactory;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class CurryEvaluator implements Evaluator {
	
	public static SExpression addNonCurriedArgs(final int numberOfNonCurriedArgsParam, final IdentifierAtom[] fLambdaArgs) {
		SExpression body = new NilAtom();
		int pointer = fLambdaArgs.length - 1;
		int numberOfNonCurriedArgs = numberOfNonCurriedArgsParam;
		while(numberOfNonCurriedArgs-- > 0) {
			body = prependList(body, fLambdaArgs[pointer--]);
		}
		return body;
	}
	
	public static SExpression addCurriedArgs(final SExpression bodyParam, final List<SExpression> quotedCurryArgs) {
		SExpression body = bodyParam;
		for (int i = quotedCurryArgs.size()-1; i>= 0; i--) {
			body = prependList(body, quotedCurryArgs.get(i));
		}
		return body;
	}

	@Override
	public SExpression eval(final SExpression expression, final Environment environment) {
		final Lambda FLambda = (Lambda) expression.getTail().getHead().eval(environment);
		final IdentifierAtom[] FLambdaArgs = FLambda.getArgumentNames();
		
		final List<SExpression> quotedCurryArgs = new ArrayList<SExpression>();
		SExpression temporary = expression.getTail().getTail();
		while (!temporary.isAtom()) {
			quotedCurryArgs.add(quoteSExpression(temporary.getHead().eval(environment)));
			temporary = temporary.getTail();
		}
		
		final int numberOfNonCurriedArgs = FLambdaArgs.length - quotedCurryArgs.size();
		
		SExpression body = addNonCurriedArgs(numberOfNonCurriedArgs, FLambdaArgs);
		body = addCurriedArgs(body, quotedCurryArgs);
		

		SExpression base = new BetterSExpression(FLambda, new NilAtom());
		base = prependList(base, new IdentifierAtom("quote"));
		body = prependList(body, base);
		body = prependList(body, new IdentifierAtom("funcall"));
		body = new BetterSExpression(body, new NilAtom());
		
		final int numRemainingArgs = FLambdaArgs.length - quotedCurryArgs.size();
		return LambdaFactory.newInstance(makeListOfReturnFunctionArguements(numRemainingArgs, FLambdaArgs), body);
		//return body;
	}
	
	public static IdentifierAtom[] makeListOfReturnFunctionArguements(final int numRemainingArgsParam, final IdentifierAtom[] fLambdaArgs) {
		int numRemainingArgs = numRemainingArgsParam;
		final IdentifierAtom[] args = new IdentifierAtom[numRemainingArgs];
		int write = args.length-1;
		int read = fLambdaArgs.length - 1;
		while (numRemainingArgs-- > 0) {
			args[write--] = fLambdaArgs[read--];
		}
		return args;
	}
	
	public static SExpression prependList(final SExpression list, final SExpression itemToAppend) {
		return new BetterSExpression(itemToAppend,list);
	}

	
	public static SExpression quoteSExpression(final SExpression expression) {
		return new BetterSExpression(new IdentifierAtomImplementation("QUOTE"), new BetterSExpression(expression, new NilAtom()));
	}
	
	public static SExpression makeList(final List<SExpression> input) {
		SExpression retVal = new NilAtom();
		
		for (int i = input.size() - 1; i >= 0; i--) {
			retVal = new BetterSExpression(input.get(i), retVal);
		}
		
		return retVal;
	}
	
	
}
