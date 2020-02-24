package com.coco.cloud.design.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU(最近最久未使用算法) HashMap方式
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/1/11 17:09
 */
public class LeastRecentlyUsedHashMap {

    static class Node {
        int key;
        int val;
        Node prev;
        Node next;
    }

    private int capacity;

    /**
     * 保存链表的头节点和尾节点
     */
    private Node first;
    private Node last;

    private Map<Integer, Node> map;

    private LeastRecentlyUsedHashMap(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
    }

    private int get(int key) {
        Node node = map.get(key);
        //为空返回-1
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.val;
    }

    private void moveToHead(Node node) {
        if (node == first) {
            return;
        } else if (node == last) {
            last.prev.next = null;
            last = last.prev;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        node.prev = first.prev;
        node.next = first;
        first.prev = node;
        first = node;
    }

    private void put(int key, int value) {
        Node node = map.get(key);

        if (node == null) {
            node = new Node();
            node.key = key;
            node.val = value;

            if(map.size() == capacity) {
                removeLast();
            }

            addToHead(node);
            map.put(key, node);
        } else {
            node.val = value;
            moveToHead(node);
        }
    }

    private void addToHead(Node node) {
        if (map.isEmpty()) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
    }

    private void removeLast() {
        map.remove(last.key);
        Node prevNode = last.prev;
        if (prevNode != null) {
            prevNode.next = null;
            last = prevNode;
        }
    }

    @Override
    public String toString() {
        return map.keySet().toString();
    }

    public static void main(String[] args) {
        LeastRecentlyUsedHashMap cache = new LeastRecentlyUsedHashMap(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(1);
        cache.put(4, 3);
        System.out.println(cache);
    }

}