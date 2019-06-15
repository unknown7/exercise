package thread.exercise;

public class Exercise15 {
	static class Executor {
		private Object lock = new Object();
		public void f() {
			synchronized (this) {
				for (int i = 0; i < 70; i++) {
					System.err.println("f()");
					Thread.yield();
				}
			}
		}
		public void g() {
			synchronized (lock) {
				for (int i = 0; i < 70; i++) {
					System.err.println("g()");
					Thread.yield();
				}
			}
		}
		public void h() {
			synchronized (new String()) {
				for (int i = 0; i < 70; i++) {
					System.err.println("h()");
					Thread.yield();
				}
			}
		}
	}

	public static void main(String[] args) {
		final Executor ex = new Executor();
		new Thread() {
			public void run() {
				ex.f();
			};
		}.start();
		new Thread() {
			public void run() {
				ex.g();
			};
		}.start();
		new Thread() {
			public void run() {
				ex.h();
			};
		}.start();
	}
}
