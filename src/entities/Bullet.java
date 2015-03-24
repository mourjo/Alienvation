package entities;

import java.awt.Point;

import simulationEngine.ActorSet;
import simulationEngine.Simulator;

public abstract class Bullet implements Actor{
	int power;
	protected int x, y;
	public Point getPosition()
	{
		return new Point(x,y);
	}

	public void cleanUp(ActorSet actors)
	{
		if(x < 0 || y < 0 || x > Simulator.canvasWidth || y > Simulator.canvasHeight)
		{
			if(this.getType() == Actor.PLAYER_BULLET)
				actors.getPlayerBullets().remove(this);

			if(this.getType() == Actor.ALIEN_BULLET)
				actors.getAlienBullets().remove(this);
		}
	}
}
