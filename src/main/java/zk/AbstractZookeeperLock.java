package zk;

import org.I0Itec.zkclient.ZkClient;

public abstract class AbstractZookeeperLock implements Lock {
    private static final String CONNECT_URL = "127.0.0.1:2181";
    protected ZkClient zkClient = new ZkClient(CONNECT_URL);
    protected static final String PATH = "/lock";

    public void lock() {
        if (tryLock()) {
//            System.err.println("get lock..");
        } else {
            waitLock();
            lock();
        }
    }

    public abstract boolean tryLock();
    public abstract void waitLock();
    public abstract void unLock();
}
