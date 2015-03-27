package simulationEngine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import entities.Actor;
import entities.AlienBullet;
import entities.AlienShip;
import entities.PlayerBullet;
import entities.PlayerShip;

public class Simulator extends JPanel {

	private static final long serialVersionUID = -5555914128154149540L;
	private int score;
	private ActorSet actors;
	private static Simulator _singleton = null;
	private ActorCreator actorFactory;
	private Random gen;
	
	
//	public static void getSize()
//	{
//		
//	}
	
	List<Point> stars;

	private Simulator()
	{
		actors = new ActorSet();
		score = 0;
		actorFactory = ActorCreator.getInstance();
		gen = new Random();
		
	}
	

	
	void init(int canvasWidth, int canvasHeight)
	{
		setSize(new Dimension(canvasWidth, canvasHeight));
		
		stars = new ArrayList<Point>();
		for (int i = 0; i < 100; i++)
			stars.add(new Point(gen.nextInt(canvasWidth), gen.nextInt(canvasHeight)));
		
		actorFactory.createBasicAlienShip(getAlienShips(), 10);
		actorFactory.createBasicPlayerShip(getPlayerShips(), 10);
	}

	static public Simulator getInstance()
	{
		if(_singleton == null)
			_singleton = new Simulator();
		return _singleton; 
	}

	public List<PlayerShip> getPlayerShips()
	{
		return actors.getPlayerShips();
	}

	public List<PlayerBullet> getPlayerBullets()
	{
		return actors.getPlayerBullets();
	}

	public List<AlienShip> getAlienShips()
	{
		return actors.getAlienShips();
	}

	public List<AlienBullet> getAlienBullets()
	{
		return actors.getAlienBullets();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		this.setBackground(Color.BLACK);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.GRAY);
		
		for(int i = 0; i < stars.size(); i++)
			g2d.fillOval((int)stars.get(i).getX(),(int)stars.get(i).getY(), 2, 2);
		
		for(int type : actors.getActors().keySet())
		{
			for(Actor actor : actors.getActors().get(type))
			{
				actor.paintComponent(g2d);
				actor.act(actors);
				actor.cleanUp(actors);
			}
		}
	}
}
