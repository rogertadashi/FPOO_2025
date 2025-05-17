package semaphore.trafficLight;

import java.util.Timer;
import java.util.TimerTask;

import semaphore.trafficLight.control.TrafficLightControl;
import semaphore.util.TurnOnOff;

public class SimpleTrafficLightControl implements TrafficLightControl{
	private final TrafficLight trafficLight;
	private final TurnOnOff green , yellow, red;
	private State state = State.OFF;

	public SimpleTrafficLightControl(TrafficLight trafficLight) {
		this.trafficLight = trafficLight;
		
		this.green = trafficLight.spotGreen();
		this.yellow = trafficLight.spotYellow();
		this.red = trafficLight.spotRed();
	}

	private Timer timer = null;
	private void configureAlert() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
	
			@Override
			public void run() {
				trafficLight.spotRed().turnOff();
				trafficLight.spotGreen().turnOff();
				
				if(trafficLight.spotYellow().isOn())
					trafficLight.spotYellow().turnOff();
				else
					trafficLight.spotYellow().turnOn();
			}
		},0,1000);
	}
	
	private void stopAlert() {
		if(timer !=null)
			timer.cancel();
		
		trafficLight.spotYellow().turnOff();
	}
	
	public void reset() {
		if(state == State.ALERT)
			stopAlert();

		green.turnOff();
		red.turnOff();
		yellow.turnOff();
		
		state = State.OFF;
		
	}
	
	
	@Override
	public void turnAlert() {
		if(state == State.ALERT)
			return;
		this.reset();
		this.configureAlert();
		state = State.ALERT;
	}
	
	@Override
	public void turnGreen() {
		this.reset();
		trafficLight.spotGreen().turnOn();
		state = State.GREEN;
	}	
	
	@Override
	public void turnYellow() {
		this.reset();
		trafficLight.spotYellow().turnOn();
		state = State.YELLOW;
	}

	@Override
	public void turnRed() {
		this.reset();
		trafficLight.spotRed().turnOn();
		state = State.RED;
	}

	@Override
	public void turnOff() {
		this.reset();
	};
	
}
