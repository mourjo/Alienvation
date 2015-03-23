package simulationEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import entities.Actor;
import entities.AlienShip;
import entities.PlayerShip;

public class Simulator extends JPanel {

	private static final long serialVersionUID = -5555914128154149540L;
	
	private int score;

	Map<Integer, Set<? extends Actor>> entities;
	
	static Simulator _singleton = null;
	
	
	private Simulator()
	{
		entities = new HashMap<Integer, Set<? extends Actor>>();
		
		entities.put(Actor.PLAYER_SHIP, new HashSet<PlayerShip>());
		entities.put(Actor.ALIEN_SHIP, new HashSet<AlienShip>());
		
		score = 0;
	}
	
	public Simulator getInstance()
	{
		if(_singleton == null)
			_singleton = new Simulator();
		return _singleton; 
	}
	
	public void simulate()
	{
		
	}
	
	private void removeActor(Actor entity)
	{
	
	}

	public Set<? extends Actor> getPlayerShips()
	{
		return entities.get(Actor.PLAYER_SHIP);
	}
	
	public Set<? extends Actor> getAlienShips()
	{
		return entities.get(Actor.ALIEN_SHIP);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		this.setBackground(Color.BLACK);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	}

}
