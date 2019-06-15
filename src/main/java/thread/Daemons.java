package thread;

import java.util.concurrent.TimeUnit;

public class Daemons {
	public static void main(String[] args) throws InterruptedException {
		Thread d = new Thread(new Daemon());
		d.setDaemon(true);
		d.start();
		System.err.println("d.isDaemon() : " + d.isDaemon());
		TimeUnit.MILLISECONDS.sleep(100);
	}
}

class Daemon implements Runnable {
	private Thread[] daemons = new Thread[10];
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			daemons[i] = new Thread(new DaemonSpawn());
			daemons[i].start();
			System.err.println("DaemonSpawn[" + i + "] started");
		}
		for (int i = 0; i < daemons.length; i++)
			System.err.println("DaemonSpawn[" + i + "].isDaemon() : " + daemons[i].isDaemon());
		while (true)
			Thread.yield();
	}
}
class DaemonSpawn implements Runnable {
	@Override
	public void run() {
		while (true)
			Thread.yield();
	}
}