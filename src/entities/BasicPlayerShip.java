package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import simulationEngine.ActorSet;
import simulationEngine.Simulator;

public class BasicPlayerShip extends PlayerShip {
	static BufferedImage image1 = null;
	
	public BasicPlayerShip()
	{
		super();
		x = gen.nextInt(Simulator.getInstance().getWidth());
		y = gen.nextInt(Simulator.getInstance().getHeight()) + 1;
		
		if(image1 == null)
			try {
				image1 = ImageIO.read(new File("img/playerShip3.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public BasicPlayerShip(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
		
		if(image1 == null)
			try {
				image1 = ImageIO.read(new File("img/playerShip3.png"));
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
	public int getSlice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int act(ActorSet actors) {
		if(y + 45 >= Simulator.getInstance().getHeight() || y <= 0)
			speedY = -speedY;
		y = y + speedY;
		
		if(gen.nextFloat() <= firingProbability)
			actors.getPlayerBullets().add(new PlayerBullet(x + 70, y + 15));
		
		return 0;
	}

}
