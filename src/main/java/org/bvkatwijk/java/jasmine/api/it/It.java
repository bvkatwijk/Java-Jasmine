package org.bvkatwijk.java.jasmine.api.it;

import org.bvkatwijk.java.jasmine.api.prefix.Prefix;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class It {

	private final String description;
	private final Runnable testCase;
	private final Prefix prefix;

}
