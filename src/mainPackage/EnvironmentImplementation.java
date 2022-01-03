package mainPackage;

import java.util.Optional;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.environment.AbstractEnvironment;
import main.lisp.evaluator.environment.CopyableScope;
import main.lisp.evaluator.function.Function;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;

public class EnvironmentImplementation extends AbstractEnvironment {
	
	public EnvironmentImplementation() {
		super();
	}
	
	public EnvironmentImplementation(final Environment parent) {
		super(parent);
	}
	
	public EnvironmentImplementation(final Environment parent, final CopyableScope scope) {
		super(parent, scope);
	}
	

	@Override
	public void assign(final IdentifierAtom arg0, final SExpression arg1) {
		this.getScope().put(arg0, arg1); 
	}

	@Override
	public void assignFun(final IdentifierAtom arg0, final Function arg1) {
		this.getScope().putFun(arg0, arg1);
	}

	@Override
	public main.lisp.evaluator.Environment copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SExpression> lookup(final IdentifierAtom arg0) {
		getParent();
		if (this.getScope().get(arg0).isPresent()) {
			return this.getScope().get(arg0);
		}
		if (this.getParent() != null) {
			return this.getParent().lookup(arg0);
		}
		return this.getScope().get(arg0);
		
	}

	@Override
	public Optional<Function> lookupFun(final IdentifierAtom arg0) {
		this.getParent();
		getParent();
		if (this.getScope().getFun(arg0).isPresent()) {
			return this.getScope().getFun(arg0);
		}
		if (this.getParent() != null) {
			return this.getParent().lookupFun(arg0);
		}
		return this.getScope().getFun(arg0);
	}

	@Override
	public main.lisp.evaluator.Environment newChild() {
		return new EnvironmentImplementation(this);
	}

}
