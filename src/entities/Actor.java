package entities;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import simulationEngine.ActorSet;
import simulationEngine.Simulator;

public abstract class Actor {

	public final static Integer PLAYER_SHIP = 40;
	public final static Integer ALIEN_SHIP = 60;

	public final static Integer PLAYER_BULLET = 400;
	public final static Integer ALIEN_BULLET = 600;

	protected int speedX = 1, speedY = 1;
	protected int x, y;
	
	protected boolean paused = false;

	protected Random gen = new Random();

	public abstract void paintComponent(Graphics2D g);

	public abstract int act(ActorSet actors);

	public abstract int getSlice();

	public abstract int getType();

	public int getSpeedX()
	{
		return speedX;
	}
	
	public void pause()
	{
		paused = true;
	}
	
	public void unPause()
	{
		paused = false;
	}
	
	public boolean isPaused()
	{
		return paused;
	}

	public int getSpeedY()
	{
		return speedY;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void setSpeedX(int xSpeed)
	{
		speedX = xSpeed;
	}

	public void setSpeedY(int ySpeed)
	{
		speedY = ySpeed;
	}

	public void cleanUp(ActorSet actors)
	{
		if(this.getType() == Actor.PLAYER_BULLET && x > Simulator.getInstance().getWidth())
			actors.getPlayerBullets().remove(this);

		if(this.getType() == Actor.ALIEN_BULLET && x <= 0)
			actors.getAlienBullets().remove(this);

//		if(this.getType() == Actor.PLAYER_SHIP)
//			actors.getPlayerShips().remove(this);

		if(this.getType() == Actor.ALIEN_SHIP && x <= 0)
			actors.getAlienShips().remove(this);
	}
	
	public static void cleanUp2(ActorSet actors)
	{
		for(int type : actors.getActors().keySet())
		{
			List<Object> delList = new ArrayList<Object>();
			for(Actor actor : actors.getActors().get(type))
			{
				if(actor.x < 0 || actor.y < 0 || actor.x > Simulator.getInstance().getWidth() || actor.y > Simulator.getInstance().getHeight())
					delList.add(actor);
			}
			actors.getActors().get(type).removeAll(delList);
		}
	}
}
