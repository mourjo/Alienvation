package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import simulationEngine.ActorSet;

public class PlayerBullet extends Bullet {
	
	public PlayerBullet(int posX, int posY)
	{
		x = posX;
		y = posY;
		
	}

	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 3, 3);

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
		// TODO Auto-generated method stub
		x++;
		return 0;
	}

}
