package thread;

public class LiftOff implements Runnable {
	protected int countDown = 3;
	private static int taskCount = 0;
	private final int id = taskCount++;
	public LiftOff() {}
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "), ";
	}
	@Override
	public void run() {
		while (countDown-- > 0) {
			System.err.print(status());
			Thread.yield();
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++)
			new Thread(new LiftOff()).start();
		System.err.println("Waiting for LiftOff");
	}
}
