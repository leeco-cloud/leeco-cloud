package com.coco.cloud.patterns.combination;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 19:06
 */
public class Test {

    public static void main(String[] args) {
        RootNode rootNode = new RootNode("/");

        Node node1 = new Node("/node1");
        Node node2 = new Node("/node2");

        Node node3 = new Node("/node3");

        Combination combination = new Combination("/combination");
        combination.addNode(node1);
        combination.addNode(node2);

        rootNode.addCombination(combination);
        rootNode.addNode(node3);

        rootNode.showUrl();
        combination.showUrl();
        node1.showUrl();
    }

}
