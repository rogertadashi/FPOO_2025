package semaphore.spot;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.awt.Point;

import semaphore.light.e27.E27LightBulb;
import semaphore.light.e27.AbstractLightE27;

import org.junit.jupiter.api.Test;

class SpotLightTest {

	@Test
	void shouldTurnOn(){
		
		//given
		AbstractLightE27 light = new E27LightBulb();
		SpotLight spot = new SpotLight(null);
		spot.setLight(light);
		
		//do action
		light.turnOff();
		light.turnOn();
		
		//check
		assertTrue(light.isOn());
		assertTrue(spot.isOn());
	}

	@Test
	void shouldTurnOff() {
		AbstractLightE27 light = new E27LightBulb();
		SpotLight spot = new SpotLight(null);
		spot.setLight(light);
		
		//do action
		light.turnOn();
		light.turnOff();
		
		//check
		assertTrue(light.isOff());
		assertTrue(spot.isOff());
		
	}
	
	@Test
	void shouldReturnCloneOfPosition() {
		//given
		SpotLight spot = new SpotLight(null);
		spot.setLight(new E27LightBulb());
		final int xLeft = 20;
		final int yTop = 50;
		spot.setPosition(xLeft, yTop);
		
		//do action
		Point position = spot.getPosition();
		position.setLocation(new Point(30,30));
		
		//check
		assertEquals(xLeft, spot.getPosition().getX());
		assertEquals(yTop, spot.getPosition().getY());
	}
	
	@Test
	void shouldReturnCloneOfDimension() {
		//given
		SpotLight spot = new SpotLight(null);
		spot.setLight(new E27LightBulb());
		
		//do action
		Dimension dimension1 = spot.getDimension();
		Dimension dimension2 = spot.getDimension();
		
		//check
		assertFalse(dimension1==dimension2);
		assertTrue(dimension1.width==dimension2.width);
		assertTrue(dimension1.height==dimension2.height);
		
	}
	
}
