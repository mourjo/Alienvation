package simulationEngine;

import java.awt.BasicStroke;
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
	private boolean initial = false;
	private static BufferedImage image = null;
	private int frameCount = 0, framerate;
	long time = System.nanoTime();
	final int frames = 24;
	private int numSlices = 4;
	private Slice slices[][];

	private List<Point> stars;

	private Simulator()
	{
		actors = new ActorSet();
		score = 0;
		actorFactory = ActorCreator.getInstance();
		gen = new Random();
		slices = new Slice[numSlices][numSlices];
		/*try {
			image = ImageIO.read(getClass().getResource("/earth.jpg"));
//			image = ImageIO.read(getClass().getResourceAsStream("/milkyWay.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	@Override
	public void setSize(Dimension d)
	{
		super.setSize(d);
		
		double sliceHeight = (double)d.getHeight()/numSlices;
		double sliceWidth = (double)d.getWidth()/numSlices;
		
		for(int i = 0; i < slices.length; i++)
		{
			for(int j = 0; j < slices[i].length; j++)
				slices[i][j].setValues(i*sliceWidth,j*sliceHeight,sliceWidth,sliceHeight); 
		}
		
		stars = new ArrayList<Point>();
		for (int i = 0; i < 500; i++)
			stars.add(new Point(gen.nextInt((int)d.getWidth())*2, gen.nextInt((int)d.getHeight())*2));
			
	}
	
	public void setNumberOfSlices(int n) //n x n
	{
		numSlices = n;
		slices = new Slice[numSlices][numSlices];
		clear();
		init(getSize().width, getSize().height);
	}
	
	public ActorSet getActors()
	{
		return actors;
	}
	
	public int numberOfSlices()
	{
		return numSlices;
	}
	
	public Slice[][] getSlices()
	{
		return slices;
	}
	
	public void togglePause()
	{
		paused = !paused;
	}

	public boolean isPaused()
	{
		return paused;
	}

	void init(int canvasWidth, int canvasHeight)
	{
		initial = false;
		
		double sliceHeight = (double)canvasHeight/numSlices;
		double sliceWidth = (double)canvasWidth/numSlices;
		
		for(int i = 0; i < slices.length; i++)
		{
			for(int j = 0; j < slices[i].length; j++)
				slices[i][j] = new Slice(i*sliceWidth,j*sliceHeight,sliceWidth,sliceHeight); 
		}
		
		setSize(new Dimension(canvasWidth, canvasHeight));
		
		actorFactory.createBasicPlayerShip(getPlayerShips(), 10);
		image = null;
		paused = false;
	}

	static public Simulator getInstance()
	{
		if(_singleton == null)
			_singleton = new Simulator();
		return _singleton; 
	}
	
	public void clear()
	{
		actors.getPlayerShips().clear();
		actors.getPlayerBullets().clear();
		actors.getAlienShips().clear();
		actors.getAlienBullets().clear();
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
			g2d.setColor(Color.WHITE);
			
			for(int i = 0; i < slices.length; i++)
			{
				for(int j = 0; j < slices[0].length; j++)
				{
					Slice slice = slices[i][j];
					g2d.drawRect((int)slice.getX(), (int)slice.getY(), (int)slice.getWidth(), (int)slice.getHeight());
				}
				
			}
			
			g2d.setColor(Color.GRAY);

			for(int i = 0; i < stars.size(); i++)
				g2d.fillOval((int)stars.get(i).getX(),(int)stars.get(i).getY(), 2, 2);

			for(int type : actors.getActors().keySet())
			{
				for(Actor actor : actors.getActors().get(type))
				{
					actor.paintComponent(g2d);
					actor.act(actors);
				}
			}
			cleanUp(actors);
		}
		else
		{
			g2d.setColor(Color.WHITE);
			
			for(int i = 0; i < slices.length; i++)
			{
				for(int j = 0; j < slices[0].length; j++)
				{
					Slice slice = slices[i][j];
					g2d.drawRect((int)slice.getX(), (int)slice.getY(), (int)slice.getWidth(), (int)slice.getHeight());
				}
				
			}
			
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
	
	private void cleanUp(ActorSet actors)
	{
		List<Actor> delList = new ArrayList<Actor>();
		for(int type : actors.getActors().keySet())
		{
			
			for(Actor actor : actors.getActors().get(type))
			{
				if(actor.getType() != Actor.PLAYER_SHIP)
				{
					if(actor.getX() < -40 || 
							actor.getY() < -40 || 
							actor.getX() > getWidth() + 40 || 
							actor.getY() > getHeight() + 40)
						delList.add(actor);
				}
				
				
				for(int type1 : actors.getActors().keySet())
				{
					for(Actor actor1 : actors.getActors().get(type1))
					{
						if(actor1.isAlien() ^ actor.isAlien())
						{
							if(Math.abs(actor1.getX() - actor.getX()) <= 20 
									&&  Math.abs(actor1.getY() - actor.getY() ) <= 20)
							{
								delList.add(actor);
								delList.add(actor1);
							}
						}
					}
				}
			}
		}
		actors.removeActors(delList);
	}
}
