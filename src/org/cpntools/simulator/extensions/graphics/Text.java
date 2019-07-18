package org.cpntools.simulator.extensions.graphics;

import java.awt.Dimension;
import java.awt.Point;

import org.cpntools.simulator.extensions.Packet;

/**
 * @author michael
 */
public class Text extends Node<Text> {

	private String text;

	/**
	 * @param x
	 * @param y
	 * @param text
	 */
	public Text(final int x, final int y, final String text) {
		this(new Point(x, y), text);
	}

	/**
	 * @param bounds
	 * @param text
	 */
	public Text(final java.awt.Rectangle bounds, final String text) {
		super(bounds);
		this.text = text;
	}

	/**
	 * @param position
	 * @param text
	 */
	public Text(final Point position, final String text) {
		this(new java.awt.Rectangle(position, new Dimension()), text);
	}

	/**
	 * @see org.cpntools.simulator.extensions.graphics.Node#getCreatePackage(java.lang.String)
	 */
	@Override
	public Packet getCreatePackage(final String canvasid) {
		final Packet p = super.getCreatePackage(canvasid);
		p.addInteger(3);
		p.addInteger(getX());
		p.addInteger(getY());
		p.addString(getText());
		return p;
	}

	/**
	 * @return
	 */
	public String getText() {
		return text;
	}

	@Override
	protected int getX() {
		if (owner == null) {
			return (int) bounds.getX();
		} else {
			return (int) bounds.getX() + owner.getX();
		}
	}

	@Override
	protected int getY() {
		if (owner == null) {
			return -(int) bounds.getY();
		} else {
			return -(int) bounds.getY() + owner.getY();
		}
	}

	void setText(final String text) {
		this.text = text;
	}

}
