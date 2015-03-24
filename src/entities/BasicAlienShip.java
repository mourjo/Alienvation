package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import simulationEngine.ActorSet;
import simulationEngine.Simulator;

public class BasicAlienShip extends AlienShip {
	
	private int sign;
	static BufferedImage image1 = null;
	static BufferedImage image2 = null;
	BufferedImage image;
	int interval;
	
	public BasicAlienShip()
	{
		super();
		sign = 1;
		x = gen.nextInt(Simulator.canvasWidth);
		y = gen.nextInt(Simulator.canvasHeight) + 1;
		image = image1;
		interval = gen.nextInt(30) + 50;
		
		if(image1 == null)
			try {
				image1 = ImageIO.read(new File("img/alienShip3.png"));
				image2 = ImageIO.read(new File("img/alienShip4.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void paintComponent(Graphics2D g2d) {
		if(interval-- == 0)
		{
			image = image == image1 ? image2 : image1;
			interval = gen.nextInt(30) + 50;
		}
		g2d.drawImage(image, x, y, null);

	}

	@Override
	public int getSlice() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int act(ActorSet actors) {
		if(y + 50 == Simulator.canvasHeight || y == 0)
			sign = -sign;
		y = y + sign;

		if(gen.nextInt(1000) <= 5)
			actors.getAlienBullets().add(new AlienBullet(x, y));
		return 0;
	}

}
