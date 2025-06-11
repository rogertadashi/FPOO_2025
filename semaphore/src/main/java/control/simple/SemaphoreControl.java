package control.simple;

import java.time.LocalTime;
import semaphore.util.TurnOnOff;

public interface SemaphoreControl extends TurnOnOff{

	void setGreenSeconds(int seconds);
	void setYelloweconds(int seconds);
	void setRedSeconds(int seconds);	
	
	void setAlertPeriod(LocalTime start, LocalTime end);
}
