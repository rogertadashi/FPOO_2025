package semaphore.trafficLight.simple;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;

import semaphore.spot.SpotLight;

public class SimpleTrafficLight {
	
	private Point position = new Point(0,0);
	private Dimension dimension = new Dimension(70,180);
	
	private SpotLight yellow;
	private SpotLight green;
	private SpotLight red;
	
	private void create(){
		//to do
	}
	
	public SimpleTrafficLight() throws IOException{
		
		this.create();
	}
	
	public SimpleTrafficLight(Point position,
							Dimension dimension) throws IOException{
		
		this.position = position;
		this.dimension = dimension;
		
		this.create();
		}
		
	public void setPosition(Point position) {
		this.position = new Point(position);
	}
	
	public Point getPosition() {
		return new Point(this.position);
	}
	
	public void setDimension(Dimension dimension) {
		this.dimension = new Dimension(dimension);
	}
	
	public Dimension getDimension() {
		return new Dimension(this.dimension);
	}
	
	
}
