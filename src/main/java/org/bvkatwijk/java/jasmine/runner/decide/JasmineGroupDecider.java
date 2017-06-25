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
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		JasmineMode jasmineMode = new JasmineModeDecider(source).get();
		log.info("Running " + source.getDescription() + " in mode " + jasmineMode);
		runCasesAndSubGroups(runNotifier, jasmineMode).accept(source);
	}

	private Consumer<? super JasmineGroup> runCasesAndSubGroups(RunNotifier runNotifier, JasmineMode jasmineMode) {
		return jasmineGroup -> {
			jasmineGroup.getCases().forEach(processCase(runNotifier, jasmineMode));
			jasmineGroup.getGroups().forEach(processGroup(runNotifier, jasmineMode));
		};
	}

	private Consumer<? super JasmineGroup> processGroup(RunNotifier runNotifier, JasmineMode jasmineMode) {
		return jasmineGroup -> {
			log.info("Deciding " + jasmineGroup.getDescription() + " for mode " + jasmineMode);
			if (shouldRun(jasmineMode, jasmineGroup)) {
				log.info("Running " + jasmineGroup.getDescription() + " for mode " + jasmineMode);
				runCasesAndSubGroups(runNotifier, jasmineMode).accept(jasmineGroup);
			} else {
				log.info("Ignoring " + jasmineGroup.getDescription() + " for mode " + jasmineMode);
				ignorer.ignoreGroup(runNotifier).accept(jasmineGroup);
			}
		};
	}

	private Consumer<? super JasmineCase> processCase(RunNotifier runNotifier, JasmineMode jasmineMode) {
		return jasmineCase -> {
			log.info("Deciding " + jasmineCase.getDescription() + " for mode " + jasmineMode);
			if (shouldRun(jasmineMode, jasmineCase)) {
				log.info("Running " + jasmineCase.getDescription() + " for mode " + jasmineMode);
				runner.runIt(runNotifier, source.getDescription()).accept(jasmineCase);
			} else {
				log.info("Ignoring " + jasmineCase.getDescription() + " for mode " + jasmineMode);
				ignorer.ignoreCase(runNotifier).accept(jasmineCase);
			}
		};
	}

	private boolean shouldRun(JasmineMode jasmineMode, JasmineNode jasmineGroup) {
		return jasmineMode.getFilter().test(jasmineGroup);
	}

}
