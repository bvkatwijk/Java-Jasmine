package org.bvkatwijk.java.jasmine.api.before;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BeforeEach {

	private final Runnable runnable;

}
