package org.bvkatwijk.java.jasmine.runner.run;

import java.util.function.Consumer;

import org.bvkatwijk.java.jasmine.compiled.JasmineCase;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

/**
 * Executor for {@link JasmineCase}
 */
public class JasmineGroupRunner {

	public Consumer<? super JasmineCase> runIt(RunNotifier runNotifier, String className) {
		return it -> {
			Description description = Description.createTestDescription(className, it.getDescription());
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
