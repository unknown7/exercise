package thread.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise20 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 2; i++)
			exec.execute(new LiftOff());
		exec.shutdown();
	}
}
class LiftOff implements Runnable {
	protected int countDown = 3;
	private static int taskCount = 0;
	private final int id = taskCount++;
	public LiftOff() {}
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	public String status() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "), ";
	}
	@Override
	public void run() {
		while (countDown-- > 0) {
			if (Thread.currentThread().isInterrupted()) {
				System.err.println("interrupt #" + id);
				return;
			}
			System.err.print(status());
			Thread.yield();
		}
	}
}