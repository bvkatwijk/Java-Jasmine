package org.bvkatwijk.java.jasmine.api.before.each;

import java.util.concurrent.atomic.AtomicBoolean;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.utils.Mocks;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BeforeEachSingleTest {
	
	private static final AtomicBoolean didRun = new AtomicBoolean(false);
	
	private static final JasmineInternalTest TEST = new JasmineInternalTest() {{
		beforeEach(() -> {
			didRun.set(true);
		});
		it("it", () -> {
			
		});
	}};
	
	@BeforeClass
	public static void runTest() {
		Mocks.getMockRunner().accept(TEST);
	}
	
	@Test
	public void beforeEach_shouldHaveRun() {
		Assert.assertTrue(didRun.get());
	}

}
