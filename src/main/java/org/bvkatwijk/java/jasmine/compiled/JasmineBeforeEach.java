package org.bvkatwijk.java.jasmine.compiled;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JasmineBeforeEach {

	private final Runnable runnable;

}
