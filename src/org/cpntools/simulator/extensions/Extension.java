package org.cpntools.simulator.extensions;

import java.util.List;


/**
 * @author michael
 */
public interface Extension extends SubscriptionHandler {
	/**
	 * Identifier for use for testing or early stages of development of extensions.
	 */
	public static final int TESTING = 999;

	/**
	 * Allow this handler to expose instruments in the CPN Tools GUI.
	 * 
	 * @return
	 */
	List<Instrument> getInstruments();

	/**
	 * NAme of this extension. Should not be more than 50 characters.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Return a list describing all registered options.
	 * 
	 * @return
	 */
	List<Option<?>> getOptions();

	/**
	 * Return an object that can be used to call using JavaExecute RPC
	 * 
	 * @return
	 */
	Object getRPCHandler();

	/**
	 * Allow this handler to subscribe to other kinds of messages, e.g., for conputing enabling or syntax check.
	 * 
	 * @return
	 */
	List<Command> getSubscriptions();

	/**
	 * Handle a packet. Only packets matching the identifier will ever be passed. handle is only called on objects
	 * created by start and hence had a Handler.
	 * 
	 * @param p
	 * @return
	 */
	Packet handle(Packet p);

	/**
	 * If result is non-null, this code will be injected into the simulator; this can be useful for generating sub code
	 * for calling the extension.
	 * 
	 * @return
	 */
	String inject();

	/**
	 * Invoke the instrument on the given element.
	 * 
	 * @param i
	 * @param e
	 */
	void invokeInstrument(Instrument i, Component e);

	/**
	 * Set the value of the option.
	 * 
	 * @param option
	 * @param value
	 */
	<T> void setOption(Option<T> option, T value);

	/**
	 * Instantiate extension (non-static factory).
	 * 
	 * @param c
	 * @return
	 */
	Extension start(Channel c);

}
