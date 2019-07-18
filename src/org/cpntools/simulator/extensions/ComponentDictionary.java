/**
 * Then name has been changed to Component instead of Element 
 * to match Component File
 */

package org.cpntools.simulator.extensions;

import org.cpntools.simulator.extensions.Component;

/**
 * @author michael
 */
public interface ComponentDictionary {
	/**
	 * @param id
	 * @return
	 */
	Component get(String id);

	/**
	 * @param id
	 * @param element
	 */
	void put(String id, Component element);
}
