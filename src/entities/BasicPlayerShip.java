package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

import simulationEngine.ActorSet;
import simulationEngine.Simulator;

public class BasicPlayerShip extends PlayerShip {
	private int sign = 1;

	@Override
	public void paint(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
//		for(int i = 0; i < starsX.length; i++)
//			g2d.fillOval(starsX[i], starsY[i], 2, 2);
		g2d.setColor(Color.RED);
		g2d.fillRect(x, y, 40, 20);
		
	}
	
	public BasicPlayerShip()
	{
		super();
		x = gen.nextInt(Simulator.canvasWidth);
		y = gen.nextInt(Simulator.canvasHeight) + 1;
	}

	@Override
	public int getSlice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getType() {
		return Actor.PLAYER_SHIP;
	}

	@Override
	public int act(ActorSet actors) {
		if(y+50 == Simulator.canvasHeight || y == 0)
			sign = -sign;
		y = y + sign;
//		y = (y + 1) % Simulator.canvasHeight;
		return 0;
	}

}
