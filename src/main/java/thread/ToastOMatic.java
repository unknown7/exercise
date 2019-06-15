package thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ToastOMatic {
	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue(),
					butteredQueue = new ToastQueue(),
					finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}
class Toast {
	public enum Status { DRY, BUTTERED, JAMMED }
	private Status status = Status.DRY;
	private final int id;
	public Toast(int id) { this.id = id; }
	public void butter() { status = Status.BUTTERED; }
	public void jam() { status = Status.JAMMED; }
	public Status getStatus() { return status; }
	public int getId() { return id; }
	@Override
	public String toString() {
		return "Toast " + id + ": " + status;
	}
}
class ToastQueue extends LinkedBlockingDeque<Toast> {
	private static final long serialVersionUID = 1L;
}
class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);
	public Toaster(ToastQueue toastQueue) {
		this.toastQueue = toastQueue;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				Toast t = new Toast(count++);
				System.err.println(t);
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.err.println("Toaster interrupted");
		}
		System.err.println("Toaster off");
	}
}
class Butterer implements Runnable {
	private ToastQueue toastQueue, buttererQueue;
	public Butterer(ToastQueue toastQueue, ToastQueue buttererQueue) {
		this.toastQueue = toastQueue;
		this.buttererQueue = buttererQueue;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast toast = toastQueue.take();
				toast.butter();
				System.err.println(toast);
				buttererQueue.put(toast);
			}
		} catch (InterruptedException e) {
			System.err.println("Butterer interrupted");
		}
		System.err.println("Butterer off");
	}
}
class Jammer implements Runnable {
	private ToastQueue buttererQueue, finishedQueue;
	public Jammer(ToastQueue buttererQueue, ToastQueue finishedQueue) {
		this.buttererQueue = buttererQueue;
		this.finishedQueue = finishedQueue;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast toast = buttererQueue.take();
				toast.jam();
				System.err.println(toast);
				finishedQueue.put(toast);
			}
		} catch (InterruptedException e) {
			System.err.println("Jammer interrupted");
		}
		System.err.println("Jammer off");
	}
}
class Eater implements Runnable {
	private ToastQueue finishedQueue;
	private int count;
	public Eater(ToastQueue finishedQueue) {
		this.finishedQueue = finishedQueue;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast toast = finishedQueue.take();
				if (toast.getId() != count++ || toast.getStatus() != Toast.Status.JAMMED) {
					System.err.println(">>>> Error: " + toast);
					System.exit(1);
				} else
					System.err.println("Chomp! " + toast);
			}
			
		} catch (InterruptedException e) {
			System.err.println("Eater interrupted");
		}
		System.err.println("Eater off");
	}
}
