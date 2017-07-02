package org.bvkatwijk.java.jasmine.compiled;

import java.util.ArrayList;
import java.util.Collection;

import org.bvkatwijk.java.jasmine.api.prefix.Prefix;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JasmineCase implements JasmineNode {

	private final String description;
	private final Prefix prefix;
	private final Runnable runnable;

	@Override
	public Collection<JasmineNode> getChilden() {
		return new ArrayList<>(0);
	}

}
