package org.bvkatwijk.java.jasmine.compiled;

import org.junit.Assert;
import org.junit.Test;

public class JasmineGroupTest {

	@Test
	public void jasmineGroup_getChildren_whenEmpty_shouldBe0() {
		Assert.assertEquals(0, JasmineGroup.builder().build().getChilden().size());
	}

}
