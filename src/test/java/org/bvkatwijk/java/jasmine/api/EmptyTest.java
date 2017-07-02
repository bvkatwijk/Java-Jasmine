package org.bvkatwijk.java.jasmine.api;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.utils.Mocks;
import org.junit.Test;

public class EmptyTest {

	@Test
	public void runningEmptyTest_shouldNotThrow() {
		Mocks.getMockRunner().accept(new JasmineInternalTest() {{}});
	}

}
