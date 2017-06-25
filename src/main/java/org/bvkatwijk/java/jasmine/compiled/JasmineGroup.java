package org.bvkatwijk.java.jasmine.compiled;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bvkatwijk.java.jasmine.api.describe.Describe;
import org.bvkatwijk.java.jasmine.api.prefix.Prefix;
import org.bvkatwijk.java.jasmine.api.test.JasmineTest;
import org.bvkatwijk.java.jasmine.name.ClassNameProvider;

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

	public static JasmineGroup of(JasmineTest jasmineTest) {
		return recursiveFrom(
				jasmineTest,
				ClassNameProvider.getClassName(jasmineTest.getClass()),
				Prefix.NONE);
	}

	private static JasmineGroup recursiveFrom(JasmineTest jasmineTest, String description, Prefix prefix) {
		return JasmineGroup.builder()
				.description(description)
				.prefix(prefix)
				.cases(toJasmineCases(jasmineTest, description, prefix))
				.groups(toJasmineGroups(jasmineTest, description))
				.build();
	}

	private static Collection<JasmineGroup> toJasmineGroups(JasmineTest jasmineTest, String parentDescription) {
		return new HashSet<>(jasmineTest.getDescribes())
				.stream()
				.map(describe -> toJasmineGroup(jasmineTest, parentDescription, describe))
				.collect(Collectors.toSet());
	}

	private static JasmineGroup toJasmineGroup(JasmineTest jasmineTest, String parentDescription, Describe describe) {
		jasmineTest.getDescribes().clear();
		jasmineTest.getIts().clear();

		describe.getTestGroup().run();
		return JasmineGroup.recursiveFrom(
				jasmineTest,
				describe.getDescription(),
				describe.getPrefix());
	}

	private static Collection<JasmineCase> toJasmineCases(JasmineTest jasmineTest, String description, Prefix prefix) {
		return jasmineTest.getIts()
				.stream()
				.map(testCase -> JasmineCase.toJasmineCase(testCase, description))
				.collect(Collectors.toSet());
	}

	@Override
	public Collection<JasmineNode> getChilden() {
		return Stream.concat(getGroups().stream(), getCases().stream()).collect(Collectors.toSet());
	}

}
