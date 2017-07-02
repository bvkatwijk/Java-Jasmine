package org.bvkatwijk.java.jasmine.utils;

import java.util.function.Consumer;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.runner.decide.JasmineGroupDecider;
import org.junit.runner.notification.RunNotifier;
import org.mockito.Mockito;

public final class Mocks {

	/** Mocked test runner for {@link JasmineInternalTest} */
	public static Consumer<JasmineInternalTest> getMockRunner() {
		return internalTest -> JasmineGroupDecider.of(getRunNotifier(), internalTest.compile()).process();
	}

	public static RunNotifier getRunNotifier() {
		return Mockito.mock(RunNotifier.class);
	}

	private Mocks() {
		throw new IllegalStateException(this.getClass().getSimpleName() + " not to be instantiated.");
	}

}
