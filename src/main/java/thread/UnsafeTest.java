package thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class UnsafeTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        Thread thread = Thread.currentThread();
//        unsafe.unpark(thread);
//        unsafe.unpark(thread);
//        unsafe.unpark(thread);

        final Thread cur = Thread.currentThread();
        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    cur.interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        unsafe.park(false, 0);



        System.err.println("hello");
    }
}
