package org.bvkatwijk.java.jasmine.api;

public interface JasmineSignature {

	/**
	 * Test group specification
	 * @param description String containing name of test group
	 * @param testGroup Runnable test group content
	 */
	void describe(String description, Runnable testGroup);

	/**
	 * Isolated Test group specification, ignore non-isolated test groups
	 * @param description String containing name of test group
	 * @param testGroup Runnable test group content
	 */
	void fdescribe(String description, Runnable testGroup);

	/**
	 * Pending Test group specification
	 * @param description String containing name of test group
	 * @param testGroup Runnable test group content
	 */
	void xdescribe(String description, Runnable testGroup);

	/**
	 * Test case specification
	 * @param description String containing name of test case
	 * @param testCase Runnable test case content
	 */
	void it(String description, Runnable testCase);

	/**
	 * Isolated Test case specification, ignore non-isolated test cases
	 * @param description String containing name of test case
	 * @param testCase Runnable test case content
	 */
	void fit(String description, Runnable testCase);

	/**
	 * Pending Test case specification
	 * @param description String containing name of test case
	 * @param testCase Runnable test case content
	 */
	void xit(String description, Runnable testCase);

}
