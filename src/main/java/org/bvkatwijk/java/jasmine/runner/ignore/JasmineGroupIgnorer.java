package org.bvkatwijk.java.jasmine.runner.ignore;

import java.util.function.Consumer;

import org.bvkatwijk.java.jasmine.compiled.JasmineCase;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;

/**
 * Ignorer for {@link JasmineCase}
 */
public class JasmineGroupIgnorer {

	public Consumer<? super JasmineCase> ignoreIt(RunNotifier runNotifier, String className) {
		return it -> {
			Description description = Description.createTestDescription(className, it.getDescription());
			runNotifier.fireTestIgnored(description);
			runNotifier.fireTestFinished(description);
		};
	}

}
