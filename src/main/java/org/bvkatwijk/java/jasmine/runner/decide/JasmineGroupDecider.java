package org.bvkatwijk.java.jasmine.runner.decide;

import java.util.Collection;
import java.util.function.Consumer;

import org.bvkatwijk.java.jasmine.compiled.JasmineBeforeAll;
import org.bvkatwijk.java.jasmine.compiled.JasmineBeforeEach;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;
import org.bvkatwijk.java.jasmine.compiled.JasmineNode;
import org.bvkatwijk.java.jasmine.mode.JasmineMode;
import org.bvkatwijk.java.jasmine.mode.JasmineModeDecider;
import org.bvkatwijk.java.jasmine.runner.ignore.JasmineGroupIgnorer;
import org.bvkatwijk.java.jasmine.runner.run.JasmineGroupRunner;
import org.junit.runner.notification.RunNotifier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JasmineGroupDecider {

	private final RunNotifier runNotifier;
	private final JasmineGroup source;
	private final JasmineGroupIgnorer ignorer;
	private final JasmineGroupRunner runner;

	public static JasmineGroupDecider of(RunNotifier runNotifier, JasmineGroup jasmineGroup) {
		return new JasmineGroupDecider(
				runNotifier,
				jasmineGroup,
				new JasmineGroupIgnorer(jasmineGroup.getDescription(), runNotifier),
				new JasmineGroupRunner(jasmineGroup.getDescription()));
	}

	public void process() {
		processCasesAndSubGroups().accept(source);
	}

	private Consumer<? super JasmineGroup> processCasesAndSubGroups() {
		return jasmineGroup -> {
			jasmineGroup.getBeforeAlls().forEach(processBeforeAll());

			JasmineMode jasmineMode = new JasmineModeDecider(jasmineGroup).get();
			JasmineCaseProcessor jasmineCaseProcessor = new JasmineCaseProcessor(runNotifier, jasmineMode, ignorer, runner);
			jasmineGroup.getCases().forEach(jasmineCaseProcessor.processCase(jasmineGroup.getBeforeEachs()));
			jasmineGroup.getGroups().forEach(processGroup(jasmineMode, jasmineGroup.getBeforeEachs()));
		};
	}

	private Consumer<? super JasmineBeforeAll> processBeforeAll() {
		return beforeAll -> beforeAll.getRunnable().run();
	}

	private Consumer<? super JasmineBeforeEach> processBeforeEach() {
		return beforeEach -> beforeEach.getRunnable().run();
	}

	private Consumer<? super JasmineGroup> processGroup(JasmineMode jasmineMode, Collection<JasmineBeforeEach> beforeEachs) {
		return jasmineGroup -> {
			if(containsF(jasmineGroup) || shouldRun(jasmineMode, jasmineGroup)) {
				beforeEachs.forEach(processBeforeEach());
				processCasesAndSubGroups().accept(jasmineGroup);
			} else {
				ignorer.ignoreGroup().accept(jasmineGroup);
			}
		};
	}

	private boolean containsF(JasmineGroup jasmineGroup) {
		return new JasmineModeDecider(jasmineGroup).get().equals(JasmineMode.FOCUS);
	}

	private boolean shouldRun(JasmineMode jasmineMode, JasmineNode jasmineGroup) {
		return jasmineMode.getFilter().test(jasmineGroup);
	}

}
