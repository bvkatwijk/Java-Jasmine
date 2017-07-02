package org.bvkatwijk.java.jasmine.mode;

import java.util.function.Supplier;

import org.bvkatwijk.java.jasmine.api.prefix.Prefix;
import org.bvkatwijk.java.jasmine.compiled.JasmineGroup;
import org.bvkatwijk.java.jasmine.utils.JasmineNodeScanner;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JasmineModeDecider implements Supplier<JasmineMode> {

	private final JasmineGroup jasmineGroup;
	
	@Override
	public JasmineMode get() {
		return new JasmineNodeScanner(node -> node.getPrefix().equals(Prefix.F)).anyMatch(jasmineGroup.getChilden())
				? JasmineMode.FOCUS : JasmineMode.NORMAL;
	}

}
