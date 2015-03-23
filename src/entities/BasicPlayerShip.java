package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import simulationEngine.ActorSet;

public class BasicPlayerShip extends PlayerShip {
	private int x, y;

	@Override
	public void paint(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
//		for(int i = 0; i < starsX.length; i++)
//			g2d.fillOval(starsX[i], starsY[i], 2, 2);
		g2d.setColor(Color.RED);
		g2d.fillOval(x, y, 30, 30);
		
	}

	@Override
	public int getSlice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int act(ActorSet actors) {
		x += 1;
		y += 1;
		return 0;
	}

}
