package control.simple;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import semaphore.trafficLight.SimpleTrafficLight;
import semaphore.trafficLight.SimpleTrafficLightControl;
import semaphore.trafficLight.TrafficLight;
import semaphore.trafficLight.control.TrafficLightControl;
import semaphore.util.gui.MyWindow;


public class OneWaySemaphoreControl implements SemaphoreControl{
	
	private List<TrafficLightControl> trafficLights = new ArrayList<>();
	
	private int greenMillis = 10_000;
	private int yellowMillis = 2_000;
	private int redMillis = 5_000;
	
	private LocalTime alertStart = LocalTime.of(0, 0);
	private LocalTime alertEnd = LocalTime.of(5, 30);
	
	private OnOff state = OnOff.OFF;

	public OneWaySemaphoreControl(List<TrafficLightControl> trafficLights) {
		this.trafficLights = trafficLights;
	}
	
	public OneWaySemaphoreControl(TrafficLightControl... trafficLights){
		this(Arrays.asList(trafficLights));
	}
	
	private boolean isAlertPeriod() {
		
		boolean START_MIDNIGHT_END = alertStart.isAfter(alertEnd);
		
		LocalTime now = LocalTime.now();
		
		if(START_MIDNIGHT_END)
			return (now.isAfter(alertStart) || now.isBefore(alertEnd));
		
		return (now.isAfter(alertStart) && now.isBefore(alertEnd));
	}
	
	private void doAlert() throws InterruptedException{
		while(isAlertPeriod()) {
			trafficLights.forEach(e->e.turnAlert());
			Thread.sleep(1_000);
		}
	}
	
	public class ForEachExample{
		
		private int[] vInt = {0,1,2,3,4,5,6,7,8,9};
		
		public void forEach(Consumer<Integer> consumer) {
			
			for(int i=0 ; i<vInt.length ; i++)
				consumer.accept(vInt[i]);
		}
		
	public static void main(String[] args) {
			
			ForEachExample fee = new ForEachExample();
			
			fee.forEach(e->System.out.println(e));
			
			fee.forEach(System.out::println);
		}
	}
	
	private void doYellowRedGreen()throws InterruptedException{
		
		trafficLights.forEach(e->e.turnYellow());
		Thread.sleep(yellowMillis);
		
		trafficLights.forEach(e->e.turnRed());
		Thread.sleep(redMillis);
		
		trafficLights.forEach(e->e.turnGreen());
		Thread.sleep(greenMillis);
	}
	
	private void run() {
		
		Runnable runnable = ()->{
			
			while (state == OnOff.ON) {
				try {
					doAlert();
					doYellowRedGreen();
				}
				catch (InterruptedException exception) {
					
					trafficLights.forEach(e->e.turnAlert());
				}
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
	}

	@Override
	public void turnOn() {
		
		if(state == OnOff.ON)
			return;
		
		state = OnOff.ON;
		run();
		
	}

	@Override
	public void turnOff() {
		
		state = OnOff.OFF;
		
	}

	@Override
	public boolean isOn() {
		
		return ( state == OnOff.ON);
	}

	@Override
	public boolean isOff() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGreenSeconds(int seconds) {
		this.greenMillis = seconds * 1_000;
		
	}

	@Override
	public void setYelloweconds(int seconds) {
		this.yellowMillis = seconds * 1_000;
	}

	@Override
	public void setRedSeconds(int seconds) {
		this.redMillis = seconds * 1_000;
		
	}

	@Override
	public void setAlertPeriod(LocalTime start, LocalTime end) {
		this.alertStart = start;
		this.alertEnd = end;
		
	}
	
	public class AppOneWaySimpleSemaphoreControlTest{
		
		static private void
		oneWaySimpleSemaphoreTest() throws IOException{
			
			SimpleTrafficLight defaultOne = new SimpleTrafficLight();
			TrafficLightControl trafficLightControl = new SimpleTrafficLightControl(defaultOne);
			SemaphoreControl semaphoreControl = new OneWaySemaphoreControl(trafficLightControl);
			
			semaphoreControl.setAlertPeriod(LocalTime.now(), LocalTime.now().plusSeconds(30));
			semaphoreControl.turnOn();
			
			MyWindow window = new MyWindow();
			window.add(defaultOne);
		}
		
		static public void main(String []args) throws IOException{
			
			oneWaySimpleSemaphoreTest();
		}
	}
	
	public class AppOneWayTwoSemaphoreControlTest{
		
		private MyWindow window = new MyWindow();
		private SemaphoreControl semaphoreControl;
		
		private SimpleTrafficLightControl createTrafficLight(Point position, Dimension dimension) throws IOException{
			
			SimpleTrafficLight trafficLight = new SimpleTrafficLight(position,dimension);
			window.add(trafficLight);
			
			return (new SimpleTrafficLightControl(trafficLight));
		}
		
		private void create() throws IOException{
			
			List<trafficLightControl> list = new ArrayList<>();
			list.add(createTrafficLight(new Point(100,100), new Dimension(70,180)));
			list.add(createTrafficLight(new Point(300,100), new Dimension(70,180)));
			
			semaphoreControl = new OneWaySemaphoreControl(list);
		}
		
		public AppOneWayTwoSemaphoreControlTest() throws IOException{
			
			this.create();
			semaphoreControl.setAlertPeriod(LocalTime.now(),LocalTime.now().plusSeconds(30));
			semaphoreControl.turnOn();
		}
		
		public static void main(String[] args) throws IOException{
			
			new AppOneWayTwoSemaphoreControlTest();
		}
	}
}
