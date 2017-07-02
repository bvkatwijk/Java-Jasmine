package org.bvkatwijk.java.jasmine.compiled;

import java.util.function.Function;

import org.bvkatwijk.java.jasmine.api.it.It;

public class JasmineCaseConverter implements Function<It, JasmineCase> {

	@Override
	public JasmineCase apply(It it) {
		return new JasmineCase(
				it.getDescription(),
				it.getPrefix(),
				it.getTestCase());
	}

}
