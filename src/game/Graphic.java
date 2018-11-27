package game;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * One graphical component in the game to be drawn in a custom way.
 */
public abstract class Graphic extends JComponent {
	private static final long serialVersionUID = 5084265228790714409L;

	private static final Dimension scaledSize(double width, double height) {
		return new Dimension((int) (width * Main.SIZE_FACTOR), (int) (height * Main.SIZE_FACTOR));
	}

	private boolean expired;
	private final boolean foreground;
	private final int xoffset, yoffset;

	/**
	 * Sets the size of this graphic according to {@link Main#SIZE_FACTOR} and sets
	 * its location to the top left of the {@link GameObject} to which it belongs.
	 * 
	 * @param width  the x dimension of this {@link Graphic}
	 * @param height the y dimension of this {@link Graphic}
	 */
	public Graphic(double width, double height) {
		this(0, 0, width, height, true);
	}

	public Graphic(double width, double height, boolean foreground) {
		this(0, 0, width, height, foreground);
	}

	/**
	 * Sets the size of this graphic according to {@link Main#SIZE_FACTOR} and sets
	 * its location to offset from the top left of the {@link GameObject} to which
	 * it belongs according to {@code xoffset} and {@code yoffset}.
	 * 
	 * @param xoffset the distance from the {@link GameObject}'s top-left corner in
	 *                the X-axis.
	 * @param yoffset the distance from the {@link GameObject}'s top-left corner in
	 *                the Y-axis.
	 * @param width   the x dimension of this {@link Graphic}
	 * @param height  the y dimension of this {@link Graphic}
	 */
	public Graphic(double xoffset, double yoffset, double width, double height) {
		this(xoffset, yoffset, width, height, true);
	}

	public Graphic(double xoffset, double yoffset, double width, double height, boolean foreground) {
		this.foreground = foreground;
		setSize(scaledSize(width, height));
		this.xoffset = (int) (xoffset * Main.SIZE_FACTOR);
		this.yoffset = (int) (yoffset * Main.SIZE_FACTOR);
		setLocation(0, 0);
	}

	protected Graphic(Graphic g) {
		foreground = g.isForeground();
		setSize(g.getSize());
		xoffset = g.getXOffset();
		yoffset = g.getYOffset();
		setLocation(0, 0);
	}

	/**
	 * A method to handle all per-frame operations.
	 */
	public abstract void act();

	/**
	 * Mark this object for destruction.
	 */
	public final void expire() {
		expired = true;
	}

	/**
	 * Overrides {@link javax.swing.JComponent#getPreferredSize()} in order to
	 * prevent resizing by {@link java.awt.Window#pack()};
	 * 
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		return getSize();
	}

	public final int getXOffset() {
		return xoffset;
	}

	public final int getYOffset() {
		return yoffset;
	}

	/**
	 * Test to see if this object should be destroyed.
	 * 
	 * @return whether or not this object ought to be destroyed.
	 */
	public final boolean isExpired() {
		return expired;
	}

	public boolean isForeground() {
		return foreground;
	}

	/** Must be overridden. */
	@Override
	public abstract void paintComponent(Graphics g);

	@Override
	public void setLocation(int x, int y) {
		super.setLocation(x + xoffset, y + yoffset);
	}
}