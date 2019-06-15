package thread.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise04 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++)
			exec.execute(new Exercise02(7));
		exec.shutdown();
	}
}
