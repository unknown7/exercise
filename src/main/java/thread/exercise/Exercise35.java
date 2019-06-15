package thread.exercise;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise35 {
	static final int MAX_LINE_SIZE = 50;
	static final int NUM_OF_SERVERS = 3;
	static final int ADJUSTMENT_PERIOD = 1000;
	public static void main(String[] args) throws IOException {
		ExecutorService exec = Executors.newCachedThreadPool();
		WebClientLine clients =
		new WebClientLine(MAX_LINE_SIZE);
		WebClientGenerator g = new WebClientGenerator(clients);
		exec.execute(g);
		exec.execute(new SimulationManager(
		exec, g, clients, ADJUSTMENT_PERIOD, NUM_OF_SERVERS));
		System.in.read();
		exec.shutdownNow();
	}
}
class WebClient {
	private final int serviceTime;
	public WebClient(int serviceTime) { this.serviceTime = serviceTime; }
	public int getServiceTime() {
		return serviceTime;
	}
	@Override
	public String toString() {
		return "[" + serviceTime + "]";
	}
}
class WebClientLine extends ArrayBlockingQueue<WebClient> {
	private static final long serialVersionUID = 1L;
	public WebClientLine(int capacity) {
		super(capacity);
	}
	@Override
	public String toString() {
		if (size() == 0)
			return "[Empty]";
		StringBuilder result = new StringBuilder();
		for (WebClient client : this)
			result.append(client);
		return result.toString();
	}
}
class WebClientGenerator implements Runnable {
	private WebClientLine clients;
	private static Random rand = new Random(47);
	volatile int loadFactor = 1;
	public WebClientGenerator(WebClientLine clients) {
		this.clients = clients;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(1000 / loadFactor);
				clients.put(new WebClient(rand.nextInt(2000)));
			}
		} catch (Exception e) {
		}
		System.err.println("WebClientGenerator terminating");
	}
}
class Server implements Runnable {
	private static int counter;
	private final int id = counter++;
	private WebClientLine clients;
	public Server(WebClientLine clients) {
		this.clients = clients;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				WebClient client = clients.take();
				TimeUnit.MILLISECONDS.sleep(client.getServiceTime());
			}
		} catch (Exception e) {
		}
		System.err.println(this + " terminating");
	}
	@Override
	public String toString() {
		return "Server " + id;
	}
	public String shortString() {
		return "S" + id;
	}
}
class SimulationManager implements Runnable {
	private ExecutorService exec;
	private WebClientGenerator gen;
	private WebClientLine clients;
	private Queue<Server> servers = new LinkedList<Server>();
	private int adjustmentPeriod;
	private boolean stable = true;
	private int prevSize;
	public SimulationManager(ExecutorService exec, WebClientGenerator gen, WebClientLine clients, int adjustmentPeiod, int n) {
		this.exec = exec;
		this.gen = gen;
		this.clients = clients;
		this.adjustmentPeriod = adjustmentPeiod;
		for (int i = 0; i < n; i++) {
			Server server = new Server(clients);
			exec.execute(server);
			servers.add(server);
		}
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				System.err.print(clients + "{");
				for (Server server : servers)
					System.err.print(server.shortString() + " ");
				System.err.println("}");
				adjustLoadFactor();
			}
		} catch (Exception e) {
		}
		System.err.println(this + "terminating");
	}
	public void adjustLoadFactor() {
		if (clients.size() > prevSize) {
			if (stable)
				stable = false;
			else if (!stable) {
				System.err.println("Peak load factor: ~" + gen.loadFactor);
				exec.shutdownNow();
			}
		} else {
			System.err.println("New load factor: " + ++gen.loadFactor);
			stable = true;
		}
		prevSize = clients.size();
	}
	@Override
	public String toString() {
		return "SimulationManager ";
	}
}