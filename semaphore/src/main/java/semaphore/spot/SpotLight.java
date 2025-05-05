package semaphore.spot;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import semaphore.light.AbstractLight;
import semaphore.light.Light;
import semaphore.light.e27.AbstractLightE27;
import semaphore.util.TurnOnOff;
import semaphore.util.gui.Paintable;

public class SpotLight implements TurnOnOff , Paintable{
	
	private AbstractLight light1;
	private Image maskOn;
	private Image maskOff;
	
	public SpotLight(Image maskOn, Image maskOff) {
		this.maskOn = maskOn;
		this.maskOff = maskOff;
	}
	
	
	private Point position = new Point(0, 0);
	private Dimension dimension = new Dimension(20, 20);

	private Light light;
	private Image mask;

	public SpotLight(Image mask) {
		this.mask = mask;
	}

	public void setLight(AbstractLightE27 light) {
		this.light1 = light;
	}

	@Override
	public void turnOn() {
		this.light1.turnOn();

	}

	@Override
	public void turnOff() {
		this.light1.turnOff();

	}

	@Override
	public boolean isOn() {
		return this.light1.isOn();
	}

	@Override
	public boolean isOff() {
		return this.light1.isOff();
	}

	public void setPosition(int xLeft, int yTop) {
		this.position = new Point(xLeft, yTop);
	}

	public Point getPosition() {
		return (Point) this.position.clone();
	}

	public void setDimension(Dimension dimension) {
		this.dimension = new Dimension(dimension);
	}

	public Dimension getDimension() {
		return (Dimension) this.dimension.clone();
	}

	public void setSize(int width, int height) {
		this.dimension = new Dimension(width, height);
	}

	@Override
	public void paint(Graphics g) {
		
		int xLeft = position.x;
		int yTop = position.y;
		int width = dimension.width;
		int height = dimension.height;
		
		if(isOn())
			g.drawImage(maskOn, xLeft, yTop, width, height, null);
		else
			g.drawImage(maskOff, xLeft, yTop, width, height, null);
	}

	@Override
	public void add(Paintable paintable) {
		// TODO Auto-generated method stub
		
	}
	
	
}
