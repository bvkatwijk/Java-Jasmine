package org.bvkatwijk.java.jasmine.runner;

import java.util.function.Consumer;

import org.bvkatwijk.java.jasmine.api.test.JasmineTest;
import org.bvkatwijk.java.jasmine.compiled.JasmineCase;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

public class JavaJasmineRunner extends Runner {

	private final Class<JasmineTest> testClass;
	private final JasmineGroup jasmineTest;
	private final JasmineTest test;

	public JavaJasmineRunner(Class<JasmineTest> testClass) throws Exception {
		this.testClass = testClass;
		test = testClass.newInstance();
		jasmineTest = test.compile();
	}

	@Override
	public Description getDescription() {
		Description description = Description.createSuiteDescription(jasmineTest.getDescription());
		jasmineTest.getCases().forEach(addCaseDescription(description));
		jasmineTest.getGroups().forEach(addGroupDescription(description));
		return description;
	}
	
	private Consumer<? super JasmineGroup> addGroupDescription(Description description) {
		return it -> {
			Description child = Description.createSuiteDescription(it.getDescription());
			it.getCases().forEach(addCaseDescription(child));
			it.getGroups().forEach(addGroupDescription(child));
			description.addChild(child);
		};
	}
	
	private Consumer<? super JasmineCase> addCaseDescription(Description description) {
		return it -> {
			description.addChild(Description.createTestDescription(testClass.getSimpleName(), it.getDescription()));
		};
	}

	@Override
	public void run(RunNotifier runNotifier) {
		runCasesAndSubGroups(runNotifier).accept(jasmineTest);
	}

	private Consumer<? super JasmineGroup> runCasesAndSubGroups(RunNotifier runNotifier) {
		return jasmineGroup -> {
			jasmineGroup.getCases().forEach(runIt(runNotifier));
			jasmineGroup.getGroups().forEach(runCasesAndSubGroups(runNotifier));
		};
	}

	private Consumer<? super JasmineCase> runIt(RunNotifier runNotifier) {
		return it -> {
			Description description = Description.createTestDescription(testClass.getSimpleName(), it.getDescription());
			try {
				runNotifier.fireTestStarted(description);
				it.getRunnable().run();
			} catch (Throwable e) {
				runNotifier.fireTestFailure(new Failure(description, e));
			}
			runNotifier.fireTestFinished(description);
		};
	}

}
