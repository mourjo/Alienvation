package entities;

import java.awt.Graphics2D;

import simulationEngine.ActorSet;

public interface Actor {
	
	final static Integer PLAYER_SHIP = 40;
	final static Integer ALIEN_SHIP = 60;
	
	final static Integer PLAYER_BULLET = 400;
	final static Integer ALIEN_BULLET = 600;
	
	void paintComponent(Graphics2D g);
	
	int act(ActorSet actors);
	
	int getSlice();
	
	int getType();
}
