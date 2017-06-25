package org.bvkatwijk.java.jasmine.runner;

import org.bvkatwijk.java.jasmine.api.test.JasmineTest;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;
import org.bvkatwijk.java.jasmine.runner.decide.JasmineGroupDecider;
import org.bvkatwijk.java.jasmine.runner.description.JasmineDescription;
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
		JasmineGroupDecider.of(runNotifier, jasmineGroup).process();
	}

}
