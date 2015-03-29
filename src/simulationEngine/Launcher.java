package simulationEngine;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Launcher extends JFrame {

	private static final long serialVersionUID = 5859915989867575984L;
	Simulator simulator;
	boolean paused = false;
	boolean init = true;
	Launcher(String name)
	{
		super(name);
		simulator = Simulator.getInstance();
		setSize(960, 700);
		setVisible(true);
		add(simulator);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				if(init && e.getKeyCode() == KeyEvent.VK_ENTER)
					simulator.init(getWidth(), getHeight());
				if(e.getKeyChar() == ' ')
					simulator.createAlienWave();
				if(e.getKeyChar() == 'p' || e.getKeyChar() == 'P' )
					simulator.togglePause();
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE ){
					Launcher.this.dispose();
					System.exit(0);
				}
				
			}
			
		});
		
		
		addComponentListener(new ComponentAdapter(){

			@Override
			public void componentResized(ComponentEvent ev) {
				simulator.setSize(((Component)ev.getSource()).getSize());
			}
			
		});
	}
	
	public void launch() throws InterruptedException
	{
		while(true)
		{
			Thread.sleep(10);
			if(!paused)
				simulator.repaint();
//			System.out.println(Simulator.getInstance().actors.getAlienShips().size());
		}
	}
	
	public static void main(String args[]) throws InterruptedException
	{
		new Launcher("Alienvation").launch();
	}
}
