package org.bvkatwijk.java.jasmine.mode;

import org.bvkatwijk.java.jasmine.Jasmine;
import org.junit.Assert;
import org.junit.Test;

public class JasmineModeDeciderTest {

	@Test
	public void decider_shouldReturnNormalMode_onDescribeIt() {
		Assert.assertEquals(JasmineMode.NORMAL,
				new JasmineModeDecider(Jasmine.DESCRIBE_IT.getJasmineGroup()).get());
	}

	@Test
	public void decider_shouldReturnNormalMode_onDescribeXIt() {
		Assert.assertEquals(JasmineMode.NORMAL,
				new JasmineModeDecider(Jasmine.DESCRIBE_XIT.getJasmineGroup()).get());
	}

	@Test
	public void decider_shouldReturnNormalMode_onXDescribeIt() {
		Assert.assertEquals(JasmineMode.NORMAL,
				new JasmineModeDecider(Jasmine.XDESCRIBE_IT.getJasmineGroup()).get());
	}

	@Test
	public void decider_shouldReturnFocusMode_onFDescribeIt() {
		Assert.assertEquals(JasmineMode.FOCUS,
				new JasmineModeDecider(Jasmine.FDESCRIBE_IT.getJasmineGroup()).get());
	}

	@Test
	public void decider_shouldReturnFocusMode_onDescribeFIt() {
		Assert.assertEquals(JasmineMode.FOCUS,
				new JasmineModeDecider(Jasmine.DESCRIBE_FIT.getJasmineGroup()).get());
	}

}
