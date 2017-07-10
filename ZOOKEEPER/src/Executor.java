import com.sun.deploy.util.StringUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Executor extends Thread implements Watcher, DataMonitorInterface {
    private DataMonitor dm;
    private ZooKeeper zk;
    private Process child;
    private String exec[];

    public Executor(String hostPort, String znode, String[] exec)
            throws KeeperException, IOException {
        this.exec = exec;

        // pass this object as watcher to ZooKeeper
        zk = new ZooKeeper(hostPort, 3000, this);
        dm = new DataMonitor(zk, znode, this);
    }

    public static void main(String[] args) {
        // get ips from configuration file
        List<String> ips = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                ips.add(line);
            }
        } catch(IOException e){
            System.out.println("Problem while reading configuration file");
        }

        String hostPort = StringUtils.join(ips, ",");
        String znode = "/znode_testowy";

        String exec[] = new String[args.length - 1];
        System.arraycopy(args, 1, exec, 0, exec.length);

        try {
            Executor ex = new Executor(hostPort, znode, exec);
            ex.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                String cmd = br.readLine();
                if(cmd.startsWith("-q")){
                    break;
                }
                if(cmd.startsWith("-ls")){
                    System.out.println("=====================================================================================");
                    System.out.println("Printing znode_testowy's tree:");
                    try {
                        ex.printChildren(ex.zk.getChildren(znode, false), znode);
                    } catch (KeeperException.NoNodeException e){
                        System.out.println("Cannot print znode_testowy's tree - znode_testowy doesn't exists");
                    }
                    System.out.println("=====================================================================================");
                    System.out.println("Tree printed.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printChildren(List<String> children, String path){
        System.out.println("=====================================================================================");
        System.out.println("Children of " + path);
        for(String child : children){
            System.out.println(child);
        }
        for(String child : children) {
            try {
                List<String> currentChildren = this.zk.getChildren(path + '/' + child, false);
                if (!currentChildren.isEmpty())
                    printChildren(currentChildren, path + '/' + child);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        try {
            synchronized (this) {
                while (!dm.dead) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void process(WatchedEvent event) {
        // passing all events to DataMonitor
        dm.process(event);
    }

    public void exists(byte[] data) {
        if(data == null){
            if (child != null){
                System.out.println("=====================================================================================");
                System.out.println("Stopping application...");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Application has been stopped");
            }
            child = null;
        } else {
            if (child != null) {
                System.out.println("=====================================================================================");
                System.out.println("Stopping application...");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Application has been stopped");
            }
            try {
                System.out.println("=====================================================================================");
                System.out.println("Starting application..");
                child = Runtime.getRuntime().exec(exec);
                new StreamWriter(child.getInputStream(), System.out);
                new StreamWriter(child.getErrorStream(), System.err);
                System.out.println("Application has started.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closing(int rc) {
        synchronized (this) {
            notifyAll();
        }
    }

    private static class StreamWriter extends Thread {
        OutputStream os;
        InputStream is;

        StreamWriter(InputStream is, OutputStream os) {
            this.is = is;
            this.os = os;
            start();
        }

        public void run() {
            byte b[] = new byte[80];
            int rc;
            try {
                while ((rc = is.read(b)) > 0) {
                    os.write(b, 0, rc);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}