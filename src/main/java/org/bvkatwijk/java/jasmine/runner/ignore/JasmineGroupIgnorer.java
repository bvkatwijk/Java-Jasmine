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
	private final RunNotifier runNotifier;

	public Consumer<? super JasmineCase> ignoreCase() {
		return jasmineCase -> runNotifier
				.fireTestIgnored(Description.createTestDescription(baseName, jasmineCase.getDescription()));
	}

	public Consumer<? super JasmineGroup> ignoreGroup() {
		return jasmineGroup -> {
			jasmineGroup.getCases().forEach(this.ignoreCase());
			jasmineGroup.getGroups().forEach(this.ignoreGroup());
		};
	}

}
