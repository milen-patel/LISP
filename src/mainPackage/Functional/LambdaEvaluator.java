package mainPackage.Functional;

import java.util.ArrayList;
import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.LambdaFactory;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class LambdaEvaluator implements Evaluator{

	@Override
	public SExpression eval(final SExpression expression, final Environment environment) {
		if (expression.getTail() instanceof NilAtom) {
			throw new RuntimeException("LAMBDA needs a body");
		}
		
		// Proccess Arguements
		SExpression current = expression.getTail().getHead();
		final List<IdentifierAtom> args = new ArrayList<IdentifierAtom>();
		while (!current.isAtom()) {
			//System.out.println("=>" + curr.toString());
			if (!(current.getHead() instanceof IdentifierAtom)) {
				throw new RuntimeException("Parameters of lambda must be only identifiers");
			}
			args.add((IdentifierAtom) current.getHead());
			current = current.getTail();
		}
		
		return LambdaFactory.newInstance(args.toArray(new IdentifierAtom[args.size()]), expression.getTail().getTail());
	}

}
