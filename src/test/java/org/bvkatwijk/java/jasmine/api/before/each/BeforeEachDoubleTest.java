package org.bvkatwijk.java.jasmine.api.before.each;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.utils.Mocks;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BeforeEachDoubleTest {

	private static final ConcurrentLinkedQueue<Integer> operations = new ConcurrentLinkedQueue<>();

	private static final JasmineInternalTest TEST = new JasmineInternalTest() {{
		beforeEach(() -> {
			operations.add(0);
		});
		beforeEach(() -> {
			operations.add(1);
		});
		it("it", () -> {

		});
	}};

	@BeforeClass
	public static void runTest() {
		Mocks.getMockRunner().accept(TEST);
	}

	@Test
	public void beforeEachs_first_shouldHaveRun() {
		Assert.assertTrue(operations.contains(0));
	}

	@Test
	public void beforeEachs_second_shouldHaveRun() {
		Assert.assertTrue(operations.contains(1));
	}

	@Test
	public void beforeEachs_shouldHavePerformedTwoOperations() {
		Assert.assertEquals(2, operations.size());
	}

}
