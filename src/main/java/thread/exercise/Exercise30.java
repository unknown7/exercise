package thread.exercise;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Exercise30 {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Character> queue = new LinkedBlockingQueue<Character>();
		Sender sender = new Sender(queue);
		Receiver receiver = new Receiver(queue);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		
		TimeUnit.SECONDS.sleep(1);
		exec.shutdownNow();
	}
}
class Sender implements Runnable {
	private Random rand = new Random(47);
	private BlockingQueue<Character> queue;
	public BlockingQueue<Character> getQueue() {
		return queue;
	}
	public Sender(BlockingQueue<Character> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		try {
			while (true) {
				for (char i = 'A'; i < 'c'; i++) {
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
					queue.put(i);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class Receiver implements Runnable {
	private Random rand = new Random(47);
	private BlockingQueue<Character> queue;
	public BlockingQueue<Character> getQueue() {
		return queue;
	}
	public Receiver(BlockingQueue<Character> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				System.err.println(queue.take());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}