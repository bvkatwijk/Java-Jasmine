package org.bvkatwijk.java.jasmine.compiled;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bvkatwijk.java.jasmine.api.prefix.Prefix;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class JasmineGroup implements JasmineNode {

	private final String description;
	private final Prefix prefix;
	@Builder.Default
	private final Collection<JasmineGroup> groups = new HashSet<>();
	@Builder.Default
	private final Collection<JasmineCase> cases = new HashSet<>();
	private final Collection<JasmineBeforeAll> beforeAlls;
	private final Collection<JasmineBeforeEach> beforeEachs;

	@Override
	public Collection<JasmineNode> getChilden() {
		return Stream
				.concat(getGroups().stream(), getCases().stream())
				.collect(Collectors.toSet());
	}

}
