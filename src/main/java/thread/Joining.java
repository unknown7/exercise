package thread;

import java.util.concurrent.TimeUnit;

public class Joining {
	public static void main(String[] args) {
		Sleeper
			sleepy = new Sleeper("Sleepy", 1),
			grumpy = new Sleeper("Grumpy", 1);
		Joiner
			dopey = new Joiner("Dopey", sleepy),
			doc = new Joiner("Doc", grumpy);
//		grumpy.interrupt();
	}
}

class Sleeper extends Thread {
	private int duration;
	public Sleeper(String name, int duration) {
		super(name);
		this.duration = duration;
		start();
	}
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println(getName() + " has awakened");
	}
}
class Joiner extends Thread {
	private Sleeper sleeper;
	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}
	@Override
	public void run() {
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println(getName() + " join completed");
	}
}