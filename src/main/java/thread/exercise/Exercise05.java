package thread.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercise05 implements Callable<String> {
	private int sum;
	private int n;
	public Exercise05(int n) {
		this.n = n;
	}
	private int fib(int n) {
		if (n < 2) return 1;
		return fib(n - 1) + fib(n - 2);
	}
	@Override
	public String call() throws Exception {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int num = fib(i);
			result.append(num + ", ");
			sum += num;
		}
		return result.append("sum:" + sum).toString();
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Future<String>> futures = new ArrayList<Future<String>>();
		for (int i = 0; i < 5; i++)
			futures.add(exec.submit(new Exercise05(7)));
		exec.shutdown();
		for (Future<String> future : futures) {
			try {
				System.err.println(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
