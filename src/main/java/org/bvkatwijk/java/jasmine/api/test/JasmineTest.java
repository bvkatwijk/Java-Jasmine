package org.bvkatwijk.java.jasmine.api.test;

import java.util.Collection;
import java.util.HashSet;

import org.bvkatwijk.java.jasmine.api.JasmineSignature;
import org.bvkatwijk.java.jasmine.api.describe.Describe;
import org.bvkatwijk.java.jasmine.api.it.It;
import org.bvkatwijk.java.jasmine.api.prefix.Prefix;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;
import org.bvkatwijk.java.jasmine.runner.JavaJasmineRunner;
import org.junit.runner.RunWith;

import lombok.Getter;

@Getter
@RunWith(JavaJasmineRunner.class)
public class JasmineTest implements JasmineSignature {
	
	final Collection<Describe> describes = new HashSet<>();
	final Collection<It> its = new HashSet<>();

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
	
	public JasmineGroup compile() {
		return JasmineGroup.of(this);
	}

}
