package com.coco.cloud.dataStruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/11 17:16
 */
public class 深度遍历二叉树_递归 {

    /** 二叉树节点 */
    public static class TreeNode{

        private Integer data;

        private TreeNode leftChild;

        private TreeNode rightChild;

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public TreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public TreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    '}';
        }
    }

    public static Stack stack = new Stack();

    public static void dfs(TreeNode treeNode){
        TreeNode leftChild = treeNode.leftChild;
        if (leftChild != null){
            dfs(leftChild);
        }
        TreeNode rightChild = treeNode.rightChild;
        if (rightChild != null){
            dfs(rightChild);
        }
        System.out.println(treeNode.data);
    }

    /** 集合转树结构 */
    public static TreeNode convert(List<Integer> list){
        TreeNode root = new TreeNode();
        root.data = list.get(0);
        deep(root,0,list);
        return root;
    }

    public static void deep(TreeNode node,int index,List<Integer> list){
        if (2 * index + 1 < list.size()){
            Integer integer = list.get(2 * index + 1);
            if (integer != null){
                node.leftChild = new TreeNode();
                node.leftChild.data = integer;
                deep(node.leftChild,2 * index + 1,list);
            }
        }
        if (2 * index + 2 < list.size()){
            Integer integer2 = list.get(2 * index + 2);
            if (integer2 != null){
                node.rightChild = new TreeNode();
                node.rightChild.data = integer2;
                deep(node.rightChild,2 * index + 2,list);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(null);
        list.add(6);
        TreeNode tree = convert(list);
        dfs(tree);
    }

}
