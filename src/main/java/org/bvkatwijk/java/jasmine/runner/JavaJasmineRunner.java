package org.bvkatwijk.java.jasmine.runner;

import java.util.function.Consumer;

import org.bvkatwijk.java.jasmine.api.test.JasmineTest;
import org.bvkatwijk.java.jasmine.compiled.JasmineCase;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;
import org.bvkatwijk.java.jasmine.mode.JasmineModeDecider;
import org.bvkatwijk.java.jasmine.runner.description.JasmineDescription;
import org.bvkatwijk.java.jasmine.runner.run.JasmineGroupRunner;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

public class JavaJasmineRunner extends Runner {

	private final JasmineGroup jasmineGroup;

	public JavaJasmineRunner(Class<JasmineTest> testClass) throws Exception {
		this.jasmineGroup = testClass.newInstance().compile();
	}

	@Override
	public Description getDescription() {
		return new JasmineDescription(jasmineGroup).get();
	}

	@Override
	public void run(RunNotifier runNotifier) {
		new JasmineModeDecider(jasmineGroup).get();
		runCasesAndSubGroups(runNotifier).accept(jasmineGroup);
	}

	private Consumer<? super JasmineGroup> runCasesAndSubGroups(RunNotifier runNotifier) {
		return jasmineGroup -> {
			jasmineGroup.getCases().forEach(runIt(runNotifier));
			jasmineGroup.getGroups().forEach(runCasesAndSubGroups(runNotifier));
		};
	}

	private Consumer<? super JasmineCase> runIt(RunNotifier runNotifier) {
		return new JasmineGroupRunner().runIt(runNotifier, jasmineGroup.getDescription());
	}

}
