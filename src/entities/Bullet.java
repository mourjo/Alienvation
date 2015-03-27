package entities;

import java.awt.Point;

public abstract class Bullet extends Actor{
	protected int power;
	
	public Point getPosition()
	{
		return new Point(x, y);
	}
	
	public int getPower()
	{
		return power;
	}
	
	
}
