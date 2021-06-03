package threads;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class ReferenceQueueTest {

	public static void main(String[] args) throws InterruptedException {

		ReferenceQueue<Object> queue = new ReferenceQueue<>();

		SubD s = new SubD();

		WeakReference<SubD> refer = new WeakReference<>(s, queue);

		new Thread(() -> {
			Reference<?> poll;
			while ((poll = queue.poll()) == null) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println(refer.get());
			}
			System.err.println("=====" + poll);
		}).start();

		TimeUnit.SECONDS.sleep(1);

		s = null;
		System.gc();
	}
}
