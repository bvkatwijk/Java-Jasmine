package org.bvkatwijk.java.jasmine.runner;

import org.bvkatwijk.java.jasmine.Jasmine;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test cases for {@link JavaJasmineRunner}
 */
public class JasmineGroupDeciderTest extends RunValidationTest {

	@Test
	public void jasmineDecider_shouldRunGroup_describeIt() {
		assertDidRun(Jasmine.DESCRIBE_IT);
	}

	@Ignore("to be implemented")
	@Test
	public void jasmineDecider_shouldRunGroup_fDescribeIt() {
		assertDidRun(Jasmine.FDESCRIBE_IT);
	}

	@Test
	public void jasmineDecider_shouldNotRunGroup_xDescribeIt() {
		assertDidNotRun(Jasmine.XDESCRIBE_IT);
	}

	@Test
	public void jasmineDecider_shouldNotRun_describeXIt() {
		assertDidNotRun(Jasmine.DESCRIBE_XIT);
	}

	@Ignore("to be implemented")
	@Test
	public void jasmineDecider_shouldRun_describeFIt() {
		assertDidRun(Jasmine.DESCRIBE_FIT);
	}

}
