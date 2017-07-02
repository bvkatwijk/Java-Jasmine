package org.bvkatwijk.java.jasmine.compiled;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JasmineBeforeAll {

	private final Runnable runnable;

}
