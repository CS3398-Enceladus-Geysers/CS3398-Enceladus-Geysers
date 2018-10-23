package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A graphic with the ability to perform actions when clicked on.
 */
public abstract class ClickableGraphic extends Graphic implements MouseListener {
	private static final long serialVersionUID = -1126958728064484219L;

	/**
	 * See {@link Graphic#Graphic(double, double)} this constructor also adds itself
	 * as a {@link MouseListener} to itself.
	 * 
	 * @param width
	 * @param height
	 */
	public ClickableGraphic(double width, double height) {
		super(width, height);
		addMouseListener(this);
	}

	/**
	 * See {@link Graphic#Graphic(double, double, double, double)} this constructor
	 * also adds itself as a {@link MouseListener} to itself.
	 * 
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 */
	public ClickableGraphic(double xoffset, double yoffset, double width, double height) {
		super(xoffset, yoffset, width, height);
		addMouseListener(this);
	}

	@Override
	public void act() {// Unused.
	}

	@Override
	public void mouseEntered(MouseEvent e) {// Unused.
	}

	@Override
	public void mouseExited(MouseEvent e) {// Unused.
	}

	@Override
	public void mousePressed(MouseEvent e) {// Unused.
	}

	@Override
	public void mouseReleased(MouseEvent e) {// Unused.
	}
}