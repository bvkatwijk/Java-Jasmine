package org.bvkatwijk.java.jasmine.compiled;

import java.util.function.Function;

import org.bvkatwijk.java.jasmine.api.before.BeforeAll;

public class JasmineBeforeAllConverter implements Function<BeforeAll, JasmineBeforeAll> {

	@Override
	public JasmineBeforeAll apply(BeforeAll beforeAll) {
		return new JasmineBeforeAll(beforeAll.getRunnable());
	}

}
