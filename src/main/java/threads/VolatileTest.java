package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VolatileTest {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();

		Person p = new Person();

		exec.execute(() -> {
			while (true) {
				try {
					TimeUnit.MILLISECONDS.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println(p.name);
			}
		});

		exec.execute(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			p.name = "lisi";
		});
	}

	static class Person {
		String name = "zhangsan";
	}
}
