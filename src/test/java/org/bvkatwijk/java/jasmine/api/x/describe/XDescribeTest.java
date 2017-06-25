package org.bvkatwijk.java.jasmine.api.x.describe;

import org.bvkatwijk.java.jasmine.api.test.JasmineTest;
import org.junit.Ignore;

@Ignore
public class XDescribeTest extends JasmineTest {{
	
	xdescribe("xdescribe", () -> {
		
		it("should not run it case", () -> {
			throw new AssertionError("should not be executed");
		});
		
	});
}}
