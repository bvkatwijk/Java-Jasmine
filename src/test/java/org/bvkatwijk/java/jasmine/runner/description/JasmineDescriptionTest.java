package org.bvkatwijk.java.jasmine.runner.description;

import org.bvkatwijk.java.jasmine.Jasmine;
import org.junit.Assert;
import org.junit.Test;

public class JasmineDescriptionTest {

	@Test
	public void description_ofAnonymousClass_hasDisplayNameJasmineTest() {
		Assert.assertEquals("JasmineInternalTest",
				new JasmineDescription(Jasmine.DESCRIBE_IT.getJasmineGroup())
				.get()
				.getDisplayName());
	}

}
