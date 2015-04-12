package entities;

import java.util.HashSet;
import java.util.Set;


public abstract class Ship extends Actor{
	protected int life = 100;
	protected double firingProbability = 0.04; //fires a bullet every time with a probability of firingProbability
	protected int damage = 20;	//the damage it makes on the opponent ship
	protected Set<Ship> attacked;
	
	Ship()
	{
		attacked = new HashSet<Ship>();
	}
	
	public final boolean reduceLife(int val)
	{
		life -= val;
		return life <= 0;
	}
	
	public final int getLifeLeft()
	{
		return life;
	}
	
	public final int getDamageOnCollision()
	{
		return damage;
	}
	
	public final Set<Ship> getAttackers()
	{
		return attacked;
	}
	
	public final void attackedBy(Ship s)
	{
		attacked.add(s);
	}
	
	public final boolean hasNotBeenAttacked(Ship s)
	{
		if(attacked.contains(s))
			return false;
		attacked.add(s);
		return true;
	}
	
}