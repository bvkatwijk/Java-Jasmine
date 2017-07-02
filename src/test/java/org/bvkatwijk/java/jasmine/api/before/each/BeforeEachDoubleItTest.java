package org.bvkatwijk.java.jasmine.api.before.each;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.utils.Mocks;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BeforeEachDoubleItTest {

	private static final ConcurrentLinkedQueue<Integer> operations = new ConcurrentLinkedQueue<>();

	private static final JasmineInternalTest TEST = new JasmineInternalTest() {{
		beforeEach(() -> {
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
	public void beforeEach_shouldHaveRun_twice() {
		Assert.assertEquals(2, operations.size());
	}

}
