package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Future<String>> futures = new ArrayList<Future<String>>();
		for (int i = 0; i < 5; i++) {
			Future<String> future = exec.submit(new TaskWithResult(i));
			futures.add(future);
			System.err.println(future.isDone());
		}
			
		
		for (Future<String> future : futures) {
			try {
				System.err.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}

class TaskWithResult implements Callable<String> {
	private int id;
	public TaskWithResult(int id) {
		this.id = id;
	}
	@Override
	public String call() throws Exception {
		return "TaskWithReslt" + id;
	}
}