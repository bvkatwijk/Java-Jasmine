package org.bvkatwijk.java.jasmine;

import java.util.function.Function;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Jasmine {

	DESCRIBE_IT((run) -> new JasmineInternalTest(){{
		describe("describe", () -> {
			it("it", () -> {
				run.run();
			});
		});
	}}),

	DESCRIBE_FIT((run) -> new JasmineInternalTest(){{
		describe("describe", () -> {
			fit("fit", () -> {
				run.run();
			});
		});
	}}),

	DESCRIBE_XIT((run) -> new JasmineInternalTest(){{
		describe("describe", () -> {
			xit("xit", () -> {
				run.run();
			});
		});
	}}),

	FDESCRIBE_IT((run) -> new JasmineInternalTest(){{
		fdescribe("fdescribe", () -> {
			it("it", () -> {
				run.run();
			});
		});
	}}),

	XDESCRIBE_IT((run) -> new JasmineInternalTest(){{
		xdescribe("xdescribe", () -> {
			it("it", () -> {
				run.run();
			});
		});
	}}),

	;

	private final Function<Runnable, JasmineInternalTest> jasmineTestSupplier;

	public JasmineInternalTest getJasmineTest() {
		return jasmineTestSupplier.apply(() -> {});
	}
	
	public JasmineInternalTest getJasmineTest(Runnable runnable) {
		return jasmineTestSupplier.apply(runnable);
	}

	public JasmineGroup getJasmineGroup() {
		return getJasmineTest().compile();
	}

}
