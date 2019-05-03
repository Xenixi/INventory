package inventory.main.util;

public class TimeMonitor {
	long startTime = System.currentTimeMillis();
	public void startTime() {
		startTime = System.currentTimeMillis();
	}
	public long getElapsedTimeMs() {
		return System.currentTimeMillis() - startTime;
	}
}
