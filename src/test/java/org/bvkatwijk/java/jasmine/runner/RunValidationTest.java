package org.bvkatwijk.java.jasmine.runner;

import java.util.concurrent.atomic.AtomicBoolean;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.runner.decide.JasmineGroupDecider;
import org.bvkatwijk.java.jasmine.utils.Mocks;
import org.junit.Assert;

import lombok.Getter;

public abstract class RunValidationTest {
	
	@Getter private final AtomicBoolean didRun = new AtomicBoolean(false);

	public void assertDidRun(JasmineInternalTest describeIt) {
		Assert.assertTrue(runAndGetResult(describeIt));
	}

	public void assertDidNotRun(JasmineInternalTest describeIt) {
		Assert.assertFalse(runAndGetResult(describeIt));
	}

	private boolean runAndGetResult(JasmineInternalTest describeIt) {
		JasmineGroupDecider.of(Mocks.getRunNotifier(), describeIt.compile()).process();
		return didRun.get();
	}


}
