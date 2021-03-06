package simulationEngine;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Launcher extends JFrame {

	private static final long serialVersionUID = 5859915989867575984L;
	Simulator simulator;
	boolean init = false;
	
	Launcher(String name)
	{
		super(name);
		setResizable(false);
		setSize(960, 600);
		buildMenus();
		simulator = Simulator.getInstance();
		
		
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
		
		addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent m) {
				if(simulator.isInitialized())
				{
					Point pt = m.getPoint();
					if(((int)pt.getY()-50) >= 0)
						simulator.createPlayerShip(-1, (int)pt.getX(), (int)pt.getY()-50);
				}
			}

			});
		
		setVisible(true);
		setLocationRelativeTo(null);		
		
		simulator.init(954, 548);
		add(simulator);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(simulator.getSize());
	}
	
	void buildMenus()
	{
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem exitMenu = new JMenuItem("Exit");
		exitMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Launcher.this.dispose();
				simulator.exit();
				System.exit(0);
				
			}});
		
		
		JMenu restartMenu = new JMenu("Restart");
		
		JMenuItem world3 = new JMenuItem("3x3 World");
		JMenuItem world4 = new JMenuItem("4x4 World");
		JMenuItem world5 = new JMenuItem("5x5 World");
		JMenuItem world6 = new JMenuItem("6x6 World");
		restartMenu.add(world3);
		restartMenu.add(world4);
		restartMenu.add(world5);
		restartMenu.add(world6);
		
		world3.addActionListener(new RestartListener(3));
		world4.addActionListener(new RestartListener(4));
		world5.addActionListener(new RestartListener(5));
		world6.addActionListener(new RestartListener(6));
		
		
		final JMenuItem pauseMenu = new JMenuItem("Pause/Unpause");
		pauseMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				simulator.togglePause();
				
			}});
		
		menubar.add(menu);
		menu.add(pauseMenu);
		menu.add(restartMenu);
		menu.add(exitMenu);
		
		setJMenuBar(menubar);
	}
	
	public void launch() throws InterruptedException
	{
		simulator.start();
	}
	
	public static void main(String args[]) throws InterruptedException
	{
		Launcher xlaunch = new Launcher("Alienvation");
		xlaunch.launch();
	}
	
	class RestartListener implements ActionListener
	{
		int cross;
		RestartListener(int n)
		{
			cross = n;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			simulator.setNumberOfSlices(cross);
		}
		
	}
}

