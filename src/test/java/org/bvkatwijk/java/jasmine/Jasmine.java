package org.bvkatwijk.java.jasmine;

import java.util.function.Supplier;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Jasmine {

	DESCRIBE_IT(() -> new JasmineInternalTest(){{
		describe("describe", () -> {
			it("it", () -> {
				//
			});
		});
	}}),

	DESCRIBE_FIT(() -> new JasmineInternalTest(){{
		describe("describe", () -> {
			fit("fit", () -> {
				//
			});
		});
	}}),

	DESCRIBE_XIT(() -> new JasmineInternalTest(){{
		describe("describe", () -> {
			xit("xit", () -> {
				//
			});
		});
	}}),

	FDESCRIBE_IT(() -> new JasmineInternalTest(){{
		fdescribe("fdescribe", () -> {
			it("it", () -> {
				//
			});
		});
	}}),

	XDESCRIBE_IT(() -> new JasmineInternalTest(){{
		xdescribe("xdescribe", () -> {
			it("it", () -> {
				//
			});
		});
	}}),

	;

	private final Supplier<JasmineInternalTest> jasmineTestSupplier;

	public JasmineInternalTest getJasmineTest() {
		return jasmineTestSupplier.get();
	}

	public JasmineGroup getJasmineGroup() {
		return getJasmineTest().compile();
	}

}
