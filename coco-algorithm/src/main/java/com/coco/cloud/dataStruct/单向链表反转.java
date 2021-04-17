package com.coco.cloud.dataStruct;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/11 15:27
 */
public class 单向链表反转 {

    /** 单向链表节点 */
    public static class Node{

        private String data;

        private Node nextNode;

        public Node(String data, Node nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }

    public static Node convertNode(Node headNode){
        Node pre = null;
        Node currentNode = headNode;
        while(currentNode != null){
            Node nextNode = currentNode.nextNode;
            currentNode.nextNode = pre;
            pre = currentNode;
            currentNode = nextNode;
        }
        return pre;
    }

    public static void iteration(Node pre){

        while( pre!= null){
            System.out.println(pre.data);
            pre = pre.nextNode;
        }

    }

    public static void main(String[] args) {
        Node headNode5 =new Node("5",null);
        Node headNode4 =new Node("4",headNode5);
        Node headNode3 =new Node("3",headNode4);
        Node headNode2 =new Node("2",headNode3);
        Node headNode1 =new Node("1",headNode2);

        Node node = convertNode(headNode1);
        iteration(node);
    }

}
