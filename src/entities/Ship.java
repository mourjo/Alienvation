package entities;


public abstract class Ship extends Actor{
	protected int life;
	protected double firingProbability = 0.009; //fires a bullet every time with a probability of firingProbability
}
