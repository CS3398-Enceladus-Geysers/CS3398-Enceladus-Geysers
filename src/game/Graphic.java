package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

/**
 * One graphical component in the game to be drawn in a custom way.
 */
public abstract class Graphic extends JComponent {
	private static final long serialVersionUID = 5084265228790714409L;
	private boolean expired;
	private final int xoffset, yoffset;

	public void setLocation(Point p) {
		Point np = ((Point) p.clone());
		np.translate(xoffset, yoffset);
		super.setLocation(np);
	}

	public void setLocation(int x, int y) {
		super.setLocation(x + xoffset, y + yoffset);
	}

	public Graphic(double xoffset, double yoffset) {
		this.xoffset = (int) (xoffset * Main.SIZE_FACTOR);
		this.yoffset = (int) (yoffset * Main.SIZE_FACTOR);
	}

	public Graphic() {
		this.xoffset = 0;
		this.yoffset = 0;
	}

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

	/**
	 * Test to see if this object should be destroyed.
	 * 
	 * @return whether or not this object ought to be destroyed.
	 */
	public final boolean isExpired() {
		return expired;
	}

	/** Must be overridden. */
	@Override
	public abstract void paintComponent(Graphics g);
}