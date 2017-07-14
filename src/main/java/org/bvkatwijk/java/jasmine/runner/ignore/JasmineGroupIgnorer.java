package org.bvkatwijk.java.jasmine.runner.ignore;

import java.util.function.Consumer;

import org.bvkatwijk.java.jasmine.compiled.JasmineCase;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;

import lombok.RequiredArgsConstructor;

/**
 * Ignorer for {@link JasmineCase}
 */
@RequiredArgsConstructor
public class JasmineGroupIgnorer {
	
	private final String baseName;

	public Consumer<? super JasmineCase> ignoreCase(RunNotifier runNotifier) {
		return jasmineCase -> {
			runNotifier.fireTestIgnored(Description.createTestDescription(baseName, jasmineCase.getDescription()));
		};
	}
	
	public Consumer<? super JasmineGroup> ignoreGroup(RunNotifier runNotifier) {
		return jasmineGroup -> {
			jasmineGroup.getCases().forEach(this.ignoreCase(runNotifier));
			jasmineGroup.getGroups().forEach(this.ignoreGroup(runNotifier));
		};
	}

}
