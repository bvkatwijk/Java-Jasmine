package org.bvkatwijk.java.jasmine.utils;

import java.util.function.Predicate;

import org.bvkatwijk.java.jasmine.compiled.JasmineNode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName="of")
public class JasmineNodeScanner {

	private final Predicate<JasmineNode> predicate;

	public boolean anyMatch(JasmineNode node) {
		return predicate.test(node)
				|| testChildren(node);
	}

	private boolean testChildren(JasmineNode node) {
		return node.getChilden()
				.stream()
				.anyMatch(this::anyMatch);
	}

}
