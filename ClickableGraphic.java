package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class ClickableGraphic extends Graphic implements MouseListener {
	private static final long serialVersionUID = -1126958728064484219L;

	public ClickableGraphic(double width, double height) {
		super(width, height);
	}

	public ClickableGraphic(double xoffset, double yoffset, double width, double height) {
		super(xoffset, yoffset, width, height);
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