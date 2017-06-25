package org.bvkatwijk.java.jasmine.runner;

import java.util.concurrent.atomic.AtomicBoolean;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.runner.decide.JasmineGroupDecider;
import org.bvkatwijk.java.jasmine.utils.Mocks;
import org.junit.Assert;
import org.junit.Test;

public class JavaJasmineRunnerTest {

	private final AtomicBoolean describeIt_didRun = new AtomicBoolean(false);
	private final AtomicBoolean xDescribeIt_didRun = new AtomicBoolean(false);
	private final AtomicBoolean describeXit_didRun = new AtomicBoolean(false);

	@Test
	public void javaJasmineRunner_shouldRunGroup_describeIt() {
		JasmineGroupDecider.of(Mocks.getRunNotifier(), new JasmineInternalTest(){{
			describe("describe", () -> {
				it("it", () -> {
					describeIt_didRun.set(true);
				});
			});
		}}.compile()).process();

		Assert.assertTrue(describeIt_didRun.get());
	}

	@Test
	public void javaJasmineRunner_shouldNotRunGroup_xDescribeIt() {
		JasmineGroupDecider.of(Mocks.getRunNotifier(), new JasmineInternalTest(){{
			xdescribe("xdescribe", () -> {
				it("it", () -> {
					xDescribeIt_didRun.set(true);
				});
			});
		}}.compile()).process();

		Assert.assertFalse(xDescribeIt_didRun.get());
	}

	@Test
	public void javaJasmineRunner_shouldNotRun_describeXIt() {
		JasmineGroupDecider.of(Mocks.getRunNotifier(), new JasmineInternalTest(){{
			describe("describe", () -> {
				xit("xit", () -> {
					describeXit_didRun.set(true);
				});
			});
		}}.compile()).process();

		Assert.assertFalse(describeXit_didRun.get());
	}

}
