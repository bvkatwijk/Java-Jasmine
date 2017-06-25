package org.bvkatwijk.java.jasmine.compiled;

import java.util.Collection;

import org.bvkatwijk.java.jasmine.api.prefix.Prefix;

public interface JasmineNode {

	/**
	 * @return children of this node
	 */
	public Collection<JasmineNode> getChilden();
	
	/**
	 * @return Prefix of this node
	 */
	public Prefix getPrefix();
	
	/**
	 * @return Description of this node
	 */
	public String getDescription();

}
