package thread;

public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		new Thread() {
			public void run() {
				ds.f();
			};
		}.start();
//		new Thread() {
//			public void run() {
//				ds.g();
//			};
//		}.start();
		ds.g();
	}
}

class DualSynch {
	private Object syncObject = new Object();
	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.err.println("f()");
			Thread.yield();
		}
	}
	public void g() {
		synchronized (syncObject) {
			for (int i = 0; i < 5; i++) {
				System.err.println("g()");
				Thread.yield();
			}
		}
	}
}