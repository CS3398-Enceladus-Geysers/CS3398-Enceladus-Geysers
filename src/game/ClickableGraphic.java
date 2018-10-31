package game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A graphic with the ability to perform actions when clicked on.
 */
public abstract class ClickableGraphic extends Graphic implements MouseListener {
	private static final long serialVersionUID = -1126958728064484219L;
	private final Graphic underlyingGraphic;

	@Override
	public void paintComponent(Graphics g) {
		underlyingGraphic.paintComponent(g);
	}

	/**
	 * See {@link Graphic#Graphic(double, double)} this constructor also adds itself
	 * as a {@link MouseListener} to itself.
	 */
	public ClickableGraphic(Graphic g) {
		super(g.getXOffset(), g.getYOffset(), g.getWidth(), g.getHeight());
		addMouseListener(this);
		underlyingGraphic = g;
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