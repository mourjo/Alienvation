
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * http://www.edu4java.com/en/game/game2.html
 * */

@SuppressWarnings("serial")
public class Trial extends JPanel {
	int x = 100;
	int y = 0;
	int starsX[];
	int starsY[];
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		this.setBackground(Color.BLACK);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		for(int i = 0; i < starsX.length; i++)
			g2d.fillOval(starsX[i], starsY[i], 2, 2);
		g2d.setColor(Color.RED);
		g2d.fillOval(x, y, 30, 30);
		
//		g2d.draw(new Ellipse2D.Double(0, 100, 30, 30));
	}
	
	public static void main(String[] args) throws InterruptedException {
		Trial game = new Trial();
		JFrame frame = new JFrame("Trial");
		frame.add(game);
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Random gen = new Random();
		
		game.starsX = new int[] {gen.nextInt(601), gen.nextInt(601), gen.nextInt(601), gen.nextInt(601),gen.nextInt(601), gen.nextInt(601), gen.nextInt(601), gen.nextInt(601),gen.nextInt(601), gen.nextInt(601), gen.nextInt(601), gen.nextInt(601),gen.nextInt(601), gen.nextInt(601), gen.nextInt(601), gen.nextInt(601),gen.nextInt(601), gen.nextInt(601), gen.nextInt(601), gen.nextInt(601), gen.nextInt(601)};
		game.starsY = new int[] {gen.nextInt(601), gen.nextInt(601), gen.nextInt(601), gen.nextInt(601),gen.nextInt(601), gen.nextInt(601), gen.nextInt(601), gen.nextInt(601),gen.nextInt(601), gen.nextInt(601), gen.nextInt(601), gen.nextInt(601),gen.nextInt(601), gen.nextInt(601), gen.nextInt(601), gen.nextInt(601),gen.nextInt(601), gen.nextInt(601), gen.nextInt(601), gen.nextInt(601), gen.nextInt(601)};
		
		while(true)
		{
			game.x = (game.x+gen.nextInt(5))%601;
			game.y = (game.y+gen.nextInt(5))%601;
			game.repaint();
			Thread.sleep(10);
		}
	}
}