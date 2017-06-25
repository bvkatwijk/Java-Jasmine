package org.bvkatwijk.java.jasmine.mode;

import java.util.function.Predicate;

import org.bvkatwijk.java.jasmine.api.prefix.Prefix;
import org.bvkatwijk.java.jasmine.compiled.JasmineNode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Various mode the JasmineRunner can run in.
 */
@Getter
@RequiredArgsConstructor
public enum JasmineMode {
	
	/**
	 * Normal Mode: Execute all groups except ignored ones
	 */
	NORMAL(node -> !node.getPrefix().equals(Prefix.X)),
	
	/**
	 * Focus mode: Execute only focused groups
	 */
	FOCUS(node -> node.getPrefix().equals(Prefix.F));
	
	private final Predicate<JasmineNode> filter;

}
