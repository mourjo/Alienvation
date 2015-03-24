package entities;

import java.awt.Graphics2D;

public abstract class PlayerShip extends Ship {

	@Override
	public void paintComponent(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSlice() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getType() {
		return Actor.PLAYER_SHIP;
	}

}
