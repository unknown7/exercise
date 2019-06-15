package thread.exercise;

import java.util.Timer;
import java.util.TimerTask;

public class Exercise14 {
	private static void printConsole(int i) {
		for (int j = 0; j < i; j++)
			System.err.append('*');
	}
	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			final int j = i;
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					printConsole(j);
				}
			}, i * 500);
		}
	}
}
