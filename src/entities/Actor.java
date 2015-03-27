package entities;

import java.awt.Graphics2D;
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
		Simulator simulator = Simulator.getInstance();
		
		if(x < -50 || y < -50 || x > simulator.getWidth() + 50 || y > simulator.getHeight() + 50)
		{
			if(this.getType() == Actor.PLAYER_BULLET)
				actors.getPlayerBullets().remove(this);

			if(this.getType() == Actor.ALIEN_BULLET)
				actors.getAlienBullets().remove(this);
			
			if(this.getType() == Actor.PLAYER_SHIP)
				actors.getPlayerShips().remove(this);

			if(this.getType() == Actor.ALIEN_SHIP)
				actors.getAlienShips().remove(this);
		}
	}
}
