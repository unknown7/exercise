package thread.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercise10 {
	private static class ThreadMethod implements Callable<String> {
		private int n;
		private int sum;
		private static ExecutorService exec = Executors.newCachedThreadPool();
		ThreadMethod(int n) {
			this.n = n;
		}
		private int fib(int n) {
			if (n < 2) return 1;
			return fib(n - 1) + fib(n - 2);
		}
		public static Future<String> runTask(int num) {
			return exec.submit(new ThreadMethod(num));
		}
		public static void shutdown() {
			exec.shutdown();
		}
		@Override
		public String call() throws Exception {
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < n; i++) {
				int num = fib(i);
				result.append(num).append(',').append(' ');
				sum += num;
			}
			result.append("sum: ").append(sum);
			return result.toString();
		}
	}
	
	public static void main(String[] args) {
		List<Future<String>> futures = new ArrayList<Future<String>>();
		for (int i = 0; i < 5; i++)
			futures.add(ThreadMethod.runTask(7));
		ThreadMethod.shutdown();
		for (Future<String> future : futures)
			try {
				System.err.println(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
	}
}
