package entities;


public abstract class Ship extends Actor{
	protected int life = 100;
	protected double firingProbability = 0.009; //fires a bullet every time with a probability of firingProbability
	protected int damage = 20;
	
	public boolean reduceLife(int val)
	{
		life -= val;
		return life <= 0;
	}
	
	public int getLifeLeft()
	{
		return life;
	}
	
	public int getDamageOnCollision()
	{
		return damage;
	}
}
