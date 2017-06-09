package org.bvkatwijk.java.jasmine.api.test;

import org.bvkatwijk.java.jasmine.api.test.JasmineTest;

public class DoubleDescribeTest extends JasmineTest {{

	describe("Suite 1 / 2", () -> {

		fit("Test 1 / 1", () -> {
			throw new IllegalStateException("e");
		});

	});

	fdescribe("Suite 2 / 2", () -> {

		xit("Test 1 / 1", () -> {

		});

	});

}}
