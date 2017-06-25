package org.bvkatwijk.java.jasmine.mode;

/**
 * Various mode the JasmineRunner can run in.
 */
public enum JasmineMode {
	
	/**
	 * Normal Mode: Execute all groups except ignored ones
	 */
	NORMAL,
	
	/**
	 * Focus mode: Execute only focused groups
	 */
	FOCUS;
}
