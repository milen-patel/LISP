package mainPackage;

import main.lisp.evaluator.OperationRegisterer;

public interface OperatorRegistryInterface extends OperationRegisterer {
	public void registerAll();
}
