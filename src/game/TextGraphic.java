package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TextGraphic extends Graphic {
	private final String message;
	private final Font font;
	private final Color color;

	public TextGraphic(double xoffset, double yoffset, double width, double height, String message, String font,
			Color color) {
		super(xoffset, yoffset, width, height);
		this.message = message;
		this.font = new Font(font, Font.BOLD, getHeight());
		this.color = color;
	}

	private static final long serialVersionUID = 2737388953311959952L;

	@Override
	public void act() {
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(message, 0, getHeight());
	}
}