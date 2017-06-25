package org.bvkatwijk.java.jasmine.runner;

import java.util.function.Consumer;

import org.bvkatwijk.java.jasmine.api.test.JasmineTest;
import org.bvkatwijk.java.jasmine.compiled.JasmineCase;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;
import org.bvkatwijk.java.jasmine.runner.description.JasmineDescription;
import org.bvkatwijk.java.jasmine.runner.run.JasmineGroupRunner;
import org.junit.runner.Description;
import org.junit.runner.Runner;
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
		return new JasmineDescription(jasmineTest).get();
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
		return new JasmineGroupRunner().runIt(runNotifier, testClass.getSimpleName());
	}

}
