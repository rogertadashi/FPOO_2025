package semaphore.util.gui;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MyWindow {
	
	JFrame frame = new JFrame();
	MyPanel panel = new MyPanel();
	
	private void configureRepaint() {
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				SwingUtilities.invokeLater(()->panel.repaint());
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 100);
	}
	
	public MyWindow() {
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(800,600));
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		configureRepaint();
	}
	
	public void add(Paintable paintable) {
		this.panel.add(paintable);
	}
	
	public void remove(Paintable paintable) {
		this.panel.remove(paintable);
	}
}
