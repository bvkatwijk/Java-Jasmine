package org.bvkatwijk.java.jasmine.runner.run;

import org.bvkatwijk.java.jasmine.api.prefix.Prefix;
import org.bvkatwijk.java.jasmine.compiled.JasmineCase;
import org.bvkatwijk.java.jasmine.utils.Mocks;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

/**
 * Test cases for {@link JasmineGroupRunner}
 */
public class JasmineGroupRunnerTest {

	@Test
	public void runner_shouldCallNotifierFinished_whenCaseSucceeds() {
		RunNotifier runNotifier = Mocks.getRunNotifier();
		new JasmineGroupRunner("sourceDescription", runNotifier).runIt().accept(mockSuccessCase());
		Mockito.verify(runNotifier).fireTestFinished(ArgumentMatchers.any());
	}

	@Test
	public void runner_shouldCallNotifierFailed_whenCaseFails() {
		RunNotifier runNotifier = Mocks.getRunNotifier();
		new JasmineGroupRunner("sourceDescription", runNotifier).runIt().accept(mockFailCase());
		Mockito.verify(runNotifier).fireTestFailure(ArgumentMatchers.any());
	}

	private JasmineCase mockSuccessCase() {
		return new JasmineCase("description", Prefix.NONE, () -> { });
	}

	private JasmineCase mockFailCase() {
		return new JasmineCase("description", Prefix.NONE, Assert::fail);
	}
}
