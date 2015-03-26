package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import simulationEngine.ActorSet;

public class AlienBullet extends Bullet {
	int speed;
	public AlienBullet(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public AlienBullet(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
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
		x -= speed;
		cleanUp(actors);
		return 0;
	}

}
