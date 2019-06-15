package thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PipedIO {
	public static void main(String[] args) throws InterruptedException {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		
		TimeUnit.SECONDS.sleep(1);
		exec.shutdownNow();
	}
}
class Sender implements Runnable {
	private Random rand = new Random(47);
	private PipedWriter out = new PipedWriter();
	public PipedWriter getOut() {
		return out;
	}
	@Override
	public void run() {
		try {
			while (true) {
				for (char i = 'A'; i < 'c'; i++) {
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
					out.write(i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class Receiver implements Runnable {
	private Random rand = new Random(47);
	private PipedReader in;
	public Receiver(Sender sender) {
		try {
			in = new PipedReader(sender.getOut());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				System.err.println((char) in.read());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}