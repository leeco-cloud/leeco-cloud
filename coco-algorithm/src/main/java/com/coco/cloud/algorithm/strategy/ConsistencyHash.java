package com.coco.cloud.algorithm.strategy;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 场景 : 有三台服务器  根据数据库的ID 选取对应的服务器IP
 * 一致性hash算法
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/1/11 13:44
 */
public class ConsistencyHash {

    /**
     * 设置一个 2^4-1大小的环 每个正整数都在环上
     * 一致性hash一般采取2^32-1个node的环 这里做了修改
     */
    private final static  int INDEX_CYCLIC = 1 << 4;

    /**
     * 记录所有服务器真实node
     */
    private static List<String> realNodes = new LinkedList<>();

    /**
     * 记录所有服务器虚拟node
     */
    private static SortedMap<Integer,String> sortedMap = new TreeMap<>();

    /**
     * 虚拟节点默认的分片阈值
     */
    private final static int VIRTUAL_NODE_SHARD = 5;

    static {
        realNodes.add("47.98.179.202");
        realNodes.add("47.99.156.222");
        realNodes.add("47.102.120.43");

        for (String ip : realNodes) {
            for(int i = 0 ; i < VIRTUAL_NODE_SHARD ;i++){
                int indexHash = hash(ip + VIRTUAL_NODE_SHARD + i );
                sortedMap.put(hash(ip + VIRTUAL_NODE_SHARD + i ),ip);
                System.out.println(indexHash);
            }
        }
    }

    /**
     * 获取最终选定的值
     * @param key key
     * @return ip
     */
    public static String getRouterService(Object key) {
        int index = hash(key);
        SortedMap<Integer,String> tailMap = sortedMap.tailMap(index);
        if (!tailMap.isEmpty()){
            return sortedMap.get(tailMap.firstKey());
        }else{
            return sortedMap.get(sortedMap.firstKey());
        }
    }

    /**
     * 获取一致性hash值 做了响应的修改
     * @param key key
     * @return hash值
     */
    private static int hash(Object key){
        // 尽可能的利用最大化的hashcode 减少hash碰撞
        int hashCode = key.hashCode();
        hashCode = hashCode ^ (hashCode >>> 16);
        hashCode &= 0x7fffffff;
        // 求下标
        return hashCode & (INDEX_CYCLIC - 1);
    }

}
