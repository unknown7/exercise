package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CountDownLatchTest {
	private int groupNum = 10;
	private CountDownLatch startLatch = new CountDownLatch(1);
	private CountDownLatch stopLatch = new CountDownLatch(groupNum);
	private ExecutorService service = Executors.newFixedThreadPool(groupNum);
	private List<Future<Integer>> result = new ArrayList<Future<Integer>>();
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		CountDownLatchTest test = new CountDownLatchTest();
		test.init();
		System.err.println("please enter start command....");
		
		reader.readLine();
		test.start();
		test.stop();

		test.printRes();
	}
	
	public void init() {
	    for(int i = 0; i < groupNum; i++) {
	        result.add(service.submit(new Calculator(startLatch, stopLatch, i)));
	    }
	    System.err.println("init is ok!");
	}
	private void start() {
		startLatch.countDown();
	}
	private void stop() throws InterruptedException {
		stopLatch.await();
		service.shutdown();
	}
	public void printRes() throws InterruptedException, ExecutionException {
	    int sum = 0;
	    for(Future<Integer> f : result) {
	        sum += f.get();
	    }
	    System.out.println("the result is " + sum);
	}
}

class Calculator implements Callable<Integer> {
	private CountDownLatch startLatch;
	private CountDownLatch stopLatch;
	private final int id;
	public Calculator(CountDownLatch startLatch, CountDownLatch stopLatch, int id) {
		this.startLatch = startLatch;
		this.stopLatch = stopLatch;
		this.id = id;
	}
	@Override
	public Integer call() throws Exception {
		startLatch.await();
		Integer sum = compute();
		stopLatch.countDown();
		stopLatch.await();
		System.err.println(this + " sum=" + sum);
		return sum;
	}
	private Integer compute() {
		int sum = 0;
		int start = id + 1;
		for (int i = (start - 1) * 10 + 1; i <= start * 10; i++)
			sum += i;
		return sum;
	}
	@Override
	public String toString() {
		return "#" + id;
	}
}