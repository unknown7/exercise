package thread;

import java.util.concurrent.TimeUnit;

public class ResponsiveUI extends Thread {
	public ResponsiveUI() {
		setDaemon(true);
		start();
	}
	@Override
	public void run() {
		while (true) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
				System.err.print("*");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws Exception {
		new ResponsiveUI();
		TimeUnit.SECONDS.sleep(3);
	}
}
