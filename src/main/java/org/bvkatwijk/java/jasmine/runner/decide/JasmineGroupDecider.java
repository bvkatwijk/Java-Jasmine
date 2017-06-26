package org.bvkatwijk.java.jasmine.runner.decide;

import java.util.function.Consumer;

import org.bvkatwijk.java.jasmine.compiled.JasmineCase;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;
import org.bvkatwijk.java.jasmine.compiled.JasmineNode;
import org.bvkatwijk.java.jasmine.mode.JasmineMode;
import org.bvkatwijk.java.jasmine.mode.JasmineModeDecider;
import org.bvkatwijk.java.jasmine.runner.ignore.JasmineGroupIgnorer;
import org.bvkatwijk.java.jasmine.runner.run.JasmineGroupRunner;
import org.junit.runner.notification.RunNotifier;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JasmineGroupDecider {

	private final RunNotifier runNotifier;
	private final JasmineGroup source;
	private final JasmineGroupIgnorer ignorer;
	private final JasmineGroupRunner runner;

	public static JasmineGroupDecider of(RunNotifier runNotifier, JasmineGroup jasmineGroup) {
		return new JasmineGroupDecider(runNotifier, jasmineGroup,
				new JasmineGroupIgnorer(jasmineGroup.getDescription()), new JasmineGroupRunner());
	}

	public void process() {
		runCasesAndSubGroups(runNotifier).accept(source);
	}

	private Consumer<? super JasmineGroup> runCasesAndSubGroups(RunNotifier runNotifier) {
		return jasmineGroup -> {
			JasmineMode jasmineMode = new JasmineModeDecider(source).get();
			jasmineGroup.getCases().forEach(processCase(runNotifier, jasmineMode));
			jasmineGroup.getGroups().forEach(processGroup(runNotifier, jasmineMode));
		};
	}

	private Consumer<? super JasmineGroup> processGroup(RunNotifier runNotifier, JasmineMode jasmineMode) {
		return jasmineGroup -> {
			if (shouldRun(jasmineMode, jasmineGroup)) {
				runCasesAndSubGroups(runNotifier).accept(jasmineGroup);
			} else {
				ignorer.ignoreGroup(runNotifier).accept(jasmineGroup);
			}
		};
	}

	private Consumer<? super JasmineCase> processCase(RunNotifier runNotifier, JasmineMode jasmineMode) {
		return jasmineCase -> {
			if (shouldRun(jasmineMode, jasmineCase)) {
				runner.runIt(runNotifier, source.getDescription()).accept(jasmineCase);
			} else {
				ignorer.ignoreCase(runNotifier).accept(jasmineCase);
			}
		};
	}

	private boolean shouldRun(JasmineMode jasmineMode, JasmineNode jasmineGroup) {
		return jasmineMode.getFilter().test(jasmineGroup);
	}

}
