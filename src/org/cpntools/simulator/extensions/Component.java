/**
 * Then name has been changed to Component instead of Element 
 * to avoid confusion with the Element file into the package graphics
 */

package org.cpntools.simulator.extensions;

/**
 * @author michael
 */
public abstract class Component {
	private String id;

	/**
	 * @param dictionary
	 * @param id
	 */
	public Component(final ComponentDictionary dictionary, final String id) {
		setId(id);
		dictionary.put(id, this);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (!(obj instanceof Component)) { return false; }
		final Component other = (Component) obj;
		if (getId() == null) {
			if (other.getId() != null) { return false; }
		} else if (!getId().equals(other.getId())) { return false; }
		return true;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getId() == null ? 0 : getId().hashCode());
		return result;
	}

	/**
	 * @param id
	 */
	public void setId(final String id) {
		this.id = id;
	}
}
