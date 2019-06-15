package thread;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo {
	static int size = 10;
	static int delay = 5;
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Exchanger<List<Fat>> exchanger = new Exchanger<List<Fat>>();
		List<Fat>
			producerList = new CopyOnWriteArrayList<Fat>(),
			consumerList = new CopyOnWriteArrayList<Fat>();
		exec.execute(new ExchangerProducer<Fat>(BasicGenerator.create(Fat.class), exchanger, producerList, size));
		exec.execute(new ExchangerConsumer<Fat>(exchanger, consumerList));
		TimeUnit.SECONDS.sleep(delay);
		exec.shutdownNow();
	}
}
class ExchangerProducer<T> implements Runnable {
	private Generator<T> generator;
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private int size;
	public ExchangerProducer(Generator<T> generator,
			Exchanger<List<T>> exchanger, List<T> holder, int size) {
		this.generator = generator;
		this.exchanger = exchanger;
		this.holder = holder;
		this.size = size;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				for (int i = 0; i < size; i++)
					holder.add(generator.next());
				holder = exchanger.exchange(holder);
			}
		} catch (InterruptedException e) {
		}
	}
}
class ExchangerConsumer<T> implements Runnable {
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private volatile T value;
	public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
		this.exchanger = exchanger;
		this.holder = holder;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				holder = exchanger.exchange(holder);
				for (T x : holder) {
					value = x;
					holder.remove(x);
				}
			}
		} catch (InterruptedException e) {
		}
		System.err.println("Final value: " + value);
	}
}