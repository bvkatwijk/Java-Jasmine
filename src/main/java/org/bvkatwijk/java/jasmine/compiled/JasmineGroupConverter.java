package org.bvkatwijk.java.jasmine.compiled;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bvkatwijk.java.jasmine.api.describe.Describe;
import org.bvkatwijk.java.jasmine.api.prefix.Prefix;
import org.bvkatwijk.java.jasmine.api.test.JasmineInternalTest;

/**
 * Converter from {@link JasmineInternalTest} to {@link JasmineGroup}
 */
public class JasmineGroupConverter implements Function<JasmineInternalTest, JasmineGroup> {

	private final JasmineCaseConverter itToCase = new JasmineCaseConverter();
	private final JasmineBeforeAllConverter toJasmineBeforeAll = new JasmineBeforeAllConverter();
	private final JasmineBeforeEachConverter toJasmineBeforeEach = new JasmineBeforeEachConverter();

	@Override
	public JasmineGroup apply(JasmineInternalTest jasmineTest) {
		return recursiveFrom(
				jasmineTest,
				getClassName(jasmineTest.getClass()),
				Prefix.NONE);
	}

	private JasmineGroup recursiveFrom(JasmineInternalTest jasmineTest, String description, Prefix prefix) {
		return JasmineGroup.builder()
				.description(description)
				.prefix(prefix)
				.cases(toJasmineCases(jasmineTest, description, prefix))
				.beforeAlls(toJasmineBeforeAlls(jasmineTest))
				.beforeEachs(toJasmineBeforeEach(jasmineTest))
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
		jasmineTest.getBeforeAlls().clear();
		jasmineTest.getBeforeEachs().clear();

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

	private Collection<JasmineBeforeAll> toJasmineBeforeAlls(JasmineInternalTest jasmineTest) {
		return jasmineTest.getBeforeAlls()
				.stream()
				.map(toJasmineBeforeAll)
				.collect(Collectors.toSet());
	}

	private Collection<JasmineBeforeEach> toJasmineBeforeEach(JasmineInternalTest jasmineTest) {
		return jasmineTest.getBeforeEachs()
				.stream()
				.map(toJasmineBeforeEach)
				.collect(Collectors.toSet());
	}

	//

	/** Return class or superclass name for better JUnit reporting */
	private static String getClassName(Class<?> clazz) {
		return clazz.getSimpleName().equals("")
				? clazz.getSuperclass().getSimpleName()
						: clazz.getSimpleName();
	}

}
