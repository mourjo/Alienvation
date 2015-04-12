package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import simulationEngine.ActorSet;
import simulationEngine.Slice;

public class PlayerBullet extends Bullet {
	
	public PlayerBullet(int posX, int posY)
	{
		x = posX;
		y = posY;
		power = 20;
	}
	
	public PlayerBullet(int posX, int posY, int pow)
	{
		x = posX;
		y = posY;
		power = pow;
	}

	@Override
	public void paintComponent(Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 6, 3);

	}

	@Override
	public Slice getSlice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getType() {
		return Actor.PLAYER_BULLET;
	}

	@Override
	public int act(ActorSet actors) {
		// TODO Auto-generated method stub
		x += speedX;
		
		return 0;
	}
	
	@Override
	public boolean isAlien()
	{
		return false;
	}

}
