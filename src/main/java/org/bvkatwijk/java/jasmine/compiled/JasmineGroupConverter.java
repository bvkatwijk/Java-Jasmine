package org.bvkatwijk.java.jasmine.compiled;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bvkatwijk.java.jasmine.api.describe.Describe;
import org.bvkatwijk.java.jasmine.api.prefix.Prefix;
import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;
import org.bvkatwijk.java.jasmine.utils.ClassNameProvider;

/**
 * Converter from {@link JasmineInternalTest} to {@link JasmineGroup}
 */
public class JasmineGroupConverter implements Function<JasmineInternalTest, JasmineGroup> {
	
	private final JasmineCaseConverter itToCase = new JasmineCaseConverter();

	@Override
	public JasmineGroup apply(JasmineInternalTest jasmineTest) {
		return recursiveFrom(
				jasmineTest,
				ClassNameProvider.getClassName(jasmineTest.getClass()),
				Prefix.NONE);
	}

	private JasmineGroup recursiveFrom(JasmineInternalTest jasmineTest, String description, Prefix prefix) {
		return JasmineGroup.builder()
				.description(description)
				.prefix(prefix)
				.cases(toJasmineCases(jasmineTest, description, prefix))
				.groups(toJasmineGroups(jasmineTest, description))
				.build();
	}

	private Collection<JasmineGroup> toJasmineGroups(JasmineInternalTest jasmineTest, String parentDescription) {
		return new HashSet<>(jasmineTest.getDescribes())
				.stream()
				.map(describe -> toJasmineGroup(jasmineTest, parentDescription, describe))
				.collect(Collectors.toSet());
	}

	private JasmineGroup toJasmineGroup(JasmineInternalTest jasmineTest, String parentDescription, Describe describe) {
		jasmineTest.getDescribes().clear();
		jasmineTest.getIts().clear();

		describe.getTestGroup().run();

		return recursiveFrom(
				jasmineTest,
				describe.getDescription(),
				describe.getPrefix());
	}

	private Collection<JasmineCase> toJasmineCases(JasmineInternalTest jasmineTest, String description, Prefix prefix) {
		return jasmineTest.getIts()
				.stream()
				.map(itToCase)
				.collect(Collectors.toSet());
	}

}