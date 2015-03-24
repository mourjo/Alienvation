package entities;

import java.awt.Point;
import java.util.Set;

import simulationEngine.ActorSet;
import simulationEngine.Simulator;

public abstract class Bullet implements Actor{
	int power;
	protected int x, y;
	public Point getPosition()
	{
		return new Point(x,y);
	}

	public void validate(ActorSet actors)
	{
		if(this.getType() == Actor.PLAYER_BULLET)
		{
			if(x < 0 || y < 0 || x > Simulator.canvasWidth || y > Simulator.canvasHeight)
			{
				Set<PlayerBullet> set = actors.getPlayerBullets();
				synchronized(set)
				{

					set.remove(this);
					Thread.yield();
				}
			}
		}
	}
}
