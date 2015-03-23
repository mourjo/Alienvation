package simulationEngine;

import javax.swing.JFrame;

public class Launcher extends JFrame {

	private static final long serialVersionUID = 5859915989867575984L;
	Simulator simulator;
	Launcher(String name)
	{
		super(name);
		simulator = Simulator.getInstance();
		
		this.setSize(960, 700);
		simulator.setCanvasSize(this.getWidth(), this.getHeight());
		this.setVisible(true);
		this.add(simulator);
		simulator.simulate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void launch() throws InterruptedException
	{
		while(true)
		{
			Thread.sleep(10);
			simulator.repaint();
		}
	}
	
	public static void main(String args[]) throws InterruptedException
	{
		new Launcher("Alienvation").launch();
	}
}
