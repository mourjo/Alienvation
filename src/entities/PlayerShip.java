package entities;

import java.awt.Graphics2D;

import simulationEngine.Slice;

public abstract class PlayerShip extends Ship {
	int direction = 1;
	Slice slice;

	@Override
	public void paintComponent(Graphics2D g) {

	}

	@Override
	public Slice getSlice() {
		return slice;
	}
	
	@Override
	public final int getType() {
		return Actor.PLAYER_SHIP;
	}

	@Override
	public final boolean isAlien()
	{
		return false;
	}
}
