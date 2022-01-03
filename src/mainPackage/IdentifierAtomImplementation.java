package mainPackage;

import java.util.Optional;

import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.scanner.tokens.Token;

public class IdentifierAtomImplementation extends IdentifierAtom {
	
	public IdentifierAtomImplementation(final Token string) {
		super(string);
	}
	
	public IdentifierAtomImplementation(final String string) {
		super(string);
	}
	
	@Override
	public SExpression eval(final Environment environment) {
		final Optional<SExpression> look =  environment.lookup(this);
		if (look.isPresent()) {
			return look.get();
		}
		throw new RuntimeException("Undefined Variable");
	}

}
