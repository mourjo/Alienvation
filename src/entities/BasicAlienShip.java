package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import simulationEngine.ActorSet;
import simulationEngine.Simulator;

public class BasicAlienShip extends AlienShip {
	
	static BufferedImage image1 = null;
	static BufferedImage image2 = null;
	BufferedImage image;
	int changeColorInterval;
	
	public BasicAlienShip()
	{
		super();
		x = Simulator.canvasWidth;
		y = gen.nextInt(Simulator.canvasHeight) + 1;
		image = image1;
		changeColorInterval = gen.nextInt(30) + 50;
		
		if(image1 == null)
			try {
				image1 = ImageIO.read(new File("img/alienShip3.png"));
				image2 = ImageIO.read(new File("img/alienShip4.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public BasicAlienShip(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
		image = image1;
		changeColorInterval = gen.nextInt(30) + 50;
		
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
		if(changeColorInterval-- == 0)
		{
			image = image == image1 ? image2 : image1;
			changeColorInterval = gen.nextInt(30) + 50;
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
		x -= speedX;
		if(gen.nextInt(1000) <= 5)
			actors.getAlienBullets().add(new AlienBullet(x, y, gen.nextInt(5)+2));
		return 0;
	}

}
