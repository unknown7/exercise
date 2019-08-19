package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportTest {
    private static ExecutorService exec = Executors.newCachedThreadPool();
    public static void main(String[] args) throws InterruptedException {
//        String s1 = null;
//        String s2 = null;
//        System.err.println(s1 == s2);
//        Thread.interrupted();
//        LockSupport.park();
//        System.err.println("block.");

        final Lock lock = new ReentrantLock();
        lock.lock();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                lock.lock();
            }
        });
        TimeUnit.SECONDS.sleep(3);
        lock.unlock();
    }
}
