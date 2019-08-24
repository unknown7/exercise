package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Visibility implements Runnable {
    private static int i;
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int j = 0; j < 1000; j++) {
            exec.execute(new Visibility());
        }
        TimeUnit.SECONDS.sleep(2);
        System.err.println("i=" + i);
//        exec.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while (true) {
//                        System.err.println("i=" + i);
//                        TimeUnit.MILLISECONDS.sleep(100);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    @Override
    public void run() {
        try {
            synchronized (Visibility.class) {
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
