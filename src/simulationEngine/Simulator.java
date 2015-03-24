package simulationEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;

import entities.Actor;
import entities.PlayerBullet;
import entities.PlayerShip;

public class Simulator extends JPanel {

	private static final long serialVersionUID = -5555914128154149540L;
	private int score;
	ActorSet actors;
	static Simulator _singleton = null;
	ActorCreator actorFactory;
	public static int canvasWidth, canvasHeight;
	Random gen;
	
	List<Point> stars;

	private Simulator()
	{
		actors = new ActorSet();
		score = 0;
		actorFactory = ActorCreator.getInstance();
		gen = new Random();
		
	}
	
	void changeCanvasSize(int canvasWidth, int canvasHeight)
	{
		Simulator.canvasWidth = canvasWidth;
		Simulator.canvasHeight = canvasHeight;
	}
	
	void init(int canvasWidth, int canvasHeight)
	{
		Simulator.canvasWidth = canvasWidth;
		Simulator.canvasHeight = canvasHeight;
		
		stars = new ArrayList<Point>();
		for (int i = 0; i < 100; i++)
			stars.add(new Point(gen.nextInt(canvasWidth), gen.nextInt(canvasHeight)));
	}

	static public Simulator getInstance()
	{
		if(_singleton == null)
			_singleton = new Simulator();
		return _singleton; 
	}

	public void simulate()
	{
		for(int i = 0; i < 15; i++)
		actorFactory.createBasicPlayerShip(getPlayerShips());
		
	}

	private void removeActor(Actor actor)
	{
		if (actor.getType() == Actor.PLAYER_SHIP)
			actors.getPlayerShips().remove(actor);
		
		else if (actor.getType() == Actor.ALIEN_SHIP)
			actors.getAlienShips().remove(actor);
		
		else if (actor.getType() == Actor.PLAYER_BULLET)
			actors.getPlayerBullets().remove(actor);
		
		else if (actor.getType() == Actor.ALIEN_BULLET)
			actors.getPlayerShips().remove(actor);
	}

	public Set<PlayerShip> getPlayerShips()
	{
		return actors.getPlayerShips();
	}

	public Set<PlayerBullet> getPlayerBullets()
	{
		return actors.getPlayerBullets();
	}

	public Set<? extends Actor> getAlienShips()
	{
		return actors.getAlienShips();
	}

	public Set<? extends Actor> getAlienBullets()
	{
		return actors.getAlienBullets();
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		this.setBackground(Color.BLACK);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.GRAY);
		for(int i = 0; i < stars.size(); i++)
			g2d.fillOval((int)stars.get(i).getX(),(int)stars.get(i).getY(), 1, 1);
		
		for(PlayerShip a : actors.getPlayerShips())
		{
			a.paint(g2d);
			a.act(actors);
		}
	}

}
