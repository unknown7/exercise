package zk;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

public class ZookeeperLock extends AbstractZookeeperLock {
    private CountDownLatch latch;
    public boolean tryLock() {
        try {
            zkClient.createEphemeral(PATH);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public void waitLock() {
        IZkDataListener listener = new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {

            }

            public void handleDataDeleted(String s) throws Exception {
                if (latch != null) {
                    latch.countDown();
                }
            }
        };
        zkClient.subscribeDataChanges(PATH, listener);
        if (zkClient.exists(PATH)) {
            latch = new CountDownLatch(1);
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        zkClient.unsubscribeDataChanges(PATH, listener);
    }

    public void unLock() {
        if (zkClient != null) {
            zkClient.delete(PATH);
            System.err.println("release..");
        }
    }
}
