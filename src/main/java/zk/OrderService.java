package zk;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrderService implements Runnable {
    private static final int SIZE = 50;
    private static CountDownLatch latch = new CountDownLatch(SIZE);
    private ZookeeperLock lock = new ZookeeperLock();

    public void run() {
        try {
            lock.lock();
            System.err.println(OrderNoGenerator.next());
        } finally {
            lock.unLock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new OrderService());
            latch.countDown();
        }
        latch.await();
        TimeUnit.SECONDS.sleep(7);
        exec.shutdown();
    }
}
