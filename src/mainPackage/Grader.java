package mainPackage;

import trace.grader.basics.GraderBasicsTraceUtility;
import util.trace.Tracer;
import gradingTools.comp524f21.assignment6.F21Assignment6Suite;
import main.lisp.evaluator.ExpressionEvaluatorFactory;

public class Grader {
	public static void main (final String[] args) {
		Tracer.showInfo(true);
		GraderBasicsTraceUtility.setBufferTracedMessages(true);
		GraderBasicsTraceUtility.setTracerShowInfo(true);
		main.LispInterpreterTraceUtility.setTracing();
		ExpressionEvaluatorFactory.setClass(GeneralExpressionEvaluator.class);
		F21Assignment6Suite.main(args);
	}
}

