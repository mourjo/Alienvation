package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import simulationEngine.ActorSet;
import simulationEngine.Simulator;
import simulationEngine.Slice;

public class BasicAlienShip extends AlienShip {
	static BufferedImage image1 = null;
	static BufferedImage image2 = null;
	
	static{
		try {
//			image1 = ImageIO.read(AlienShip.class.getResource("/spaceship4.png"));
			image1 = ImageIO.read(AlienShip.class.getResource("/alienShip3.png"));
			image2 = ImageIO.read(AlienShip.class.getResource("/alienShip4.png"));
//			image2 = ImageIO.read(AlienShip.class.getResource("/spaceship5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	BufferedImage image;
	int changeColorInterval;
	
	public BasicAlienShip()
	{
		super();
		damage = 20;
		life = 50;
		firingProbability = 0.009;
		x = Simulator.getInstance().getWidth();
		y = gen.nextInt(Simulator.getInstance().getHeight()) + 1;
		image = image1;
		changeColorInterval = gen.nextInt(30) + gen.nextInt(50);
		
	}
	
	public BasicAlienShip(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
		damage = 20;
		life = 50;
		image = image1;
		firingProbability = 0.009;
		changeColorInterval = gen.nextInt(30) + 50;
//		speedX = gen.nextInt(10) + 3;
		
//		if(image1 == null)
//			try {
//				image1 = ImageIO.read(getClass().getResourceAsStream("/alienShip3.png"));//classloader.getResource("/image/name.jpg")
//				image2 = ImageIO.read(getClass().getResourceAsStream("/alienShip4.png"));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}

	@Override
	public void paintComponent(Graphics2D g2d) {
		if(!paused)
		{
			if(changeColorInterval-- == 0)
			{
				image = image == image1 ? image2 : image1;
				changeColorInterval = gen.nextInt(30) + 50;
			}
		}
		g2d.drawImage(image, x, y, null);

	}

	@Override
	public Slice getSlice() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int act(ActorSet actors) {
		x -= speedX;
		
		if(gen.nextFloat() <= firingProbability)
			actors.getActorList().add(new AlienBullet(x, y + 10, 1 + speedX));
		
		return 0;
	}

}
