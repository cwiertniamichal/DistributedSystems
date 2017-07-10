import org.apache.zookeeper.*;
import org.apache.zookeeper.KeeperException.*;
import org.apache.zookeeper.data.Stat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataMonitor implements Watcher, AsyncCallback.StatCallback, AsyncCallback.ChildrenCallback {
    private ZooKeeper zk;
    private String znode;
    boolean dead;
    private DataMonitorInterface listener;
    private boolean exists;
    private Map<String, Integer> children = new HashMap<>();
    private int childrenSum;

    public DataMonitor(ZooKeeper zk, String znode, DataMonitorInterface listener){
        this.zk = zk;
        this.znode = znode;
        this.listener = listener;

        System.out.println("=====================================================================================");
        System.out.println("Fetching initial information about znode_testowy...");
        try {
            if(zk.exists(znode, false) == null) {
                this.exists = false;
                this.children.put(znode, 0);
                childrenSum = 0;
                System.out.println("znode_testowy doesn't exists");
            }
            else {
                this.exists = true;
                initChildren(znode);
                System.out.println("znode_testowy exists and has " + this.childrenSum + " children");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Initial data fetched");

        zk.exists(znode, true, this, null);
        zk.getChildren(znode, true, this, null);
    }

    private void initChildren(String path){
        try {
            List<String> childrenList = zk.getChildren(path, false);
            this.children.put(path, childrenList.size());
            this.childrenSum += childrenList.size();
            zk.getChildren(path, true, this, null);
            for (String child : childrenList) {
                try {
                    initChildren(path + "/" + child);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (KeeperException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // Processing exists result
    public void processResult(int rc, String path, Object ctx, Stat stat){
        boolean exists;
        if(rc == Code.OK.intValue()) {
            exists = true;
        }
        else if(rc == Code.NONODE.intValue()) {
            exists = false;
            this.children = new HashMap<>();
            this.childrenSum = 0;
            this.children.put(znode, 0);
        }
        else if(rc == Code.NOAUTH.intValue()){
            dead = true;
            listener.closing(rc);
            return;
        }
        else{
            zk.exists(znode, true, this, null);
            return;
        }

        byte b[] = null;
        if(exists){
            try{
                b = zk.getData(znode, false, null);
            }
            catch (KeeperException e){
                e.printStackTrace();
            } catch (InterruptedException e){
                return;
            }
        }

        if(this.exists != exists) {
            listener.exists(b);
            this.exists = exists;
        }

    }

    public void processResult(int rc, String path, Object ctx, List<String> childrenList) {
        if (rc == Code.OK.intValue()) {
            int children = childrenList.size();
            if (this.children.get(path) != null) {
                if (children > this.children.get(path)) {
                    childrenSum += 1;
                    System.out.println("=====================================================================================");
                    System.out.println("Added child to " + path + ". Now it has " + children + " children");
                    System.out.println("znode_testowy has got " + this.childrenSum + " children.");
                    for (String child : childrenList) {
                        if (!this.children.containsKey(path + "/" + child)) {
                            this.children.put(path + "/" + child, 0);
                            zk.getChildren(path + "/" + child, true, this, null);
                        }
                    }
                }
                if (children < this.children.get(path)) {
                    childrenSum -= 1;
                    System.out.println("=====================================================================================");
                    System.out.println("Deleted child from " + path + ". Now it has " + children + " children");
                    System.out.println("znode_testowy has got " + this.childrenSum + " children.");
                }
                this.children.put(path, children);
            } else {
                zk.getChildren(path, true, this, null);
                // return;
            }
        }
    }

    public void process(WatchedEvent event){
        String path = event.getPath();
        if(event.getType() == Watcher.Event.EventType.None) {
            switch(event.getState()){
                case SyncConnected:
                    break;
                case Expired:
                    dead = true;
                    listener.closing(Code.SESSIONEXPIRED.intValue());
                    break;
            }
        }
        else{
            if(path != null && this.children.containsKey(path)){
                zk.exists(znode, true, this, null);
                zk.getChildren(path, true, this, null);
            }
        }
    }
}
