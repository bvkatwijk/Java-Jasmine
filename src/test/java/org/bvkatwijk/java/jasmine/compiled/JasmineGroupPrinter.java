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
				appendGroups(
						jasmineGroup.getGroups(),
						jasmineGroup.getDescription(),
						"\n\t"),
				"\n\t");
	}

	private String appendGroups(Collection<JasmineGroup> groups, String result, String indent) {
		for(JasmineGroup nextGroup : groups) {
			result = result + indent + nextGroup.getDescription();
			result = appendGroups(nextGroup.getGroups(), result, indent + "\t");
			result = appendCases(nextGroup.getCases(), result, indent + "\t");
		}
		return result;
	}

	private String appendCases(Collection<JasmineCase> cases, String result, String indent) {
		for(JasmineCase nextCase : cases) {
			result = result + indent + nextCase.getDescription();
		}
		return result;
	}
}
