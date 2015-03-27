package simulationEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Launcher extends JFrame {

	private static final long serialVersionUID = 5859915989867575984L;
	Simulator simulator;
	Launcher(String name)
	{
		super(name);
		simulator = Simulator.getInstance();
		
		this.setSize(960, 700);
		simulator.init(this.getWidth(), this.getHeight());
		this.setVisible(true);
		this.add(simulator);
		simulator.simulate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == ' ')
					ActorCreator.getInstance().createBasicAlienShip(simulator.getAlienShips(), 10);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {}
			
		});
	}
	
	public void launch() throws InterruptedException
	{
		while(true)
		{
			Thread.sleep(10);
			simulator.repaint();
			System.out.println(Simulator.getInstance().actors.getAlienShips().size());
		}
	}
	
	public static void main(String args[]) throws InterruptedException
	{
		new Launcher("Alienvation").launch();
	}
}
