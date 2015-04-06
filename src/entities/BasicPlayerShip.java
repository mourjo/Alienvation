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
	double firingProbability = 0.09;
	
	public BasicPlayerShip(Slice s) 
	{
		super();
		slice = s;
		
		x = (int) (gen.nextInt((int) s.getWidth()) + s.getX());
		y = (int) (gen.nextInt((int) s.getHeight()) + s.getY());
		
		if(image1 == null)
			try {
				image1 = ImageIO.read(getClass().getResource("/playerShip3.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public BasicPlayerShip(int x, int y, int slice)
	{
		super();
		this.x = x;
		this.y = y;
		
		if(image1 == null)
			try {
				image1 = ImageIO.read(getClass().getResource("/playerShip3.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void paintComponent(Graphics2D g2d) {
		
		g2d.drawImage(image1, x, y, null);
		
	}
	
	
	@Override
	public Slice getSlice() {
		return slice;
	}

	@Override
	public int act(ActorSet actors) {
		
		if(y >= Math.min(slice.getY() + slice.getHeight() - displayBuffer, Simulator.getInstance().getHeight() - displayBuffer))
			direction = -1;
		else if(y <= slice.getY())
			direction = 1;
		y = y + direction*speedY;
		
		if(gen.nextFloat() <= firingProbability)
			actors.getPlayerBullets().add(new PlayerBullet(x + 70, y + 15));
		
		return 0;
	}

}
