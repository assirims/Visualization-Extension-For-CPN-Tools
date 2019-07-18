package org.cpntools.simulator.extensions;

/**
 * @author michael
 */
public class Invocation {

	private final Component element;
	private final Instrument instrument;

	/**
	 * @param i
	 * @param e
	 */
	public Invocation(final Instrument i, final Component e) {
		instrument = i;
		element = e;
	}

	/**
	 * @return
	 */
	public Component getElement() {
		return element;
	}

	/**
	 * @return
	 */
	public Instrument getInstrument() {
		return instrument;
	}

}
