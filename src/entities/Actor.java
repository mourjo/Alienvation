package entities;

import java.awt.Graphics2D;
import java.util.Random;

import simulationEngine.ActorSet;

public abstract class Actor {
	
	public final static Integer PLAYER_SHIP = 40;
	public final static Integer ALIEN_SHIP = 60;
	
	public final static Integer PLAYER_BULLET = 400;
	public final static Integer ALIEN_BULLET = 600;
	
	protected int speedX = 1, speedY = 1;
	protected int x, y;
	
	protected Random gen = new Random();
	
	public abstract void paintComponent(Graphics2D g);
	
	public abstract int act(ActorSet actors);
	
	public abstract int getSlice();
	
	public abstract int getType();
	
	public int getSpeedX()
	{
		return speedX;
	}
	
	public int getSpeedY()
	{
		return speedY;
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
