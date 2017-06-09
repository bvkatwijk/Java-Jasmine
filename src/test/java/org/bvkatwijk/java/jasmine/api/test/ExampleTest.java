package org.bvkatwijk.java.jasmine.api.test;

import org.junit.Assert;

public class ExampleTest extends JasmineTest {{

	describe("A suite", () -> {
		it("should run its testcase", () -> {
			Assert.assertEquals(true, 1 == 1);
		});
	});

}}
