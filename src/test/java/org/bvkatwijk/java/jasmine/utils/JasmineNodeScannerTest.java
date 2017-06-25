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

					xdescribe("xdescribe", () -> {

						it("it", () -> {
							//
						});

					});

				}}.compile()));
	}
	
	@Test
	public void scanner_shouldDetectIgnoreCase() {
		Assert.assertTrue(JasmineNodeScanner
				.of(node -> Prefix.X.equals(node.getPrefix()))
				.anyMatch(new JasmineTest(){{

					describe("describe", () -> {

						xit("xit", () -> {
							//
						});

					});

				}}.compile()));
	}
	
	@Test
	public void scanner_shouldDetectFocusGroup() {
		Assert.assertTrue(JasmineNodeScanner
				.of(node -> Prefix.F.equals(node.getPrefix()))
				.anyMatch(new JasmineTest(){{

					fdescribe("fdescribe", () -> {

						it("it", () -> {
							//
						});

					});

				}}.compile()));
	}

	@Test
	public void scanner_shouldDetectFocusCase() {
		Assert.assertTrue(JasmineNodeScanner
				.of(node -> Prefix.F.equals(node.getPrefix()))
				.anyMatch(new JasmineTest(){{

					describe("describe", () -> {

						fit("fit", () -> {
							//
						});

					});

				}}.compile()));
	}

}
