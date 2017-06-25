package org.bvkatwijk.java.jasmine.compiled;

import java.util.Collection;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JasmineGroupPrinter {
	
	private final JasmineGroup jasmineGroup;
	
	@Override
	public String toString() {
		return appendCases(
				jasmineGroup.getCases(),
				appendGroups(jasmineGroup.getGroups(), jasmineGroup.getDescription()));
	}

	private String appendGroups(Collection<JasmineGroup> groups, String result) {
		for(JasmineGroup nextGroup : groups) {
			result = appendGroups(nextGroup.getGroups(), result);
			result = appendCases(nextGroup.getCases(), result);
		}
		return result;
	}

	private String appendCases(Collection<JasmineCase> cases, String result) {
		for(JasmineCase nextCase : cases) {
			result = result + "\n\t" + nextCase.getDescription();
		}
		return result;
	}
}
