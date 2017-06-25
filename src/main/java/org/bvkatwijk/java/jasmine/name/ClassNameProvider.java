package org.bvkatwijk.java.jasmine.name;

public final class ClassNameProvider {
	
	/**
	 * Return class name of supplied class
	 */
	public static String getClassName(Class<?> clazz) {
		return clazz.getSimpleName().equals("")
				? clazz.getSuperclass().getSimpleName()
						: clazz.getSimpleName();
	}

	private ClassNameProvider() {
		throw new IllegalStateException(this.getClass().getSimpleName() + " not to be instantiated.");
	}

}
