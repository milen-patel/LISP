package mainPackage;

import gradingTools.comp524f21.assignment6.ClassRegistryA6;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.OperationRegisterer;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;
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

public class MyClassRegistry implements ClassRegistryA6 {

	@Override
	public Class<? extends Evaluator> getAndEvaluator() {
		return AndEval.class;
	}

	@Override
	public Class<? extends Evaluator> getCondEvaluator() {
		return CondEvaluator.class;
	}

	@Override
	public Class<? extends OperationRegisterer> getCustomOperationRegisterer() {
		return OperatorRegistry.class;
	}

	@Override
	public Class<? extends Evaluator> getEvalEvaluator() {
		return EvalEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getGTEEvaluator() {
		return GreaterThanOrEqualToEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getGTEvaluator() {
		return GreaterThanEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getLTEEvaluator() {
		return LessThanOrEqualToEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getLTEvaluator() {
		return LessThanEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getListEvaluator() {
		return ListEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getLoadEvaluator() {
		return LoadEvaluator.class;
	}

	@Override
	public Class<?> getMain() {
		return Runner.class;
	}

	@Override
	public Class<? extends Evaluator> getNotEvaluator() {
		return NotEval.class;
	}

	@Override
	public Class<? extends Evaluator> getOrEvaluator() {
		return OrEval.class;
	}

	@Override
	public Class<? extends Evaluator> getQuoteEvaluator() {
		return QuoteEvaluator.class;
	}

	@Override
	public Class<? extends SExpression> getStringFormattingSExpression() {
		return BetterSExpression.class;
	}

	@Override
	public Class<? extends SExpression> getFunctionCallingSExpression() {
		return null;
	}

	@Override
	public Class<? extends Evaluator> getFunctionEvaluator() {
		return FunctionEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getLetEvaluator() {
		return LetEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getBasicFuncallEvaluator() {
		return FuncallEvaluator.class;
	}

	@Override
	public Class<? extends IdentifierAtom> getIdentifierAtomWithLookup() {
		return IdentifierAtomImplementation.class;
	}

	@Override
	public Class<? extends SExpression> getLambdaCallingSExpression() {
		return null;
	}

	@Override
	public Class<? extends Evaluator> getLambdaEvaluator() {
		return LambdaEvaluator.class;
	}

	@Override
	public Class<? extends EnvironmentImplementation> getNestedLexicalEnvironment() {
		return EnvironmentImplementation.class;
	}

	@Override
	public Class<? extends Evaluator> getSetqEvaluator() {
		return SetqEvaluator.class;
	}

	@Override
	public Class<? extends OperationRegisterer> getStatefulOperationRegisterer() {
		return OperatorRegistry.class;
	}

}
