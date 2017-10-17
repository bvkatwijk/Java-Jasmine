package org.bvkatwijk.java.jasmine.runner.decide;

import java.util.Collection;
import java.util.function.Consumer;

import org.bvkatwijk.java.jasmine.compiled.JasmineBeforeEach;
import org.bvkatwijk.java.jasmine.compiled.JasmineCase;
import org.bvkatwijk.java.jasmine.compiled.JasmineNode;
import org.bvkatwijk.java.jasmine.mode.JasmineMode;
import org.bvkatwijk.java.jasmine.runner.ignore.JasmineGroupIgnorer;
import org.bvkatwijk.java.jasmine.runner.run.JasmineGroupRunner;
import org.junit.runner.notification.RunNotifier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JasmineCaseProcessor {

	private final RunNotifier runNotifier;
	private final JasmineMode jasmineMode;
	private final JasmineGroupIgnorer ignorer;
	private final JasmineGroupRunner runner;

	public Consumer<? super JasmineCase> processCase(Collection<JasmineBeforeEach> beforeEachs) {
		return jasmineCase -> runOrIgnoreIt(beforeEachs, jasmineCase);
	}

	private void runOrIgnoreIt(Collection<JasmineBeforeEach> beforeEachs, JasmineCase jasmineCase) {
		if (shouldRun(jasmineMode, jasmineCase)) {
			beforeEachs.forEach(processBeforeEach());
			runner.runIt().accept(jasmineCase);
		} else {
			ignorer.ignoreCase().accept(jasmineCase);
		}
	}

	private Consumer<? super JasmineBeforeEach> processBeforeEach() {
		return beforeEach -> beforeEach.getRunnable().run();
	}

	private boolean shouldRun(JasmineMode jasmineMode, JasmineNode jasmineGroup) {
		return jasmineMode.getFilter().test(jasmineGroup);
	}

}
