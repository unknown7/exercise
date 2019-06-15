package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityBlockingQueueDemo {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
		exec.execute(new PrioritizedTaskProducer(queue, exec));
		exec.execute(new PrioritizedTaskConsumer(queue));
	}
}
class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
	private Random rand = new Random(47);
	private static int counter;
	private final int id = counter++;
	private final int priority;
	protected static List<PrioritizedTask> sequence = new ArrayList<PrioritizedTask>();
	public PrioritizedTask(int priority) {
		this.priority = priority;
		sequence.add(this);
	}
	@Override
	public int compareTo(PrioritizedTask o) {
		return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
	}
	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println(this);
	}
	@Override
	public String toString() {
		return String.format("[%1$-3d]", priority) + " Task " + id;
	}
	public String summary() {
		return "(" + id + ":" + priority + ")";
	}
	public static class EndSentinel extends PrioritizedTask {
		private ExecutorService exec;
		public EndSentinel(ExecutorService exec) {
			super(-1);
			this.exec = exec;
		}
		@Override
		public void run() {
			int count = 0;
			for (PrioritizedTask task : sequence) {
				System.err.print(task.summary());
				if (++count % 5 == 0)
					System.err.println();
			}
			System.err.println();
			System.err.println(this + " Calling shutdownNow()");
			exec.shutdownNow();
		}
	}
}
class PrioritizedTaskProducer implements Runnable {
	private Random rand = new Random(47);
	private Queue<Runnable> queue;
	private ExecutorService exec;
	public PrioritizedTaskProducer(Queue<Runnable> queue, ExecutorService exec) {
		this.queue = queue;
		this.exec = exec;
	}
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			queue.add(new PrioritizedTask(rand.nextInt(10)));
			Thread.yield();
		}
		try {
			for (int i = 0; i < 10; i++) {
				TimeUnit.MILLISECONDS.sleep(250);
				queue.add(new PrioritizedTask(rand.nextInt(10)));
			}
			for (int i = 0; i < 10; i++)
				queue.add(new PrioritizedTask(i));
			queue.add(new PrioritizedTask.EndSentinel(exec));
		} catch (Exception e) {
		}
		System.err.println("Finished PrioritizedTaskProducer");
	}
}
class PrioritizedTaskConsumer implements Runnable {
	private PriorityBlockingQueue<Runnable> q;
	public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
		this.q = q;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted())
				q.take().run();
		} catch (Exception e) {
		}
		System.err.println("Finished PrioritizedTaskConsumer");
	}
}