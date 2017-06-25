package org.bvkatwijk.java.jasmine.runner;

import java.util.concurrent.atomic.AtomicBoolean;

import org.bvkatwijk.java.jasmine.Jasmine;
import org.bvkatwijk.java.jasmine.runner.decide.JasmineGroupDecider;
import org.bvkatwijk.java.jasmine.utils.Mocks;
import org.junit.Assert;

import lombok.Getter;

public abstract class RunValidationTest {
	
	@Getter private final AtomicBoolean didRun = new AtomicBoolean(false);

	public void assertDidRun(Jasmine jasmine) {
		Assert.assertTrue(runAndGetResult(jasmine));
	}

	public void assertDidNotRun(Jasmine jasmine) {
		Assert.assertFalse(runAndGetResult(jasmine));
	}

	private boolean runAndGetResult(Jasmine jasmine) {
		JasmineGroupDecider.of(Mocks.getRunNotifier(), jasmine.getJasmineTest(() -> getDidRun().set(true)).compile()).process();
		return didRun.get();
	}


}
