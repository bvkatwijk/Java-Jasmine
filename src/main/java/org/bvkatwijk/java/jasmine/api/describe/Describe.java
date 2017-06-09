package org.bvkatwijk.java.jasmine.api.describe;

import org.bvkatwijk.java.jasmine.api.prefix.Prefix;

import lombok.Value;

@Value
public class Describe {

	private final String description;
	private final Runnable testGroup;
	private final Prefix prefix;

}
