package com.coco.cloud.dataStruct;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/11 22:24
 */
public class 快慢指针 {

    public static class FastSlowNode{

        private Integer value;

        private FastSlowNode nextNode;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public FastSlowNode getNextNode() {
            return nextNode;
        }

        public void setNextNode(FastSlowNode nextNode) {
            this.nextNode = nextNode;
        }

        @Override
        public String toString() {
            return "FastSlowNode{" +
                    "value=" + value +
                    ", nextNode=" + nextNode +
                    '}';
        }
    }

    public static void isCyclization(FastSlowNode root){
        FastSlowNode node = interStation(root);
        if (node != null){
            FastSlowNode slow = root;
            FastSlowNode fast = node;
            while(slow != fast){
                slow = slow.getNextNode();
                fast = fast.getNextNode();
            }
            System.out.println("成环节点:" + slow.getValue());
        }
    }

    /**
     * 获取交叉点
     */
    public static FastSlowNode interStation(FastSlowNode root){
        FastSlowNode slow = root.getNextNode();
        FastSlowNode fast = root.getNextNode().getNextNode();
        while(fast != null){
            slow = slow.getNextNode();
            if (fast.getNextNode() == null || fast.getNextNode().getNextNode() == null){
                System.out.println("不成环");
                return null;
            }
            fast = fast.getNextNode().getNextNode();
            if (slow == fast){
                System.out.println("成环");
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        FastSlowNode a = new FastSlowNode();
        a.setValue(1);

        FastSlowNode b = new FastSlowNode();
        b.setValue(2);
        a.setNextNode(b);

        FastSlowNode c = new FastSlowNode();
        c.setValue(3);
        b.setNextNode(c);

        FastSlowNode d = new FastSlowNode();
        d.setValue(4);
        c.setNextNode(d);

        FastSlowNode e = new FastSlowNode();
        e.setValue(5);
        d.setNextNode(e);

        FastSlowNode f = new FastSlowNode();
        f.setValue(6);
        e.setNextNode(f);

        FastSlowNode g = new FastSlowNode();
        g.setValue(7);
        f.setNextNode(g);

        FastSlowNode h = new FastSlowNode();
        h.setValue(8);
        g.setNextNode(h);

        h.setNextNode(e);

        isCyclization(a);
    }

}
