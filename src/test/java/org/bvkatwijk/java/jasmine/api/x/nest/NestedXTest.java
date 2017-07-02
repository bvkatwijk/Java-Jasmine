package org.bvkatwijk.java.jasmine.api.x.nest;

import java.util.concurrent.atomic.AtomicBoolean;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.utils.Mocks;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import lombok.Getter;

public class NestedXTest {

	@Getter private static final AtomicBoolean d_it_didRun = new AtomicBoolean(false);
	@Getter private static final AtomicBoolean d_xd_it_didRun = new AtomicBoolean(false);
	@Getter private static final AtomicBoolean d_xd_d_it_didRun = new AtomicBoolean(false);
	@Getter private static final AtomicBoolean d_d_it_didRun = new AtomicBoolean(false);
	@Getter private static final AtomicBoolean d_d_d_it_didRun = new AtomicBoolean(false);

	private static final JasmineInternalTest NESTED_X_TEST = new JasmineInternalTest(){{
		describe("describe", () -> {
			it("it", () -> {
				d_it_didRun.set(true);
			});
			xdescribe("xdescribe", () -> {
				it("it", () -> {
					d_xd_it_didRun.set(true);
				});
				describe("describe", () -> {
					it("it", () -> {
						d_xd_d_it_didRun.set(true);
					});
				});
			});
			describe("fdescribe", () -> {
				it("it", () -> {
					d_d_it_didRun.set(true);
				});
				describe("describe", () -> {
					it("it", () -> {
						d_d_d_it_didRun.set(true);
					});
				});
			});
		});
	}};

	@BeforeClass
	public static void runTest() {
		Mocks.getMockRunner().accept(NESTED_X_TEST);
	}

	@Test
	public void d_it_didRun_shouldBeTrue() {
		Assert.assertTrue(d_it_didRun.get());
	}

	@Test
	public void d_xd_it_didRun_shouldBeFalse() {
		Assert.assertFalse(d_xd_it_didRun.get());
	}

	@Test
	public void d_xd_d_it_didRun_shouldBeFalse() {
		Assert.assertFalse(d_xd_d_it_didRun.get());
	}

	@Test
	public void d_d_it_didRun_shouldBeTrue() {
		Assert.assertTrue(d_d_it_didRun.get());
	}

	@Test
	public void d_d_d_it_didRun_shouldBeTrue() {
		Assert.assertTrue(d_d_d_it_didRun.get());
	}

}
