package org.bvkatwijk.java.jasmine.api.before.all;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.utils.Mocks;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BeforeAllDoubleItTest {

	private static final ConcurrentLinkedQueue<Integer> operations = new ConcurrentLinkedQueue<>();

	private static final JasmineInternalTest TEST = new JasmineInternalTest() {{
		beforeAll(() -> {
			operations.add(0);
		});
		it("it", () -> {

		});
		it("it", () -> {

		});
	}};

	@BeforeClass
	public static void runTest() {
		Mocks.getMockRunner().accept(TEST);
	}

	@Test
	public void beforeAll_shouldHaveRunOnce() {
		Assert.assertEquals(1, operations.size());
	}

}
