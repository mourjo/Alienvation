import javax.swing.*;
import javax.swing.plaf.LayerUI;

import java.awt.*;

public class Trial {

	public static void main(String args[])
	{
		JFrame frame = new JFrame();
		frame.setSize(600, 600);
		final int frames = 24;
		frame.add(new JPanel(){
			int x = 10, y = 10, frameCount = 0, framerate;
			long time = System.nanoTime();
			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.RED);
				g2d.fillRect(10, y+1, 100, 50);
				y = (y+1)%500;
				if(frameCount++ == frames)
				{
					framerate = (int) (((double)frameCount/(System.nanoTime() - time)) * Math.pow(10,9));
					//    				 System.out.println(System.nanoTime() - time);
					time = System.nanoTime();

					frameCount = 0;
				}
				g2d.drawString(framerate + "", 400, 10);
			}
		});
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem op1 = new JMenuItem("Exit");
		frame.setJMenuBar(menubar);
		menubar.add(menu);
		menu.add(op1);

		menubar.setVisible(true);

		while(true)
		{
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.repaint();
		}
	}
}