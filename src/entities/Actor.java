package entities;

import java.awt.Graphics2D;
import java.util.Random;

import simulationEngine.ActorSet;
import simulationEngine.Slice;

public abstract class Actor {

	public final static Integer PLAYER_SHIP = 40;
	public final static Integer ALIEN_SHIP = 60;

	public final static Integer PLAYER_BULLET = 401;
	public final static Integer ALIEN_BULLET = 601;

	protected int speedX = 3, speedY = 3;
	protected int x, y;
	protected boolean paused = false;
	static protected Random gen = new Random();

	public abstract void paintComponent(Graphics2D g);

	public abstract int act(ActorSet actors);

	public abstract Slice getSlice();

	public abstract int getType();

	public abstract boolean isAlien();
	
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
}
