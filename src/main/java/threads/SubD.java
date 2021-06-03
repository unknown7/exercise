package threads;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class SubD {

	static class Main {
		public int i = 5;
		public synchronized void operationSup() {
			i--;
			System.out.println("Main print i =" + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class Sub extends Main {
		public synchronized void operationSub() {
			while (i > 0) {
				i--;
				System.out.println("Sub print i = " + i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {

		SubD s = new SubD();
		WeakReference<SubD> weakReference = new WeakReference<>(s);
		System.err.println(weakReference.get());
		s = null;
		System.gc();
		System.err.println(weakReference.get());

		new Thread(new Runnable() {
			public void run() {
				Sub sub = new Sub();
				sub.operationSub();
			}
		}).start();
	}

}
