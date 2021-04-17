package com.coco.cloud.dataStruct;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/11 14:47
 */
public class 双向链表 {

    /**
     * 头节点
     */
    static DoubleNode head = new DoubleNode();

    /** 尾节点 */
    static DoubleNode tail = new DoubleNode();

    /** 双向循环链表节点 */
    public static class DoubleNode{

        private String data;

        private DoubleNode prevNode;

        private DoubleNode nextNode;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public DoubleNode getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(DoubleNode prevNode) {
            this.prevNode = prevNode;
        }

        public DoubleNode getNextNode() {
            return nextNode;
        }

        public void setNextNode(DoubleNode nextNode) {
            this.nextNode = nextNode;
        }
    }

    static {
        head.nextNode = tail;
        tail.prevNode = head;
    }

    public void addNode(DoubleNode doubleNode){
        tail.prevNode.nextNode = doubleNode;
        doubleNode.prevNode = tail.prevNode;
        doubleNode.nextNode = tail;
        tail.prevNode = doubleNode;
    }

    public void iteration(){
        DoubleNode node = head.nextNode;
        while(node != null && node != tail){
            System.out.println(node.data);
            node = node.nextNode;
        }
    }

    public static void main(String[] args) {
        双向链表 l = new 双向链表();
        DoubleNode doubleNode = new DoubleNode();
        doubleNode.setData("1");
        l.addNode(doubleNode);
        DoubleNode doubleNode2 = new DoubleNode();
        doubleNode2.setData("2");
        l.addNode(doubleNode2);
        l.iteration();
    }

}
