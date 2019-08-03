package thread;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {
        LockSupport.park();
        System.err.println("block.");
    }
}
