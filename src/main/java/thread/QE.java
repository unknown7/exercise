package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QE {
	enum PoolType {
		CACHED, FIXED(5), SINGLE;
		PoolType() {}
		PoolType(int nums) {
			this.nums = nums;
		}
		private int nums = 0;
		public int nums() {
			return nums;
		}
	}
	private static int DEFAULT_THREAD_NUMS = 5;
	private static ExecutorService exec = Executors.newCachedThreadPool();
	
	public static void create(PoolType type) {
		switch (type) {
		default:
		case CACHED: exec = Executors.newCachedThreadPool(); break;
		case FIXED: exec = Executors.newFixedThreadPool(PoolType.FIXED.nums()); break;
		case SINGLE: exec = Executors.newSingleThreadExecutor(); break;
		}
	}
	
	public static <T extends Runnable> void exec(Class<T> instance) {
		exec(instance, DEFAULT_THREAD_NUMS);
	}
	public static <T extends Runnable> void exec(Class<T> instance, int nums) {
		for (int i = 0; i < nums; i++)
			try {
				exec.execute(instance.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		exec.shutdown();
	}
}
