package thread.exercise;

public class Exercise01 implements Runnable {
	private static int count = 0;
	private final int id = count++;
	public Exercise01() {
		System.err.println("#" + id + " start up!");
	}
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.err.println("#" + id + " run..." + i);
			Thread.yield();
		}
		System.err.println("#" + id + " shut down");
		return;
	}
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			new Thread(new Exercise01()).start();
	}
}
