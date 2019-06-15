package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SerialNumberChecker {
	private static final int SIZE = 10;
	private static CircularSet set = new CircularSet(1000);
	private static ExecutorService exec = Executors.newCachedThreadPool();
	static class SerialChecker implements Runnable {
		@Override
		public void run() {
			while (true) {
				int serial = SerialNumberGenerator.nextSerialNumber();
				if (set.contains(serial)) {
					System.err.println(serial);
					System.exit(0);
				}
				set.add(serial);
			}
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < SIZE; i++)
			exec.execute(new SerialChecker());
	}
}

class SerialNumberGenerator {
	private static volatile int serialNumber = 0;
	public static int nextSerialNumber() {
		return serialNumber++;
	}
}