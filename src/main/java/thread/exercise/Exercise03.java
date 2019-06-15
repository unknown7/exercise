package thread.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise03 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new Exercise01());
		exec.shutdown();
		
		System.err.println("------");
		
		exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++)
			exec.execute(new Exercise01());
		exec.shutdown();
	}
}
