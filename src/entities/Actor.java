package entities;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;

public interface Actor {
	
	final static Integer PLAYER_SHIP = 40;
	final static Integer ALIEN_SHIP = 60;
	
	final static Integer BASIC_PLAYER_SHIP = 100;
	final static Integer MEDIUM_PLAYER_SHIP = 200;
	final static Integer EXPERT_PLAYER_SHIP = 300;
	final static Integer BASIC_ALIEN_SHIP = 400;
	final static Integer MEDIUM_ALIEN_SHIP = 500;
	final static Integer EXPERT_ALIEN_SHIP = 600;
	
	void paint(Graphics2D g);
	
	int act(Map<Integer, List<? extends Actor>> actors);
	
	int getSlice();
	
	int getType();
}
