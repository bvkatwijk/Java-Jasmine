package org.bvkatwijk.java.jasmine.api.test;

import java.util.Collection;
import java.util.HashSet;

import org.bvkatwijk.java.jasmine.api.JasmineSignature;
import org.bvkatwijk.java.jasmine.api.before.BeforeAll;
import org.bvkatwijk.java.jasmine.api.before.BeforeEach;
import org.bvkatwijk.java.jasmine.api.describe.Describe;
import org.bvkatwijk.java.jasmine.api.it.It;
import org.bvkatwijk.java.jasmine.api.prefix.Prefix;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroupConverter;

import lombok.Getter;

@Getter
public abstract class JasmineInternalTest implements JasmineSignature {

	final Collection<Describe> describes = new HashSet<>();
	final Collection<It> its = new HashSet<>();
	final Collection<BeforeAll> beforeAlls = new HashSet<>();
	final Collection<BeforeEach> beforeEachs = new HashSet<>();

	@Override
	public void describe(String description, Runnable testGroup) {
		this.describes.add(new Describe(description, testGroup, Prefix.NONE));
	}

	@Override
	public void fdescribe(String description, Runnable testGroup) {
		this.describes.add(new Describe(description, testGroup, Prefix.F));
	}

	@Override
	public void xdescribe(String description, Runnable testGroup) {
		this.describes.add(new Describe(description, testGroup, Prefix.X));
	}

	@Override
	public void it(String description, Runnable testCase) {
		this.its.add(new It(description, testCase, Prefix.NONE));
	}

	@Override
	public void fit(String description, Runnable testCase) {
		this.its.add(new It(description, testCase, Prefix.F));
	}

	@Override
	public void xit(String description, Runnable testCase) {
		this.its.add(new It(description, testCase, Prefix.X));
	}

	/**
	 * @param beforeAll
	 *            runnable to be executed once before all groups and cases
	 */
	@Override
	public void beforeAll(Runnable beforeAll) {
		this.beforeAlls.add(new BeforeAll(beforeAll));
	}

	/**
	 * @param beforeEach
	 *            runnable to be executed before each subgroup and case
	 */
	@Override
	public void beforeEach(Runnable beforeEach) {
		this.beforeEachs.add(new BeforeEach(beforeEach));
	}

	/**
	 * Compile this {@link JasmineInternalTest}. Internal use only.
	 * 
	 * @return compiled {@link JasmineGroup}
	 */
	public JasmineGroup compile() {
		return new JasmineGroupConverter().apply(this);
	}

}
