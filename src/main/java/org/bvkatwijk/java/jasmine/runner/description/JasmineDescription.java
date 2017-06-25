package org.bvkatwijk.java.jasmine.runner.description;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.bvkatwijk.java.jasmine.compiled.JasmineCase;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;
import org.junit.runner.Description;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JasmineDescription implements Supplier<Description> {
	
	private final JasmineGroup jasmineTest;
	
	@Override
	public Description get() {
		Description description = Description.createSuiteDescription(jasmineTest.getDescription());
		jasmineTest.getCases().forEach(addCaseDescription(description));
		jasmineTest.getGroups().forEach(addGroupDescription(description));
		return description;
	}
	
	private Consumer<? super JasmineGroup> addGroupDescription(Description description) {
		return it -> {
			Description child = Description.createSuiteDescription(it.getDescription());
			it.getCases().forEach(addCaseDescription(child));
			it.getGroups().forEach(addGroupDescription(child));
			description.addChild(child);
		};
	}
	
	private Consumer<? super JasmineCase> addCaseDescription(Description description) {
		return it -> {
			description.addChild(Description.createTestDescription(jasmineTest.getDescription(), it.getDescription()));
		};
	}

}
