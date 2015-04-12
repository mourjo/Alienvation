package entities;

import java.awt.Point;

public abstract class Bullet extends Actor{
	protected int power = 10;
	
	public Point getPosition()
	{
		return new Point(x, y);
	}
	
	public final int getPower()
	{
		return power;
	}
	
	
}
