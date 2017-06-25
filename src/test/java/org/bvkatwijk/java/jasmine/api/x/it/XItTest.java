package org.bvkatwijk.java.jasmine.api.x.it;

import org.bvkatwijk.java.jasmine.api.test.JasmineTest;

public class XItTest extends JasmineTest {{
	
	describe("A suite", () -> {
		
		xit("should not run xit case", () -> {
			throw new AssertionError("should not be executed");
		});
		
	});
}}
