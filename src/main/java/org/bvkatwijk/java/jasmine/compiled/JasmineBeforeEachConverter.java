package org.bvkatwijk.java.jasmine.compiled;

import java.util.function.Function;

import org.bvkatwijk.java.jasmine.api.before.BeforeEach;

public class JasmineBeforeEachConverter implements Function<BeforeEach, JasmineBeforeEach> {

	@Override
	public JasmineBeforeEach apply(BeforeEach beforeEach) {
		return new JasmineBeforeEach(beforeEach.getRunnable());
	}

}
