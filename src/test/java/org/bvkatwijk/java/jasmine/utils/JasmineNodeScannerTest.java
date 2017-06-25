package org.bvkatwijk.java.jasmine.utils;

import org.bvkatwijk.java.jasmine.Jasmine;
import org.bvkatwijk.java.jasmine.api.prefix.Prefix;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for {@link JasmineNodeScanner}
 */
public class JasmineNodeScannerTest {

	@Test
	public void scanner_shouldDetectIgnoreGroup() {
		Assert.assertTrue(new JasmineNodeScanner(node -> Prefix.X.equals(node.getPrefix()))
				.anyMatch(Jasmine.XDESCRIBE_IT.getJasmineGroup()));
	}
	
	@Test
	public void scanner_shouldDetectIgnoreCase() {
		Assert.assertTrue(new JasmineNodeScanner(node -> Prefix.X.equals(node.getPrefix()))
				.anyMatch(Jasmine.DESCRIBE_XIT.getJasmineGroup()));
	}
	
	@Test
	public void scanner_shouldDetectFocusGroup() {
		Assert.assertTrue(new JasmineNodeScanner(node -> Prefix.F.equals(node.getPrefix()))
				.anyMatch(Jasmine.FDESCRIBE_IT.getJasmineGroup()));
	}

	@Test
	public void scanner_shouldDetectFocusCase() {
		Assert.assertTrue(new JasmineNodeScanner(node -> Prefix.F.equals(node.getPrefix()))
				.anyMatch(Jasmine.DESCRIBE_FIT.getJasmineGroup()));
	}

}
