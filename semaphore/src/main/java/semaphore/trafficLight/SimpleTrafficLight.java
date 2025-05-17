package semaphore.trafficLight;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

import semaphore.light.e27.E27LightBulb;
import semaphore.spot.SpotLight;
import semaphore.util.TurnOnOff;
import semaphore.util.gui.Paintable;

public class SimpleTrafficLight implements Paintable,TrafficLight{
	
	private Point position = new Point(0,0);
	private Dimension dimension = new Dimension(70,180);
	
	private SpotLight yellow;
	private SpotLight green;
	private SpotLight red;
	private BufferedImage mask;
	
	private String currentRelativePath() {
		
		return "/" + 
				this.getClass()
				.getPackageName()
				.toString()
				.replace('.','/')+
				"/";
	}
	
	private SpotLight createSpot( String color ) throws IOException{
		
		int xLeft = this.position.x;
		int yTop = this.position.y;
		int width = this.dimension.width;
		int height = this.dimension.height;
		
		final String path = currentRelativePath() + "img/";
		URL url;
		
		url = getClass().getResource(path + color +"On.png");
		BufferedImage maskOn = ImageIO.read(url);
		
		url = getClass().getResource(path + color +"Off.png");
		BufferedImage maskOff = ImageIO.read(url);
		
		SpotLight spot = new SpotLight(maskOn,maskOff);
		spot.setPosition(xLeft, yTop);
		spot.setSize(width, height);
		
		return spot;
	}
	
	//--Paintable Implementation-------------
		@Override
		public void paint(Graphics g) {
			
			synchronized (g) {
				
				int xLeft = this.position.x;
				int yTop = this.position.y;
				int width = this.dimension.width;
				int height = this.dimension.height;
				
				g.drawImage(null, xLeft, yTop, width, height, null);
				
				this.green.paint(g);
				this.yellow.paint(g);
				this.red.paint(g);
			}
		}
	
	
	private void create() throws IOException{
	
		this.green = createSpot("green");
		this.green.setLight(new E27LightBulb());
		
		this.yellow = createSpot("yellow");
		this.yellow.setLight(new E27LightBulb());
		
		this.red = createSpot("red");
		this.red.setLight(new E27LightBulb());
		
		String path = this.currentRelativePath();
		URL url = this.getClass().getResource(path + "img/trafficLight.png");
		this.mask = ImageIO.read(url);
		
	}
	
	private void configurePosition() {
			
		final int WIDTH = ( this.dimension.width - 20);
		final int HEIGHT = ((this.dimension.height -30 ) / 3);
		final Dimension dimension = new Dimension(WIDTH,HEIGHT);
		
		int xLeft = this.position.x + 10;
		int yTop = this.position.y + 10;
		this.green.setPosition(xLeft, yTop);
		this.green.setDimension(dimension);
		
		yTop = (yTop + 5 + HEIGHT);
		this.yellow.setPosition(xLeft, yTop);
		this.yellow.setDimension(dimension);

		yTop = (yTop + 5 + HEIGHT);
		this.red.setPosition(xLeft, yTop);
		this.red.setDimension(dimension);
	}
	
	
	public SimpleTrafficLight() throws IOException{
		
		this.create();
		this.configurePosition();
	}
	
	public SimpleTrafficLight(Point position,
							Dimension dimension) throws IOException{
		
		this.position = position;
		this.dimension = dimension;
		
		this.create();
		this.configurePosition();
		
	}
		
	public void setPosition(Point position) {
		this.position = new Point(position);
		this.configurePosition();
	}
	
	public Point getPosition() {
		return new Point(this.position);
	}
	
	public void setDimension(Dimension dimension) {
		this.dimension = new Dimension(dimension);
		this.configurePosition();
	}
	
	public Dimension getDimension() {
		return new Dimension(this.dimension);
	}

	@Override
	public void add(Paintable paintable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TurnOnOff spotGreen() {
		return this.green;
	}

	@Override
	public TurnOnOff spotYellow() {
		return this.yellow;
	}

	@Override
	public TurnOnOff spotRed() {
		return this.red;
	}
	
	
		
}
