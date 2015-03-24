package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import simulationEngine.ActorSet;
import simulationEngine.Simulator;

public class BasicPlayerShip extends PlayerShip {
	private int sign = 1;
	static BufferedImage image1 = null;
	static BufferedImage image2 = null;
	BufferedImage image;
	int interval;

	@Override
	public void paintComponent(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
//		for(int i = 0; i < starsX.length; i++)
//			g2d.fillOval(starsX[i], starsY[i], 2, 2);
		g2d.setColor(Color.RED);
//		g2d.fillRect(x, y, 40, 20);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		if(interval-- == 0)
		{
			image = image == image1 ? image2 : image1;
			interval = gen.nextInt(30) + 50;
		}
		g2d.drawImage(image, x, y, null);
//		g2d.drawImage(image, x, y, 60, 30, null);
		
	}
	
	public BasicPlayerShip()
	{
		super();
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
	public int getSlice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getType() {
		return Actor.PLAYER_SHIP;
	}

	@Override
	public int act(ActorSet actors) {
		if(y+50 == Simulator.canvasHeight || y == 0)
			sign = -sign;
		y = y + sign;
//		y = (y + 1) % Simulator.canvasHeight;
		if(gen.nextInt(1000) <= 5)
			actors.getPlayerBullets().add(new PlayerBullet(x+40,y));
		return 0;
	}

}
