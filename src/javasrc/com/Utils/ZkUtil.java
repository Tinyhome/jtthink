package com.Utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tinyhome on 2016/10/24.
 */
public class ZkUtil {


    @Autowired
    BuyNowData buyNowData;
    CuratorFramework zkClient;

    public ZkUtil(CuratorFramework curatorFramework) throws Exception {
        zkClient=curatorFramework;

    }

    //为演示新增的方法，开始监听节点
    public boolean watchBook() throws Exception {
        String bookPath="/buynow/20161024/book";
        if(existsNode(bookPath)) //监听节点时，节点必须要存在
        {
            zkClient.getData().usingWatcher(curatorWatcher)
                    .forPath(bookPath);
            return true;
        }
        return false;

    }

    //监听节点，
    public CuratorWatcher curatorWatcher=new CuratorWatcher() {
        public void process(WatchedEvent watchedEvent) throws Exception {
            if(watchedEvent.getType()== Watcher.Event.EventType.NodeDataChanged)
            {
                //这里重新获取值 更新数据
                buyNowData.initProdlist();
                zkClient.getData().usingWatcher(curatorWatcher)
                        .forPath(watchedEvent.getPath());
            }
                //节点删除后也要监听
            else if(watchedEvent.getType()==Watcher.Event.EventType.NodeDeleted)
            {
                //这里重新获取值 更新数据
                buyNowData.initProdlist();
                zkClient.getData().usingWatcher(curatorWatcher)
                        .forPath(watchedEvent.getPath());
            }
        }
    };

    public void SetPathData(String path,String data) throws Exception {
        if(!this.existsNode(path)){
            //递归创建节点
            zkClient.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT).forPath(path,data.getBytes());
            return;
        }
        zkClient.setData().forPath(path,data.getBytes());
    }

    public String getPathData(String path) throws Exception {
        if(!this.existsNode(path)) return "";
        return new String(zkClient.getData().forPath(path));

    }

    public boolean existsNode(String path) throws Exception {
        if(zkClient.checkExists().forPath(path)==null)
            return false;
        return  true;
    }


    /**

    CuratorFramework zkClient;

    public ZkUtil(CuratorFramework curatorFramework) throws Exception {
        this.zkClient = curatorFramework;
        zkClient.getData().usingWatcher(curatorWatcher).forPath("/name");
    }

    public CuratorWatcher curatorWatcher=new CuratorWatcher() {
        public void process(WatchedEvent watchedEvent) throws Exception {
            if(watchedEvent.getType()== Watcher.Event.EventType.NodeDataChanged)
            {
                System.out.println("node changed");
                System.out.println(getPathData(watchedEvent.getPath()));
                zkClient.getData().usingWatcher(curatorWatcher)
                        .forPath(watchedEvent.getPath());
            }
        }
    };

    public void SetPathData(String path,String data) throws Exception {
        if(!this.existsNode(path)){
            //递归创建节点
            zkClient.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT).forPath(path,data.getBytes());
            return;
        }
        zkClient.setData().forPath(path,data.getBytes());
    }

    public String getPathData(String path) throws Exception {
        if(!this.existsNode(path)) return "";
        return new String(zkClient.getData().forPath(path));

    }

    public boolean existsNode(String path) throws Exception {
        if(zkClient.checkExists().forPath(path)==null)
            return false;
        return  true;
    }
     */



}
