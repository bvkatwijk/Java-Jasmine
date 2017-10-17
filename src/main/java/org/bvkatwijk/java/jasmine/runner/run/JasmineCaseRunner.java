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
public class JasmineCaseRunner {

	private final String sourceDescription;
	private final RunNotifier runNotifier;

	public Consumer<? super JasmineCase> runIt() {
		return it -> tryToRun(
				it.getRunnable(),
				Description.createTestDescription(sourceDescription, it.getDescription()));
	}

	private void tryToRun(Runnable runnable, Description description) {
		try {
			runNotifier.fireTestStarted(description);
			runnable.run();
		} catch (Throwable e) {
			runNotifier.fireTestFailure(new Failure(description, e));
		}
		runNotifier.fireTestFinished(description);
	}

}
