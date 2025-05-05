package semaphore.util.gui;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	Set<Paintable> paintables = new HashSet<>();
	
	public void add(Paintable paintable){
		paintables.add(paintable);
	}
	
	public void remove(Paintable paintable){
		paintables.remove(paintable);
	}
	
	@Override
	public void paint(Graphics g) {
		synchronized(g) {
			for(Paintable paintable : paintables)
				paintable.paint(g);
		}
	}
}
