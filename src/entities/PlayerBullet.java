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
	public void paintComponent(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 6, 3);

	}

	@Override
	public int getSlice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getType() {
		return Actor.PLAYER_BULLET;
	}

	@Override
	public int act(ActorSet actors) {
		// TODO Auto-generated method stub
		x++;
		cleanUp(actors);
		return 0;
	}

}
