package org.bvkatwijk.java.jasmine.runner;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.junit.Test;

public class JavaJasmineRunnerTest extends RunValidationTest {

	@Test
	public void javaJasmineRunner_shouldRunGroup_describeIt() {
		assertDidRun(new JasmineInternalTest(){{
			describe("describe", () -> {
				it("it", () -> {
					getDidRun().set(true);
				});
			});
		}});
	}

	@Test
	public void javaJasmineRunner_shouldNotRunGroup_xDescribeIt() {
		assertDidNotRun(new JasmineInternalTest(){{
			xdescribe("xdescribe", () -> {
				it("it", () -> {
					getDidRun().set(true);
				});
			});
		}});
	}

	@Test
	public void javaJasmineRunner_shouldNotRun_describeXIt() {
		assertDidNotRun(new JasmineInternalTest(){{
			describe("describe", () -> {
				xit("xit", () -> {
					getDidRun().set(true);
				});
			});
		}});
	}

}
