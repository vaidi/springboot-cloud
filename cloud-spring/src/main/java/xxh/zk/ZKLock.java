//package xxh.zk;
//
//import org.apache.curator.CuratorZookeeperClient;
//import org.apache.curator.RetryPolicy;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.zookeeper.ZKUtil;
//import org.apache.zookeeper.client.ZKClientConfig;
//import org.apache.zookeeper.common.ZKConfig;
//
//import java.awt.geom.FlatteningPathIterator;
//import java.util.List;
//import java.util.TreeMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * @Author: elyuan
// * @Date: 2020/12/13 6:11 下午
// */
//public class ZKLock implements Lock {
//
//
//    private static final String ZK_PATH="/test/lock";
//    private static final String LOCK_PREFIX=ZK_PATH+"/";
//    private static final long WAIT_TIME =1000;
//
//    CuratorFramework  client = null;
//    CuratorZookeeperClient curatorZookeeperClient = null;
//
//    private String locked_short_path =null;
//    private String locked_path=null;
//    private String prior_path=null;
//    final AtomicInteger lockCount = new AtomicInteger();
//    private Thread thread;
//
//    public ZKLock(){
//        curatorZookeeperClient =  CuratorFrameworkFactory.
//
//
//        String connectString =null;
//        RetryPolicy retryPolicy = null;
//        client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
//        boolean pathContainOk = client.
//
//    }
//
//
//    @Override
//    public boolean lock() {
//        synchronized (this){
//            if(lockCount.get() == 0){
//                thread = Thread.currentThread();
//                lockCount.incrementAndGet();
//            }else {
//                if (!thread.equals(Thread.currentThread())) {
//                    return false;
//                }
//                lockCount.incrementAndGet();
//                return true;
//            }
//        }
//        try {
//            boolean locked = false;
//            locked = tryLock();
//            if(locked){
//                return true;
//            }
//            while (!locked){
//                wait();
//                List<String> waiters = getWaiters();
//                if(checkLocked(waiters)){
//                    locked = true;
//                }
//                return true;
//
//            }
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//            unlock();
//        }
//        return false;
//    }
//
//    private boolean checkLocked(List<String> waiters) {
//        return false;
//    }
//
//    private List<String> getWaiters() {
//        return null;
//    }
//
//    private boolean tryLock() {
//
//
//
//        return false;
//    }
//
//    @Override
//    public boolean unlock() {
//        return false;
//    }
//}
