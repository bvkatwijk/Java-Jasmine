package org.bvkatwijk.java.jasmine.runner.decide;

import java.util.function.Consumer;

import org.bvkatwijk.java.jasmine.compiled.JasmineBeforeAll;
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
		return new JasmineGroupDecider(
				runNotifier,
				jasmineGroup,
				new JasmineGroupIgnorer(jasmineGroup.getDescription()),
				new JasmineGroupRunner(jasmineGroup.getDescription()));
	}

	public void process() {
		processCasesAndSubGroups(runNotifier).accept(source);
	}
	
	/**
	 * 
	 * @param runNotifier
	 * @return
	 */
	private Consumer<? super JasmineGroup> processCasesAndSubGroups(RunNotifier runNotifier) {
		return jasmineGroup -> {
			jasmineGroup.getBeforeAlls().forEach(processBeforeAll());

			JasmineMode jasmineMode = new JasmineModeDecider(jasmineGroup).get();
			jasmineGroup.getCases().forEach(processCase(runNotifier, jasmineMode));
			jasmineGroup.getGroups().forEach(processGroup(runNotifier, jasmineMode));
		};
	}

	private Consumer<? super JasmineBeforeAll> processBeforeAll() {
		return beforeAll -> beforeAll.getRunnable().run();
	}

	private Consumer<? super JasmineGroup> processGroup(RunNotifier runNotifier, JasmineMode jasmineMode) {
		return jasmineGroup -> {
			if(containsF(jasmineGroup) || shouldRun(jasmineMode, jasmineGroup)) {
				processCasesAndSubGroups(runNotifier).accept(jasmineGroup);
			} else {
				ignorer.ignoreGroup(runNotifier).accept(jasmineGroup);
			}
		};
	}

	private boolean containsF(JasmineGroup jasmineGroup) {
		return new JasmineModeDecider(jasmineGroup).get().equals(JasmineMode.FOCUS);
	}

	private Consumer<? super JasmineCase> processCase(RunNotifier runNotifier, JasmineMode jasmineMode) {
		return jasmineCase -> {
			if (shouldRun(jasmineMode, jasmineCase)) {
				runner.runIt(runNotifier).accept(jasmineCase);
			} else {
				ignorer.ignoreCase(runNotifier).accept(jasmineCase);
			}
		};
	}

	private boolean shouldRun(JasmineMode jasmineMode, JasmineNode jasmineGroup) {
		return jasmineMode.getFilter().test(jasmineGroup);
	}

}
