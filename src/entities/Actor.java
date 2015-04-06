package entities;

import java.awt.Graphics2D;
import java.util.Random;

import simulationEngine.ActorSet;
import simulationEngine.Simulator;
import simulationEngine.Slice;

public abstract class Actor {

	public final static Integer PLAYER_SHIP = 40;
	public final static Integer ALIEN_SHIP = 60;

	public final static Integer PLAYER_BULLET = 400;
	public final static Integer ALIEN_BULLET = 600;

	protected int speedX = 3, speedY = 3;
	protected int x, y;
	
	protected boolean paused = false;

	static protected Random gen = new Random();

	public abstract void paintComponent(Graphics2D g);

	public abstract int act(ActorSet actors);

	public abstract Slice getSlice();

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
	
	public abstract boolean isAlien();
}
