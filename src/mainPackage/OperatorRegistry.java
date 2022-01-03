package mainPackage;

import main.lisp.evaluator.BuiltinOperationManagerSingleton;
import mainPackage.Expression.CondEvaluator;
import mainPackage.Expression.EvalEvaluator;
import mainPackage.Expression.ListEvaluator;
import mainPackage.Expression.LoadEvaluator;
import mainPackage.Expression.QuoteEvaluator;
import mainPackage.Functional.FuncallEvaluator;
import mainPackage.Functional.LambdaEvaluator;
import mainPackage.Functional.SetqEvaluator;
import mainPackage.Logical.AndEval;
import mainPackage.Logical.NotEval;
import mainPackage.Logical.OrEval;
import mainPackage.Relational.GreaterThanEvaluator;
import mainPackage.Relational.GreaterThanOrEqualToEvaluator;
import mainPackage.Relational.LessThanEvaluator;
import mainPackage.Relational.LessThanOrEqualToEvaluator;

public class OperatorRegistry implements OperatorRegistryInterface  {

	@Override
	public void registerAll() {
		registerOperations();
	}
	
	@Override
	public void registerOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("<", new LessThanEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("<=", new LessThanOrEqualToEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew(">", new GreaterThanEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew(">=", new GreaterThanOrEqualToEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("LOAD", new LoadEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("and", new AndEval()); 
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("list", new ListEvaluator()); 
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("quote", new QuoteEvaluator()); 
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("EVAL", new EvalEvaluator()); 
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("or", new OrEval()); 
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("not", new NotEval()); 
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("COND", new CondEvaluator()); 
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("setq", new SetqEvaluator()); 
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("LAMBDA", new LambdaEvaluator()); 
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("funcall", new FuncallEvaluator()); 
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("function", new FunctionEvaluator()); 
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("let", new LetEvaluator()); 
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("curry", new CurryEvaluator()); 
	}

}
