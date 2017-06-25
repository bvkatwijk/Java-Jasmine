package org.bvkatwijk.java.jasmine;

import java.util.function.Supplier;

import org.bvkatwijk.java.jasmine.api.test.JasmineTest;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Jasmine {

	DESCRIBE_IT(() -> new JasmineTest(){{
		describe("describe", () -> {
			it("it", () -> {
				//
			});
		});
	}}),

	DESCRIBE_FIT(() -> new JasmineTest(){{
		describe("describe", () -> {
			fit("fit", () -> {
				//
			});
		});
	}}),

	DESCRIBE_XIT(() -> new JasmineTest(){{
		describe("describe", () -> {
			xit("xit", () -> {
				//
			});
		});
	}}),

	FDESCRIBE_IT(() -> new JasmineTest(){{
		fdescribe("fdescribe", () -> {
			it("it", () -> {
				//
			});
		});
	}}),

	XDESCRIBE_IT(() -> new JasmineTest(){{
		xdescribe("xdescribe", () -> {
			it("it", () -> {
				//
			});
		});
	}}),

	;

	private final Supplier<JasmineTest> jasmineTestSupplier;

	public JasmineTest getJasmineTest() {
		return jasmineTestSupplier.get();
	}

	public JasmineGroup getJasmineGroup() {
		return jasmineTestSupplier.get().compile();
	}

}
