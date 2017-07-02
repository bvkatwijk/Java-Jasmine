package org.bvkatwijk.java.jasmine.compiled;

import java.util.Collection;
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
	private final Collection<JasmineGroup> groups;
	private final Collection<JasmineCase> cases;
	private final Collection<JasmineBeforeAll> beforeAlls;

	@Override
	public Collection<JasmineNode> getChilden() {
		return Stream.concat(getGroups().stream(), getCases().stream()).collect(Collectors.toSet());
	}

}
