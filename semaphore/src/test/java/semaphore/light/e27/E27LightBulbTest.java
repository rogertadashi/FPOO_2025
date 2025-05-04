package semaphore.light.e27;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import semaphore.light.Light;

class E27LightBulbTest{
	@Test
    void shouldTurnOn() {

        //given
        Light light = new E27LightBulb();

        //do action
        light.turnOff();
        light.turnOn();

        //check
        assertTrue(light.isOn());
    }
	
	@Test
    void shouldTurnOff() {

        //given
        Light light = new E27LightBulb();

        //do action
        light.turnOn();
        light.turnOff();

        //check
        assertTrue(light.isOff());
    }
	
}
