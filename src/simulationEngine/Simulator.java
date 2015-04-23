package simulationEngine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import entities.Actor;
import entities.BasicPlayerShip;
import entities.Bullet;
import entities.Ship;

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
	private boolean exitCondition;

	private List<Point> stars;

	private Simulator()
	{
		actors = new ActorSet();
		score = 0;
		actorFactory = ActorCreator.getInstance();
		gen = new Random();
		slices = new Slice[numSlices][numSlices];
		exitCondition = false;
	}
	
	public void start()
	{
		while(!exitCondition)
		{
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
			refresh();
			
		}
	}
	
	public void exit()
	{
		exitCondition = true;
	}

	@Override
	public void setSize(Dimension d)
	{
		super.setSize(d);

		int sliceHeight = getSize().height/numSlices;
		int sliceWidth = getSize().width/numSlices;

		for(int i = 0; i < slices.length; i++)
		{
			for(int j = 0; j < slices[i].length; j++)
				slices[i][j].setValues(i*sliceWidth,j*sliceHeight,sliceWidth,sliceHeight); 
		}

		stars = new ArrayList<Point>();
		for (int i = 0; i < 500; i++)
			stars.add(new Point(gen.nextInt((int)d.getWidth())*2, gen.nextInt((int)d.getHeight())*2));
	}

	public boolean isInitialized()
	{
		return !(actors == null);
	}

	public void setNumberOfSlices(int n) //n x n
	{
		numSlices = n;
		slices = new Slice[numSlices][numSlices];
		clear();
		System.out.println(getSize().width + ", " + getSize().height);
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

		int sliceHeight = canvasHeight/numSlices;
		int sliceWidth = canvasWidth/numSlices;

		for(int i = 0; i < slices.length; i++)
		{
			for(int j = 0; j < slices[i].length; j++)
				slices[i][j] = new Slice(i*sliceWidth,j*sliceHeight,sliceWidth,sliceHeight); 
		}

		setSize(new Dimension(canvasWidth, canvasHeight));

		image = null;
		paused = false;
		System.out.println(getSize().width + ", " + getSize().height);
	}

	static public Simulator getInstance()
	{
		if(_singleton == null)
			_singleton = new Simulator();
		return _singleton; 
	}

	public void clear()
	{
		synchronized(actors)
		{
			actors.getActorList().clear();
		}
		score = 0;
	}

	public void createPlayerShip(int type, int xPt, int yPt)
	{
		if(!paused)
		{
			Slice s = null;
			xPt = Math.max(1,xPt - BasicPlayerShip.getWidth()/2);
			for(int i = 0; i < slices.length; i++)
			{
				for(int j = 0; j < slices[i].length; j++)
				{
					if(xPt >= slices[i][j].getX() && 
							xPt < slices[i][j].getX() + slices[i][j].getWidth() &&
							yPt >= slices[i][j].getY() && 
							yPt < slices[i][j].getY() + slices[i][j].getHeight())
					{
						s = slices[i][j];
						break;
					}
				}
			}
			actorFactory.createBasicPlayerShip(actors.getActorList(), xPt, yPt, s);
		}

	}

	public void createAlienWave()
	{
		if(!paused && actors.getAlienShipCount() == 0)
			actorFactory.createBasicAlienShip(actors.getActorList(), 10);
	}

	public void refresh()
	{

		if(!paused)
		{
			for(Actor actor : actors.getActorList())
			{
				actor.unPause();
				actor.act(actors);
			}
			cleanUp(actors);

		}
		else
		{
			for(Actor actor : actors.getActorList())
				actor.pause();
		}

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
				for(int j = 0; j < slices[i].length; j++)
				{
					Slice slice = slices[i][j];
					g2d.drawRect((int)slice.getX(), (int)slice.getY(), (int)slice.getWidth(), (int)slice.getHeight());
				}

			}

			g2d.setColor(Color.GRAY);

			for(int i = 0; i < stars.size(); i++)
				g2d.fillOval((int)stars.get(i).getX(),(int)stars.get(i).getY(), 2, 2);

			for(Actor actor : actors.getActorList())
				actor.paintComponent(g2d);


			g2d.setColor(Color.RED);
			g2d.setFont(new Font("Arial", Font.BOLD, 20));
			DrawStringMeasurement sm = new DrawStringMeasurement(g2d);
			double width = sm.getWidth("Score: " + score);
			Dimension size = getSize();
			g2d.drawString("Score: " + score, (float)(size.width/2d - width/2d), (float)(20));
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

			for(Actor actor : actors.getActorList())
				actor.paintComponent(g2d);


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
	private synchronized void cleanUp(ActorSet actors)
	{
		List<Actor> delList = new ArrayList<Actor>();
		for(Actor actor : actors.getActorList())
		{
			int type = actor.getType();
			if(type == Actor.ALIEN_SHIP && actor.getX() < -40)
				score -= 10;
			if(type != Actor.PLAYER_SHIP)
			{
				if(actor.getX() < -40 || 
						actor.getY() < -40 || 
						actor.getX() > getWidth() + 40 || 
						actor.getY() > getHeight() + 40)
				{
					delList.add(actor);
					continue;
				}
			}



			for(Actor actor1 : actors.getActorList())
			{
				int type1 = actor1.getType();
				if(actor1.isAlien() ^ actor.isAlien())
				{
					if(Math.abs(actor1.getX() - actor.getX()) <= 20
							&&  Math.abs(actor1.getY() - actor.getY() ) <= 20)
					{

						if(type1 == Actor.PLAYER_SHIP)
						{
							if(type == Actor.ALIEN_BULLET)
							{
								//player ship vs alien bullet
								if(((Ship)actor1).reduceLife(((Bullet)actor).getPower()))
								{
									delList.add(actor1);
									score -= 20;
								}
								delList.add(actor);
							}

							else	//player ship vs alien ship
							{
								Ship playerShip = (Ship)actor1;
								Ship alienShip = (Ship)actor;
								if(playerShip.hasNotBeenAttacked(alienShip) && playerShip.reduceLife(alienShip.getDamageOnCollision()))
								{
									playerShip.getAttackers().clear();
									delList.add(playerShip);
									score -= 20;
								}

								if(alienShip.hasNotBeenAttacked(playerShip) && alienShip.reduceLife(playerShip.getDamageOnCollision()))
								{
									alienShip.getAttackers().clear();
									delList.add(alienShip);
									score += 10;
								}
							}

						}

						else if(type1 == Actor.ALIEN_SHIP && type == Actor.PLAYER_BULLET)
						{
							//alien ship vs player bullet
							if(((Ship)actor1).reduceLife(((Bullet)actor).getPower()))
							{
								delList.add(actor1);
								score += 10;
							}
							delList.add(actor);
						}

						else if((type1 == Actor.PLAYER_BULLET || type1 == Actor.ALIEN_BULLET) && 
								(type == Actor.ALIEN_BULLET || type == Actor.PLAYER_BULLET))
						{
							//clash of bullets, annihilate each other
							delList.add(actor1);
							delList.add(actor);
						}
					}
				}
			}
		}

		actors.removeActors(delList);
	}
}
