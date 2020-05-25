package com.coco.cloud.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计和实现一个  LRU (最近最少使用) 缓存机制
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/25 20:32
 */
public class LRUCache {

    /**
     * 采用hash表 + 双向链表 = 哈希链表
     * @param capacity 容量
     */
    public LRUCache(int capacity) {
        nodeMap = new HashMap<>(capacity);
        this.capacity = capacity;

        head = new Node();
        head.pre = null;

        tail = new Node();
        tail.next = null;

        head.next = tail;
        tail.pre = head;
    }

    /**
     * 存储node的map
     */
    private Map<Integer,Node> nodeMap;

    /**
     * 最大容量
     */
    private int capacity;

    /**
     * 节点
     */
    private static class Node{
        int key;
        int value;
        Node pre;
        Node next;
    }

    /**
     * 头节点
     */
    private Node head;

    /**
     * 尾节点
     */
    private Node tail;

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node != null){
            // 获取之后 移动到链表尾部
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;

            addToHead(node);

            return node.value;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        Node node = nodeMap.get(key);
        if (node != null){
            node.value = value;
            node.pre.next = node.next;
            node.next.pre = node.pre;
            // 添加到头节点
            addToHead(node);
        }else{
            if (nodeMap.values().size() >= capacity){
                // 移除尾节点
                removeLastNode();
            }
            node = new Node();
            node.value = value;
            node.key = key;
            addToHead(node);
            nodeMap.put(key,node);
        }
    }

    /**
     * 移除尾节点
     */
    private void removeLastNode(){
        nodeMap.remove(tail.pre.key);
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
    }

    /**
     * 添加到头节点
     */
    private void addToHead(Node node){
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    @Override
    public String toString() {
        return nodeMap.keySet().toString();
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

}
