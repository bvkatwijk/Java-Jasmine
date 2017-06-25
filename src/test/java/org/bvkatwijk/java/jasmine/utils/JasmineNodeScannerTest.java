package org.bvkatwijk.java.jasmine.utils;

import org.bvkatwijk.java.jasmine.api.prefix.Prefix;
import org.bvkatwijk.java.jasmine.api.test.JasmineTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for {@link JasmineNodeScanner}
 */
public class JasmineNodeScannerTest {

	@Test
	public void scanner_shouldDetectIgnoreGroup() {
		Assert.assertTrue(JasmineNodeScanner
				.of(node -> Prefix.X.equals(node.getPrefix()))
				.anyMatch(new JasmineTest(){{

					describe("describe", () -> {

						xit("should not run xit case", () -> {
							throw new AssertionError("should not be executed");
						});

					});

				}}.compile()));
	}

	@Test
	public void scanner_shouldDetectIgnoreCase() {
		Assert.assertTrue(JasmineNodeScanner
				.of(node -> Prefix.X.equals(node.getPrefix()))
				.anyMatch(new JasmineTest(){{

					xdescribe("xdescribe", () -> {

						it("should not run it case", () -> {
							throw new AssertionError("should not be executed");
						});

					});

				}}.compile()));
	}

}
