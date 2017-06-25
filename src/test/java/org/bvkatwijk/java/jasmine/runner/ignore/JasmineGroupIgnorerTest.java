package org.bvkatwijk.java.jasmine.runner.ignore;

import java.util.concurrent.atomic.AtomicBoolean;

import org.bvkatwijk.java.jasmine.api.prefix.Prefix;
import org.bvkatwijk.java.jasmine.compiled.JasmineCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;

/**
 * Test cases for {@link JasmineGroupIgnorer}
 */
public class JasmineGroupIgnorerTest {
	
	private final AtomicBoolean didRun = new AtomicBoolean(false);

	@Test
	public void ignorer_shouldNotRunJasmineCase() {
		new JasmineGroupIgnorer("baseName")
		.ignoreCase(new RunNotifier())
		.accept(new JasmineCase("description", Prefix.NONE, () -> { didRun.set(true); }));
		
		Assert.assertFalse(didRun.get());
	}
}
