package org.bvkatwijk.java.jasmine.utils;

import java.util.Collection;
import java.util.function.Predicate;

import org.bvkatwijk.java.jasmine.compiled.JasmineNode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JasmineNodeScanner {

	private final Predicate<JasmineNode> predicate;

	public boolean anyMatch(JasmineNode node) {
		return predicate.test(node)
				|| testChildren(node);
	}

	public boolean anyMatch(Collection<JasmineNode> node) {
		return node.stream()
				.anyMatch(this::anyMatch);
	}

	private boolean testChildren(JasmineNode node) {
		return anyMatch(node.getChilden());
	}

}
