package org.bvkatwijk.java.jasmine.api.f.nest;

import java.util.concurrent.atomic.AtomicBoolean;

import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.utils.Mocks;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import lombok.Getter;

public class NestedFDescribeTest {

	@Getter private static final AtomicBoolean d_it_didRun = new AtomicBoolean(false);
	@Getter private static final AtomicBoolean d_fd_it_didRun = new AtomicBoolean(false);
	@Getter private static final AtomicBoolean d_fd_d_it_didRun = new AtomicBoolean(false);
	@Getter private static final AtomicBoolean d_d_it_didRun = new AtomicBoolean(false);
	@Getter private static final AtomicBoolean d_d_d_it_didRun = new AtomicBoolean(false);

	private static final JasmineInternalTest TEST = new JasmineInternalTest(){{
		describe("describe", () -> {
			it("it", () -> {
				d_it_didRun.set(true);
			});
			fdescribe("fdescribe", () -> {
				it("it", () -> {
					d_fd_it_didRun.set(true);
				});
				describe("describe", () -> {
					it("it", () -> {
						d_fd_d_it_didRun.set(true);
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
		Mocks.getMockRunner().accept(TEST);
	}
	
	@Test
	public void d_it_didRun_shouldBeFalse() {
		Assert.assertFalse(d_it_didRun.get());
	}
	
	@Test
	public void d_fd_it_didRun_shouldBeTrue() {
		Assert.assertTrue(d_fd_it_didRun.get());
	}
	
	@Test
	public void d_fd_d_it_didRun_shouldBeTrue() {
		Assert.assertTrue(d_fd_d_it_didRun.get());
	}
	
	@Test
	public void d_d_it_didRun_shouldBeFalse() {
		Assert.assertFalse(d_d_it_didRun.get());
	}
	
	@Test
	public void d_d_d_it_didRun_shouldBeFalse() {
		Assert.assertFalse(d_d_d_it_didRun.get());
	}

}