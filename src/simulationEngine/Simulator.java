package simulationEngine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
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
	private boolean paused = false;
	private boolean pausedPainted = false;
	private boolean initial = false;
	private static BufferedImage image = null;
	private int frameCount = 0, framerate;
	long time = System.nanoTime();
	final int frames = 24;

	private List<Point> stars;

	private Simulator()
	{
		actors = new ActorSet();
		score = 0;
		actorFactory = ActorCreator.getInstance();
		gen = new Random();
		/*try {
			image = ImageIO.read(getClass().getResource("/earth.jpg"));
//			image = ImageIO.read(getClass().getResourceAsStream("/milkyWay.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public ActorSet getActors()
	{
		return actors;
	}

	public void togglePause()
	{
		paused = !paused;
		//		if(!paused)
		//			pausedPainted = false;
	}

	public boolean isPaused()
	{
		return paused;
	}

	void init(int canvasWidth, int canvasHeight)
	{
		initial = false;
		setSize(new Dimension(canvasWidth, canvasHeight));

		stars = new ArrayList<Point>();
		for (int i = 0; i < 10; i++)
			stars.add(new Point(gen.nextInt(canvasWidth), gen.nextInt(canvasHeight)));

		actorFactory.createBasicAlienShip(getAlienShips(), 10);
		actorFactory.createBasicPlayerShip(getPlayerShips(), 10);
		image = null;
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

	public void createAlienWave()
	{
		if(!paused)
			actorFactory.createBasicAlienShip(actors.getAlienShips(), 10);
	}


	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.setBackground(Color.BLACK);


		if(initial)
		{
			//			g2d.drawImage(image, 0, 0, getSize().width, getSize().height, null);

			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Arial", Font.BOLD, 45));
			DrawStringMeasurement sm = new DrawStringMeasurement(g2d);
			double width = sm.getWidth("Alienvation");
			Dimension size = getSize();
			g2d.drawString("Alienvation", (float)(size.width/2d - width/2d), (float)((size.height/2d)*0.8 ));

			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Arial", Font.ITALIC, 20));
			sm = new DrawStringMeasurement(g2d);
			width = sm.getWidth("Press enter to start");
			g2d.drawString("Press enter to start", (float)(size.width/2d - width/2d), (float)((size.height/2d) * 0.9 ));

		}
		else if(!paused)
		{


			g2d.setColor(Color.GRAY);

			for(int i = 0; i < stars.size(); i++)
				g2d.fillOval((int)stars.get(i).getX(),(int)stars.get(i).getY(), 2, 2);

			for(int type : actors.getActors().keySet())
			{
				long t = System.currentTimeMillis();
				for(Actor actor : actors.getActors().get(type))
				{
					actor.paintComponent(g2d);
					actor.act(actors);
//					actor.cleanUp(actors);
				}
				
//				System.out.println(actors.getPlayerShips().size() +" "+ actors.getPlayerBullets().size() +" " +actors.getAlienShips().size() +" "+ actors.getAlienBullets().size());
				//				if(System.currentTimeMillis() - t > 30)
				//				System.out.println(type + " " +(System.currentTimeMillis() - t) + " ms");
			}
			Actor.cleanUp2(actors);
		}
		else
		{

			g2d.setColor(Color.GRAY);

			for(int i = 0; i < stars.size(); i++)
				g2d.fillOval((int)stars.get(i).getX(),(int)stars.get(i).getY(), 2, 2);

			for(int type : actors.getActors().keySet())
			{
				for(Actor actor : actors.getActors().get(type))
				{
					actor.pause();
					actor.paintComponent(g2d);
					actor.unPause();
				}
			}
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Arial", Font.BOLD, 95));
			DrawStringMeasurement sm = new DrawStringMeasurement(g2d);
			double width = sm.getWidth("PAUSED");
			Dimension size = getSize();
			g2d.drawString("PAUSED", (float)(size.width/2d - width/2d), (float)(size.height/2d ));

		}
		
		 if(frameCount++ == frames)
		 {
			 framerate = (int) (((double)frameCount/(System.nanoTime() - time)) * Math.pow(10,9));
			 time = System.nanoTime();
			 
			 frameCount = 0;
		 }
		 g2d.setColor(Color.RED);
		 g2d.setFont(new Font("Arial", Font.BOLD, 20));
		 g2d.drawString("Frame rate: " + framerate, 800, 20);

	}
}
