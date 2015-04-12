package entities;

import java.awt.Graphics2D;

import simulationEngine.Slice;

public abstract class AlienShip extends Ship {

	@Override
	public void paintComponent(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public Slice getSlice() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public final int getType() {
		return Actor.ALIEN_SHIP;
	}
	
	@Override
	public final boolean isAlien()
	{
		return true;
	}
}
