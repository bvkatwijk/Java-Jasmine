package org.bvkatwijk.java.jasmine.compiled;

import org.bvkatwijk.java.jasmine.api.it.It;
import org.bvkatwijk.java.jasmine.api.prefix.Prefix;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JasmineCase {

	private final String description;
	private final Prefix prefix;
	private final Runnable runnable;

	public static JasmineCase toJasmineCase(It it, String parentDescription) {
		return new JasmineCase(
				it.getDescription(),
				it.getPrefix(),
				it.getTestCase());
	}

}
