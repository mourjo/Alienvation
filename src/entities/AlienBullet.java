package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import simulationEngine.ActorSet;

public class AlienBullet extends Bullet {

	public AlienBullet(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void paintComponent(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 6, 3);

	}

	@Override
	public int getSlice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getType() {
		return Actor.ALIEN_BULLET;
	}

	@Override
	public int act(ActorSet actors) {
		x--;
		cleanUp(actors);
		return 0;
	}

}
