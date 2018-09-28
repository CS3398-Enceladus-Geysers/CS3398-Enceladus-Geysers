package game;

import java.awt.Graphics;

import javax.swing.JComponent;

public abstract class Graphic extends JComponent {
	private static final long serialVersionUID = 5084265228790714409L;
	private boolean expired;
	private boolean visible;

	/** Must be overridden. */
	@Override
	public abstract void paint(Graphics g);
}