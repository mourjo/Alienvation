package entities;

import java.util.Random;

public abstract class Ship implements Actor{
	protected int slice;
	protected int life;
	protected int bulletFrequency;
	protected int x, y;
	protected Random gen = new Random();
}
