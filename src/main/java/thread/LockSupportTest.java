package thread;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {
        String s1 = null;
        String s2 = null;
        System.err.println(s1 == s2);
        LockSupport.park();
        System.err.println("block.");
    }
}
