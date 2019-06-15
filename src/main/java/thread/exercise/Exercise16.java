package thread.exercise;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise16 {
	static class Executor {
		private Lock lock = new ReentrantLock();
		public void f() {
			lock.lock();
			try {
				for (int i = 0; i < 70; i++) {
					System.err.println("f()");
					Thread.yield();
				}
			} finally {
				lock.unlock();
			}
		}
		public void g() {
			lock.lock();
			try {
				for (int i = 0; i < 70; i++) {
					System.err.println("g()");
					Thread.yield();
				}
			} finally {
				lock.unlock();
			}
		}
		public void h() {
			lock.lock();
			try {
				for (int i = 0; i < 70; i++) {
					System.err.println("h()");
					Thread.yield();
				}
			} finally {
				lock.unlock();
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
