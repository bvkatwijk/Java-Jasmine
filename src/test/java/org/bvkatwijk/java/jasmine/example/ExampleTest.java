package org.bvkatwijk.java.jasmine.example;

import org.bvkatwijk.java.jasmine.api.test.JasmineTest;
import org.junit.Assert;

public class ExampleTest extends JasmineTest {{

	describe("A suite", () -> {
		it("should run its testcase", () -> {
			Assert.assertEquals(true, true);
		});
	});

}}
