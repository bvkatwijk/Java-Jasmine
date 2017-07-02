package org.bvkatwijk.java.jasmine.api.before.all;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.utils.Mocks;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class NestedBeforeEachTest {

	private static final String BEFORE_ALL = "Before all";
	private static final String BEFORE_EACH_1 = "Before each 1";
	private static final String BEFORE_EACH_2 = "Before each 2";

	private static final ConcurrentLinkedQueue<String> operations = new ConcurrentLinkedQueue<>();

	private static final JasmineInternalTest TEST = new JasmineInternalTest() {{

		beforeAll(() -> {
			operations.add(BEFORE_ALL);
		});

		describe("describe1", () -> {

			beforeEach(() -> {
				operations.add(BEFORE_EACH_1);
			});

			it("it", () -> {});

			it("it", () -> {});

		});

		describe("describe2", () -> {

			beforeEach(() -> {
				operations.add(BEFORE_EACH_2);
			});

			it("it", () -> {});

			it("it", () -> {});

			it("it", () -> {});

		});

	}};

	@BeforeClass
	public static void runTest() {
		Mocks.getMockRunner().accept(TEST);
	}

	@Test
	public void operations_shouldhaveSizeSix() {
		Assert.assertEquals(6, operations.size());
	}

	@Test
	public void operations_shouldContainOne_BeforeAll() {
		Assert.assertEquals(1,
				operations
				.stream()
				.filter(operation -> operation.equals(BEFORE_ALL))
				.count());
	}

	@Test
	public void operations_shouldContainTwo_BeforeEachOne() {
		Assert.assertEquals(2,
				operations
				.stream()
				.filter(operation -> operation.equals(BEFORE_EACH_1))
				.count());
	}

	@Test
	public void operations_shouldContainThree_BeforeEachTwo() {
		Assert.assertEquals(3,
				operations
				.stream()
				.filter(operation -> operation.equals(BEFORE_EACH_2))
				.count());
	}

}
