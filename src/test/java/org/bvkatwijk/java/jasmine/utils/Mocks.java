package org.bvkatwijk.java.jasmine.utils;

import org.junit.runner.notification.RunNotifier;
import org.mockito.Mockito;

public final class Mocks {

	public static RunNotifier getRunNotifier() {
		return Mockito.mock(RunNotifier.class);
	}
	
	private Mocks() {
		throw new IllegalStateException(this.getClass().getSimpleName() + " not to be instantiated.");
	}

}
