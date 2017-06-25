package org.bvkatwijk.java.jasmine.api.describe;

import org.bvkatwijk.java.jasmine.api.prefix.Prefix;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Describe {

	private final String description;
	private final Runnable testGroup;
	private final Prefix prefix;

}
