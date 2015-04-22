package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import simulationEngine.ActorSet;
import simulationEngine.Simulator;
import simulationEngine.Slice;

public class BasicPlayerShip extends PlayerShip {
	static BufferedImage image1 = null;
	static final int displayBuffer = 20;
	
	static{
		try {
			image1 = ImageIO.read(BasicPlayerShip.class.getResource("/playerShip3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BasicPlayerShip(Slice s) 
	{
		super();
		slice = s;
		damage = 50;
		
		x = (int) (gen.nextInt((int) s.getWidth()) + s.getX());
		y = (int) (gen.nextInt((int) s.getHeight()) + s.getY());
		
	}
	
	public BasicPlayerShip(int x, int y, Slice s)
	{
		super();
		this.x = x;
		this.y = y;
		if(s==null)
			throw new NullPointerException();
		slice = s;
		damage = 50;
		
		
	}

	@Override
	public void paintComponent(Graphics2D g2d) {
		
		g2d.drawImage(image1, x, y, null);
		
	}
	
	public static int getWidth()
	{
		return (int)image1.getWidth();
	}
	
	
	@Override
	public Slice getSlice() {
		return slice;
	}

	@Override
	public int act(ActorSet actors) {
//		if(slice == null)
//		{
//			System.out.println(slice);
//			return -1;
//		}
		if(x >= Math.min(slice.getX() + slice.getWidth(), Simulator.getInstance().getWidth()) || x <= slice.getX())
				x = (int) (gen.nextInt((int)slice.getWidth()) + slice.getX());
		
		if(y >= Math.min(slice.getY() + slice.getHeight() - displayBuffer, Simulator.getInstance().getHeight() - displayBuffer))
			direction = -1;
		else if(y <= slice.getY())
			direction = 1;
		y = y + direction*speedY;
		
		if(gen.nextFloat() <= firingProbability)
			actors.getActorList().add(new PlayerBullet(x + 70, y + 15));
		
		return 0;
	}

}
