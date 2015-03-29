package simulationEngine;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

class DrawStringMeasurement {

	Font font;
	FontRenderContext context;

	public DrawStringMeasurement(Graphics2D g2) {
		font = g2.getFont();
		context = g2.getFontRenderContext();
	}

	private Rectangle2D getBounds(String message) {
		return font.getStringBounds(message, context);
	}

	public double getWidth(String message) {
		Rectangle2D bounds = getBounds(message);
		return bounds.getWidth();
	}

	public double getHeight(String message) {
		Rectangle2D bounds = getBounds(message);
		return bounds.getHeight();
	}

}