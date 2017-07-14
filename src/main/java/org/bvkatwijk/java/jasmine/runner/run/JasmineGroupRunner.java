package org.bvkatwijk.java.jasmine.runner.run;

import java.util.function.Consumer;

import org.bvkatwijk.java.jasmine.compiled.JasmineCase;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import lombok.RequiredArgsConstructor;

/**
 * Executor for {@link JasmineCase}
 */
@RequiredArgsConstructor
public class JasmineGroupRunner {
	
	private final String sourceDescription;

	public Consumer<? super JasmineCase> runIt(RunNotifier runNotifier) {
		return it -> tryToRun(
				runNotifier,
				it.getRunnable(),
				Description.createTestDescription(sourceDescription, it.getDescription()));
	}

	private void tryToRun(RunNotifier runNotifier, Runnable runnable, Description description) {
		try {
			runNotifier.fireTestStarted(description);
			runnable.run();
		} catch (Throwable e) {
			runNotifier.fireTestFailure(new Failure(description, e));
		}
		runNotifier.fireTestFinished(description);
	}

}
